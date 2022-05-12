package v_camp.builder.entities;

public class Product {
	private double weight;
	private double price;
	private int sku;
	private boolean available = true;
	
	public Product(double weight, double price, int sku) {
		super();
		this.weight = weight;
		this.price = price;
		this.sku = sku;
	}
	
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSku() {
		return sku;
	}
	public void setSku(int sku) {
		this.sku = sku;
	}
	public boolean getAvailable() {
		return this.available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
}
