package v_camp.iterator;

import java.util.ArrayList;
import java.util.List;

import v_camp.facade.Order;
import v_camp.singleton.ProductInventory;

public class OrderList implements Iterator {
	private static final OrderList INSTANCE = new OrderList();
	
	private OrderList() {
		
	}
	
	public static OrderList getInstance() {
		return INSTANCE;
	}
	
	private List<Order> orders = new ArrayList<Order>();
	private int position = 0;
	
	public void addOrder(Order order) {
		orders.add(order);
	}

	@Override
	public boolean hasNext() {
		return position < orders.size();
	}

	@Override
	public Order next() {
		if(!hasNext()) {
			return null;
		}
		
		Order order = orders.get(position);
		position++;
		return order;
	}

	@Override
	public void reset() {
		this.position = 0;
	}
}
