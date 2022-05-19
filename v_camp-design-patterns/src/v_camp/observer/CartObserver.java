package v_camp.observer;

import v_camp.builder.entities.Product;

public class CartObserver {

	public void updated(Product product, int type) {
		String phrase = "Cart updated: " + product.getProductType();
		
		switch(type) {
		case(1):
			phrase = phrase + " added";
			break;
		case(2):
			phrase = phrase + " removed";
			break;
		default:
			throw new IllegalArgumentException("Invalid type, only one or two allowed for 2nd parameter.");
		}
		
		System.out.println(phrase);
	}
}
