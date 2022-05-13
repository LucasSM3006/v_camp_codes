package v_camp.composite;

import java.util.ArrayList;
import java.util.List;

import v_camp.builder.entities.Product;

public class Cart {
	private List<Product> products = new ArrayList<Product>();
	
	private double totalPrice;
	
	private double shippingPrice;
	
	public void addProductToCart(Product product) {
		products.add(product);
		product.setAvailable(false);
	}
	
	public int getAmountOfProductsInCart() {
		return products.size();
	}
	
	public double getTotal() {
		return totalPrice;
	}
	
	public double getWeight() {
		double totalWeight = 0;
		for(Product product : products) {
			totalWeight = totalWeight + product.getWeight();
		}
		return totalWeight;
	}

	public double getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(double shippingPrice) {
		this.shippingPrice = shippingPrice;
	}
}
