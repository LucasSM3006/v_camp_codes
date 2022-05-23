package v_camp.builder.entities;


public class Computer extends Product {
	private String processor;
	private String graphicsCard;

	public Computer(double weight, double price, int sku, ProductType type, String processor, String graphicsCard) {
		super(weight, price, sku, type);
		if(weight == 0 || price == 0 || sku == 0 || type == null || processor == null || graphicsCard == null) throw new NullPointerException("Null values for computer.");
		this.processor = processor;
		this.graphicsCard = graphicsCard;
	}
	
	public String getProcessor() {
		return this.processor;
	}
	
	public String getGraphicsCard() {
		return this.graphicsCard;
	}
}
