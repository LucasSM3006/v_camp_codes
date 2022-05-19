package v_camp.observer;

import v_camp.composite.Cart;
import v_camp.facade.Order;

public class ShippingObserver implements Observer {

	@Override
	public void updated(Order order) {
		Cart ordrCart = order.getCart();
		
		System.out.println("Changed shipping method on Order N" + order.getOrderId() + " to: " + ordrCart.getShippingMethod());
	}

}
