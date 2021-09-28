package models;

/**
 * Class to model a full vaccine
 * @author Micheal O' Sullivan
 * 
 */

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;


@Entity
@Table(name = "fullvaccine", schema = "vaccinedata")
public class FullVaccine extends Vaccination {

    private String date2;
    private Boolean dose2;

    @OneToOne
    @JoinColumn(name="clientCollectionCode")
    private ClientCollection clientCollectionCode;

    public FullVaccine(){

    }

    public FullVaccine (String vacName, String efficacy, String date, boolean dose2, String date2, ClientCollection clientCollectionCode){
        setDose2(dose2);
    	setDate2(date2);
        setClientCollectionCode(clientCollectionCode);
    }


    public Boolean getDose2() {
    	return dose2;
    }
    
    public void setDose2(Boolean dose2) {
    	this.dose2 = dose2;
    }
    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public ClientCollection getClientCollectionCode() {
        return clientCollectionCode;
    }

    public void setClientCollectionCode(ClientCollection clientCollectionCode) {
        this.clientCollectionCode = clientCollectionCode;
    }



    public String toString(){
        return "Vaccine Name: " + getVacName() + "\nEfficancy: " + getEfficacy();
    }




}
