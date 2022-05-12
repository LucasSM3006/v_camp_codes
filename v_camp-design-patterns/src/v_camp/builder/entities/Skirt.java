package v_camp.builder.entities;

public class Skirt extends Product {
	private int size;
	private String brand;

	public Skirt(double weight, double price, int sku, int size, String brand) {
		super(weight, price, sku);
		this.size = size;
		this.brand = brand;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public String getBrand() {
		return this.brand;
	}
	
	public String printInfo() {
		String info = "Skirt info: " + "\n";
        info += "Weight: " + this.getWeight() + "\n";
        info += "Price: " + this.getPrice() + "\n";
        info += "Sku: " + this.getSku() + "\n";
        info += "Size: " + getSize() + "\n";
        info += "Brand: " + getBrand() + "\n";
        
        return info;
	}

}
