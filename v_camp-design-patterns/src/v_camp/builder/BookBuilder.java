package v_camp.builder;

import v_camp.builder.entities.Book;
import v_camp.builder.entities.ProductType;

public class BookBuilder implements Builder {
	private double weight;
	private double price;
	private int sku;
	private String bookName;
	private int pageCount;
	
	@Override
	public void setWeight(double weight) {
		if(weight < 0) throw new IllegalArgumentException("Weight below zero.");
		else this.weight = weight;
	}

	@Override
	public void setPrice(double price) {
		if(price <= 0) throw new IllegalArgumentException("Price below or equal to zero.");
		else this.price = price;
	}

	@Override
	public void setSku(int sku) {
		this.sku = sku;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	public Book getResult() {
		if(weight == 0 || price == 0 || sku == 0 || bookName == null || pageCount == 0) throw new NullPointerException("Null values for book.");
		return new Book(weight, price, sku, ProductType.Book, bookName, pageCount);
	}
}
