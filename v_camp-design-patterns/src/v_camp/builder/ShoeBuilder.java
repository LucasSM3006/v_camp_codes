package v_camp.builder;

import v_camp.builder.entities.ProductType;
import v_camp.builder.entities.Shoe;

public class ShoeBuilder implements Builder {
	private double weight;
	private double price;
	private int sku;
	private double sizeUS;
	private String material;

	@Override
	public void setWeight(double weight) {
		if(weight < 0) throw new IllegalArgumentException("Weight below zero.");
		else this.weight = weight;
	}

	@Override
	public void setPrice(double price) {
		if(price <= 0) throw new IllegalArgumentException("Price below or equal to zero.");
		else this.price = price;
	}

	@Override
	public void setSku(int sku) {
		this.sku = sku;
	}
	
	public void setSize(double sizeUS) {
		this.sizeUS = sizeUS;
	}
	
	public void setMaterial(String material) {
		this.material = material;
	}
	
	public Shoe getResult() {
		if(weight == 0 || price == 0 || sku == 0 || sizeUS == 0 || material == null) throw new NullPointerException("Null values for shoe.");
		return new Shoe(weight, price, sku, ProductType.Shoe, sizeUS, material);
	}
}
