package v_camp.client;

import java.util.ArrayList;
import java.util.List;

import v_camp.builder.entities.Product;
import v_camp.singleton.ProductInventory;

public class Catalog {
	private static ProductInventory inventory = ProductInventory.getInstance();
	private static List<Product> products = inventory.getProductQuantity();
	
	public static List<Product> getCatalog() {
		return products;
	}
}
