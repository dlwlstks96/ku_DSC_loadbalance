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

    public Reservation selectBySeatNumber(Reservation res) {
        List<Reservation> results = jdbcTemplate.query(
                "select * from RESERVE01 where NAME = ? AND BIRTH = ? AND PHONENUMBER = ? AND SEATNUMBER = ?",
                new RowMapper<Reservation>() {
                    @Override
                    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Reservation reservation = new Reservation(
                                rs.getString("NAME"),
                                rs.getString("BIRTH"),
                                rs.getString("PHONENUMBER"),
                                rs.getString("SEATNUMBER"));
                        reservation.setTicketID(rs.getLong("ID"));
                        return reservation;
                    }
                },
                res.getName(), res.getBirth(), res.getPhoneNumber(), res.getSeatNumber());
        return results.isEmpty() ? null : results.get(0);
    }

//    //예매 정보 삭제
//    public void delete(final Reservation reservation) {
//        jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                PreparedStatement pstmt = con.prepareStatement(
//                        "delete from RESERVE01 where NAME = ? and BIRTH = ? and SEATNUMBER = ?");
//                pstmt.setString(1, reservation.getName());
//                pstmt.setString(2, reservation.getBirth());
//                pstmt.setString(3, reservation.getSeatNumber());
//                return pstmt;
//            }
//        });
//    }

    //좌석 정보로 예매 정보 확인
//    public Reservation selectBySeatNumber(String name, String birth, String seatNumber) {
//        List<Reservation> results = jdbcTemplate.query(
//                "select * from RESERVE01 where NAME = ? AND BIRTH = ? AND SEATNUMBER = ?",
//                new RowMapper<Reservation>() {
//                    @Override
//                    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        Reservation reservation = new Reservation(
//                                rs.getString("NAME"),
//                                rs.getString("BIRTH"),
//                                rs.getString("SEATNUMBER"));
//                        reservation.setTicketID(rs.getLong("ID"));
//                        return reservation;
//                    }
//                },
//                name, birth, seatNumber);
//        return results.isEmpty() ? null : results.get(0);
//    }

}
