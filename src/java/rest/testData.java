/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.Date;

/**
 *
 * @author Kasper
 */
public class testData {
      
   private String airline;
   private long price;
   private int flightId;
   private String takeOffDate;
   private String landingDate;
   private String departure;
   private String destination;
   private int seats;
   private int availSeats;
   private boolean bookingCode;

    public testData(String airline, long price, int flightId, String takeOffDate, String landingDate, String departure, String destination, int seats, int availSeats, boolean bookingCode) {
        this.airline = airline;
        this.price = price;
        this.flightId = flightId;
        this.takeOffDate = takeOffDate;
        this.landingDate = landingDate;
        this.departure = departure;
        this.destination = destination;
        this.seats = seats;
        this.availSeats = availSeats;
        this.bookingCode = bookingCode;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getTakeOffDate() {
        return takeOffDate;
    }

    public void setTakeOffDate(String takeOffDate) {
        this.takeOffDate = takeOffDate;
    }

    public String getLandingDate() {
        return landingDate;
    }

    public void setLandingDate(String landingDate) {
        this.landingDate = landingDate;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getAvailSeats() {
        return availSeats;
    }

    public void setAvailSeats(int availSeats) {
        this.availSeats = availSeats;
    }

    public boolean isBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(boolean bookingCode) {
        this.bookingCode = bookingCode;
    }
    
}
