package controller;

/**
 * Controller class for Client
 * @author Micheal O' Sullivan
 * 
 */

import models.Client;
import models.ClientCollection;
import models.FullVaccine;

import javax.persistence.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ClientController {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamples");

    public String addClient(String code, String id, String fName,String lName,String phone, String email){
        Client c = new Client();
        ClientCollection t = new ClientCollection();


        EntityManager em = emf.createEntityManager();
        EntityTransaction txn = em.getTransaction();

        t.setClientCollectionCode(code);
        txn.begin();


        c.setClientCollectionCode(t);
        c.setId(id);
        c.setEmail(email);
        c.setFirstName(fName);
        c.setLastName(lName);
        c.setPhone(phone);

        em.persist(c);
        txn.commit();
        return "\nClient Added";
    }


    public void remClient(int id){
        EntityManager em = emf.createEntityManager();
        Client client = em.find(Client.class,id);
        em.getTransaction().begin();
        em.remove(client);
        em.getTransaction().commit();
    }


    public List<Client> findClientByID(String id){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT e FROM Client e WHERE e.id = :id");
        query.setParameter("id",id);
        List<Client> result = query.getResultList();
        result.forEach(System.out::println);
//        em.close();

        return result;
    }
    
    public List<Client> findClients(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamples");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT e FROM Client e ");
        List<Client> results = query.getResultList();
        results.forEach(System.out::println);
        return results;

    }

    public List <Client> sortName(List <Client> results){
        Collections.sort(results, Comparator.comparing(Client::getFirstName));
        return results;
    }



}
