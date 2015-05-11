/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controllers.exceptions.NonexistentEntityException;
import entity.Customer;
import entity.flightInstance;
import entity.reservation;
import entity.seat;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import rest.Reservations;
import rest.testData;

/**
 *
 * @author Alex
 */
public class Facade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA5PU");
    EntityManager em = emf.createEntityManager();

    private CustomerJpaController cjc = new CustomerJpaController(emf);
    private airportJpaController ajc = new airportJpaController(emf);
    private flightInstanceJpaController fijc = new flightInstanceJpaController(emf);
    private flightJpaController fjc = new flightJpaController(emf);
    private reservationJpaController rjc = new reservationJpaController(emf);
    private seatJpaController sjc = new seatJpaController(emf);

    public Object getjson() {
        Gson gson = new Gson();

        List<testData> availFlights = new ArrayList();

        String airline = "";
        long price = 0;
        int flightId;
        String takeOffDate = "";
        String landingDate = "";
        String departure = "";
        String destination = "";
        int seats = 0;
        int availableSeats = 0;
        boolean bookingCode = true;

        List<flightInstance> fiList = fijc.findflightInstanceEntities();
        for (int i = 0; i < fiList.size(); i++) {
            airline = fiList.get(i).getFlight().getType();
            price = Integer.parseInt(fiList.get(i).getPrice());
            flightId = fiList.get(i).getId();
            takeOffDate = fiList.get(i).getTakeOffDate();
            landingDate = fiList.get(i).getLandingDate();
            departure = fiList.get(i).getDeparture().getCode();
            destination = fiList.get(i).getArrival().getCode();
            seats = fiList.get(i).getFlight().getTotalSeats().size();
            availableSeats = fiList.get(i).getFreeSeats().size();

            testData availFlight = new testData(airline, price, flightId, takeOffDate, landingDate, departure, destination, seats, availableSeats, bookingCode);

            availFlights.add(availFlight);
        }
        return gson.toJson(availFlights);
    }

    public Object getFlight(String startAirport, String startDate) {
        Gson gson = new Gson();

        List<testData> availFlights = new ArrayList();

        String airline = "";
        long price = 0;
        int flightId;
        String takeOffDate = "";
        String landingDate = "";
        String departure = "";
        String destination = "";
        int seats = 0;
        int availableSeats = 0;
        boolean bookingCode = true;

        List<flightInstance> fiList = fijc.getFlightByStartAndDate(startAirport, startDate);

        for (int i = 0; i < fiList.size(); i++) {
            airline = fiList.get(i).getFlight().getType();
            price = Integer.parseInt(fiList.get(i).getPrice());
            flightId = fiList.get(i).getId();
            takeOffDate = fiList.get(i).getTakeOffDate();
            landingDate = fiList.get(i).getLandingDate();
            departure = fiList.get(i).getDeparture().getCode();
            destination = fiList.get(i).getArrival().getCode();
            seats = fiList.get(i).getFlight().getTotalSeats().size();
            availableSeats = fiList.get(i).getFreeSeats().size();

            testData availFlight = new testData(airline, price, flightId, takeOffDate, landingDate, departure, destination, seats, availableSeats, bookingCode);

            availFlights.add(availFlight);
           
        }
        return gson.toJson(availFlights);
    }

    public Object getFlightStartEndDate(String startAirport, String endAirport, String startDate) {
        Gson gson = new Gson();

        List<testData> availFlights = new ArrayList();

        String airline = "";
        long price = 0;
        int flightId;
        String takeOffDate = "";
        String landingDate = "";
        String departure = "";
        String destination = "";
        int seats = 0;
        int availableSeats = 0;
        boolean bookingCode = true;

        List<flightInstance> fiList = fijc.getFlightByStartEndAndDate(startAirport, endAirport, startDate);

        for (int i = 0; i < fiList.size(); i++) {
            airline = fiList.get(i).getFlight().getType();
            price = Integer.parseInt(fiList.get(i).getPrice());
            flightId = fiList.get(i).getId();
            takeOffDate = fiList.get(i).getTakeOffDate();
            landingDate = fiList.get(i).getLandingDate();
            departure = fiList.get(i).getDeparture().getCode();
            destination = fiList.get(i).getArrival().getCode();
            seats = fiList.get(i).getFlight().getTotalSeats().size();
            availableSeats = fiList.get(i).getFreeSeats().size();

            testData availFlight = new testData(airline, price, flightId, takeOffDate, landingDate, departure, destination, seats, availableSeats, bookingCode);

            availFlights.add(availFlight);
           
        }
        return gson.toJson(availFlights);
    }

    public Object getResId(int resId) {

        Gson gson = new Gson();
        reservation res = rjc.findreservation(resId);
        Customer cust = res.getCustomer();
        List<Customer> customers = new ArrayList();
        customers.add(cust);
        Reservations res2 = new Reservations(res.getId(), res.getFlightInstance().getId(), customers, Integer.parseInt(res.getFlightInstance().getPrice()));
       
        return gson.toJson(res2);
    }

    public Object deleteRes(int resId) throws NonexistentEntityException {
        Gson gson = new Gson();
        reservation res = rjc.findreservation(resId);
        Customer cust = res.getCustomer();
        List<Customer> customers = new ArrayList();
        customers.add(cust);
        Reservations res2 = new Reservations(res.getId(), res.getFlightInstance().getId(), customers, Integer.parseInt(res.getFlightInstance().getPrice()));
       

        rjc.destroy(resId);
        sjc.destroy(res.getSeat().getId());
        return gson.toJson(res2);
    }

    public Object createRes(Customer cust, List<String> seats, int flightId) {
        Gson gson = new Gson();

        List<Customer> customers = new ArrayList();
        customers.add(cust);
        seat seat = new seat(seats, customers.get(0));
        flightInstance fi = fijc.findflightInstance(flightId);
        reservation res = new reservation(fi, seat, customers.get(0));

        sjc.create(seat);
        rjc.create(res);

        Reservations res2 = new Reservations(res.getId(), flightId, customers, Integer.parseInt(fi.getPrice()));
      

        return gson.toJson(res2);
    }
}
