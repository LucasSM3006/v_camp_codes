package factorytest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import v_camp.factory.Shipping;
import v_camp.factory.ShippingCreator;
import v_camp.iterator.OrderList;
import v_camp.singleton.ProductInventory;

public class FactoryTest {
	
	@Before
	public void setup() {
		ProductInventory inv = ProductInventory.getInstance();
		inv.resetInventory();
	}
	
	@Test
	public void ShouldBeAEROWhenWeightBelowTen() {
		//Setup
		double weight = 9.9;
		
		//action
		Shipping shipping = ShippingCreator.getShipping(weight);
		
		Assert.assertTrue(shipping.getShippingMethod() == "Aero");
	}
	
	@Test
	public void ShouldBeROADWhenWeightAboveTen() {
		//Setup
		double weight = 10.1;
		
		//action
		Shipping shipping = ShippingCreator.getShipping(weight);
		
		Assert.assertTrue(shipping.getShippingMethod() == "Road");
	}
}
