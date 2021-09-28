package models;

/**
 * Class to model a name
 * @author Micheal O' Sullivan
 * 
 */

import javax.persistence.*;



@MappedSuperclass
/**
 * Applies to the entities that inherit from Name
 * https://docs.jboss.org/hibernate/jpa/2.1/api/javax/persistence/MappedSuperclass.html
 * 
 */

 
public class Name {
    private String firstName;
    private String lastName;

    public Name(){

    	/**
    	 * Construtor of class Name
    	 */
    }
    
    /**
     * 
     * @param myFirstName
     * @param myLastName
     */

    public Name(String myFirstName, String myLastName){
        setFirstName(myFirstName);
        setLastName(myLastName);
    }

    /**
     * Get first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set first name
     * @param A string will represent the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Get last name
     * @return last name
     */

    public String getLastName() {
        return lastName;
    }
    
    /**
     * Set last name
     * @param A string will represent the last name
     */

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    /**
     * @return a string with last name and full name
     * If first name = null, name not created and message no name is returned
     */

    public String toString(){
        if(getFirstName()==null){
            return "no name";
        }
        return "Name: "+getFirstName()+" "+getLastName();
    }

}
