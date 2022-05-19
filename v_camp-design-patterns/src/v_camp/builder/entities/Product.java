package v_camp.builder.entities;

public class Product {
	private double weight;
	private double price;
	private int sku;
	private boolean available = true;
	private ProductType type;
	
	public Product(double weight, double price, int sku, ProductType type) {
		super();
		this.weight = weight;
		this.price = price;
		this.sku = sku;
		this.type = type;
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
	public ProductType getProductType() {
		return this.type;
	}
}
