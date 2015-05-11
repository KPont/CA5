/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entity.Customer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kasper
 */
public class Reservations {
    private int reservationID;
    private int flightID;
    private List<Customer> Passengers;
    private long totalPrice;

    public Reservations(int reservationID, int flightID, List<Customer> Passengers, long totalPrice) {
        this.reservationID = reservationID;
        this.flightID = flightID;
        this.Passengers = Passengers;
        this.totalPrice = totalPrice;
    }

    
    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public List<Customer> getPassengers() {
        return Passengers;
    }

    public void setPassengers(List<Customer> Passengers) {
        this.Passengers = Passengers;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
