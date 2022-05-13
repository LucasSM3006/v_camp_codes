package v_camp.factory;

public class AeroShippingCreator extends ShippingCreator {

	@Override
	public Shipping createShipping() {
		return new Aero();
	}

}
