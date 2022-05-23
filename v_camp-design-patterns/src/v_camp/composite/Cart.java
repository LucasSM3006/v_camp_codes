package v_camp.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import v_camp.builder.entities.Product;
import v_camp.factory.Shipping;
import v_camp.factory.ShippingCreator;
import v_camp.observer.CartObserver;
import v_camp.observer.Observer;
import v_camp.singleton.ProductInventory;

public class Cart {
	//To control the switch on the observer.
	static final int ADDED = 1;
	static final int REMOVED = 2;
	private List<Product> products = new ArrayList<Product>();
	
	private double shippingPrice;
	
	private Shipping shipping;
	
	private List<CartObserver> observers = new ArrayList<>();
	
	public void attach(CartObserver observer) {
		observers.add(observer);
	}
	
	public void dettach(CartObserver observer) {
		observers.remove(observer);
	}
	
	public void addProductToCart(Product product) {
		if(product == null) {
			System.out.println("Non existent product.");
		}
		else if(product.getAvailable() == false) {
			System.out.println("Could not add product to cart. Unavailable.");
			return;
		}
		else {
			products.add(product);
			product.setAvailable(false);
			observers.forEach(o->o.updated(product, ADDED));
		}
	}
	
	public void removeProductFromCart(int sku, int quantity) {
		ProductInventory inventory = ProductInventory.getInstance();
		int count = 0, size = products.size();
		
		for(int i = 0; i < size; i++){
		    Product product = products.get(i-count);
		    if(count < quantity && product.getSku() == sku){
		        inventory.unblockProductsFromStock(sku,1);
		        observers.forEach(o->o.updated(product, REMOVED));
		        products.remove(i-count);
		        count++;
		    }
		}
		if(count != quantity){
		    System.out.println("Removed units, but could not remove desired quantity.");
		}
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public int getAmountOfProductsInCart() {
		return products.size();
	}
	
	public double getTotal() {
		double totalPrice = 0;
		for(Product product : products) {
			totalPrice = totalPrice + product.getPrice();
		}
		return totalPrice;
	}
	
	public double getTotalPlusShipping() {
		return this.getTotal() + this.getShippingPrice();
	}
	
	public double getWeight() {
		double totalWeight = 0;
		for(Product product : products) {
			totalWeight = totalWeight + product.getWeight();
		}
		return totalWeight;
	}

	public double getShippingPrice() {
		double tenPercentOfTotal = getTotal() > 7.99 ? getTotal() * 0.1 : 7.99;
		this.shippingPrice = getAmountOfProductsInCart();
		return shippingPrice + tenPercentOfTotal;
	}
	
	public Shipping getShippingMethod() {
		this.shipping = ShippingCreator.getShipping(getWeight());
		return shipping;
	}
}
