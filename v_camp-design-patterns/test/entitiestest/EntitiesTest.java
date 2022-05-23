package entitiestest;

import org.junit.Assert;
import org.junit.Test;

import v_camp.builder.BookBuilder;
import v_camp.builder.ComputerBuilder;
import v_camp.builder.ShoeBuilder;
import v_camp.builder.SkirtBuilder;
import v_camp.builder.entities.Book;
import v_camp.builder.entities.Computer;
import v_camp.builder.entities.Product;
import v_camp.builder.entities.ProductType;
import v_camp.builder.entities.Shoe;
import v_camp.builder.entities.Skirt;

public class EntitiesTest {
	
	@Test(expected = NullPointerException.class)
	public void ExceptionThrowWhenTryingToCreateEmptyProduct() {
		Product product = new Product(0, 0, 0, null);
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionThrownWhenTryingToCreateEmptyBook() {
		//Setup
		Book book = new Book(0, 0, 0, null, null, 0);
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionThrownWhenTryingToCreateEmptyComputer() {
		//Setup
		Computer computer = new Computer(0, 0, 0, null, null, null);
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionThrownWhenTryingToCreateEmptyShoe() {
		//Setup
		Shoe shoe = new Shoe(0, 0, 0, null, 0, null);
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionThrowWhenTryingToCreateEmptySkirt() {
		//Setup
		Skirt skirt = new Skirt(0, 0, 0, null, 0, null);
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionThrownWhenCreatingBookWithMissingFields() {
		//Setup
		Book book = new Book(1.0, 49.99, 1, ProductType.Book, null, 400);
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionThrownWhenCreatingComputerWithMissingFields() {
		//Setup
		Computer pc = new Computer(3.5, 399.99, 2, ProductType.Computer, null, "GT 710");
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionThrownWhenCreatingShoeWithMissingFields() {
		//Setup
		Shoe shoe = new Shoe(0.8, 69.99, 3, ProductType.Shoe, 0, "Leather");
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionThrowWhenCreatingSkirtWithMissingFields() {
		//Setup
		Skirt skirt = new Skirt(0.2, 29.99, 4, ProductType.Skirt, 0, "Linux");
	}
	
	@Test
	public void GetValidProduct() {
		Product product = new Product(1.0, 10, 10, ProductType.Skirt);
		
		product.setAvailable(false);
		product.setPrice(200.00);
		product.setSku(20);
		product.setWeight(20.0);
	}
	
	@Test
	public void GetValidBookWhenInfoIsCorrect() {
		//Setup
		Book book = new Book(1.0, 10.0, 1, ProductType.Book, "Interesting Book.", 400);
		
		//Action
		Assert.assertTrue(book.getAvailable() == true);
		Assert.assertTrue(book.getWeight() == 1.0);
		Assert.assertTrue(book.getPrice() == 10.0);
		Assert.assertTrue(book.getSku() == 1);
		Assert.assertTrue(book.getProductType() == ProductType.Book);
		Assert.assertTrue(book.getBookName() == "Interesting Book.");
		Assert.assertTrue(book.getPageCount() == 400);
	}
	
	@Test
	public void GetValidComputerWhenInfoIsCorrect() {
		//Setup
		Computer computer = new Computer(3.0, 399.99, 2, ProductType.Computer, "i5 7700k", "Rx 590 8Gb");
		
		//Action
		Assert.assertTrue(computer.getAvailable() == true);
		Assert.assertTrue(computer.getWeight() == 3.0);
		Assert.assertTrue(computer.getPrice() == 399.99);
		Assert.assertTrue(computer.getSku() == 2);
		Assert.assertTrue(computer.getProductType() == ProductType.Computer);
		Assert.assertTrue(computer.getProcessor() == "i5 7700k");
		Assert.assertTrue(computer.getGraphicsCard() == "Rx 590 8Gb");
	}
	
	@Test
	public void GetValidShoeWhenInfoIsCorrect() {
		//Setup
		Shoe shoe = new Shoe(0.9, 49.99, 3, ProductType.Shoe, 35.5, "Leather");
		
		//Action
		Assert.assertTrue(shoe.getAvailable() == true);
		Assert.assertTrue(shoe.getWeight() == 0.9);
		Assert.assertTrue(shoe.getPrice() == 49.99);
		Assert.assertTrue(shoe.getSku() == 3);
		Assert.assertTrue(shoe.getProductType() == ProductType.Shoe);
		Assert.assertTrue(shoe.getSizeUS() == 35.5);
		Assert.assertTrue(shoe.getMaterial() == "Leather");
	}
	
	@Test
	public void GetValidSkirtWhenInfoIsCorrect() {
		//Setup
		Skirt skirt = new Skirt(0.2, 39.99, 4, ProductType.Skirt, 38, "Gucci");
		
		//Action
		Assert.assertTrue(skirt.getAvailable() == true);
		Assert.assertTrue(skirt.getWeight() == 0.2);
		Assert.assertTrue(skirt.getPrice() == 39.99);
		Assert.assertTrue(skirt.getSku() == 4);
		Assert.assertTrue(skirt.getProductType() == ProductType.Skirt);
		Assert.assertTrue(skirt.getSize() == 38);
		Assert.assertTrue(skirt.getBrand() == "Gucci");
	}
}
