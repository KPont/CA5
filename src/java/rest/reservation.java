/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;

/**
 *
 * @author Kasper
 */
public class reservation {
    private String reservationID;
    private String flightID;
    private ArrayList<passengers> Passengers;
    private double totalPrice;

    public reservation(String reservationID, String flightID, ArrayList<passengers> Passengers, double totalPrice) {
        this.reservationID = reservationID;
        this.flightID = flightID;
        this.Passengers = Passengers;
        this.totalPrice = totalPrice;
    }

    
    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public ArrayList<passengers> getPassengers() {
        return Passengers;
    }

    public void setPassengers(ArrayList<passengers> Passengers) {
        this.Passengers = Passengers;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
