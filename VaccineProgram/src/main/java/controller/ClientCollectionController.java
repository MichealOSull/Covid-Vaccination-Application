package controller;

/**
 * Controller class for ClientCollection
 * @author Micheal O' Sullivan
 * 
 */

import models.ClientCollection;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ClientCollectionController {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamples");

    public void addClientCollection(ClientCollection clientCollection, String clientCollectionText){

        clientCollection.setClientCollectionCode(clientCollectionText);
        clientCollection.printClientCollection();

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(clientCollection);
        em.getTransaction().commit();
    }
    
    public List<ClientCollection> sortCode(List <ClientCollection> res){
        Collections.sort(res, Comparator.comparing(ClientCollection::getClientCollectionCode));
        return res;
    }




    public List <ClientCollection> getClientCollections(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<ClientCollection> res = em.createQuery("select clientCollectionCode from ClientCollection clientCollectionCode", ClientCollection.class).getResultList();
        System.out.println(res);
        em.getTransaction().commit();

        return res;
    }
}
