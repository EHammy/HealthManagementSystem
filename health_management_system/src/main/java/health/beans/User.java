package health.beans;




import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	@Autowired
	private Address address;
	private String username;
    private String password;
	
	public User(String name) {
	}
	
	public User(String name, Address address) {
		
	}
	
	public User(Address address) {
		
	}

}
