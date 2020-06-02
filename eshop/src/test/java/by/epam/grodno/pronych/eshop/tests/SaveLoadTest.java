package by.epam.grodno.pronych.eshop.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import by.epam.grodno.pronych.eshop.controller.ProductController;
import by.epam.grodno.pronych.eshop.controller.UserController;
import by.epam.grodno.pronych.eshop.service.dts.ProductMsg;
import by.epam.grodno.pronych.eshop.service.dts.UserMsg;
import by.epam.grodno.pronych.eshop.tests.config.TestBeanConfig;

import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestBeanConfig.class})
public class SaveLoadTest {
	
	@Autowired
	private ProductController productController;
	
	@Autowired
	private UserController userController;

	int newId = 0;
	ProductMsg productMsg;
	@Before
	public void testBT() {
		productMsg = new ProductMsg();
		productMsg.setName("TestProduct");
		productMsg.setPrice(22);
		ResponseEntity<Integer> reId =  productController.add(productMsg);
		newId = (Integer)reId.getBody();
	}
	
	@After
	public void testAT() {
		ResponseEntity<?> deleteResult =  productController.delete(newId);
		assertEquals(deleteResult.getStatusCode(), HttpStatus.OK);		
	}
	
	@Test
	public void testSaveAndLoad() {
		productMsg.setId(newId);
		
		ResponseEntity<?> getResult =  productController.get(newId);
		assertNotNull(getResult);
		assertEquals(getResult.getStatusCode(), HttpStatus.OK);		
		assertEquals(getResult.getBody(), productMsg);		
		
	}
	
	@Test
	public void testSetToBlackList() {
		UserMsg userMsg = new UserMsg();
		userMsg.setId(1);
		userController.setToBlackList(userMsg);
		
		boolean isUserInBlackList = userController.isUserInBlackList(userMsg);
		System.out.println(isUserInBlackList);
		assertEquals(isUserInBlackList,true);
		
		userController.removeFromBlackList(userMsg);
		isUserInBlackList = userController.isUserInBlackList(userMsg);
		System.out.println(isUserInBlackList);
		assertEquals(isUserInBlackList,false);
	}

}
