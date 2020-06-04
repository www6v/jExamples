package javacore.jsqlparser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.SelectUtils;
import net.sf.jsqlparser.util.TablesNamesFinder;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;
import net.sf.jsqlparser.util.deparser.SelectDeParser;
import org.junit.Test;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class JSqlParserTest {

    /**
     * find all tables
     *
     * @throws JSQLParserException
     */
    @Test
    public void testTablesNamesFinder() throws JSQLParserException {
        String sql = "select vend_id, vend_name, vend_address, vend_city, vend_state, vend_zip, vend_country from vendors";

        CCJSqlParserManager parserManager = new CCJSqlParserManager();

        Statement statement = parserManager.parse(new StringReader(sql));
        Select selectStatement = (Select) statement;

        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(selectStatement);
        tableList.forEach(System.err::println);

    }

    /**
     * find all columns name and alias
     *
     * @throws JSQLParserException
     */
    @Test
    public void testRetrieveAllSelectedItems() throws JSQLParserException {
        Select stmt = (Select) CCJSqlParserUtil.parse("SELECT col1 AS a, col2 AS b, col3 AS c FROM table WHERE col1 = 10 AND col2 = 20 AND col3 = 30");

        Map<String, Expression> map = new HashMap<>();
        for (SelectItem selectItem : ((PlainSelect) stmt.getSelectBody()).getSelectItems()) {
            selectItem.accept(new SelectItemVisitorAdapter() {
                @Override
                public void visit(SelectExpressionItem item) {
                    map.put(item.getAlias().getName(), item.getExpression());
                }
            });
        }
        System.err.println("map " + map);
    }

    /**
     * create simple select statment
     *
     * @throws JSQLParserException
     */
    @Test
    public void testBuildSimpleSelect() throws JSQLParserException {
        Select select = SelectUtils.buildSelectFromTable(new Table("mytable"));
        Select select2 = SelectUtils.buildSelectFromTableAndExpressions(new Table("mytable"), new Column("a"), new Column("b"));
        Select select3 = SelectUtils.buildSelectFromTableAndExpressions(new Table("mytable"), "a+b", "test");

        PlainSelect plainSelect = (PlainSelect) select3.getSelectBody();
        plainSelect.getSelectItems().forEach(System.err::println);
    }

    /**
     * replace String value
     *
     * @throws JSQLParserException
     */
    @Test
    public void replaceStringValues() throws JSQLParserException {
        String sql = "SELECT NAME, ADDRESS, COL1 FROM USER WHERE SSN IN ('11111111111111', '22222222222222');";
        Select select = (Select) CCJSqlParserUtil.parse(sql);

        //Start of value modification
        StringBuilder buffer = new StringBuilder();
        ExpressionDeParser expressionDeParser = new ExpressionDeParser() {

            @Override
            public void visit(StringValue stringValue) {
                this.getBuffer().append("XXXX");
            }

        };
        SelectDeParser deparser = new SelectDeParser(expressionDeParser, buffer);
        expressionDeParser.setSelectVisitor(deparser);
        expressionDeParser.setBuffer(buffer);
        select.getSelectBody().accept(deparser);
        //End of value modification


        System.err.println(buffer.toString());
        //Result is: SELECT NAME, ADDRESS, COL1 FROM USER WHERE SSN IN (XXXX, XXXX)
    }

    /**
     * replace where clause column name
     *
     * @throws JSQLParserException
     */
    @Test
    public void replaceColumnNamesWithinWhere() throws JSQLParserException {
        Select stmt = (Select) CCJSqlParserUtil.parse("SELECT col1 AS a, col2 AS b, col3 AS c FROM table WHERE col_1 = 10 AND col_2 = 20 AND col_3 = 30");
        System.err.println("before " + stmt.toString());

        ((PlainSelect) stmt.getSelectBody()).getWhere().accept(new ExpressionVisitorAdapter() {
            @Override
            public void visit(Column column) {
                column.setColumnName(column.getColumnName().replace("_", ""));
            }
        });

        System.err.println("after " + stmt.toString());
    }

    @Test
    public void selectUtilsTest() throws JSQLParserException {
        /**
         *  find table, all columns
         */
        Select select = SelectUtils.buildSelectFromTable(new Table("customer"));
        assertEquals("select * from customer", select.toString().toLowerCase());


        /**
         *  find table, specific columns
         */
        select = SelectUtils.buildSelectFromTableAndExpressions(new Table("customer"), "a", "b");
        assertEquals("select a, b from customer", select.toString().toLowerCase());

    }
}
