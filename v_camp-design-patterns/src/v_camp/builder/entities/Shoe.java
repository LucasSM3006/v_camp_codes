package v_camp.builder.entities;

public class Shoe extends Product {
	private double sizeUS;
	private String material;
	
	public Shoe(double weight, double price, int sku, ProductType type, double sizeUS, String material) {
		super(weight, price, sku, type);
		if(weight == 0 || price == 0 || sku == 0 || type == null || sizeUS == 0 || material == null) throw new NullPointerException("Null values for shoe.");
		this.sizeUS = sizeUS;
		this.material = material;
	}

	public String getMaterial() {
		return this.material;
	}
	
	public double getSizeUS() {
		return this.sizeUS;
	}
}
