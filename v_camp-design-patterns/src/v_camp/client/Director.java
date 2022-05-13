package v_camp.client;

import v_camp.builder.BookBuilder;
import v_camp.builder.ComputerBuilder;
import v_camp.builder.ShoeBuilder;
import v_camp.builder.SkirtBuilder;
//import v_camp.builder.entities.Book;

public class Director {
	
    public void constructBook(BookBuilder builder) {
        builder.setWeight(0.5);
        builder.setPrice(9.50);
        builder.setSku(1);
        builder.setBookName("Clean code");
        builder.setPageCount(400);
    }

    public void constructComputer(ComputerBuilder builder) {
        builder.setWeight(2.75);
        builder.setPrice(350.00);
        builder.setSku(2);
        builder.setCpu("Intel i5 - 7700");
        builder.setGpu("RX 570 4Gb");
    }

    public void constructSkirt(SkirtBuilder builder) {
        builder.setWeight(0.2);
        builder.setPrice(10.50);
        builder.setSku(3);	
        builder.setSize(38);
        builder.setBrand("Generic");
    }
   
    public void constructShoe(ShoeBuilder builder) {
        builder.setWeight(0.5);
        builder.setPrice(9.50);
        builder.setSku(4);
        builder.setSize(35.5);
        builder.setMaterial("Leather");
    } 
}
