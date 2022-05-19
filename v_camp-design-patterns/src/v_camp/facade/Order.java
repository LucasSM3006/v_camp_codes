package v_camp.facade;

import java.util.ArrayList;
import java.util.List;

import v_camp.builder.entities.Product;
import v_camp.composite.Cart;
import v_camp.factory.Shipping;
import v_camp.observer.BackOffice;
import v_camp.observer.Observer;
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
	
	public void dettach(Observer observer) {
		observers.remove(observer);
	}
	
	public void changeStatusToPending() {
		this.status = "Pending";
	}
	
	public void changeStatusToPaid() {
		this.status = "Paid";
		
		for(Product prod : products) {
			productInventory.blockProductsFromStock(prod.getSku(), 1);
		}
		
		observers.forEach(o->o.updated(this));
	}
	
	public void changeStatusToShipped() {
		this.status = "Shipped";
		
		for(Product prod : products) {
			productInventory.removeProductsFromStock(prod.getSku(), 1);
		}
		
		observers.forEach(o->o.updated(this));
	}
	
	public void changeStatusToCompleted() {
		this.status = "Completed";
		
		observers.forEach(o->o.updated(this));
	}
	
	public void changeStatusToCancelled() {
		this.status = "Cancelled";
		
		for(Product prod : products) {
			productInventory.unblockProductsFromStock(prod.getSku(), 1);
		}
		
		observers.forEach(o->o.updated(this));
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Shipping getShipping() {
		return this.shipping = this.cart.getShippingMethod();
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public String getStatus() {
		return status;
	}
	
	public int getOrderId() {
		return this.orderId;
	}
}
