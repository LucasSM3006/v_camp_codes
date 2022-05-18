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
import v_camp.facade.Order;
import v_camp.factory.AeroShippingCreator;
import v_camp.factory.RoadShippingCreator;
import v_camp.factory.Shipping;
import v_camp.factory.ShippingCreator;
import v_camp.iterator.OrderList;
import v_camp.singleton.ProductInventory;

public class Client {
	public static void main(String[] args) {
		//Director
		ProductInventory inventory = (ProductInventory) ClientProducts.createList();
		
		Cart cart = new Cart();
		Cart cart2 = new Cart();
		
		//Blocking products.
		//inventory.blockProductsFromStock(1, 0);
		//inventory.blockProductsFromStock(2, 0);
		//inventory.blockProductsFromStock(3, 0);
		//inventory.blockProductsFromStock(4, 0);
		
		//Filling up the cart.
		cart.addProductToCart(inventory.getProduct(2));
		cart.addProductToCart(inventory.getProduct(2));
		cart.addProductToCart(inventory.getProduct(2));
		cart.addProductToCart(inventory.getProduct(2));
		cart.addProductToCart(inventory.getProduct(2));
		cart.addProductToCart(inventory.getProduct(2));
		cart.addProductToCart(inventory.getProduct(2));
		cart.addProductToCart(inventory.getProduct(2));
		cart.addProductToCart(inventory.getProduct(2));
		cart.addProductToCart(inventory.getProduct(2));
		cart.addProductToCart(inventory.getProduct(2));
		cart.addProductToCart(inventory.getProduct(2));
		
		cart2.addProductToCart(inventory.getProduct(4));
		cart2.addProductToCart(inventory.getProduct(1));
		
		//for(Product product : inventory.getProductQuantity()) {
		//	cart.addProductToCart(product);
		//}
	
		//int priceToAdd = cart.getAmountOfProductsInCart();
		
		//System.out.println(Catalog.getCatalog());
		//System.out.println("Cart information:");
		//System.out.println("Shipping Method: " + cart.getShippingMethod().getShippingMethod());
		//System.out.println("Products in Cart: " + cart.getAmountOfProductsInCart());
		//System.out.println("Weight (Kg): " + cart.getWeight());
		//System.out.println("Cart Price (USD): " + cart.getTotal());
		//System.out.println("Cart Price w/ Shipping (USD): " + cart.getTotalPlusShipping());
		
		OrderList orderList = OrderList.getInstance();
		Order order1 = new Order(cart);
		Order order2 = new Order(cart2);
		
		orderList.addOrder(order1);
		orderList.addOrder(order2);
		
		while(orderList.hasNext()) {
			Order order = orderList.next();
			Cart ordrCart = order.getCart();
			//order.changeStatusToPaid();
			
			double totalPrice = ordrCart.getTotalPlusShipping();
			Shipping shipMethod = ordrCart.getShippingMethod();
			String shipping = shipMethod.getShippingMethod();
			
			System.out.println("Order status: " + order.getStatus());
			System.out.print("Total + Shipping: " + totalPrice);
			System.out.println(" USD");
			System.out.println("Weight: " + ordrCart.getWeight());
			System.out.println("Shipping Method: " + shipping);
			System.out.println("");
		}
		
//		for(Product prod : inventory.getListOfProducts()) {
//			System.out.println("Available: " + prod.getAvailable());
//		};
//		
//		for(Product prod : order1.getCart().getProducts()) {
//			System.out.println(prod);
//		}
			
			orderList.reset(); //Resets the postion on the OrderList.
		while(orderList.hasNext()) {
			Order order = orderList.next();
			//order.changeStatusToShipped();

			System.out.println("Order status: " + order.getStatus());
		}
		
		List<Product> listcart1 = order1.getCart().getProducts();
		
		System.out.println("");
		for(Product prod : inventory.getListOfProducts()) {
			order1.getCart().removeProductFromCart(prod);			
		}
		
		System.out.println("");
		
		for(Product prod : order1.getCart().getProducts()) {
			System.out.println(prod.getAvailable());
		}
		
		for(Product prod : order2.getCart().getProducts()) {
			System.out.println(prod.getAvailable());
		};
	}
}
