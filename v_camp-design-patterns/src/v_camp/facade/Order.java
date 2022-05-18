package v_camp.facade;

import java.util.ArrayList;
import java.util.List;

import v_camp.builder.entities.Product;
import v_camp.composite.Cart;
import v_camp.factory.Shipping;
import v_camp.singleton.ProductInventory;

public class Order {
	private Cart cart;
	private Shipping shipping;
	private String status;
	private List<Product> products;
	private ProductInventory productInventory = ProductInventory.getInstance();
	
	public Order(Cart cart) {
		this.cart = cart;
		this.shipping = this.cart.getShippingMethod();
		this.products = cart.getProducts();
		changeStatusToPending();
	}
	
	public void changeStatusToPending() {
		this.status = "Pending";
	}
	
	public void changeStatusToPaid() {
		this.status = "Paid";
		
		for(Product prod : products) {
			productInventory.blockProductsFromStock(prod.getSku(), 1);
		}
	}
	
	public void changeStatusToShipped() {
		this.status = "Shipped";
		
		for(Product prod : products) {
			productInventory.removeProductsFromStock(prod.getSku(), 1);
		}
	}
	
	public void changeStatusToCompleted() {
		this.status = "Completed";
	}
	
	public void changeStatusToCancelled() {
		this.status = "Cancelled";
		
		for(Product prod : products) {
			productInventory.unblockProductsFromStock(prod.getSku(), 1);
		}
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public String getStatus() {
		return status;
	}
}
