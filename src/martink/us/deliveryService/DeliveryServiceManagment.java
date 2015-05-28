package martink.us.deliveryService;

public interface DeliveryServiceManagment {
	
	public int createClient(String name, String phone, String adress,double weight, int priority, int type, int process);
	public boolean deleteClient(Client client) throws Exception;
	
	public int getArraySize();	
	 
	public Client showItem(int index);
	public int getId(Client temp);	
	public Client getClient(int id);
	public boolean checkId(int id);
	
	public double ernedMoney();
	public double predictedMoney();
	
	public void updateProcess(int i, Client temp) throws Exception;
	public void loadDataSQL () throws Exception;
	public void saveData(String fileName);
}