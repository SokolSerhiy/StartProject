package skylab.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import skylab.dao.PersonDao;
import skylab.entity.Person;
import skylab.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonDao personDao;
	
	public void create(Person person) {
		personDao.create(person);
	}

	public void update(Person person) {
		personDao.update(person);
	}

	public List<Person> getAll() {
		return personDao.getAll();
	}
}
