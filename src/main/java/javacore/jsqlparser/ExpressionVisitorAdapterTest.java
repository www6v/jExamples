package javacore.jsqlparser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.OracleHint;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.StatementVisitorAdapter;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.SelectUtils;
import org.junit.*;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ExpressionVisitorAdapterTest {

    @Test
    public void testInExpressionProblem() throws JSQLParserException {
        final List exprList = new ArrayList();
        Select select = (Select) CCJSqlParserUtil.parse("select * from foo where x in (?,?,?)");
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        Expression where = plainSelect.getWhere();
        where.accept(new ExpressionVisitorAdapter() {

            @Override
            public void visit(InExpression expr) {
                super.visit(expr);
                exprList.add(expr.getLeftExpression());
                exprList.add(expr.getLeftItemsList());
                exprList.add(expr.getRightItemsList());
            }
        });

        assertTrue(exprList.get(0) instanceof Expression);
        assertNull(exprList.get(1));
        assertTrue(exprList.get(2) instanceof ItemsList);
    }

    @Test
    public void testInExpression() throws JSQLParserException {
        final List exprList = new ArrayList();
        Select select = (Select) CCJSqlParserUtil.
                parse("select * from foo where (a,b) in (select a,b from foo2)");
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        Expression where = plainSelect.getWhere();
        where.accept(new ExpressionVisitorAdapter() {

            @Override
            public void visit(InExpression expr) {
                super.visit(expr);
                exprList.add(expr.getLeftExpression());
                exprList.add(expr.getLeftItemsList());
                exprList.add(expr.getRightItemsList());
            }
        });

        assertNull(exprList.get(0));
        assertTrue(exprList.get(1) instanceof ItemsList);
        assertTrue(exprList.get(2) instanceof ItemsList);
    }

    @Test
    public void testOracleHintExpressions() throws JSQLParserException {
        testOracleHintExpression("select --+ MYHINT \n * from foo", "MYHINT", true);
        testOracleHintExpression("select /*+ MYHINT */ * from foo", "MYHINT", false);
    }

    public static void testOracleHintExpression(String sql, String hint, boolean singleLine) throws JSQLParserException {
        Select select = (Select) CCJSqlParserUtil.parse(sql);
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        final OracleHint[] holder = new OracleHint[1];
        assertNotNull(plainSelect.getOracleHint());
        plainSelect.getOracleHint().accept(new ExpressionVisitorAdapter() {

            @Override
            public void visit(OracleHint hint) {
                super.visit(hint);
                holder[0] = hint;
            }
        });

        assertNotNull(holder[0]);
        assertEquals(singleLine, holder[0].isSingleLine());
        assertEquals(hint, holder[0].getValue());
    }

    @Test
    public void testCurrentTimestampExpression() throws JSQLParserException {
        final List<String> columnList = new ArrayList<String>();
        Select select = (Select) CCJSqlParserUtil.
                parse("select * from foo where bar < CURRENT_TIMESTAMP");
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        Expression where = plainSelect.getWhere();
        where.accept(new ExpressionVisitorAdapter() {

            @Override
            public void visit(Column column) {
                super.visit(column);
                columnList.add(column.getColumnName());
            }
        });

        assertEquals(1, columnList.size());
        assertEquals("bar", columnList.get(0));
    }

    @Test
    public void testCurrentDateExpression() throws JSQLParserException {
        final List<String> columnList = new ArrayList<String>();
        Select select = (Select) CCJSqlParserUtil.
                parse("select * from foo where bar < CURRENT_DATE");
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        Expression where = plainSelect.getWhere();
        where.accept(new ExpressionVisitorAdapter() {

            @Override
            public void visit(Column column) {
                super.visit(column);
                columnList.add(column.getColumnName());
            }
        });

        assertEquals(1, columnList.size());
        assertEquals("bar", columnList.get(0));
    }

    @Test
    public void testSubSelectExpressionProblem() throws JSQLParserException {
        Select select = (Select) CCJSqlParserUtil.
                parse("SELECT * FROM t1 WHERE EXISTS (SELECT * FROM t2 WHERE t2.col2 = t1.col1)");
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        Expression where = plainSelect.getWhere();
        ExpressionVisitorAdapter adapter = new ExpressionVisitorAdapter();
        adapter.setSelectVisitor(new SelectVisitorAdapter());
        try {
            where.accept(adapter);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void testCaseWithoutElse() throws JSQLParserException {
        Expression expr = CCJSqlParserUtil.parseExpression("CASE WHEN 1 then 0 END");
        ExpressionVisitorAdapter adapter = new ExpressionVisitorAdapter();
        expr.accept(adapter);
    }

    @Test
    public void testCaseWithoutElse2() throws JSQLParserException {
        Expression expr = CCJSqlParserUtil.parseExpression("CASE WHEN 1 then 0 ELSE -1 END");
        ExpressionVisitorAdapter adapter = new ExpressionVisitorAdapter();
        expr.accept(adapter);
    }

    @Test
    public void testCaseWithoutElse3() throws JSQLParserException {
        Expression expr = CCJSqlParserUtil.parseExpression("CASE 3+4 WHEN 1 then 0 END");
        ExpressionVisitorAdapter adapter = new ExpressionVisitorAdapter();
        expr.accept(adapter);
    }

    @Test
    public void testAnalyticFunctionWithoutExpression502() throws JSQLParserException {
        Expression expr = CCJSqlParserUtil.parseExpression("row_number() over (order by c)");
        ExpressionVisitorAdapter adapter = new ExpressionVisitorAdapter();
        expr.accept(adapter);
    }

    @Test
    public void testWithExpression() throws JSQLParserException {
        String sql = "WITH dept_count AS (\n" +
                "  SELECT deptno, COUNT(*) AS dept_count\n" +
                "  FROM   emp\n" +
                "  GROUP BY deptno)\n" +
                "SELECT e.ename AS employee_name,\n" +
                "       dc1.dept_count AS emp_dept_count,\n" +
                "       m.ename AS manager_name,\n" +
                "       dc2.dept_count AS mgr_dept_count\n" +
                "FROM   emp e,\n" +
                "       dept_count dc1,\n" +
                "       emp m,\n" +
                "       dept_count dc2\n" +
                "WHERE  e.deptno = dc1.deptno\n" +
                "AND    e.mgr = m.empno\n" +
                "AND    m.deptno = dc2.deptno";
        Select select = (Select) CCJSqlParserUtil.parse(sql);
        List<WithItem> withItemsList = select.getWithItemsList();
        assertEquals("dept_count as (select dedeptno, count(*) as dept_count from emp group by deptno)", withItemsList.get(0));
    }

    @Test
    public void testWithItemsListExpression() throws JSQLParserException {
        List<SelectItem> selectItems = new ArrayList<>();
        String sql = "WITH \n" +
                "  dept_costs AS (\n" +
                "    SELECT dname, SUM(sal) dept_total\n" +
                "    FROM   emp e, dept d\n" +
                "    WHERE  e.deptno = d.deptno\n" +
                "    GROUP BY dname),\n" +
                "  avg_cost AS (\n" +
                "    SELECT SUM(dept_total)/COUNT(*) avg\n" +
                "    FROM   dept_costs)\n" +
                "SELECT *\n" +
                "FROM   dept_costs\n" +
                "WHERE  dept_total > (SELECT avg FROM avg_cost)\n" +
                "ORDER BY dname";
        Select select = (Select) CCJSqlParserUtil.parse(sql);
        List<WithItem> withItemsList = select.getWithItemsList();
        SelectBody selectBody = select.getSelectBody();
        PlainSelect plainSelect = (PlainSelect) selectBody;
        Expression where = plainSelect.getWhere();
        where.accept(new ExpressionVisitorAdapter() {
            @Override
            public void visit(SubSelect subSelect) {
                super.visit(subSelect);
                try {
                    Select select1 = SelectUtils.
                            buildSelectFromTableAndExpressions(new Table("avg_cost"), "bpm", "avg");
                    subSelect.setSelectBody(select1.getSelectBody());
                } catch (JSQLParserException e) {
                    e.printStackTrace();
                }
            }
        });
        select.setSelectBody(selectBody);
        System.err.println(select.toString());
        System.err.println("===");
    }

    @Test
    public void testParserManager() throws JSQLParserException {
        String sql = "WITH \n" +
                "  dept_costs AS (\n" +
                "    SELECT dname, SUM(sal) dept_total\n" +
                "    FROM   emp e, dept d\n" +
                "    WHERE  e.deptno = d.deptno\n" +
                "    GROUP BY dname),\n" +
                "  avg_cost AS (\n" +
                "    SELECT SUM(dept_total)/COUNT(*) avg\n" +
                "    FROM   dept_costs)\n" +
                "SELECT *\n" +
                "FROM   dept_costs\n" +
                "WHERE  dept_total > (SELECT avg FROM avg_cost)\n" +
                "ORDER BY dname";
        CCJSqlParserManager ccjSqlParserManager = new CCJSqlParserManager();
        Statement statement = ccjSqlParserManager.parse(new StringReader(sql));
        Select select = (Select) statement;
        List<WithItem> withItemsList = select.getWithItemsList();
        withItemsList.forEach(i -> {
            System.err.println(i.getName());
            System.err.println(i.getSelectBody());
        });
        select.accept(new StatementVisitorAdapter() {
            @Override
            public void visit(Select select) {
                super.visit(select);
                select.setSelectBody(SelectUtils.buildSelectFromTable(new Table("ball")).getSelectBody());
                try {
                    select.setWithItemsList(null);
                    select.setSelectBody(((Select) CCJSqlParserUtil.parse("select a, b, c from titan")).getSelectBody());
                } catch (JSQLParserException e) {
                    e.printStackTrace();
                }
            }

        });
        System.err.println("===============");
        System.err.println(select);
    }
}
