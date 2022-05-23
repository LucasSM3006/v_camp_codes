package facadetest;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import v_camp.builder.entities.Book;
import v_camp.builder.entities.ProductType;
import v_camp.composite.Cart;
import v_camp.facade.Order;
import v_camp.factory.Aero;
import v_camp.factory.Road;
import v_camp.factory.Shipping;
import v_camp.observer.CartObserver;
import v_camp.observer.ShippingObserver;
import v_camp.observer.StatusObserver;
import v_camp.singleton.ProductInventory;

public class FacadeTest {
	@Before
	public void setup() {
		ProductInventory inv = ProductInventory.getInstance();
		inv.resetInventory();
		
		Cart cart = new Cart();
		Order order = new Order(cart);
		order.resetId();
	}
	
	@Test
	public void ShouldCreateOrder() {
		//Setup
		Cart cart = new Cart();
		Order order = new Order(cart);
	}
	
	@Test
	public void ShouldAttachAndDetachObserver() {
		//Setup
		ShippingObserver shippingObs = new ShippingObserver();
		StatusObserver statusObs = new StatusObserver();
		Cart cart = new Cart();
		Order order = new Order(cart);
		
		//Action
		order.attach(statusObs);
		order.dettach(statusObs);
		order.attach(shippingObs);
		order.dettach(shippingObs);
	}
	
	@Test
	public void ShouldChangeStatusToPaid() {
		//Setup
		Cart cart = new Cart();
		Order order = new Order(cart);
		
		//Action
		order.changeStatusToPaid();
		order.changeStatusToPaid(); //Second run does nothing due to already being paid.
		assertTrue(order.getStatus() == "Paid");
	}
	
	@Test
	public void ShouldAlertObserversWhenPaid() {
		//Setup
		Cart cart = new Cart();
		Order order = new Order(cart);
		StatusObserver statusObs = new StatusObserver();
		order.attach(statusObs);
		
		//Action
		order.changeStatusToPaid();
		order.changeStatusToPaid(); //Second run does nothing due to already being paid.
		assertTrue(order.getStatus() == "Paid");
	}
	
	@Test
	public void ShouldNotChangeStatusToPaidIfAlreadyPaid() {
		//Setup
		Cart cart = new Cart();
		Order order = new Order(cart);
		
		//Action
		order.changeStatusToPaid();
		order.changeStatusToCancelled();
		order.changeStatusToPaid();
		assertTrue(order.getStatus() == "Cancelled");
	}
	
	@Test
	public void ShouldBlockProductsWhenPaid() {
		//Setup
		ProductInventory inventory = ProductInventory.getInstance();
		Cart cart = new Cart();
		Order order = new Order(cart);
		Book book0 = new Book(1.0, 10.0, 1, ProductType.Book, "Interesting Book.", 400);
		
		inventory.addProduct(book0);
		cart.addProductToCart(inventory.getProduct(1));
		
		//Action
		order.changeStatusToPaid();
		assertTrue(book0.getAvailable() == false);
	}
	
	@Test
	public void ShouldChangeStatusToShippedIfPaid() {
		//Setup
		ProductInventory inventory = ProductInventory.getInstance();
		Cart cart = new Cart();
		Order order = new Order(cart);
		Book book0 = new Book(1.0, 10.0, 1, ProductType.Book, "Interesting Book.", 400);
		
		inventory.addProduct(book0);
		cart.addProductToCart(inventory.getProduct(1));
		
		//Action
		order.changeStatusToPaid();
		order.changeStatusToShipped();
		order.changeStatusToShipped(); //Does nothing if already shipped.
		assertTrue(inventory.getListOfProducts().isEmpty());
		assertTrue(order.getStatus() == "Shipped");
	}
	
	@Test
	public void ShouldNotChangeStatusToShippedIfNotPaid() {
		//Setup
		Cart cart = new Cart();
		Order order = new Order(cart);
		
		//Action
		order.changeStatusToShipped();
		assertTrue(order.getStatus() != "Shipped");
	}
	
	@Test
	public void ShouldAlertObserversWhenShipped() {
		//Setup
		Cart cart = new Cart();
		Order order = new Order(cart);
		StatusObserver statusObs = new StatusObserver();
		order.attach(statusObs);
		
		//Action
		order.changeStatusToPaid();
		order.changeStatusToShipped();
	}
	
	@Test
	public void ShouldChangeStatusToCompletedIfShipped() {
		//Setup
		Cart cart = new Cart();
		Order order = new Order(cart);
		
		//Action
		order.changeStatusToPaid();
		order.changeStatusToShipped();
		order.changeStatusToCompleted();
		order.changeStatusToCompleted(); //Testing if branch.
		assertTrue(order.getStatus() == "Completed");
	}
	
	@Test
	public void ShouldNotChangeStatusToCompletedIfNotShipped() {
		//Setup
		Cart cart = new Cart();
		Order order = new Order(cart);
		
		//Action
		order.changeStatusToPaid();
		order.changeStatusToCompleted();
		assertTrue(order.getStatus() != "Completed");
	}
	
	@Test
	public void ShouldWarnObserversWhenOrderIsCompleted() {
		//Setup
		StatusObserver statusObs = new StatusObserver();
		Cart cart = new Cart();
		Order order = new Order(cart);
		order.attach(statusObs);
		
		//Action
		order.changeStatusToPaid();
		order.changeStatusToShipped();
		order.changeStatusToCompleted();
		assertTrue(order.getStatus() == "Completed");
	}
	
	@Test
	public void ShouldCancelOrderAtAnyPointEXCEPTWhenShippedOrCompleted() {
		//Setup
		Cart cart = new Cart();
		Order order0 = new Order(cart);
		Order order1 = new Order(cart);
		Order order2 = new Order(cart);
		Order order3 = new Order(cart);
		
		//Action
		order0.changeStatusToPaid();
		order0.changeStatusToCancelled();
		
		order1.changeStatusToPaid();
		order1.changeStatusToShipped();
		order1.changeStatusToCancelled();
		
		order2.changeStatusToCancelled();
		
		assertTrue(order0.getStatus() == "Cancelled");
		assertTrue(order1.getStatus() == "Shipped");
		assertTrue(order2.getStatus() == "Cancelled");
	}
	
	@Test
	public void ShouldUnblockProductsWhenOrderCancelled() {
		ProductInventory inventory = ProductInventory.getInstance();
		Book book0 = new Book(1.0, 10.0, 1, ProductType.Book, "Interesting Book.", 400);
		Cart cart = new Cart();
		Order order0 = new Order(cart);
		inventory.addProduct(book0);
		
		cart.addProductToCart(inventory.getProduct(1));
		
		order0.changeStatusToPaid();
		assertTrue(book0.getAvailable() == false);
		order0.changeStatusToCancelled();
		assertTrue(book0.getAvailable() == true);
	}
	
	@Test
	public void ShouldDoNothingIfOrderIsCancelled() {
		Order order0 = new Order(new Cart());
				
		order0.changeStatusToCancelled();
		order0.changeStatusToCancelled();
	}
	
	@Test
	public void ShouldNotCancelCompletedOrders() {
		Order order0 = new Order(new Cart());
		
		order0.changeStatusToPaid();
		order0.changeStatusToShipped();
		order0.changeStatusToCompleted();
		order0.changeStatusToCancelled();
		assertTrue(order0.getStatus() != "Cancelled");
	}
	
	@Test
	public void ShouldAlertObserversWhenOrderIsCancelled() {
		Order order0 = new Order(new Cart());
		StatusObserver obs = new StatusObserver();
		order0.attach(obs);
		
		order0.changeStatusToPaid();
		order0.changeStatusToCancelled();
		assertTrue(order0.getStatus() == "Cancelled");
	}
	
	@Test
	public void ShouldReturnSameCart() {
		Cart cart = new Cart();
		Order order0 = new Order(cart);
		
		Cart cartFromOrder = order0.getCart();
		assertTrue(cartFromOrder == cart);
	}
	
	@Test
	public void ShouldGetShipping() {
		//Setup
		ProductInventory inventory = ProductInventory.getInstance();
		Book book = new Book(11.0, 10.0, 1, ProductType.Book, "A really heavy book.", 400);
		inventory.addProduct(book);
		Cart cart = new Cart();
		Order order0 = new Order(cart);
		cart.addProductToCart(book);
		
		//Action
		Shipping orderShipping = order0.getShipping();
		assertTrue(orderShipping instanceof Shipping);
	}
	
	@Test
	public void ShouldGetAeroShippingIfWeightBelow10() {
		//Setup
		ProductInventory inventory = ProductInventory.getInstance();
		Book book = new Book(9.9, 10.0, 1, ProductType.Book, "A really light book.", 400);
		inventory.addProduct(book);
		Cart cart = new Cart();
		Order order0 = new Order(cart);
		cart.addProductToCart(book);
		
		//Action
		Shipping orderShipping = order0.getShipping();
		assertTrue(orderShipping instanceof Aero);
	}
	
	@Test
	public void ShouldGetRoadShippingIfWeightAbove10() {
		//Setup
		ProductInventory inventory = ProductInventory.getInstance();
		Book book = new Book(10.1, 10.0, 1, ProductType.Book, "A really heavy book.", 400);
		inventory.addProduct(book);
		Cart cart = new Cart();
		Order order0 = new Order(cart);
		cart.addProductToCart(book);
		
		//Action
		Shipping orderShipping = order0.getShipping();
		assertTrue(orderShipping instanceof Road);
	}
	
	@Test
	public void ShouldAlertObserverIfShippingChanges() {
		//Setup
		ProductInventory inventory = ProductInventory.getInstance();
		ShippingObserver obs = new ShippingObserver();
		Book book = new Book(10.1, 10.0, 1, ProductType.Book, "A really heavy book.", 400);
		inventory.addProduct(book);
		Cart cart = new Cart();
		Order order0 = new Order(cart);
		order0.attach(obs);
		cart.addProductToCart(book);
		
		//Action
		Shipping orderShipping = order0.getShipping();
		assertTrue(orderShipping instanceof Road);
		cart.removeProductFromCart(1, 1);
		orderShipping = order0.getShipping();
	}
}
