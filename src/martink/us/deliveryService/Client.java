package martink.us.deliveryService;

import java.io.Serializable;

/**
* Class for manipulating functions with client's object.
*
* @author  Tadas Martinkus
* @version 1.0
* @since   2015-04-20
*/
public abstract class Client implements Serializable{
	private String name;
	private String phone;
	private String address;
	private int id;
	private int priority;
	private int type;
	private int process;
	private double weight;
	//set
	/**
	 *	Sets or changes client's type
	 *
	 * @param newType client type. True for "Privatus", false for "Verslo" 
	 */
	public void setType(int newType){
		this.type = newType;
	}
	/**
	 *	Sets or changes client's name
	 *
	 * @param newName	client's name 
	 */
	public void setName(String newName){
		this.name = newName;
	}
	/**
	 *	Sets or changes client's phone number
	 *
	 * @param newPhone	client's phone 
	 */
	public void setPhone(String newPhone){
		this.phone = newPhone;
	}
	/**
	 *	Sets or changes client's delivery address
	 *
	 * @param newAddress client's address
	 */
	public void setAdress(String newAddress){
		this.address = newAddress;
	}
	/**
	 *	Sets or changes package priority
	 *
	 * @param newPriority package priority. True for "Ne", false for "Taip"
	 */
	public void setPriority(int newPriority){
		this.priority = newPriority;
	}
	/**
	 *	Sets or changes package weight
	 *
	 * @param newWeight	package weight
	 */
	public void setWeight(double newWeight){
		this.weight = newWeight;
	}
	/**
	 *	Sets or changes delivery process 
	 *
	 * @param newProcess Possible variants "Patvirtinta", "Laukiama", "Atsaukta". 
	 */
	public void setProcess(int newProcess){
		this.process = newProcess;
	}
	/**
	 *	Sets or changes client's id. By default it is random generated. It cannot by same as other client's id.
	 *
	 * @param newID	client's id 
	 */
	public void setID(int newID){
		this.id = newID;
	}	

	//get
	
	/**
	 * Gives type of the client 
	 * 
	 * @return type of the client. True for "Privatus", false for "Verslo" 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * Gives name of the client
	 * @return name of the client.
	 */	
	
	public String getName(){
		return name;
	}
	/**
	 * Gives phone of the client
	 * @return phone of the client.
	 */	
	public String getPhone(){
		return phone;
	}
	/**
	 * Gives address of the client.
	 * @return address of the client.
	 */	
	public String getAdress(){
		return address;
	}
	/**
	 * Gives priority of the client.
	 * @return priority of the client. True for "Ne", false for "Taip"
	 */	
	public int getPriority(){
		return priority;
	}
	/**
	 * Gives weight of the package. 
	 * @return weight of the package. 
	 */
	public double getWeight(){
		return weight;
	}
	/**
	 * Gives process of the delivery.
	 * @return process of the delivery. 
	 */
	public int getProcess(){
		return process;
	}
	/**
	 * Gives id of the client. 
	 * @return id of the client. 
	 */
	public int getID(){
		return id;
	}
	
    //poli
	/**
	 * 
	 * @return delivery price which needs to be paid by client.
	 */
 	public abstract double getPrice();

 	// hash and equals
 	
	@Override
	public int hashCode() {
		return id;
	}
	@Override
	public boolean equals(Object client) {
		if(client instanceof Client && this.id == ((Client) client).getID()) {
			return true;
		}
		else {
			return false;					
		}
	}
}
