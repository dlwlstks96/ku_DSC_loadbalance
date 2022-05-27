package spring;

import domain.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TicketDao {

    private JdbcTemplate jdbcTemplate;

    public TicketDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    //예매 정보 DB에 삽입
    public void insert(final Ticket ticket) {
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        "insert into RESERVE01 (NAME, BIRTH, SEATNUMBER) " +
                                "values (?, ?, ?)");
                pstmt.setString(1, ticket.getName());
                pstmt.setString(2, ticket.getBirth());
                pstmt.setString(3, ticket.getSeatNumber());
                return pstmt;
            }
        });
    }

}
