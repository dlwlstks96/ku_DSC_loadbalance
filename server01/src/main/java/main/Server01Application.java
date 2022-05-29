package main;

import config.AppCtx;
import domain.Ticket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.TicketDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class Server01Application {

	private static TicketDao ticketDao;

	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppCtx.class);

		SpringApplication.run(Server01Application.class, args);

		ticketDao = ctx.getBean(TicketDao.class);

		insertTicketInfo();
		//selectTicketInfo();
		//deleteTicketInfo();

		ctx.close();

	}

	private static void insertTicketInfo() throws IOException {
		System.out.println("-----insert Ticket info");
		System.out.println("예매할 티켓 정보를 입력하세요: (name birth seatNumber)");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String userInput = reader.readLine();
		String[] ticketInfo = userInput.split(" ");
		String name = ticketInfo[0];
		String birth = ticketInfo[1];
		String seatNumber = ticketInfo[2];

		Ticket ticket = new Ticket(name, birth, seatNumber);

		ticketDao.insert(ticket);

		System.out.println("예매 완료");
	}

	private static void selectTicketInfo() throws IOException {
		System.out.println("-----select Ticket info");
		System.out.println("조회할 좌석 정보를 입력하세요: (name birth seatNumber)");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String userInput = reader.readLine();
		String[] ticketInfo = userInput.split(" ");
		String name = ticketInfo[0];
		String birth = ticketInfo[1];
		String seatNumber = ticketInfo[2];

		//Ticket ticket = new Ticket(name, birth, seatNumber);

		Ticket ticket = ticketDao.selectBySeatNumber(name, birth, seatNumber);

		System.out.println("조회하신 예매 내역입니다.");
		System.out.println("이름: " + ticket.getName() + " 생년월일: " + ticket.getBirth() +
				" 좌석 번호: " + ticket.getSeatNumber() + " 예매 번호: " + ticket.getTicketID());

	}

	private static void deleteTicketInfo() throws IOException {
		System.out.println("-----delete Ticket info");
		System.out.println("삭제할 좌석 정보를 입력하세요: (name birth seatNumber)");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String userInput = reader.readLine();
		String[] ticketInfo = userInput.split(" ");
		String name = ticketInfo[0];
		String birth = ticketInfo[1];
		String seatNumber = ticketInfo[2];

		Ticket ticket = new Ticket(name, birth, seatNumber);

		ticketDao.delete(ticket);

		System.out.println("삭제 완료");
	}

}
