package v_camp.observer;

import v_camp.composite.Cart;
import v_camp.facade.Order;
import v_camp.factory.Shipping;

public class ShippingObserver implements Observer {

	@Override
	public void updated(Order order) {
		if(order == null) throw new NullPointerException("Order parameter cannot be null.");
		Cart ordrCart = order.getCart();
		Shipping ordrShipping = ordrCart.getShippingMethod();
		
		System.out.println("Changed shipping method on Order N" + order.getOrderId() + " to: " + ordrShipping.getShippingMethod());
		
		BackOffice.renderOrderList();
	}

}
