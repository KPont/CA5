/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
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
public class reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private flightInstance flightInstance;

    
    @OneToOne
    private seat seat;
    
    @OneToOne
    private Customer customer;
    
    public reservation() {
    }

    public reservation(flightInstance flightInstance, seat seat, Customer customer) {
        this.flightInstance = flightInstance;
        this.seat = seat;
        this.customer = customer;
    }

    public flightInstance getFlightInstance() {
        return flightInstance;
    }

    public void setFlightInstance(flightInstance flightInstance) {
        this.flightInstance = flightInstance;
    }

    public seat getSeat() {
        return seat;
    }

    public void setSeat(seat seat) {
        this.seat = seat;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
}
