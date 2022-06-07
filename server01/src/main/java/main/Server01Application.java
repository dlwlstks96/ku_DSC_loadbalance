package main;

import config.AppCtx;
import domain.Reservation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.ReservationDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class Server01Application {

	private static ReservationDao reservationDao;

	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppCtx.class);

		SpringApplication.run(Server01Application.class, args);

		reservationDao = ctx.getBean(ReservationDao.class);

		insertReservationInfo();
		//selectTicketInfo();
		//deleteTicketInfo();

		ctx.close();

	}

	private static void insertReservationInfo() throws IOException {
		System.out.println("-----insert Reservation info");
		System.out.println("예매할 티켓 정보를 입력하세요: (name birth seatNumber)");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String userInput = reader.readLine();
		String[] reservationInfo = userInput.split(" ");
		String name = reservationInfo[0];
		String birth = reservationInfo[1];
		String phoneNumber = reservationInfo[2];
		String seatNumber = reservationInfo[2];

		Reservation reservation = new Reservation(name, birth, phoneNumber, seatNumber);

		reservationDao.insert(reservation);

		System.out.println("예매 완료");
	}

	private static void selectReservationInfo() throws IOException {
		System.out.println("-----select Ticket info");
		System.out.println("조회할 좌석 정보를 입력하세요: (name birth seatNumber)");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String userInput = reader.readLine();
		String[] ticketInfo = userInput.split(" ");
		String name = ticketInfo[0];
		String birth = ticketInfo[1];
		String phoneNumber = ticketInfo[2];
		String seatNumber = ticketInfo[3];

		//Ticket ticket = new Ticket(name, birth, seatNumber);

		Reservation res = new Reservation(name, birth, phoneNumber, seatNumber);

		Reservation findReservation = reservationDao.selectBySeatNumber(res);

		System.out.println("조회하신 예매 내역입니다.");
		System.out.println("이름: " + findReservation.getName() + " 생년월일: " + findReservation.getBirth() +
				" 좌석 번호: " + findReservation.getSeatNumber() + " 예매 번호: " + findReservation.getTicketID());

	}

//	private static void deleteTicketInfo() throws IOException {
//		System.out.println("-----delete Ticket info");
//		System.out.println("삭제할 좌석 정보를 입력하세요: (name birth seatNumber)");
//
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		String userInput = reader.readLine();
//		String[] ticketInfo = userInput.split(" ");
//		String name = ticketInfo[0];
//		String birth = ticketInfo[1];
//		String seatNumber = ticketInfo[2];
//
//		Reservation reservation = new Reservation(name, birth, seatNumber);
//
//		ReservationDao.delete(reservation);
//
//		System.out.println("삭제 완료");
//	}

}
