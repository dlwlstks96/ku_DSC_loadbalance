package domain;

import java.time.LocalDateTime;

public class Ticket {

    private Long ticketID; //DB 자동 생성
    private String name;
    private String birth;
    private String seatNumber;

    //아래 변수들은 추가할지 상의 필요
    //private String password
    //private LocalDateTime reserveDateTime

    public Ticket(String name, String birth, String seatNumber) {
        this.name = name;
        this.birth = birth;
        this.seatNumber = seatNumber;
    }

    public void setTicketID(Long ticketID) {
        this.ticketID = ticketID;
    }

    public Long getTicketID() {
        return ticketID;
    }

    public String getName() {
        return name;
    }

    public String getBirth() {
        return birth;
    }

    public String getSeatNumber() {
        return seatNumber;
    }
}
