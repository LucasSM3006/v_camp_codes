package v_camp.observer;

import v_camp.facade.Order;

public class StatusObserver implements Observer {

	@Override
	public void updated(Order order) {
		if(order == null) throw new NullPointerException("Order parameter cannot be null.");
		String status = order.getStatus();
		
		System.out.println("Order N" + order.getOrderId() + " status has changed to: " + status);
		
		BackOffice.renderOrderList();
	}
}
