package javacore.mybatis_interceptor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableCondition {
    private String operator;
    private String fieldName;
    private String fieldValue;
}
