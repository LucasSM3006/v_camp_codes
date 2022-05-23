package iteratortest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import v_camp.composite.Cart;
import v_camp.facade.Order;
import v_camp.iterator.OrderList;
import v_camp.singleton.ProductInventory;

public class IteratorTest {
	@Before
	public void setup() {
		ProductInventory inv = ProductInventory.getInstance();
		inv.resetInventory();
		OrderList list = OrderList.getInstance();
		list.reset();
		list.resetOrders();
	}
	
	@Test
	public void ShouldAddOrderToList() {
		Order order0 = new Order(new Cart());
		OrderList list = OrderList.getInstance();
		
		list.addOrder(order0);
	}
	
	@Test
	public void ShouldHaveNext() {
		Order order0 = new Order(new Cart());
		OrderList list = OrderList.getInstance();
		
		list.addOrder(order0);
		
		assertTrue(list.hasNext());
	}
	
	@Test
	public void ShouldNotHaveNext() {
		Order order0 = new Order(new Cart());
		OrderList list = OrderList.getInstance();
		
		list.addOrder(order0);
		
		Order order = list.next();
		assertFalse(list.hasNext());
	}
	
	@Test
	public void ShouldGetNullWhenEmpty() {
		OrderList list = OrderList.getInstance();
		
		assertTrue(list.next() == null);
	}
}
