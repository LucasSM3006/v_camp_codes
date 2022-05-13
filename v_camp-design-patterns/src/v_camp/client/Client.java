package v_camp.client;

import java.util.ArrayList;
import java.util.List;

import v_camp.builder.BookBuilder;
import v_camp.builder.ComputerBuilder;
import v_camp.builder.ShoeBuilder;
import v_camp.builder.SkirtBuilder;
import v_camp.builder.entities.Book;
import v_camp.builder.entities.Computer;
import v_camp.builder.entities.Product;
import v_camp.builder.entities.Shoe;
import v_camp.builder.entities.Skirt;
import v_camp.composite.Cart;
import v_camp.factory.AeroShippingCreator;
import v_camp.factory.RoadShippingCreator;
import v_camp.factory.Shipping;
import v_camp.factory.ShippingCreator;
import v_camp.singleton.ProductInventory;

public class Client {
	public static void main(String[] args) {
		//Director
		Director director = new Director();
		
		//Builders
		BookBuilder bbuilder = new BookBuilder();
		ComputerBuilder cbuilder = new ComputerBuilder();
		ShoeBuilder shbuilder = new ShoeBuilder();
		SkirtBuilder skbuilder = new SkirtBuilder();
		
		//Product inventory
		ProductInventory inventory = ProductInventory.getInstance();
		
		//Entities
		Book book[] = new Book[10];
		Computer computer[] = new Computer[10];
		Skirt skirt[] = new Skirt[10];
		Shoe shoe[] = new Shoe[10];
		
		//Instantiating the entities
		for(int i = 0; i < 10; i++) {
			List<Product> catalogProducts = new ArrayList<Product>();
			
			director.constructBook(bbuilder);
			book[i] = bbuilder.getResult();
			
			director.constructComputer(cbuilder);
			computer[i] = cbuilder.getResult();
			
			director.constructShoe(shbuilder);
			shoe[i] = shbuilder.getResult();
			
			director.constructSkirt(skbuilder);
			skirt[i] = skbuilder.getResult();
			
			inventory.addProduct(book[i]);
			inventory.addProduct(computer[i]);
			inventory.addProduct(skirt[i]);
			inventory.addProduct(shoe[i]);
		}
		
		Cart cart = new Cart();
		System.out.println(cart.getAmountOfProductsInCart());
		
		Shipping shippingMethod;
		
		inventory.blockProductsFromStock(1, 9);
		inventory.blockProductsFromStock(2, 5);
		inventory.blockProductsFromStock(3, 9);
		inventory.blockProductsFromStock(4, 9);
		
		for(Product product : inventory.getProductQuantity()) {
			System.out.println(product.getAvailable());
			
			if(product.getAvailable() == true) {
				cart.addProductToCart(product);
			}
		}
		
		if(cart.getWeight() > 10.0) {
			shippingMethod = createShippingMethod(new RoadShippingCreator());
		}
		else {
			shippingMethod = createShippingMethod(new AeroShippingCreator());
		}
		int priceToAdd = cart.getAmountOfProductsInCart();
		cart.setShippingPrice(priceToAdd);
		
		System.out.println(Catalog.getCatalog());
		System.out.println(shippingMethod.getShippingMethod());
		System.out.println(cart.getAmountOfProductsInCart());
		System.out.println(cart.getWeight());
		System.out.println("USD " + priceToAdd);
	}
	
	public static Shipping createShippingMethod(ShippingCreator creator) {
		Shipping shipping = creator.getShipping();
		return shipping;
	}
}
