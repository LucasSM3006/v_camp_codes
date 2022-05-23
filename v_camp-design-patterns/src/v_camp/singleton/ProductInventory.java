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
	
	private static List<Product> products = new ArrayList<Product>();
	
	public void addProduct(Product product) {
		if(product == null) throw new NullPointerException("Product sent to add is null.");
		products.add(product);
	}
	
	public List<Product> getListOfProducts() {
		return products;
	}
	
	public Product getProduct(int sku) {
		if(products.isEmpty()) throw new NullPointerException("Inventory is empty, add products.");
		else {
			for(Product prod : products) {
				if(prod.getSku() == sku) {
					if(prod.getAvailable() == true) {
						return prod;
					}
					else {
						continue;
					}
				}
			}
		}
		throw new NullPointerException("No products with specified SKU found.");
	}
	
	public void removeProductsFromStock(int sku, int quantity) {
		if(products.isEmpty()) throw new NullPointerException("Inventory is empty.");
		ProductInventory inventory = ProductInventory.getInstance();
		int count = 0, size = products.size();
		
		for(int i = 0; i < size; i++){
		    Product product = products.get(i-count);
		    if(product.getAvailable() == false && count < quantity && product.getSku() == sku){
		        products.remove(i-count);
		        count++;
		    }
		}
		if(count != quantity){
			if(count == 0) System.out.println("Could not remove desired quantity.");
			else System.out.println("Removed units, but could not remove desired quantity.");
		}
	}
	
	public void blockProductsFromStock(int sku, int quantity) {
		int count = 0;
		
		if(products.isEmpty()) {
			throw new NullPointerException("Inventory is empty, add products.");
		}
		
		for(Product prod : products) {
			if(count == quantity) break;
			if(prod.getAvailable() == false) continue;
			if(prod.getSku() == sku) {
				prod.setAvailable(false);
				count++;
			}
		}
	}
	
	public void unblockProductsFromStock(int sku, int quantity) {
		int count = 0;
		
		if(products.isEmpty()) {
			throw new NullPointerException("Inventory is empty, add products.");
		}
		
		for(Product prod : products) {
			if(count == quantity) break;
			if(prod.getAvailable() == true) continue;
			if(prod.getSku() == sku) {
				prod.setAvailable(true);
				count++;
			}
		}
	}
	
	public void resetInventory() {
		products.removeAll(products);
	}
}
