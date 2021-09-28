package models;

/**
 * Class to model a person
 * @author Micheal O' Sullivan
 * 
 */

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class Person extends Name {
    private String phone;
    private String email;

    public Person(){
    }


    public Person( String firstName, String lastName, String phone, String email){
        super(firstName,lastName);
        setPhone(phone);
        setEmail(email);
    }


    public String getPhone() {
        return phone;
    }



    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }



    public String toString(){
        return super.toString()+" ,Email:"+getEmail()+", "+getPhone();
    }


    public void printPerson(){
        System.out.println(toString());
    }
}
