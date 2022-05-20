package v_camp.observer;

import v_camp.composite.Cart;
import v_camp.facade.Order;
import v_camp.factory.Shipping;
import v_camp.iterator.OrderList;

public class BackOffice {
	public static void renderOrderList() {
		OrderList orderList = OrderList.getInstance();
		
		while(orderList.hasNext()) {
			Order order = orderList.next();
			Cart ordrCart = order.getCart();
			//order.changeStatusToPaid();
			
			double totalPrice = ordrCart.getTotalPlusShipping();
			Shipping shipMethod = order.getShipping();
			
			System.out.println("Order N" + order.getOrderId() + " info: ");
			System.out.println("Order status: " + order.getStatus());
			System.out.print("Total + Shipping: " + totalPrice);
			System.out.println(" USD");
			System.out.println("Weight: " + ordrCart.getWeight());
			System.out.println("Shipping Method: " + shipMethod.getShippingMethod());
			System.out.println("");
		}
		
		orderList.reset();
	}
}
