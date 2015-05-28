package martink.us.deliveryService;

public class BussinesClient extends Client{
	private double price;
	
	public double getPrice(){
		return price = getWeight() * 10;
	}
}