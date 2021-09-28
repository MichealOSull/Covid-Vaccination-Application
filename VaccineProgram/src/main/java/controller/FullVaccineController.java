package controller;

/**
 * Controller class for FullVaccine
 * @author Micheal O' Sullivan
 * 
 */

import models.FullVaccine;
import models.Client;
import models.ClientCollection;

import javax.persistence.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class FullVaccineController {

    public String addFullVaccine(String code, String vName,String vEff,String vDate, String vDate2, String vDose2){
        FullVaccine m = new FullVaccine();
        ClientCollection c = new ClientCollection();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamples");
        EntityManager em = emf.createEntityManager();
        EntityTransaction txn = em.getTransaction();

        List <FullVaccine> m2=findFullVaccineByCode(code);
        System.out.println(m2);

        {
            c.setClientCollectionCode(code);
            txn.begin();

            m.setClientCollectionCode(c);
            m.setVacName(vName);
            m.setEfficacy(vEff);
            m.setDate(vDate);
            
            if (vDose2 == "Yes") {
                m.setDose2(true);
                System.out.println("true");
            } else {
                m.setDose2(false);
            }
            System.out.println(m.toString() + m.getClientCollectionCode());
            m.setDate2(vDate2);
            em.persist(m);
            txn.commit();
            return "\nVaccine Added";
        }


    }



    public List<FullVaccine> findFullVaccineByCode(String code){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamples");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT e FROM FullVaccine e WHERE e.clientCollectionCode = :code");
        query.setParameter("code",new ClientCollection(code));
        List<FullVaccine> result = query.getResultList();
        result.forEach(System.out::println);
        return result;
    }
    
    public List<Client> findFullVaccineByName(String vName){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamples");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT e FROM FullVaccine e WHERE e.vName = :VACNAME");
        query.setParameter("vName",vName);
        List<Client> result = query.getResultList();
        result.forEach(System.out::println);
//        em.close();

        return result;
    }

    public List<FullVaccine> findFullVaccines(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamples");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT e FROM FullVaccine e ");
        List<FullVaccine> resultList = query.getResultList();
        resultList.forEach(System.out::println);
        return resultList;
    }
}
