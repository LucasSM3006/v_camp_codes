package buildertest;

import java.util.ArrayList;
import java.util.List;

import v_camp.builder.BookBuilder;
import v_camp.builder.Builder;
import v_camp.builder.ComputerBuilder;
import v_camp.builder.ShoeBuilder;
import v_camp.builder.SkirtBuilder;
import v_camp.builder.entities.Book;
import v_camp.builder.entities.Computer;
import v_camp.builder.entities.Product;
import v_camp.builder.entities.Shoe;
import v_camp.builder.entities.Skirt;
import v_camp.client.ClientProducts;
import v_camp.composite.Cart;
import v_camp.facade.Order;
import v_camp.factory.AeroShippingCreator;
import v_camp.factory.RoadShippingCreator;
import v_camp.factory.Shipping;
import v_camp.factory.ShippingCreator;
import v_camp.iterator.OrderList;
import v_camp.observer.BackOffice;
import v_camp.observer.CartObserver;
import v_camp.observer.ShippingObserver;
import v_camp.observer.StatusObserver;
import v_camp.singleton.ProductInventory;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BuilderTester {
	
	@Test(expected = NullPointerException.class)
	public void ExceptionThrownWhenTryingToCreateEmptyBook() {
		//Setup
		BookBuilder bbuilder = new BookBuilder();
		
		//Action
		Book book = bbuilder.getResult();
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionThrownWhenTryingToCreateEmptyComputer() {
		//Setup
		ComputerBuilder cbuilder = new ComputerBuilder();
		
		//Action
		Computer pc = cbuilder.getResult();
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionThrownWhenTryingToCreateEmptyShoe() {
		//Setup
		ShoeBuilder shbuilder = new ShoeBuilder();
		
		//Action
		Shoe shoe = shbuilder.getResult();
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionThrowWhenTryingToCreateEmptySkirt() {
		//Setup
		SkirtBuilder skbuilder = new SkirtBuilder();
		
		//Action
		Skirt skirt = skbuilder.getResult();
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionThrownWhenCreatingBookWithMissingFields() {
		//Setup
		BookBuilder bbuilder = new BookBuilder();
		bbuilder.setSku(1);
		bbuilder.setPrice(10.0);
		bbuilder.setWeight(1.0);
		bbuilder.setBookName(null);
		bbuilder.setPageCount(400);
		
		//Action
		Book book = bbuilder.getResult();
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionThrownWhenCreatingComputerWithMissingFields() {
		//Setup
		ComputerBuilder cbuilder = new ComputerBuilder();
		cbuilder.setPrice(699.99);
		cbuilder.setSku(1);
		cbuilder.setWeight(1.0);
		cbuilder.setGpu("RTX 3080");
		cbuilder.setCpu(null);
		
		//Action
		Computer pc = cbuilder.getResult();
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionThrownWhenCreatingShoeWithMissingFields() {
		//Setup
		ShoeBuilder shbuilder = new ShoeBuilder();
		shbuilder.setMaterial("Leather");
		shbuilder.setSize(0);
		shbuilder.setSku(1);
		shbuilder.setWeight(1.0);
		shbuilder.setSize(43);
		
		//Action
		Shoe shoe = shbuilder.getResult();
	}
	
	@Test(expected = NullPointerException.class)
	public void ExceptionThrowWhenCreatingSkirtWithMissingFields() {
		//Setup
		SkirtBuilder skbuilder = new SkirtBuilder();
		skbuilder.setBrand("Java");
		skbuilder.setSize(0);
		skbuilder.setPrice(10.0);
		skbuilder.setSize(43);
		skbuilder.setSku(1);
		
		//Action
		Skirt skirt = skbuilder.getResult();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ExceptionThrowWhenPriceOfBookIsSetAtZeroOrBelow() {
		//Setup
		BookBuilder bbuilder = new BookBuilder();
		bbuilder.setPrice(-1);
		
		//Action
		Book book = bbuilder.getResult();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ExceptionThrowWhenPriceOfComputerIsSetAtZeroOrBelow() {
		//Setup
		ComputerBuilder cbuilder = new ComputerBuilder();
		cbuilder.setPrice(-1);
		
		//Action
		Computer computer = cbuilder.getResult();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ExceptionThrowWhenPriceOfShoeIsSetAtZeroOrBelow() {
		//Setup
		ShoeBuilder shbuilder = new ShoeBuilder();
		shbuilder.setPrice(-1);
		
		//Action
		Shoe shoe = shbuilder.getResult();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ExceptionThrowWhenPriceOfSkirtIsSetAtZeroOrBelow() {
		//Setup
		SkirtBuilder skbuilder = new SkirtBuilder();
		skbuilder.setPrice(-1);
		
		//Action
		Skirt skirt = skbuilder.getResult();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ExceptionThrowWhenWeightOfBookIsSetAtZeroOrBelow() {
		//Setup
		BookBuilder bbuilder = new BookBuilder();
		bbuilder.setWeight(-1);
		
		//Action
		Book book = bbuilder.getResult();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ExceptionThrowWhenWeightOfComputerIsSetAtZeroOrBelow() {
		//Setup
		ComputerBuilder cbuilder = new ComputerBuilder();
		cbuilder.setWeight(-1);
		
		//Action
		Computer computer = cbuilder.getResult();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ExceptionThrowWhenWeightOfShoeIsSetAtZeroOrBelow() {
		//Setup
		ShoeBuilder shbuilder = new ShoeBuilder();
		shbuilder.setWeight(-1);
		
		//Action
		Shoe shoe = shbuilder.getResult();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ExceptionThrowWhenWeightOfSkirtIsSetAtZeroOrBelow() {
		//Setup
		SkirtBuilder skbuilder = new SkirtBuilder();
		skbuilder.setWeight(-1);
		
		//Action
		Skirt skirt = skbuilder.getResult();
	}
	
	@Test
	public void GetValidBookWhenInfoIsCorrect() {
		//Setup
		BookBuilder bbuilder = new BookBuilder();
		bbuilder.setBookName("Bookworm Adventures");
		bbuilder.setPageCount(400);
		bbuilder.setPrice(10.0);
		bbuilder.setWeight(1.1);
		bbuilder.setSku(1);
		
		//Action
		Book book = bbuilder.getResult();
		assert(book.getAvailable() == true);
		assert(book.getBookName() == "Bookworm Adventures");
		assert(book.getPageCount() == 400);
		assert(book.getPrice() == 10.0);
		assert(book.getWeight() == 1.1);
		assert(book.getSku() == 1);
	}
	
	@Test
	public void GetValidComputerWhenInfoIsCorrect() {
		//Setup
		ComputerBuilder cbuilder = new ComputerBuilder();
		cbuilder.setCpu("Intel Core i9 9900k");
		cbuilder.setGpu("RTX 3080 Ti");
		cbuilder.setPrice(1199.99);
		cbuilder.setWeight(4.5);
		cbuilder.setSku(1);
		
		//Action
		Computer computer = cbuilder.getResult();
		assert(computer.getAvailable() == true);
		assert(computer.getProcessor() == "Intel Core i9 9900k");
		assert(computer.getGraphicsCard() == "RTX 3080 Ti");
		assert(computer.getPrice() == 1199.99);
		assert(computer.getWeight() == 4.5);
		assert(computer.getSku() == 1);
	}
	
	@Test
	public void GetValidShoeWhenInfoIsCorrect() {
		//Setup
		ShoeBuilder shbuilder = new ShoeBuilder();
		shbuilder.setMaterial("Leather");
		shbuilder.setSize(42);
		shbuilder.setPrice(45.0);
		shbuilder.setWeight(0.4);
		shbuilder.setSku(3);
		
		//Action
		Shoe shoe = shbuilder.getResult();
		assert(shoe.getAvailable() == true);
		assert(shoe.getMaterial() == "Leather");
		assert(shoe.getSizeUS() == 42);
		assert(shoe.getPrice() == 45.0);
		assert(shoe.getWeight() == 0.4);
		assert(shoe.getSku() == 3);
	}
	
	@Test
	public void GetValidSkirtWhenInfoIsCorrect() {
		//Setup
		SkirtBuilder skbuilder = new SkirtBuilder();
		skbuilder.setBrand("Java");
		skbuilder.setSize(36);
		skbuilder.setPrice(10.0);
		skbuilder.setWeight(0.2);
		skbuilder.setSku(4);
		
		//Action
		Skirt skirt = skbuilder.getResult();
		
		assert(skirt.getAvailable() == true);
		assert(skirt.getBrand() == "Java");
		assert(skirt.getSize() == 36);
		assert(skirt.getPrice() == 10.0);
		assert(skirt.getWeight() == 0.2);
		assert(skirt.getSku() == 4);
	}
}