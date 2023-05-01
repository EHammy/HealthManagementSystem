package health.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "routines")
public class Routines {
	@Id
    @GeneratedValue
    private int id;
	private String routineName;
    private String exercise1;
    private String exercise2;
    private String exercise3;
    private String exercise4;
    private String exercise5;
    

    public Routines(int id, String routineName, String exercise1, String exercise2, String exercise3, String exercise4, String exercise5) {
    	this.id = id;
    	this.routineName = routineName;
    	this.exercise1 = exercise1;
        this.exercise2 = exercise2;
        this.exercise3 = exercise3;
        this.exercise4 = exercise4;
        this.exercise5 = exercise5;
       
    }
    public Routines(String routineName, String exercise1, String exercise2, String exercise3, String exercise4, String exercise5) {
    	this.routineName = routineName;
    	this.exercise1 = exercise1;
        this.exercise2 = exercise2;
        this.exercise3 = exercise3;
        this.exercise4 = exercise4;
        this.exercise5 = exercise5;
       
    }
   

   
    
}
