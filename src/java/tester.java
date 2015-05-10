
import controllers.CustomerJpaController;
import controllers.airportJpaController;
import controllers.flightInstanceJpaController;
import controllers.flightJpaController;
import controllers.reservationJpaController;
import controllers.seatJpaController;
import entity.Customer;
import entity.airport;
import entity.flight;
import entity.flightInstance;
import entity.reservation;
import entity.seat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alex
 */
public class tester {

    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA5PU");
        EntityManager em = emf.createEntityManager();

        CustomerJpaController cjc = new CustomerJpaController(emf);
        airportJpaController ajc = new airportJpaController(emf);
        flightInstanceJpaController fijc = new flightInstanceJpaController(emf);
        flightJpaController fjc = new flightJpaController(emf);
        reservationJpaController rjc = new reservationJpaController(emf);
        seatJpaController sjc = new seatJpaController(emf);

        
        //Laver flight instance, og hvad sådan en skal bruge
        List<String> totalSeats = new ArrayList();
        List<String> freeSeats = new ArrayList();

        String seatA = "A21";
        String seatB = "B66";
        String seatC = "B87";
        totalSeats.add(seatC);
        totalSeats.add(seatB);
        totalSeats.add(seatA);

        freeSeats.add(seatC);
        freeSeats.add(seatB);

        flight flight = new flight("Test Type", totalSeats);
        fjc.create(flight);

        airport airportFrom = new airport("CPH", "Copenhagen");
        airport airportTo = new airport("BAR", "Barcelona");

        ajc.create(airportFrom);
        ajc.create(airportTo);

        flightInstance fI = new flightInstance("11-05-2015", "11-05-2015", "1234", freeSeats, airportFrom, airportTo, flight);

        fijc.create(fI);
        
        
        ////laver en reservation til den nye flightinstance
        List<String> orderedSeats = new ArrayList();
        orderedSeats.add(seatA);
        
        
        
        Customer cust = new Customer("steve", "stevenson", "Hvidovrevej", "Hvidovre", "2650", "Denmark");
        
        cjc.create(cust);
        
        seat seat = new seat(freeSeats, cust);
        sjc.create(seat);
        
        reservation res = new reservation(fI, seat, cust);
        
        rjc.create(res);
    }

}
