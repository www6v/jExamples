package javacore.mybatis_interceptor;

import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsListVisitor;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.statement.select.SubSelect;

/// 自己建立的
public class ItemsListVisitorImpl implements ItemsListVisitor {
    @Override
    public void visit(SubSelect subSelect) {

    }

    @Override
    public void visit(ExpressionList expressionList) {

    }

    @Override
    public void visit(MultiExpressionList multiExpressionList) {

    }
}
