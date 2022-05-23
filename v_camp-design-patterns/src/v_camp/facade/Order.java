package v_camp.facade;

import java.util.ArrayList;
import java.util.List;

import v_camp.builder.entities.Product;
import v_camp.composite.Cart;
import v_camp.factory.Shipping;
import v_camp.observer.BackOffice;
import v_camp.observer.Observer;
import v_camp.observer.ShippingObserver;
import v_camp.singleton.ProductInventory;

public class Order {
	private Cart cart;
	private Shipping shipping;
	private String status;
	private List<Product> products;
	private ProductInventory productInventory = ProductInventory.getInstance();
	private static int staticId = 1;
	private int orderId;
	
	private List<Observer> observers = new ArrayList<>();
	private List<ShippingObserver> shippingObservers = new ArrayList<>();
	
	public Order(Cart cart) {
		this.cart = cart;
		this.shipping = this.cart.getShippingMethod();
		this.products = cart.getProducts();
		changeStatusToPending();
		orderId = staticId;
		Order.staticId++;
	}
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void attach(ShippingObserver so) {
		shippingObservers.add(so);
	}
	
	public void dettach(Observer observer) {
		observers.remove(observer);
	}
	
	public void dettach(ShippingObserver so) {
		shippingObservers.remove(so);
	}
	
	public void changeStatusToPending() {
		this.status = "Pending";
	}
	
	public void changeStatusToPaid() {
		if(this.status == "Paid");
		else if(this.status == "Pending") {
			this.status = "Paid";
			
			observers.forEach(o->o.updated(this));
		}
	}
	
	public void changeStatusToShipped() {
		if(this.status == "Shipped");
		else if(this.status == "Paid") {
			this.status = "Shipped";
			
			for(Product prod : products) {
				productInventory.removeProductsFromStock(prod.getSku(), 1);
			}
			
			observers.forEach(o->o.updated(this));
		}
	}
	
	public void changeStatusToCompleted() {
		if(this.status == "Completed");
		else if(this.status == "Shipped"){
			this.status = "Completed";
			
			observers.forEach(o->o.updated(this));
		}
	}
	
	public void changeStatusToCancelled() {
		if(this.status == "Cancelled");
		if(this.status == "Shipped") System.out.println("Could not cancel order, already shipped. Contact Courier.");
		if(this.status == "Completed") System.out.println("Could not cancel order, already completed.");
		else if(this.status == "Paid" || this.status == "Pending") {
			this.status = "Cancelled";
			
			for(Product prod : products) {
				productInventory.unblockProductsFromStock(prod.getSku(), 1);
			}
			
			observers.forEach(o->o.updated(this));
		}
	}

	public Cart getCart() {
		return cart;
	}

	public Shipping getShipping() {
		if(this.shipping.getShippingMethod() != this.cart.getShippingMethod().getShippingMethod()) {
			shippingObservers.forEach(o->o.updated(this));
		}
		this.shipping = this.cart.getShippingMethod();
		return this.shipping;
	}

	public String getStatus() {
		return status;
	}
	
	public int getOrderId() {
		return this.orderId;
	}
	
	public void resetId() {
		staticId = 1;
	}
}
