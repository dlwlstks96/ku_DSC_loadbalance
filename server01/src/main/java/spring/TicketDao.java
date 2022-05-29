package spring;

import domain.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    //예매 정보 삭제
    public void delete(final Ticket ticket) {
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        "delete from RESERVE01 where NAME = ? and BIRTH = ? and SEATNUMBER = ?");
                pstmt.setString(1, ticket.getName());
                pstmt.setString(2, ticket.getBirth());
                pstmt.setString(3, ticket.getSeatNumber());
                return pstmt;
            }
        });
    }

    //좌석 정보로 예매 정보 확인
    public Ticket selectBySeatNumber(String name, String birth, String seatNumber) {
        List<Ticket> results = jdbcTemplate.query(
                "select * from RESERVE01 where NAME = ? AND BIRTH = ? AND SEATNUMBER = ?",
                new RowMapper<Ticket>() {
                    @Override
                    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Ticket ticket = new Ticket(
                                rs.getString("NAME"),
                                rs.getString("BIRTH"),
                                rs.getString("SEATNUMBER"));
                        ticket.setTicketID(rs.getLong("ID"));
                        return ticket;
                    }
                },
                name, birth, seatNumber);
        return results.isEmpty() ? null : results.get(0);
    }

}
