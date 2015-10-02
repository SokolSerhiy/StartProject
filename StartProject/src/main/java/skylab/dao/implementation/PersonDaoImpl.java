package skylab.dao.implementation;

import org.springframework.stereotype.Repository;

import skylab.dao.PersonDao;
import skylab.entity.Person;

@Repository
public class PersonDaoImpl extends GeneralDaoImpl<Person, Integer> implements PersonDao{

	public PersonDaoImpl() {
		super(Person.class);
	}
}
