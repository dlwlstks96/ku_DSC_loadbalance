package spring;

import domain.Reservation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReservationDao {

    private JdbcTemplate jdbcTemplate;

    public ReservationDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //예매 정보 DB에 삽입
    public void insert(final Reservation reservation) {
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        "insert into RESERVE01 (NAME, BIRTH, PHONENUMBER, SEATNUMBER) " +
                                "values (?, ?, ?, ?)");
                pstmt.setString(1, reservation.getName());
                pstmt.setString(2, reservation.getBirth());
                pstmt.setString(3, reservation.getPhoneNumber());
                pstmt.setString(4, reservation.getSeatNumber());
                return pstmt;
            }
        });
    }

}
