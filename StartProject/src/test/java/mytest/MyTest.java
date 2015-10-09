package mytest;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import ua.skylab.entity.EntityTestClass;
import ua.skylab.service.EntityTestClassSevice;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
public class MyTest {

	@Autowired
	EntityTestClassSevice entityTestClassSevice;

	@Before
	public void before() {
		entityTestClassSevice.save(new EntityTestClass());
		entityTestClassSevice.save(new EntityTestClass());
		entityTestClassSevice.save(new EntityTestClass());
		entityTestClassSevice.save(new EntityTestClass());
		entityTestClassSevice.save(new EntityTestClass());
		entityTestClassSevice.save(new EntityTestClass());
		entityTestClassSevice.save(new EntityTestClass());
	}

	@After
	public void after() {
		List<EntityTestClass> list = entityTestClassSevice.getAll();
		for (EntityTestClass entityTestClass : list) {
			entityTestClassSevice.delete(entityTestClass.getId());
		}
	}

	@Test
	public void testCrudService() {
		List<EntityTestClass> list = entityTestClassSevice.getAll();
		EntityTestClass entity = list.get(0);
		entity.setName("Name");
		entityTestClassSevice.update(entity);
		entityTestClassSevice.delete(entity.getId());
		list = entityTestClassSevice.getAll();
		int expected = list.size();
		int actual = 6;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testPageAndCrudGetPageOne() {
		List<EntityTestClass> list = entityTestClassSevice.getPage("1");
		int expected = list.size();
		int actual = 5;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testPageAndCrudGetPageTwo() {
		List<EntityTestClass> list = entityTestClassSevice.getPage("1&2");
		int expected = list.size();
		int actual = 2;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testPageAndCrudGetPageTreeASC() {
		List<EntityTestClass> list = entityTestClassSevice
				.getPage("1&2&asc&id");
		int expected = list.size();
		int actual = 2;
		Assert.assertEquals(expected, actual);
		List<EntityTestClass> listSorted = new ArrayList<EntityTestClass>(list);
		PropertyComparator.sort(listSorted, new MutableSortDefinition("id",
				true, true));
		for (int i = 0; i < list.size(); i++) {
			Assert.assertEquals(listSorted.get(i).getId(), list.get(i).getId());
		}
	}

	@Test
	public void testPageAndCrudGetPageTreeDESC() {
		List<EntityTestClass> list = entityTestClassSevice
				.getPage("1&2&desc&id");
		int expected = list.size();
		int actual = 2;
		Assert.assertEquals(expected, actual);
		List<EntityTestClass> listSorted = new ArrayList<EntityTestClass>(list);
		PropertyComparator.sort(listSorted, new MutableSortDefinition("id",
				true, false));
		for (int i = 0; i < list.size(); i++) {
			Assert.assertEquals(listSorted.get(i).getId(), list.get(i).getId());
		}
	}

	@Test
	public void testPageAndCrudGetPageOutOfBound() {
		List<EntityTestClass> list = entityTestClassSevice.getPage("5");
		int expected = list.size();
		int actual = 2;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testPageAndCrudGetPage() {
		List<EntityTestClass> list = entityTestClassSevice.getPage();
		int expected = list.size();
		int actual = 5;
		Assert.assertEquals(expected, actual);
	}
}
