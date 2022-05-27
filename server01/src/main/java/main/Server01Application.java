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

		ticketDao = ctx.getBean(TicketDao.class);

		insertTicketInfo();
		SpringApplication.run(Server01Application.class, args);

		ctx.close();

	}

	private static void insertTicketInfo() throws IOException {
		System.out.println("-----inset Ticket info");
		System.out.println("등록할 티켓 정보를 입력하세요: (name birth seatNumber)");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String userInput = reader.readLine();
		String[] ticketInfo = userInput.split(" ");
		String name = ticketInfo[0];
		String birth = ticketInfo[1];
		String seatNumber = ticketInfo[2];

		Ticket ticket = new Ticket(name, birth, seatNumber);

		ticketDao.insert(ticket);
	}

}
