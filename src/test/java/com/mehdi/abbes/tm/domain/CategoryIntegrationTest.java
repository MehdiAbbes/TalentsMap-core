package com.mehdi.abbes.tm.domain;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mehdi.abbes.tm.domain.Category;
import com.mehdi.abbes.tm.repository.CategoryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@Transactional
public class CategoryIntegrationTest {

	@Test
	public void testMarkerMethod() {
	}

	@Autowired
	private CategoryDataOnDemand dod;

	@Autowired
	CategoryRepository categoryRepository;

	@Test
	public void testCount() {
		Assert.assertNotNull(
				"Data on demand for 'Category' failed to initialize correctly",
				dod.getRandomCategory());
		long count = categoryRepository.count();
		Assert.assertTrue(
				"Counter for 'Category' incorrectly reported there were no entries",
				count > 0);
	}

	@Test
	public void testFind() {
		Category obj = dod.getRandomCategory();
		Assert.assertNotNull(
				"Data on demand for 'Category' failed to initialize correctly",
				obj);
		Long id = obj.getId();
		Assert.assertNotNull(
				"Data on demand for 'Category' failed to provide an identifier",
				id);
		obj = categoryRepository.findOne(id);
		Assert.assertNotNull(
				"Find method for 'Category' illegally returned null for id '"
						+ id + "'", obj);
		Assert.assertEquals(
				"Find method for 'Category' returned the incorrect identifier",
				id, obj.getId());
	}

	@Test
	public void testFindAll() {
		Assert.assertNotNull(
				"Data on demand for 'Category' failed to initialize correctly",
				dod.getRandomCategory());
		long count = categoryRepository.count();
		Assert.assertTrue(
				"Too expensive to perform a find all test for 'Category', as there are "
						+ count
						+ " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test",
				count < 250);
		List<Category> result = categoryRepository.findAll();
		Assert.assertNotNull(
				"Find all method for 'Category' illegally returned null",
				result);
		Assert.assertTrue(
				"Find all method for 'Category' failed to return any data",
				result.size() > 0);
	}

	@Test
	public void testFindEntries() {
		Assert.assertNotNull(
				"Data on demand for 'Category' failed to initialize correctly",
				dod.getRandomCategory());
		long count = categoryRepository.count();
		if (count > 20) {
			count = 20;
		}
		int firstResult = 0;
		int maxResults = (int) count;
		List<Category> result = categoryRepository.findAll(
				new org.springframework.data.domain.PageRequest(firstResult
						/ maxResults, maxResults)).getContent();
		Assert.assertNotNull(
				"Find entries method for 'Category' illegally returned null",
				result);
		Assert.assertEquals(
				"Find entries method for 'Category' returned an incorrect number of entries",
				count, result.size());
	}

	@Test
	public void testFlush() {
		Category obj = dod.getRandomCategory();
		Assert.assertNotNull(
				"Data on demand for 'Category' failed to initialize correctly",
				obj);
		Long id = obj.getId();
		Assert.assertNotNull(
				"Data on demand for 'Category' failed to provide an identifier",
				id);
		obj = categoryRepository.findOne(id);
		Assert.assertNotNull(
				"Find method for 'Category' illegally returned null for id '"
						+ id + "'", obj);
		boolean modified = dod.modifyCategory(obj);
		Integer currentVersion = obj.getVersion();
		categoryRepository.flush();
		Assert.assertTrue(
				"Version for 'Category' failed to increment on flush directive",
				((currentVersion != null) && (obj.getVersion() > currentVersion))
						|| !modified);
	}

	@Test
	public void testSaveUpdate() {
		Category obj = dod.getRandomCategory();
		Assert.assertNotNull(
				"Data on demand for 'Category' failed to initialize correctly",
				obj);
		Long id = obj.getId();
		Assert.assertNotNull(
				"Data on demand for 'Category' failed to provide an identifier",
				id);
		obj = categoryRepository.findOne(id);
		boolean modified = dod.modifyCategory(obj);
		Integer currentVersion = obj.getVersion();
		Category merged = categoryRepository.save(obj);
		categoryRepository.flush();
		Assert.assertEquals(
				"Identifier of merged object not the same as identifier of original object",
				merged.getId(), id);
		Assert.assertTrue(
				"Version for 'Category' failed to increment on merge and flush directive",
				((currentVersion != null) && (obj.getVersion() > currentVersion))
						|| !modified);
	}

	@Test
	public void testSave() {
		Assert.assertNotNull(
				"Data on demand for 'Category' failed to initialize correctly",
				dod.getRandomCategory());
		Category obj = dod.getNewTransientCategory(Integer.MAX_VALUE);
		Assert.assertNotNull(
				"Data on demand for 'Category' failed to provide a new transient entity",
				obj);
		Assert.assertNull("Expected 'Category' identifier to be null",
				obj.getId());
		categoryRepository.save(obj);
		categoryRepository.flush();
		Assert.assertNotNull(
				"Expected 'Category' identifier to no longer be null",
				obj.getId());
	}

	@Test
	public void testDelete() {
		Category obj = dod.getRandomCategory();
		Assert.assertNotNull(
				"Data on demand for 'Category' failed to initialize correctly",
				obj);
		Long id = obj.getId();
		Assert.assertNotNull(
				"Data on demand for 'Category' failed to provide an identifier",
				id);
		obj = categoryRepository.findOne(id);
		categoryRepository.delete(obj);
		categoryRepository.flush();
		Assert.assertNull("Failed to remove 'Category' with identifier '" + id
				+ "'", categoryRepository.findOne(id));
	}
}
