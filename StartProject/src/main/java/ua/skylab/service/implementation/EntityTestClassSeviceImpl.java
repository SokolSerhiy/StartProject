package ua.skylab.service.implementation;

import org.springframework.stereotype.Service;

import ua.skylab.entity.EntityTestClass;
import ua.skylab.service.EntityTestClassSevice;

@Service("name")
public class EntityTestClassSeviceImpl extends PageAndCrudServiceImpl<EntityTestClass, Integer> implements EntityTestClassSevice{

	private static final int[] ARRAY = {5, 10, 20};
	
	public EntityTestClassSeviceImpl() {
		super(ARRAY, "id", "name");
	}
}
