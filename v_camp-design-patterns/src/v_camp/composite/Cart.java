package v_camp.composite;

import java.util.ArrayList;
import java.util.List;

import v_camp.builder.entities.Product;
import v_camp.factory.Shipping;
import v_camp.factory.ShippingCreator;

public class Cart {
	private List<Product> products = new ArrayList<Product>();
	
	private double shippingPrice;
	
	private Shipping shipping;
	
	public void addProductToCart(Product product) {
		if(product == null) {
			System.out.println("Stock is out.");
		}
		else if(product.getAvailable() == false) {
			return;
		}
		else {
			products.add(product);
			//product.setAvailable(false); Old method.
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
