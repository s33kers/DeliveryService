package martink.us.deliveryService;

public class PrivateClient extends Client {
	private double price;
	
	public double getPrice(){
		int priority = 0;
		if(getPriority() == 0)
		{
			priority = 2;
		}
		return price = getWeight() * 10 * 1.21 + priority;
	}
}
