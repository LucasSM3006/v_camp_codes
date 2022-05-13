package v_camp.factory;

public class RoadShippingCreator extends ShippingCreator {

	@Override
	public Shipping createShipping() {
		return new Road();
	}

}
