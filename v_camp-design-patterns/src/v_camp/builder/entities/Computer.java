package v_camp.builder.entities;


public class Computer extends Product {
	private String processor;
	private String graphicsCard;

	public Computer(double weight, double price, int sku, ProductType type, String processor, String graphicsCard) {
		super(weight, price, sku, type);
		this.processor = processor;
		this.graphicsCard = graphicsCard;
	}
	
	public String getProcessor() {
		return this.processor;
	}
	
	public String getGraphicsCard() {
		return this.graphicsCard;
	}
	
	public String printInfo() {
		String info = "Computer info: " + "\n";
        info += "Weight: " + this.getWeight() + "\n";
        info += "Price: " + this.getPrice() + "\n";
        info += "Sku: " + this.getSku() + "\n";
        info += "CPU: " + getProcessor() + "\n";
        info += "GPU: " + getGraphicsCard() + "\n";
        
        return info;
	}
}
