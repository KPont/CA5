/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import controllers.CustomerJpaController;
import controllers.airportJpaController;
import controllers.exceptions.NonexistentEntityException;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rest.ApiResource;

/**
 *
 * @author Alex
 */
public class ApiFacadeTest {

    private ApiResource apiR = new ApiResource();

    public ApiFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA5PU");
        EntityManager em = emf.createEntityManager();

        Persistence.generateSchema("CA5PU", null);

        CustomerJpaController cjc = new CustomerJpaController(emf);
        airportJpaController ajc = new airportJpaController(emf);
        flightInstanceJpaController fijc = new flightInstanceJpaController(emf);
        flightJpaController fjc = new flightJpaController(emf);
        reservationJpaController rjc = new reservationJpaController(emf);
        seatJpaController sjc = new seatJpaController(emf);

        //Laver flight instance, og hvad sådan en skal bruge
        List<String> totalSeats = new ArrayList();
        List<String> freeSeats = new ArrayList();

        List<String> totalSeats2 = new ArrayList();
        List<String> freeSeats2 = new ArrayList();

        String seatA = "A21";
        String seatB = "B66";
        String seatC = "B87";
        totalSeats.add(seatC);
        totalSeats.add(seatB);
        totalSeats.add(seatA);
        totalSeats2.add(seatC);
        totalSeats2.add(seatB);
        totalSeats2.add(seatA);

        freeSeats2.add(seatA);
        freeSeats2.add(seatC);
        freeSeats2.add(seatB);

        flight flight = new flight("Test Type", totalSeats);
        flight flight2 = new flight("Test Type2", totalSeats2);
        fjc.create(flight);
        fjc.create(flight2);

        airport airport1 = new airport("CPH", "Copenhagen");
        airport airport2 = new airport("BAR", "Barcelona");

        ajc.create(airport1);
        ajc.create(airport2);

        flightInstance fI = new flightInstance("11-05-2015", "11-05-2015", "1234", freeSeats, airport1, airport2, flight);
        flightInstance fI2 = new flightInstance("16-05-2015", "16-05-2015", "1234", freeSeats2, airport2, airport1, flight2);

        fijc.create(fI);
        fijc.create(fI2);

        List<String> orderedSeats = new ArrayList();
        orderedSeats.add(seatA);

        List<String> orderedSeats2 = new ArrayList();
        orderedSeats.add(seatB);
        orderedSeats.add(seatC);

        Customer cust = new Customer("steve", "stevenson", "Hvidovrevej", "Hvidovre", "2650", "Denmark");
        Customer cust2 = new Customer("steve2", "stevenson2", "Hvidovrevej2", "Hvidovre2", "2650", "Denmark2");
        Customer cust3 = new Customer("steve3", "stevenson3", "Hvidovrevej3", "Hvidovre3", "2650", "Denmark3");

        cjc.create(cust);
        cjc.create(cust2);
        cjc.create(cust3);

        seat seat = new seat(freeSeats, cust);
        seat seat2 = new seat(freeSeats2, cust2);

        sjc.create(seat);
        sjc.create(seat2);

        reservation res = new reservation(fI, seat, cust);
        reservation res2 = new reservation(fI2, seat2, cust2);

        rjc.create(res);
        rjc.create(res2);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @After
    public void teardown() {

    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetJson() {
        assertEquals(apiR.getJson(), "[{\"airline\":\"Test Type\",\"price\":1234,\"flightId\":3,\"takeOffDate\":\"11-05-2015\",\"landingDate\":\"11-05-2015\",\"departure\":\"CPH\",\"destination\":\"BAR\",\"seats\":3,\"availSeats\":0,\"bookingCode\":true},"
                + "{\"airline\":\"Test Type2\",\"price\":1234,\"flightId\":4,\"takeOffDate\":\"16-05-2015\",\"landingDate\":\"16-05-2015\",\"departure\":\"BAR\",\"destination\":\"CPH\",\"seats\":3,\"availSeats\":3,\"bookingCode\":true}]");
    }

//    @Test
//    public void testPostJson() {
//        Gson gson = new Gson();
//        String pas = gson.toJson(new Customer("Jess", "Jess", "vej", "by", "2696", "Dinamarca"));
//
//        String seatA = "A21";
//        String seatB = "B66";
//
//        List<String> seats = new List();
//
//        seats.add(seatA);
//        seats.add(seatB);
//
//        apiR.postJson(pas, seats, "4");
//    }
    @Test
    public void testGetFlight() throws ParseException {
        assertEquals(apiR.getFlight("CPH", "11-05-2015"), "[{\"airline\":\"Test Type\",\"price\":1234,\"flightId\":3,\"takeOffDate\":\"11-05-2015\",\"landingDate\":\"11-05-2015\",\"departure\":\"CPH\",\"destination\":\"BAR\",\"seats\":3,\"availSeats\":0,\"bookingCode\":true}]");
        assertEquals(apiR.getFlight("BAR", "16-05-2015"), "[{\"airline\":\"Test Type2\",\"price\":1234,\"flightId\":4,\"takeOffDate\":\"16-05-2015\",\"landingDate\":\"16-05-2015\",\"departure\":\"BAR\",\"destination\":\"CPH\",\"seats\":3,\"availSeats\":3,\"bookingCode\":true}]");
    }

    @Test
    public void testGetFlightStartEndDate() {
        assertEquals(apiR.getFlightStartEndDate("CPH", "BAR", "11-05-2015"), "[{\"airline\":\"Test Type\",\"price\":1234,\"flightId\":3,\"takeOffDate\":\"11-05-2015\",\"landingDate\":\"11-05-2015\",\"departure\":\"CPH\",\"destination\":\"BAR\",\"seats\":3,\"availSeats\":0,\"bookingCode\":true}]");
        assertEquals(apiR.getFlightStartEndDate("BAR", "CPH", "16-05-2015"), "[{\"airline\":\"Test Type2\",\"price\":1234,\"flightId\":4,\"takeOffDate\":\"16-05-2015\",\"landingDate\":\"16-05-2015\",\"departure\":\"BAR\",\"destination\":\"CPH\",\"seats\":3,\"availSeats\":3,\"bookingCode\":true}]");
    }

    @Test
    public void testGetResId() {

        assertEquals(apiR.getResId("11"), "{\"reservationID\":11,\"flightID\":4,\"Passengers\":[{\"id\":6,\"firstName\":\"steve2\",\"lastName\":\"stevenson2\",\"street\":\"Hvidovrevej2\",\"city\":\"Hvidovre2\",\"zip\":\"2650\",\"country\":\"Denmark2\"}],\"totalPrice\":1234}");
    }

    @Test
    public void testDeleteResId() throws NonexistentEntityException {
        Gson gson = new Gson();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA5PU");
        EntityManager em = emf.createEntityManager();
        reservationJpaController rjc = new reservationJpaController(emf);

        assertEquals(rjc.findreservationEntities().size(), 2);
        assertEquals(apiR.getResId("10"), "{\"reservationID\":10,\"flightID\":3,\"Passengers\":[{\"id\":5,\"firstName\":\"steve\",\"lastName\":\"stevenson\",\"street\":\"Hvidovrevej\",\"city\":\"Hvidovre\",\"zip\":\"2650\",\"country\":\"Denmark\"}],\"totalPrice\":1234}");
        assertEquals(apiR.deleteResId("10"), "{\"reservationID\":10,\"flightID\":3,\"Passengers\":[{\"id\":5,\"firstName\":\"steve\",\"lastName\":\"stevenson\",\"street\":\"Hvidovrevej\",\"city\":\"Hvidovre\",\"zip\":\"2650\",\"country\":\"Denmark\"}],\"totalPrice\":1234}");

        assertEquals(rjc.findreservationEntities().size(), 1);

    }

}
