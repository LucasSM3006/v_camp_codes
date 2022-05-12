package v_camp.builder.entities;

public class Book extends Product {
	private String bookName;
	private int pageCount;
	
	public Book(double weight, double price, int sku, String bookName, int pageCount) {
		super(weight, price, sku);
		this.bookName = bookName;
		this.pageCount = pageCount;
	}
	
	public String getBookName() {
		return bookName;
	}
	
	public int getPageCount() {
		return pageCount;
	}
	
	public String printInfo() {
		String info = "Book info: " + "\n";
        info += "Weight: " + this.getWeight() + "\n";
        info += "Price: " + this.getPrice() + "\n";
        info += "Sku: " + this.getSku() + "\n";
        info += "Name: " + getBookName() + "\n";
        info += "Pages: " + getPageCount() + "\n";
        
        return info;
	}
}
