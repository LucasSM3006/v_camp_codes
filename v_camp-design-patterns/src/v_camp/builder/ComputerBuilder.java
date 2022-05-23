package v_camp.builder;

import v_camp.builder.entities.Computer;
import v_camp.builder.entities.ProductType;

public class ComputerBuilder implements Builder {
	private double weight;
	private double price;
	private int sku;
	private String cpu;
	private String gpu;

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
	
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	
	public void setGpu(String gpu) {
		this.gpu = gpu;
	}
	
	public Computer getResult() {
		if(weight == 0 || price == 0 || sku == 0 || cpu == null || gpu == null) throw new NullPointerException("Null values for computer.");
		return new Computer(weight, price, sku,ProductType.Computer ,cpu, gpu);
	}
}
