package v_camp.factory;

public abstract class ShippingCreator {

	public Shipping getShipping() {
		Shipping shipping = createShipping();
		
		return shipping;
	}
	
	public abstract Shipping createShipping();
}
