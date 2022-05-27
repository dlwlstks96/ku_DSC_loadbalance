package spring;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class TicketDao {

    private JdbcTemplate jdbcTemplate;

    public TicketDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //스프링 책 191쪽 보면서
    //[4.2 JdbcTemplate 이용한 조회 쿼리 실행] 구현해야함

}
