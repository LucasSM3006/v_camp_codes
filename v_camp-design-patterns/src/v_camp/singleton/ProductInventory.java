package v_camp.singleton;

import java.util.ArrayList;
import java.util.List;

import v_camp.builder.entities.Product;

public class ProductInventory {
	private ProductInventory() {
		
	}
	private static final ProductInventory INSTANCE = new ProductInventory();
	
	public static ProductInventory getInstance() {
		return INSTANCE;
	}
	
	private List<Product> products = new ArrayList<Product>();
	
	public void addProduct(Product product) {
		this.products.add(product);
	}
	
	public List<Product> getProductQuantity() {
		return products;
	};
	
	public void removeProductFromStock(int sku, int quantity) {
		List<Product> productsToRemove = new ArrayList<Product>();
		//Goes through the products list and finds the same sku.
		for(Product prod : products) {
			if (productsToRemove.size() == quantity) {
				break;
			}
			if(prod.getSku() == sku) {
				productsToRemove.add(prod);
			}
		}
		//Removes all objects inside that match.
		products.removeAll(productsToRemove);
	}
	
	public void blockProductsFromStock(int sku, int quantity) {
		List<Product> productsToBlock = new ArrayList<Product>();
		for(Product prod : products) {
			if (productsToBlock.size() == quantity) {
				break;
			}
			if (prod.getSku() == sku) {
				prod.setAvailable(false);
			}
		}
	}
}
