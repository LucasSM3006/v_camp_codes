package singletontest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import v_camp.builder.entities.Book;
import v_camp.builder.entities.Product;
import v_camp.builder.entities.ProductType;
import v_camp.singleton.ProductInventory;

public class SingletonTest {
	@Before
	public void setup() {
		ProductInventory inv = ProductInventory.getInstance();
		inv.resetInventory();
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionWhenNoProductsMatchSKU() {
		//SEtup
		ProductInventory inventory = ProductInventory.getInstance();
		Book book = new Book(1.2, 49.99, 1, ProductType.Book, "Coding Adventures", 550);
		Book book2 = new Book(1.2, 49.99, 1, ProductType.Book, "Coding Adventures Vol 2", 550);
		inventory.addProduct(book2);
		inventory.addProduct(book);
		
		//Action
		inventory.getProduct(2);
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionWhenListEmpty() {
		//Setup
		ProductInventory inventory = ProductInventory.getInstance();
		
		//Action
		inventory.getProduct(0);
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionWhenAddingNullProduct() {
		//Setup
		ProductInventory inventory = ProductInventory.getInstance();
		
		//Action
		inventory.addProduct(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionWhenTryingToRemoveProductsAndInventoryIsEmpty() {
		//Setup
		ProductInventory inventory = ProductInventory.getInstance();
		
		//Action
		inventory.removeProductsFromStock(3, 1);
		System.out.println(inventory.getListOfProducts());
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionWhenTryingBlockProductOnStockAndStockIsEmpty() {
		//Setup
		ProductInventory inventory = ProductInventory.getInstance();
		inventory.blockProductsFromStock(1, 1);
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionWhenUnblockingProductsButStockIsEmpty() {
		//Setup
		ProductInventory inventory = ProductInventory.getInstance();
		inventory.unblockProductsFromStock(1, 1);
	}
	
	@Test
	public void GetCorrectSingletonInstance() {
		//Setup
		ProductInventory inventory = ProductInventory.getInstance();
		ProductInventory inventory2 = ProductInventory.getInstance();
		ProductInventory inventory3 = ProductInventory.getInstance();
		
		//Action
		Assert.assertTrue(inventory == inventory2);
		Assert.assertTrue(inventory2 == inventory3);
		Assert.assertTrue(inventory3 == inventory);
	}
	
	@Test
	public void AddProductToInventory() {
		//Setup
		ProductInventory inventory = ProductInventory.getInstance();
		Book book = new Book(1.0, 2.0, 1, ProductType.Book, "BookWorm Adv", 400);
		
		//Action
		inventory.addProduct(book);
	}
	
	
	@Test
	public void GetRightList() {
		//Setup
		ProductInventory inventory = ProductInventory.getInstance();
		Book book = new Book(1.0, 2.0, 1, ProductType.Book, "BookWorm Adv", 400);
		
		//Action
		inventory.addProduct(book);
		List<Product> products = inventory.getListOfProducts();
	}
	
	@Test
	public void GetAProductWhenAvailable() {
		//SEtup
		ProductInventory inventory = ProductInventory.getInstance();
		Book book0 = new Book(1.2, 49.99, 1, ProductType.Book, "Coding Adventures", 550);
		Book book1 = new Book(1.2, 49.99, 1, ProductType.Book, "Coding Adventures Vol 2", 550);
		Book book2 = new Book(1.2, 49.99, 1, ProductType.Book, "Coding Adventures Vol 2", 550);
		book1.setAvailable(false);
		book2.setAvailable(false);
		inventory.addProduct(book0);
		inventory.addProduct(book1);
		
		//Action
		Product book3 = inventory.getProduct(1);
		assertTrue(book3.getAvailable() == true);
		assertTrue(book3.getPrice() == 49.99);
		assertTrue(book3 == book0);
	}
	
	@Test
	public void SuccessfullyRemoveProductFromInventory() {
		//Setup
		ProductInventory inventory = ProductInventory.getInstance();
		Book book0 = new Book(1.2, 49.99, 1, ProductType.Book, "Coding Adventures", 550);
		book0.setAvailable(false);
		inventory.addProduct(book0);
		
		inventory.removeProductsFromStock(1, 1);
		assertTrue(inventory.getListOfProducts().isEmpty() == true);
	}
	
	@Test
	public void TriedToRemoveMoreThanPossible() {
		//Setup
		ProductInventory inventory = ProductInventory.getInstance();
		Book book0 = new Book(1.2, 49.99, 1, ProductType.Book, "Coding Adventures", 550);
		book0.setAvailable(false); //Necessary for logic.
		inventory.addProduct(book0);
		
		//Action
		inventory.removeProductsFromStock(1, 2);
		assertTrue(inventory.getListOfProducts().isEmpty() == true);
	}
	
	@Test
	public void BlockProductOnStock() {
		//Setup
		ProductInventory inventory = ProductInventory.getInstance();
		Book book0 = new Book(1.2, 49.99, 1, ProductType.Book, "Coding Adventures", 550);
		inventory.addProduct(book0);
		inventory.blockProductsFromStock(1, 1);
		
		//Action
		assertFalse(book0.getAvailable());
	}
	
	@Test
	public void UnblockProductsFromStock() {
		//Setup
		ProductInventory inventory = ProductInventory.getInstance();
		Book book0 = new Book(1.2, 49.99, 1, ProductType.Book, "Coding Adventures", 550);
		book0.setAvailable(false);
		inventory.addProduct(book0);
		
		//Action
		inventory.unblockProductsFromStock(1, 1);
		assertTrue(book0.getAvailable());
	}
	
	@Test
	public void AllIfBranchesForUnblockProducts() {
		//Setup
		ProductInventory inventory = ProductInventory.getInstance();
		Book book0 = new Book(1.2, 49.99, 1, ProductType.Book, "Coding Adventures", 550);
		Book book1 = new Book(1.2, 49.99, 1, ProductType.Book, "Coding Adventures", 550);
		Book book2 = new Book(1.2, 49.99, 1, ProductType.Book, "Coding Adventures", 550);
		
		book2.setAvailable(false);
		
		inventory.addProduct(book0);
		inventory.addProduct(book1);
		inventory.addProduct(book2);
		
		//Action
		inventory.unblockProductsFromStock(1, 1);
		inventory.unblockProductsFromStock(1, 0);
	}
	
	@Test
	public void SkuIfBranchOnUnblock() {
		//Setup
		ProductInventory inventory = ProductInventory.getInstance();
		Book book0 = new Book(1.2, 49.99, 1, ProductType.Book, "Coding Adventures", 550);
		Book book1 = new Book(1.2, 49.99, 1, ProductType.Book, "Coding Adventures", 550);
		Book book2 = new Book(1.2, 49.99, 1, ProductType.Book, "Coding Adventures", 550);
		
		book2.setAvailable(false);
		
		inventory.addProduct(book0);
		inventory.addProduct(book1);
		inventory.addProduct(book2);
		
		//Action
		inventory.unblockProductsFromStock(0, 1);
	}
	
	@Test
	public void IfBranchOnGetProduct() {
		//SEtup
		ProductInventory inventory = ProductInventory.getInstance();
		Book book0 = new Book(1.2, 49.99, 1, ProductType.Book, "Coding Adventures", 550);
		Book book1 = new Book(1.2, 49.99, 1, ProductType.Book, "Coding Adventures Vol 2", 550);
		book1.setAvailable(false);
		inventory.addProduct(book0);
		inventory.addProduct(book1);
		
		//Action
		Product book3 = inventory.getProduct(1);
		assertTrue(book3.getAvailable() == true);
		assertTrue(book3.getPrice() == 49.99);
		assertTrue(book3 == book0);
	}
}
