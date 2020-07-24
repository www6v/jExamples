package javacore.interceptor.sqlinterceptor;

import javacore.interceptor.sqlinterceptor.visitor.SelectVisitorImpl;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.StringReader;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

/// 在原始sql上做增强
@Intercepts({@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class})})
public class SqlInterceptor implements Interceptor {

    public static final ThreadLocal<List<String>> LOCAL_SQL_LIST_TABLENAME = new ThreadLocal<List<String>>();
    public static final ThreadLocal<List<SysTableExt>> LOCAL_USER_LIST_TABLENAME = new ThreadLocal<List<SysTableExt>>();

    @Autowired
    private SysTableExtService sysTableExtService;

    CCJSqlParserManager parserManager = new CCJSqlParserManager();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler handler = (StatementHandler)invocation.getTarget();
        MetaObject statementHandler = SystemMetaObject.forObject(handler);
        MappedStatement mappedStatement = (MappedStatement) statementHandler.getValue("delegate.mappedStatement");

        BoundSql boundSql = handler.getBoundSql();
        String sql = boundSql.getSql();

        String id = mappedStatement.getId();

        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        CCJSqlParserManager parserManager = retrieveTablenameInSql(sql);
        retrieveOperatorPermission();


        if ("SELECT".equals(sqlCommandType.name())) {
            Select select = (Select)parserManager.parse(new StringReader(sql));
            select.getSelectBody().accept(new SelectVisitorImpl());
            statementHandler.setValue("delegate.boundSql.sql",select.toString());
        }

        removeThreadLocalVar();
        return invocation.proceed();
    }

    private void removeThreadLocalVar() {
        LOCAL_SQL_LIST_TABLENAME.remove();
        LOCAL_USER_LIST_TABLENAME.remove();
    }

    private void retrieveOperatorPermission() {
//        SysUser currentUser = SecurityUtils.getCurrentUser();
//        if(currentUser==null) return ;
//
//        List<SysRole> roles = currentUser.getRoles();
        List<SysRole> roles = null;
        List<SysTableExt> operatorPermission = sysTableExtService.getListSysTableExts(roles);
        LOCAL_USER_LIST_TABLENAME.set(operatorPermission);
    }

    private CCJSqlParserManager retrieveTablenameInSql(String sql) throws JSQLParserException {
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Statement statement = parserManager.parse(new StringReader(sql));
        Select selectStatement = (Select) statement;
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(selectStatement);

        LOCAL_SQL_LIST_TABLENAME.set(tableList);
        return parserManager;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}

