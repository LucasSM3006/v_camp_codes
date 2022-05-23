package compositetest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import v_camp.builder.entities.Book;
import v_camp.builder.entities.ProductType;
import v_camp.composite.Cart;
import v_camp.facade.Order;
import v_camp.factory.Aero;
import v_camp.factory.Road;
import v_camp.factory.Shipping;
import v_camp.iterator.OrderList;
import v_camp.observer.CartObserver;
import v_camp.singleton.ProductInventory;

public class CompositeTest {
	@Before
	public void setup() {
		ProductInventory inv = ProductInventory.getInstance();
		inv.resetInventory();
	}
	
	@Test
	public void AttachAndDettachObserver() {
		Cart cart = new Cart();
		CartObserver obs = new CartObserver();
		
		cart.attach(obs);
		cart.dettach(obs);
	}
	
	@Test
	public void ShouldAddProductToCart() {
		Cart cart = new Cart();
		Book book = new Book(1.0, 10.0, 1, ProductType.Book, "Interesting Book.", 400);
		
		cart.addProductToCart(book);
		assertFalse(cart.getProducts().isEmpty());
	}
	
	@Test
	public void ShouldNotAddNullProductToCart() {
		Cart cart = new Cart();
		
		cart.addProductToCart(null);
		assertTrue(cart.getProducts().isEmpty());
	}
	
	@Test
	public void ShouldNotAddProductIfNotAvailable() {
		Cart cart = new Cart();
		Book book = new Book(1.0, 10.0, 1, ProductType.Book, "Interesting Book.", 400);
		book.setAvailable(false);
		
		cart.addProductToCart(book);
		assertTrue(cart.getProducts().isEmpty());
	}
	
	@Test
	public void ShouldAlertObserverWhenProductIsAdded() {
		CartObserver obs = new CartObserver();
		Cart cart = new Cart();
		Book book = new Book(1.0, 10.0, 1, ProductType.Book, "Interesting Book.", 400);
		cart.attach(obs);
		
		cart.addProductToCart(book);
		assertFalse(cart.getProducts().isEmpty());
	}
	
	@Test
	public void ShouldRemoveProductsFromCart() {
		ProductInventory inventory = ProductInventory.getInstance();
		Cart cart = new Cart();
		Book book0 = new Book(1.0, 10.0, 1, ProductType.Book, "Interesting Book.", 400);
		Book book1 = new Book(1.0, 10.0, 1, ProductType.Book, "Interesting Book.", 400);
		inventory.addProduct(book0);
		inventory.addProduct(book1);
		
		cart.addProductToCart(inventory.getProduct(1));
		cart.addProductToCart(inventory.getProduct(1));
		
		assertFalse(cart.getProducts().isEmpty());
		cart.removeProductFromCart(1, 2);
		assertTrue(cart.getProducts().isEmpty());
	}
	
	@Test
	public void ShouldNotRemoveMoreProductsThatMatchTheSkuThanInCart() {
		ProductInventory inventory = ProductInventory.getInstance();
		Cart cart = new Cart();
		Book book0 = new Book(1.0, 10.0, 1, ProductType.Book, "Interesting Book.", 400);
		Book book1 = new Book(1.0, 10.0, 2, ProductType.Book, "Interesting Book.", 400);
		Book book2 = new Book(1.0, 10.0, 2, ProductType.Book, "Interesting Book.", 400);
		Book book3 = new Book(1.0, 10.0, 2, ProductType.Book, "Interesting Book.", 400);
		inventory.addProduct(book0);
		inventory.addProduct(book1);
		inventory.addProduct(book2);
		inventory.addProduct(book3);
		
		cart.addProductToCart(inventory.getProduct(1));
		cart.addProductToCart(inventory.getProduct(2));
		cart.addProductToCart(inventory.getProduct(2));
		cart.addProductToCart(inventory.getProduct(2));
		
		assertFalse(cart.getProducts().isEmpty());
		cart.removeProductFromCart(1, 2);
		assertFalse(cart.getProducts().isEmpty());
	}
	
	@Test
	public void ShouldAlertObserversWhenProductIsRemoved() {
		CartObserver obs = new CartObserver();
		ProductInventory inventory = ProductInventory.getInstance();
		Cart cart = new Cart();
		Book book0 = new Book(1.0, 10.0, 1, ProductType.Book, "Interesting Book.", 400);
		
		cart.attach(obs);
		inventory.addProduct(book0);
		cart.addProductToCart(inventory.getProduct(1));
		
		cart.removeProductFromCart(1, 2);
		assertTrue(cart.getProducts().isEmpty());
	}
	
	@Test
	public void ShouldNotRemoveAnythingIfQuantityParameterSetToZero() {
		CartObserver obs = new CartObserver();
		ProductInventory inventory = ProductInventory.getInstance();
		Cart cart = new Cart();
		Book book0 = new Book(1.0, 10.0, 1, ProductType.Book, "Interesting Book.", 400);
		
		cart.attach(obs);
		inventory.addProduct(book0);
		cart.addProductToCart(inventory.getProduct(1));
		
		cart.removeProductFromCart(1, 0);
		assertFalse(cart.getProducts().isEmpty());
	}
	
	@Test
	public void ShouldGetRightAmountOfProductsAsInt() {
		Cart cart = new Cart();
		Book book0 = new Book(1.0, 10.0, 1, ProductType.Book, "Interesting Book.", 400);
	
		cart.addProductToCart(book0);
		
		assertTrue(1 == cart.getAmountOfProductsInCart());
	}
	
	@Test
	public void ShouldGetCorrectTotalPrice() {
		Cart cart = new Cart();
		Book book0 = new Book(1.0, 10.0, 1, ProductType.Book, "Interesting Book.", 400);
		Book book1 = new Book(1.0, 11.0, 1, ProductType.Book, "Interesting Book.", 400);
	
		cart.addProductToCart(book0);
		cart.addProductToCart(book1);
		
		assertTrue(21.0 == cart.getTotal());
	}
	
	@Test
	public void ShouldGetCorrectTotalPricePlusShipping() {
		Cart cart = new Cart();
		Book book0 = new Book(1.0, 10.0, 1, ProductType.Book, "Interesting Book.", 400);
		Book book1 = new Book(1.0, 10.0, 1, ProductType.Book, "Interesting Book.", 400);
	
		cart.addProductToCart(book0);
		cart.addProductToCart(book1);
		
		assertTrue(cart.getTotalPlusShipping() == 24.0); //10 + 10 = 20 || 20 + (N of products) = 22 || 22 + 10% of 20 = 24.
	}
	
	@Test
	public void ShouldGetCorrectShippingValueIfPriceBelowSevenNinetyNine() {
		Cart cart = new Cart();
		Book book0 = new Book(1.0, 6.0, 1, ProductType.Book, "Interesting Book.", 400);
	
		cart.addProductToCart(book0);
		
		assertTrue(cart.getTotalPlusShipping() == 14.99);
	}
	
	@Test
	public void ShouldGetCorrectWeight() {
		Cart cart = new Cart();
		Book book0 = new Book(1.0, 99.99, 1, ProductType.Book, "Interesting Book.", 400);
		Book book1 = new Book(1.0, 0.01, 1, ProductType.Book, "Interesting Book, but lighter.", 1);
	
		cart.addProductToCart(book0);
		cart.addProductToCart(book1);
		
		assertTrue(cart.getWeight() == 2.0);
	}
	
	@Test
	public void ShouldGetAeroShippingIfWeightBelow10() {
		//Setup
		Cart cart = new Cart();
		Book book0 = new Book(1.0, 99.99, 1, ProductType.Book, "Interesting Book.", 400);
		cart.addProductToCart(book0);
		
		//Action
		Shipping shipping = cart.getShippingMethod();
		assertTrue(shipping instanceof Aero);
	}
	
	@Test
	public void ShouldGetRoadShippingIfWeightAbove10() {
		//Setup
		Book book0 = new Book(10.1, 10.0, 1, ProductType.Book, "A really heavy book.", 400);
		Cart cart = new Cart();
		cart.addProductToCart(book0);
		
		//Action
		Shipping shipping = cart.getShippingMethod();
		assertTrue(shipping instanceof Road);
	}
	
}
