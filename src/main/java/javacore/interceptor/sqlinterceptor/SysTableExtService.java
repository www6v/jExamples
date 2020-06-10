package javacore.interceptor.sqlinterceptor;

//import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
//import com.honeywell.mom.upms.common.domain.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SysTableExtService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<SysTableExt> getListSysTableExts(List<SysRole>  roles) {
        if(roles == null) { return null; }

        List<SysTableExt> sysTableExts = new ArrayList<SysTableExt>();

        String sql = "select user_role, table_name, operator, field_name, field_value  from  SYS_TABLE_EXT  where user_role=?";
        RowMapper<SysTableExt> rowMapper=new BeanPropertyRowMapper<SysTableExt>(SysTableExt.class);

        roles.forEach(role -> {
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, role.getId().toString());
            maps.forEach(x -> {
//            String userRole = (String) x.get("user_role");
                String tableName = (String) x.get("table_name");
                String operator = (String) x.get("operator");
                String fieldName = (String)x.get("field_name");
                String fieldValue = (String) x.get("field_value");

                SysTableExt sysTableExt = new SysTableExt();
                sysTableExt.setUserRole(role.getId().toString());
                sysTableExt.setTableName(tableName);
                sysTableExt.setOperator(operator);
                sysTableExt.setFieldName(fieldName);
                sysTableExt.setFieldValue(fieldValue);

                sysTableExts.add(sysTableExt);
            });
        });

        return sysTableExts;
    }
}






