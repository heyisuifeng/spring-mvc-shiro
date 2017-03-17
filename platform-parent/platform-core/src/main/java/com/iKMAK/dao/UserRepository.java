package com.iKMAK.dao;

import com.iKMAK.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leaf.Ye on 2017/3/16.
 */
@Repository
public class UserRepository {

    @Inject
    private JdbcTemplate jdbcTemplate;

    public User getUserByUsername(String loginName){
        User user =
        jdbcTemplate.queryForObject("SELECT * FROM t_user WHERE username=?", new Object[]{loginName}, new RowMapper<User>() {

            public User mapRow(ResultSet resultSet, int i) throws SQLException{
                User u = new User();
                u.setUserId(resultSet.getInt("id"));
                u.setUsername(resultSet.getString("username"));
                u.setNickname(resultSet.getString("nickname"));
                u.setPassword(resultSet.getString("password"));
                return u;
            }
        });
        return user;
    }
}
