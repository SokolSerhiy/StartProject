package skylab.service;

import java.util.List;

import skylab.entity.Person;

public interface PersonService {
	
	void create (Person person);
	void update (Person person);
	List<Person> getAll();
	
}
