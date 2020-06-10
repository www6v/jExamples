package javacore.interceptor.sqlinterceptor.visitor;


import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.WithItem;

@Slf4j
public class ExpressionVisitorImpl implements ExpressionVisitor {
    @Override
    public void visit(SignedExpression signedExpression) {
        signedExpression.accept(new javacore.mybatis_interceptor.ExpressionVisitorImpl());
    }

    @Override
    public void visit(JdbcParameter jdbcParameter) {
    }

    @Override
    public void visit(JdbcNamedParameter jdbcNamedParameter) {
    }

    @Override
    public void visit(Parenthesis parenthesis) {
        parenthesis.getExpression().accept(new ExpressionVisitorImpl());
    }

    @Override
    public void visit(Between between) {
        between.getLeftExpression().accept(new ExpressionVisitorImpl());
        between.getBetweenExpressionStart().accept(new ExpressionVisitorImpl());
        between.getBetweenExpressionEnd().accept(new ExpressionVisitorImpl());
    }

    @Override
    public void visit(InExpression inExpression) {
        if (inExpression.getLeftExpression() != null) {
            inExpression.getLeftExpression()
                    .accept(new ExpressionVisitorImpl());
        } else if (inExpression.getLeftItemsList() != null) {
            inExpression.getLeftItemsList().accept(new ItemsListVisitorImpl());
        }
        inExpression.getRightItemsList().accept(new ItemsListVisitorImpl());
    }

    @Override
    public void visit(FullTextSearch fullTextSearch) {

    }

    @Override
    public void visit(SubSelect subSelect) {
        if (subSelect.getWithItemsList() != null) {
            for (WithItem withItem : subSelect.getWithItemsList()) {
                withItem.accept(new SelectVisitorImpl());
            }
        }
        subSelect.getSelectBody().accept(new SelectVisitorImpl());
    }

    @Override
    public void visit(ExistsExpression existsExpression) {
        existsExpression.getRightExpression().accept(
                new ExpressionVisitorImpl());
    }

    @Override
    public void visit(AllComparisonExpression allComparisonExpression) {
        allComparisonExpression.getSubSelect().getSelectBody()
                .accept(new SelectVisitorImpl());
    }

    @Override
    public void visit(AnyComparisonExpression anyComparisonExpression) {
        anyComparisonExpression.getSubSelect().getSelectBody()
                .accept(new SelectVisitorImpl());
    }

    @Override
    public void visit(OracleHierarchicalExpression oexpr) {
        if (oexpr.getStartExpression() != null) {
            oexpr.getStartExpression().accept(this);
        }

        if (oexpr.getConnectExpression() != null) {
            oexpr.getConnectExpression().accept(this);
        }
    }

    @Override
    public void visit(RowConstructor rowConstructor) {
        for (Expression expr : rowConstructor.getExprList().getExpressions()) {
            expr.accept(this);
        }
    }

    @Override
    public void visit(CastExpression cast) {
        cast.getLeftExpression().accept(new ExpressionVisitorImpl());
    }

    @Override
    public void visit(Addition addition) {
        visitBinaryExpression(addition);
    }

    @Override
    public void visit(Division division) {
        visitBinaryExpression(division);
    }

    @Override
    public void visit(IntegerDivision integerDivision) {
    }

    @Override
    public void visit(Multiplication multiplication) {
        visitBinaryExpression(multiplication);
    }

    @Override
    public void visit(Subtraction subtraction) {
        visitBinaryExpression(subtraction);
    }

    @Override
    public void visit(AndExpression andExpression) {
        visitBinaryExpression(andExpression);
    }

    @Override
    public void visit(OrExpression orExpression) {
        visitBinaryExpression(orExpression);
    }

    @Override
    public void visit(EqualsTo equalsTo) {
        visitBinaryExpression(equalsTo);
    }

    @Override
    public void visit(GreaterThan greaterThan) {
        visitBinaryExpression(greaterThan);
    }

    @Override
    public void visit(GreaterThanEquals greaterThanEquals) {
        visitBinaryExpression(greaterThanEquals);
    }

    @Override
    public void visit(LikeExpression likeExpression) {
        visitBinaryExpression(likeExpression);
    }

    @Override
    public void visit(MinorThan minorThan) {
        visitBinaryExpression(minorThan);
    }

    @Override
    public void visit(MinorThanEquals minorThanEquals) {
        visitBinaryExpression(minorThanEquals);
    }

    @Override
    public void visit(NotEqualsTo notEqualsTo) {
        visitBinaryExpression(notEqualsTo);
    }

    @Override
    public void visit(Concat concat) {
        visitBinaryExpression(concat);
    }

    @Override
    public void visit(Matches matches) {
        visitBinaryExpression(matches);
    }

    // bitwiseAnd位运算
    @Override
    public void visit(BitwiseAnd bitwiseAnd) {
        visitBinaryExpression(bitwiseAnd);
    }

    @Override
    public void visit(BitwiseOr bitwiseOr) {
        visitBinaryExpression(bitwiseOr);
    }

    @Override
    public void visit(BitwiseXor bitwiseXor) {
        visitBinaryExpression(bitwiseXor);
    }

    // 取模运算modulo
    @Override
    public void visit(Modulo modulo) {
        visitBinaryExpression(modulo);
    }

    @Override
    public void visit(RegExpMatchOperator rexpr) {
        visitBinaryExpression(rexpr);
    }

    @Override
    public void visit(RegExpMySQLOperator regExpMySQLOperator) {
        visitBinaryExpression(regExpMySQLOperator);
    }

    // 二元表达式
    public void visitBinaryExpression(BinaryExpression binaryExpression) {
        binaryExpression.getLeftExpression()
                .accept(new ExpressionVisitorImpl());
        binaryExpression.getRightExpression().accept(
                new ExpressionVisitorImpl());
    }

    @Override
    public void visit(AnalyticExpression aexpr) {
    }

//    @Override
//    public void visit(WithinGroupExpression withinGroupExpression) {
//
//    }
//
//    @Override
//    public void visit(WithinGroupExpression wgexpr) {
//    }

    @Override
    public void visit(ExtractExpression eexpr) {
    }

    @Override
    public void visit(IntervalExpression iexpr) {
    }

    @Override
    public void visit(JsonExpression jsonExpr) {
    }

    @Override
    public void visit(OracleHint hint) {
    }

    @Override
    public void visit(TimeKeyExpression timeKeyExpression) {
    }

    @Override
    public void visit(CaseExpression caseExpression) {
    }

    @Override
    public void visit(WhenClause whenClause) {
    }

    @Override
    public void visit(UserVariable var) {
    }

    @Override
    public void visit(NumericBind bind) {
    }

    @Override
    public void visit(KeepExpression aexpr) {
    }

    @Override
    public void visit(MySQLGroupConcat groupConcat) {
    }

    @Override
    public void visit(ValueListExpression valueListExpression) {
    }

    @Override
    public void visit(Column tableColumn) {
    }

    @Override
    public void visit(DoubleValue doubleValue) {
    }

    @Override
    public void visit(LongValue longValue) {
    }

    @Override
    public void visit(HexValue hexValue) {
    }

    @Override
    public void visit(DateValue dateValue) {
    }

    @Override
    public void visit(TimeValue timeValue) {
    }

    @Override
    public void visit(TimestampValue timestampValue) {
    }

    @Override
    public void visit(BitwiseRightShift bitwiseRightShift) {

    }

    @Override
    public void visit(BitwiseLeftShift bitwiseLeftShift) {
    }

    @Override
    public void visit(NullValue nullValue) {
    }

    @Override
    public void visit(Function function) {
    }

    @Override
    public void visit(StringValue stringValue) {
    }

    @Override
    public void visit(IsNullExpression isNullExpression) {
    }

    @Override
    public void visit(IsBooleanExpression isBooleanExpression) {

    }

    @Override
    public void visit(DateTimeLiteralExpression literal) {
    }

    ///
    @Override
    public void visit(NotExpression notExpression) {
    }

    @Override
    public void visit(NextValExpression nextValExpression) {
    }

    @Override
    public void visit(CollateExpression collateExpression) {
    }

    @Override
    public void visit(SimilarToExpression similarToExpression) {
    }

    @Override
    public void visit(ArrayExpression arrayExpression) {
    }

    @Override
    public void visit(JsonOperator jsonOperator) {
    }
}

