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
		this.products.add(product);
	}
	
	public List<Product> getListOfProducts() {
		return products;
	}
	
	public Product getProduct(int sku) {
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
		return null;
	}
	
	public void removeProductsFromStock(int sku, int quantity) {
//		List<Product> productsToRemove = new ArrayList<Product>();
		int count = 0;
		//Goes through the products list and finds the same sku.
		for(Product prod : products) {
			if(count == quantity) break;
			if(prod.getSku() == sku) {
				if(prod.getAvailable() == false) {
					products.remove(prod);
					break;
				}
			}
		}
//		for(Product prod : products) {
//			if (productsToRemove.size() == quantity) {
//				break;
//			}
//			if(prod.getSku() == sku) {
//				productsToRemove.add(prod);
//			}
//		}
//		products.removeAll(productsToRemove);
	}
	
	public void blockProductsFromStock(int sku, int quantity) {
		int count = 0;
		
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
		
		for(Product prod : products) {
			if(count == quantity) break;
			if(prod.getAvailable() == true) continue;
			if(prod.getSku() == sku) {
				prod.setAvailable(true);
				count++;
			}
		}
	}
}
