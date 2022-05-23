package v_camp.client;

import java.util.ArrayList;
import java.util.List;

import v_camp.builder.entities.Book;
import v_camp.builder.entities.Computer;
import v_camp.builder.entities.Product;
import v_camp.builder.entities.Shoe;
import v_camp.builder.entities.Skirt;
import v_camp.singleton.ProductInventory;

public class Catalog {
	private static ProductInventory inventory = ProductInventory.getInstance();
	private static List<Product> products = inventory.getListOfProducts();
	
	public static List<Product> getCatalog() {
		return products;
	}
	
	public static void printCatalog() {
		int bookCount = 0;
		int computerCount = 0;
		int shoeCount = 0 ;
		int skirtCount = 0;
		
		System.out.println("Available products...");
		for(Product prod : products) {
			if(prod.getAvailable() == false) continue;
			
			if(prod instanceof Book) {
				bookCount++;
			}
			else if(prod instanceof Computer) {
				computerCount++;
			}
			else if(prod instanceof Shoe) {
				shoeCount++;
			}
			else if(prod instanceof Skirt) {
				skirtCount++;
			}
			else {
				continue;
			}
		}
		
		if(bookCount == 0 && computerCount == 0 && shoeCount == 0 && skirtCount == 0) {
			System.out.println("No products available.");
		}
		else {
			System.out.println("Number of books available: " + bookCount);
			System.out.println("Number of computers available: " + computerCount);
			System.out.println("Number of shoes available: " + shoeCount);
			System.out.println("Number of skirts available: " + skirtCount);
		}
	}
}
