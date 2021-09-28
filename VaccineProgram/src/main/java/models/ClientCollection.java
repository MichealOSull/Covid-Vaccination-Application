package models;

/**
 * Class to model Client Collection
 * @author Micheal O' Sullivan
 * 
 */

import org.eclipse.persistence.annotations.Array;
import org.eclipse.persistence.annotations.ExcludeDefaultMappings;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Entity
@Table(name = "clientcollection", schema = "vaccinedata")
public class ClientCollection {
    @Id
    private String clientCollectionCode;

    @OneToMany
    private List<Client> clients=new ArrayList<>();


    @OneToMany
    private  List<FullVaccine> fullVaccines=new ArrayList<>();


//
    public ClientCollection(){}

    public ClientCollection(String clientCollectionCode){
        setClientCollectionCode(clientCollectionCode);
    }

    public String getClientCollectionCode() {
        return clientCollectionCode;
    }

    public void setClientCollectionCode(String code) {
        this.clientCollectionCode = code;
    }

    public List<FullVaccine> getFullVaccine() {
        return fullVaccines;
    }

    public void add(Client c){
        clients.add(c);
    }

    public void remove(Client c){
        clients.remove(c);
    }

    public List<Client> getClients() {
        return clients;
    }

    public String toString(){
        return clientCollectionCode;
    }

    public void printClientCollection(){
        System.out.println(toString()+"\n");
    }
}
