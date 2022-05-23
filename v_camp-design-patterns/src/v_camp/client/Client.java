package v_camp.client;

import v_camp.composite.Cart;
import v_camp.facade.Order;
import v_camp.iterator.OrderList;
import v_camp.observer.CartObserver;
import v_camp.observer.ShippingObserver;
import v_camp.observer.StatusObserver;
import v_camp.singleton.ProductInventory;

public class Client {
	
	public static void main(String[] args) {
		//Objects neeeded.
		ProductInventory inventory = (ProductInventory) ClientProducts.createList();
		Cart cart = new Cart();
		CartObserver cartObs = new CartObserver();
		ShippingObserver shippingObs = new ShippingObserver();
		StatusObserver statusObs = new StatusObserver();		
		OrderList orderList = OrderList.getInstance();
		Order order1 = new Order(cart);
		
		//Printing catalog of available products.
		Catalog.printCatalog();
		System.out.println();
		
		//Attaching the observers.
		cart.attach(cartObs);
		order1.attach(shippingObs);
		order1.attach(statusObs);
		
		//Adding the order to the list.
		orderList.addOrder(order1);
		
		//Actions.
		System.out.println("Adding two computers to cart.");
		cart.addProductToCart(inventory.getProduct(2));
		cart.addProductToCart(inventory.getProduct(2));
		
		System.out.println("Removing one computer from the cart.");
		cart.removeProductFromCart(2, 1);
		
		System.out.println("Adding three books to cart.");
		cart.addProductToCart(inventory.getProduct(1));
		cart.addProductToCart(inventory.getProduct(1));
		
		System.out.println("Changing order.");
		order1.changeStatusToPaid();
		order1.changeStatusToShipped();
		order1.changeStatusToCompleted();

		Catalog.printCatalog();
		
		System.out.println();
		System.out.println("New order & cart.");
		System.out.println();
		Cart cart1 = new Cart();
		Order order2 = new Order(cart1);
		
		orderList.addOrder(order2);
		cart1.attach(cartObs);
		order2.attach(shippingObs);
		order2.attach(statusObs);
		
		System.out.println("Adding two computers to cart.");
		cart1.addProductToCart(inventory.getProduct(2));
		cart1.addProductToCart(inventory.getProduct(2));
		
		System.out.println("Removing one computer from the cart.");
		cart1.removeProductFromCart(2, 1);
		
		System.out.println("Adding three books to cart.");
		cart1.addProductToCart(inventory.getProduct(1));
		cart1.addProductToCart(inventory.getProduct(1));
		
		System.out.println("Changing order N2.");
		order2.changeStatusToPaid();
		
		System.out.println("Catalog after Order 2 is paid, but before order 2 is cancelled.");
		System.out.println();
		
		Catalog.printCatalog();
		System.out.println();
		
		order2.changeStatusToCancelled();
		
		System.out.println("Catalog after Order 2 was cancelled.");
		Catalog.printCatalog();
	}
}
