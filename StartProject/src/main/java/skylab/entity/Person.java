package skylab.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Size(min = 10, max = 12, message = "Phone number must be beetween 10 and 12")
	@Pattern(regexp="^[0-9]*$", message = "Phone number must have only numbers")
	private String phoneNumber;
//	@Pattern(regexp="^[à-ÿÀ-ßa-zA-Z]*$", message = "First name must have only letters")
	private String firstName;
//	@Pattern(regexp="^[à-ÿÀ-ßa-zA-Z]*$", message = "Last name must have only letters")
	private String lastName;
	@Pattern(regexp="^[a-zA-Z0-9\\._-]*@[a-zA-Z]*\\.[a-zA-Z]{2,3}$", message = "Wrong e-mail")
	private String eMail;

	public Person() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
}
