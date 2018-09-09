package javacore.collections;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by wangwei110 on 2018/8/23.
 */
class Pair implements Comparable<Pair> {
    private Float score;
    private String targetId;

    Pair(Float score, String targetId) {
        this.score = score;
        this.targetId = targetId;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }


    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    @Override
    public boolean equals(Object obj) {
        return new EqualsBuilder().append(this.score, ( (Pair)obj ).score)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 21).append(this.score)
                .toHashCode();
    }

    @Override
    public int compareTo(Pair o) {
        Pair myClass =  o;

        return new CompareToBuilder()
                .append(this.score, myClass.score)
                .toComparison();
    }
}