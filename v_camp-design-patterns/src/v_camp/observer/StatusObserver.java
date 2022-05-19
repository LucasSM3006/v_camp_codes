package v_camp.observer;

import v_camp.facade.Order;

public class StatusObserver implements Observer {

	@Override
	public void updated(Order order) {
		String status = order.getStatus();
		
		System.out.println("Order N" + order.getOrderId() + " status has changed to: " + status);
	}

}
