package v_camp.builder.entities;

public class Skirt extends Product {
	private int size;
	private String brand;

	public Skirt(double weight, double price, int sku, ProductType type, int size, String brand) {
		super(weight, price, sku, type);
		if(weight == 0 || price == 0 || sku == 0 || type == null || size == 0 || brand == null) throw new NullPointerException("Null values for skirt.");
		this.size = size;
		this.brand = brand;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public String getBrand() {
		return this.brand;
	}
}
