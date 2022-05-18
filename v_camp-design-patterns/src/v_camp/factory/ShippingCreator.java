package v_camp.factory;

public abstract class ShippingCreator {

	public static Shipping getShipping(double totalWeight) {		
		ShippingCreator shippingMethod;
		
		if(totalWeight > 10.0) {
			shippingMethod = new RoadShippingCreator();
			return shippingMethod.createShipping();
		}
		else {
			shippingMethod = new AeroShippingCreator();
			return shippingMethod.createShipping();
		}
	}
	
	public abstract Shipping createShipping();
}
