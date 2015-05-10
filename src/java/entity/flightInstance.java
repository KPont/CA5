/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Alex
 */
@Entity
public class flightInstance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String takeOffDate;
    private String landingDate;
    private String price;
    private List<String> freeSeats;
    
    @OneToOne
    private airport departure;
    
    @OneToOne
    private airport arrival;
    
    @ManyToOne
    private flight flight;
    
    @OneToMany(mappedBy = "flightInstance")
    private List<reservation> reservations;

    

    public flightInstance(String takeOffDate, String landingDate, String price, List<String> freeSeats, airport departure, airport arrival, flight flight) {
        this.takeOffDate = takeOffDate;
        this.landingDate = landingDate;
        this.price = price;
        this.freeSeats = freeSeats;
        this.departure = departure;
        this.arrival = arrival;
        this.flight = flight;
    }
    
    public flightInstance() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(List<String> freeSeats) {
        this.freeSeats = freeSeats;
    }

    public airport getDeparture() {
        return departure;
    }

    public void setDeparture(airport departure) {
        this.departure = departure;
    }

    public airport getArrival() {
        return arrival;
    }

    public void setArrival(airport arrival) {
        this.arrival = arrival;
    }

    public flight getFlight() {
        return flight;
    }

    public void setFlight(flight flight) {
        this.flight = flight;
    }

    public List<reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<reservation> reservations) {
        this.reservations = reservations;
    }
    
 
    
}
