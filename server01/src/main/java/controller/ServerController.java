package controller;

import domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.ReservationDao;

import java.util.List;

@RestController
public class ServerController { //8081번 포트

    private final ReservationDao reservationDao;

    @Autowired
    public ServerController(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    @GetMapping("/")
    public String checkToClient(){



        return "";
    }

    @PostMapping("/")
    public String reserveToClient(@RequestBody MultiValueMap<String, String> body){
        String name = body.get("name").get(0);
        String dateOfBirth = body.get("dateOfBirth").get(0);
        String phoneNumber = body.get("phoneNumber").get(0);
        String seatNumber = body.get("seatNumber").get(0);

        Reservation reservation = new Reservation(name, dateOfBirth, phoneNumber, seatNumber);

        reservationDao.insert(reservation); //DB에 저장

        System.out.println("name:" + name
                + " dateOfBirth:" +dateOfBirth
                + " phoneNumber:" + phoneNumber
                + " seatNumber:"+ seatNumber);
        return "";
    }
}
