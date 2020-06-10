package javacore.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SysExtentionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SysExtention getSingleSysExtention(String id) {
        String sql = "select id, val, sql_expr  from  SYS_EXTENTION  where id=?";
        RowMapper<SysExtention> rowMapper=new BeanPropertyRowMapper<SysExtention>(SysExtention.class);
        SysExtention sysExtention= jdbcTemplate.queryForObject(sql, rowMapper,id);

        return sysExtention;
    }
}
