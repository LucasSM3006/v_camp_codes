package v_camp.client;

import v_camp.builder.BookBuilder;
import v_camp.builder.ComputerBuilder;
import v_camp.builder.ShoeBuilder;
import v_camp.builder.SkirtBuilder;
import v_camp.builder.entities.Book;
import v_camp.builder.entities.Computer;
import v_camp.builder.entities.Shoe;
import v_camp.builder.entities.Skirt;
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
		inventory.removeProductFromStock(1, 9);
		inventory.removeProductFromStock(2, 9);
		inventory.removeProductFromStock(3, 9);
		inventory.removeProductFromStock(4, 9);
		
		inventory.blockProductsFromStock(1, 1);
		
		System.out.println(inventory.getProductQuantity());
	}
}
