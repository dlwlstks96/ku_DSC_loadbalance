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

		ctx.close();

	}

}
