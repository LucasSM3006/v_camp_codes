package observertest;

import org.junit.Before;
import org.junit.Test;

import v_camp.builder.entities.Book;
import v_camp.builder.entities.ProductType;
import v_camp.composite.Cart;
import v_camp.facade.Order;
import v_camp.iterator.OrderList;
import v_camp.observer.BackOffice;
import v_camp.observer.CartObserver;
import v_camp.observer.ShippingObserver;
import v_camp.observer.StatusObserver;
import v_camp.singleton.ProductInventory;

public class ObserverTest {
	
	@Before
	public void setup() {
		ProductInventory inv = ProductInventory.getInstance();
		inv.resetInventory();
		OrderList l = OrderList.getInstance();
		l.resetOrders();
		l.reset();
		Order o = new Order(new Cart());
		o.resetId();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ShouldThrowExceptionWhenParameterIsAboveTwo() {
		CartObserver obs = new CartObserver();
		Book b = new Book(1.0, 19.99, 1, ProductType.Book, "Testing", 200);
		obs.updated(b, 3);
	}
	
	@Test(expected = NullPointerException.class)
	public void ShouldThrowExceptionIfProductParameterIsNull() {
		CartObserver obs = new CartObserver();
		obs.updated(null, 3);
	}
	
	@Test
	public void ShouldPrintAddedMessageIfTypeValid() {
		CartObserver obs = new CartObserver();
		Book b = new Book(1.0, 19.99, 1, ProductType.Book, "Testing", 200);
		obs.updated(b, 1);
	}
	
	@Test
	public void ShouldPrintRemovedMessageIfTypeValid() {
		CartObserver obs = new CartObserver();
		Book b = new Book(1.0, 19.99, 1, ProductType.Book, "Testing", 200);
		obs.updated(b, 2);
	}
	
	//StatusObserver Test.
	
	@Test(expected = NullPointerException.class)
	public void ShouldThrowExceptionWhenOrderParamaterIsNull() {
		StatusObserver obs = new StatusObserver();
		obs.updated(null);
	}
	
	@Test
	public void ShouldPrintStatusMessage() {
		StatusObserver obs = new StatusObserver();
		Order order = new Order(new Cart());
		
		obs.updated(order);
	}
	
	//Shippingobserver Test.
	
	@Test(expected = NullPointerException.class)
	public void ShouldThrowExceptionWhenReceivingNullOrder() {
		ShippingObserver obs = new ShippingObserver();
		
		obs.updated(null);
	}
	
	@Test
	public void ShouldPrintShippingMessageAndRenderList() {
		ShippingObserver obs = new ShippingObserver();
		Book b = new Book(1.0, 19.99, 1, ProductType.Book, "Testing", 200);
		Cart c = new Cart();
		Order o = new Order(c);
		c.addProductToCart(b);
		
		obs.updated(o);
	}
	
	//BackOffice Test.
	
	@Test
	public void ShouldRenderOrderList() {
		BackOffice bo = new BackOffice();
		bo.renderOrderList();
	}
	
	@Test
	public void ShouldRenderOrderListWhenThereAreOrders() {
		BackOffice bo = new BackOffice();
		OrderList list = OrderList.getInstance();
		
		Cart cart = new Cart();
		Order order0 = new Order(cart);
		
		list.addOrder(order0);
		bo.renderOrderList();
	}
}
