package javacore.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.*;

/// 动态变更sql的ResultType
@Intercepts(@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class ResultTypeInterceptor implements Interceptor {
    private static final List<ResultMapping> EMPTY_RESULTMAPPING = new ArrayList<ResultMapping>(0);
    public static final String DEFAULT_KEY = "resultType";
    private String resultType = DEFAULT_KEY;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Object[] args = invocation.getArgs();
//        MappedStatement ms = (MappedStatement) args[0];

        StatementHandler handler = (StatementHandler) invocation.getTarget();
        MetaObject statementHandler = SystemMetaObject.forObject(handler);
        MappedStatement ms = (MappedStatement) statementHandler.getValue("delegate.mappedStatement");

        Object parameterObject = args[1];
        Class resultType = getResultType(parameterObject);
        if (resultType == null) {
            return invocation.proceed();
        }
        //复制ms，重设类型
        args[0] = newMappedStatement(ms, resultType);
        return invocation.proceed();
    }

    public MappedStatement newMappedStatement(MappedStatement ms, Class resultType) {
        //下面是新建的过程，考虑效率和复用对象的情况下，这里最后生成的ms可以缓存起来，下次根据 ms.getId() + "_" + getShortName(resultType) 直接返回 ms,省去反复创建的过程
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId() + "_" + getShortName(resultType), ms.getSqlSource(), ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuilder keyProperties = new StringBuilder();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        //count查询返回值int
        List<ResultMap> resultMaps = new ArrayList<ResultMap>();
        ResultMap resultMap = new ResultMap.Builder(ms.getConfiguration(), ms.getId(), resultType, EMPTY_RESULTMAPPING).build();
        resultMaps.add(resultMap);
        builder.resultMaps(resultMaps);
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }

    private String getShortName(Class clazz) {
        return clazz.getClass().getSimpleName();
    }

    private Class getResultType(Object parameterObject) {
        if (parameterObject == null) {
            return null;
        } else if (parameterObject instanceof Class) {
            return (Class) parameterObject;
        } else if (parameterObject instanceof Map) {
            //解决不可变Map的情况
            if (((Map) (parameterObject)).containsKey(resultType)) {
                Object result = ((Map) (parameterObject)).get(resultType);
                return objectToClass(result);
            } else {
                return null;
            }
        } else {
            MetaObject metaObject = SystemMetaObject.forObject(parameterObject);
            Object result = metaObject.getValue(resultType);
            return objectToClass(result);
        }
    }

    private Class objectToClass(Object object) {
        if (object == null) {
            return null;
        } else if (object instanceof Class) {
            return (Class) object;
        } else if (object instanceof String) {
            try {
                return Class.forName((String) object);
            } catch (Exception e) {
                throw new RuntimeException("Invalid class: " + e.getMessage(), e);
            }
        } else {
            throw new RuntimeException(resultType);
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String resultType = properties.getProperty("resultType");
        if (resultType != null && resultType.length() > 0) {
            this.resultType = resultType;
        }
    }
}
