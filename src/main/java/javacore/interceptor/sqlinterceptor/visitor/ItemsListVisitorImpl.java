package javacore.interceptor.sqlinterceptor.visitor;

import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsListVisitor;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.expression.operators.relational.NamedExpressionList;
import net.sf.jsqlparser.statement.select.SubSelect;

public class ItemsListVisitorImpl implements ItemsListVisitor {
    @Override
    public void visit(SubSelect subSelect) {
    }

    @Override
    public void visit(ExpressionList expressionList) {
    }

    @Override
    public void visit(NamedExpressionList namedExpressionList) {
    }

    @Override
    public void visit(MultiExpressionList multiExpressionList) {
    }
}
