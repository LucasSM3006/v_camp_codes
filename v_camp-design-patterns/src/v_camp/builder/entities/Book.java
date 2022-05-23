package v_camp.builder.entities;

public class Book extends Product {
	private String bookName;
	private int pageCount;
	
	public Book(double weight, double price, int sku, ProductType type, String bookName, int pageCount) {
		super(weight, price, sku, type);
		if(weight == 0 || price == 0 || sku == 0 || type == null || bookName == null || pageCount == 0) throw new NullPointerException("Null values for book.");
		this.bookName = bookName;
		this.pageCount = pageCount;
	}
	
	public String getBookName() {
		return bookName;
	}
	
	public int getPageCount() {
		return pageCount;
	}
}
