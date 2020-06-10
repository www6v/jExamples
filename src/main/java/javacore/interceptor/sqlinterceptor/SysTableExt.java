package javacore.interceptor.sqlinterceptor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysTableExt {
    private String userRole;
    private String tableName;
    private String operator;
    private String fieldName;
    private String fieldValue;
}


