package javacore.interceptor;

import lombok.extern.slf4j.Slf4j;
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

/// 替换整个sql表达式
@Slf4j
@Intercepts({@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class})})
public class DataAuthorityInterceptor implements Interceptor {

    @Autowired
    private SysExtentionService sysExtentionService;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler handler = (StatementHandler) invocation.getTarget();
        MetaObject statementHandler = SystemMetaObject.forObject(handler);
        MappedStatement mappedStatement = (MappedStatement) statementHandler.getValue("delegate.mappedStatement");

        BoundSql boundSql = handler.getBoundSql();
        String sql = boundSql.getSql();

        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Statement statement = parserManager.parse(new StringReader(sql));
        Select selectStatement = (Select) statement;
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(selectStatement);

        // id: namespace+id
        String id = mappedStatement.getId();

        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        //        if (sql.contains("SELECT") || sql.contains("select")) {
        if ("SELECT".equals(sqlCommandType)) {
            try{
                SysExtention sysExtention = sysExtentionService.getSingleSysExtention(id);
                String sqlExpr = sysExtention.getSqlExpr();
                statementHandler.setValue("boundSql.sql", sqlExpr);
            }
            catch(Exception e) {
                log.error(e.getMessage(), e);
            }
            finally{

                return invocation.proceed();
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
