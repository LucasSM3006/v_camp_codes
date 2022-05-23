package v_camp.builder;

import v_camp.builder.entities.ProductType;
import v_camp.builder.entities.Skirt;

public class SkirtBuilder implements Builder {
	private double weight;
	private double price;
	private int sku;
	private int size;
	private String brand;

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
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public Skirt getResult() {
		if(weight == 0 || price == 0 || sku == 0 || size == 0 || brand == null) throw new NullPointerException("Null values for skirt.");
		return new Skirt(weight, price, sku, ProductType.Skirt,size, brand);
	}
}