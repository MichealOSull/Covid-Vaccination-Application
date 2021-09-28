package models;

/**
 * Class to model a Client
 * @author Micheal O' Sullivan
 * 
 */

import javax.persistence.*;
import java.sql.Date;



@Entity
@Table(name = "client", schema = "vaccinedata")
public class Client extends Person{

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name="code")
    private ClientCollection code;

    public Client(){

    }

    public Client (String id, String firstName,String lastName, String phone, String email, ClientCollection location){
        setId(id);
        setClientCollectionCode(code);
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public ClientCollection getClientCollectionCode() {
        return code;
    }


    public void setClientCollectionCode(ClientCollection code) {
        this.code = code;
    }

    public String toString(){
        return "ID:  " + id + "\nName:  "+ getFirstName() + " " + getLastName() + "\nPhone:  " + getPhone() + "\nEmail:  " + getEmail();
    }

}
