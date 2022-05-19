package v_camp.builder.entities;

public class Shoe extends Product {
	private double sizeUS;
	private String material;
	
	public Shoe(double weight, double price, int sku, ProductType type, double sizeUS, String material) {
		super(weight, price, sku, type);
		this.sizeUS = sizeUS;
		this.material = material;
	}

	public String getMaterial() {
		return this.material;
	}
	
	public double getSizeUS() {
		return this.sizeUS;
	}
	
	public String printInfo() {
		String info = "Shoe info: " + "\n";
        info += "Weight: " + this.getWeight() + "\n";
        info += "Price: " + this.getPrice() + "\n";
        info += "Sku: " + this.getSku() + "\n";
        info += "Size: " + getSizeUS() + "\n";
        info += "Material: " + getMaterial() + "\n";
        
        return info;
	}
}
