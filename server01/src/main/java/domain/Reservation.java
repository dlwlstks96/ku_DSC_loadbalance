package domain;

public class Reservation {

    private Long ticketID; //DB 자동 생성
    private String name;
    private String birth;
    private String phoneNumber;
    private String seatNumber;

    public Reservation(String name, String birth, String phoneNumber, String seatNumber) {
        this.name = name;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {return phoneNumber;}

    public String getBirth() {
        return birth;
    }

    public String getSeatNumber() {
        return seatNumber;
    }
}
