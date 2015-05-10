
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
       
    public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA5PU");
    EntityManager em = emf.createEntityManager();
    }
    
}
