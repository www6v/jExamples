package javacore.interceptor.sqlinterceptor.visitor;

import javacore.interceptor.sqlinterceptor.SysTableExt;
import javacore.interceptor.sqlinterceptor.SqlInterceptor;
import javacore.interceptor.sqlinterceptor.TableCondition;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FromItemVisitorImpl implements FromItemVisitor {

    private Expression enhancedCondition;

    // FROM 表名
    @Override
    public void visit(Table tableName) {
      if (isActionTable(tableName.getFullyQualifiedName())) {
          List<TableCondition> test = getTableCondition(tableName.getFullyQualifiedName().toUpperCase());

          if (test!=null) {
                for (TableCondition tableCondition : test) {
                    Expression[] expressions = null;
                    if ("between".equalsIgnoreCase(tableCondition.getOperator())|| "not between".equalsIgnoreCase(tableCondition.getOperator())) {
                        //expressions = new Expression[] { new LongValue(tableCondition.getFieldName()),new LongValue(tableCondition.getOperator()),new LongValue(tableCondition.getFieldValue()) };
                    } else if ("is null".equalsIgnoreCase(tableCondition.getOperator())|| "is not null".equalsIgnoreCase(tableCondition.getOperator())) {
                        //expressions = new Expression[] { new LongValue(	tableCondition.getFieldName()) };
                    } else {
                        //  最常用的情况，比如where   1 = 1
                        Column column = new Column(new Table(tableName.getAlias()!=null?tableName.getAlias().getName():tableName.getFullyQualifiedName()), tableCondition.getFieldName());
                        if ("1".equals(tableCondition.getFieldName())) {
                            expressions = new Expression[] {new LongValue(tableCondition.getFieldName()),new LongValue(tableCondition.getFieldValue())};
                        }else{
                            expressions = new Expression[] {column,new StringValue(tableCondition.getFieldValue())};
                        }
                    }

                    Expression operator = this.getOperator(
                            tableCondition.getOperator(), expressions);
                    if (this.enhancedCondition != null) {
                        enhancedCondition = new AndExpression(enhancedCondition , operator);
                    } else {
                        enhancedCondition = operator;
                    }
                }
            }
        }
    }

    // FROM 子查询
    @Override
    public void visit(SubSelect subSelect) {
        // 如果是子查询的话返回到select接口实现类
        subSelect.getSelectBody().accept(new SelectVisitorImpl());
    }

    @Override
    public void visit(SubJoin subjoin) {
        subjoin.getLeft().accept(new FromItemVisitorImpl());
//        subjoin.getJoin().getRightItem().accept(new FromItemVisitorImpl());
        subjoin.getJoinList().get(0).getRightItem().accept(new FromItemVisitorImpl());
    }

    // FROM 横向子查询
    @Override
    public void visit(LateralSubSelect lateralSubSelect) {
        lateralSubSelect.getSubSelect().getSelectBody()
                .accept(new SelectVisitorImpl());
    }

    @Override
    public void visit(ValuesList valuesList) {
    }

    @Override
    public void visit(TableFunction tableFunction) {
    }

    private Expression getOperator(String op, Expression[] exp) {
        if ("=".equals(op)) {
            EqualsTo eq = new EqualsTo();
            eq.setLeftExpression(exp[0]);
            eq.setRightExpression(exp[1]);
            return eq;
        } else if (">".equals(op)) {
            GreaterThan gt = new GreaterThan();
            gt.setLeftExpression(exp[0]);
            gt.setRightExpression(exp[1]);
            return gt;
        } else if (">=".equals(op)) {
            GreaterThanEquals geq = new GreaterThanEquals();
            geq.setLeftExpression(exp[0]);
            geq.setRightExpression(exp[1]);
            return geq;
        } else if ("<".equals(op)) {
            MinorThan mt = new MinorThan();
            mt.setLeftExpression(exp[0]);
            mt.setRightExpression(exp[1]);
            return mt;
        } else if ("<=".equals(op)) {
            MinorThanEquals leq = new MinorThanEquals();
            leq.setLeftExpression(exp[0]);
            leq.setRightExpression(exp[1]);
            return leq;
        } else if ("<>".equals(op)) {
            NotEqualsTo neq = new NotEqualsTo();
            neq.setLeftExpression(exp[0]);
            neq.setRightExpression(exp[1]);
            return neq;
        } else if ("is null".equalsIgnoreCase(op)) {
            IsNullExpression isNull = new IsNullExpression();
            isNull.setNot(false);
            isNull.setLeftExpression(exp[0]);
            return isNull;
        } else if ("is not null".equalsIgnoreCase(op)) {
            IsNullExpression isNull = new IsNullExpression();
            isNull.setNot(true);
            isNull.setLeftExpression(exp[0]);
            return isNull;
        } else if ("like".equalsIgnoreCase(op)) {
            LikeExpression like = new LikeExpression();
            like.setNot(false);
            like.setLeftExpression(exp[0]);
            like.setRightExpression(exp[1]);
            return like;
        } else if ("not like".equalsIgnoreCase(op)) {
            LikeExpression nlike = new LikeExpression();
            nlike.setNot(true);
            nlike.setLeftExpression(exp[0]);
            nlike.setRightExpression(exp[1]);
            return nlike;
        } else if ("between".equalsIgnoreCase(op)) {
            Between bt = new Between();
            bt.setNot(false);
            bt.setLeftExpression(exp[0]);
            bt.setBetweenExpressionStart(exp[1]);
            bt.setBetweenExpressionEnd(exp[2]);
            return bt;
        } else if ("not between".equalsIgnoreCase(op)) {
            Between bt = new Between();
            bt.setNot(true);
            bt.setLeftExpression(exp[0]);
            bt.setBetweenExpressionStart(exp[1]);
            bt.setBetweenExpressionEnd(exp[2]);
            return bt;
        } else {
            // 如果没有该运算符对应的语句
            return null;
        }

    }

    public Expression getEnhancedCondition() {
        return enhancedCondition;
    }

    // 判断传入的table是否是要进行操作的table
    public boolean isActionTable(String tableName) {
        List<SysTableExt> sysTableExts = SqlInterceptor.LOCAL_USER_LIST_TABLENAME.get();

        if(sysTableExts==null){
           return false;
        }
        boolean tableMatched = sysTableExts.stream().anyMatch(sysTableExt -> {
            String tableNameOfPermission = sysTableExt.getTableName();
            return tableNameOfPermission.equalsIgnoreCase(tableName);
        });

        return tableMatched;
    }

    public List<TableCondition> getTableCondition(String tableName) {
        List<TableCondition> result = new ArrayList();

        List<SysTableExt> sysTableExts = SqlInterceptor.LOCAL_USER_LIST_TABLENAME.get();
        sysTableExts.forEach(sysTableExt -> {
            if(sysTableExt.getTableName().equalsIgnoreCase(tableName)){
                String operator = sysTableExt.getOperator();
                String fieldName = sysTableExt.getFieldName();
                String fieldValue = sysTableExt.getFieldValue();

                TableCondition tableCondition = new TableCondition();
                tableCondition.setOperator(operator);
                tableCondition.setFieldName(fieldName);
                tableCondition.setFieldValue(fieldValue);

                result.add(tableCondition);
            }
        });

        return result;
    }

    @Override
    public void visit(ParenthesisFromItem parenthesisFromItem) {
    }
}

