package com.example.helloboot;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// component 메타애노테이션을 가지는 repository 를 사용
@Repository
public class HelloRepositoryJdbc implements HelloRepository {

    private final JdbcTemplate jdbcTemplate;

    public HelloRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Hello findHello(String name) {
        try {
            return jdbcTemplate.queryForObject("select * from hello where name = '" + name + "'", (rs, rowNum) -> new Hello(
                    rs.getString("name"),
                    rs.getInt("count")
            ));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void increaseCount(String name) {
        var hello = findHello(name);
        if (hello == null) {
            jdbcTemplate.update("insert into hello values(?, ?)", name, 1);
            return;
        }
        jdbcTemplate.update("update hello set count = ? where name = ?", hello.getCount() + 1, name);
    }
}
