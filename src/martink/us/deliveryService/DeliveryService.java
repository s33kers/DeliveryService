package martink.us.deliveryService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Service for creating your own delivery service company and manipulate actions
 * like creating new customer, accepting offer and etc.
 *
 * @author Tadas Martinkus
 * @version 1.0
 * @since 2015-04-20
 */

public class DeliveryService implements DeliveryServiceManagment {
	ArrayList<Client> clientList = new ArrayList<>();
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet result = null;
	PreparedStatement preparedStmt = null;
	
	String string;
	int integerr = 0;
	double doublee;
	
	String driver = "com.mysql.jdbc.Driver";
	String myURL = "jdbc:mysql://gluosnis.serveriai.lt/";
	String name = "martink";
	String password = "gaidys1379";
	public void loadDataSQL () throws Exception
	{
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(myURL,name,password);
			statement = connect.createStatement();
			result = statement.executeQuery("select * from martink_DeliveryService.orders");
			while(result.next()){
				Client temp = null;
				if (result.getInt(8) == 0) {
					temp = new PrivateClient();
				} else {
					temp = new BussinesClient();
				}
				integerr = result.getInt(1);
				temp.setID(integerr);
				string = result.getString(2);
				temp.setName(string);
				string = result.getString(3);
				temp.setPhone(string);
				string = result.getString(4);
				temp.setAdress(string);
				integerr = result.getInt(5);
				temp.setProcess(integerr);
				string = result.getString(6);
				temp.setWeight(Double.parseDouble(string));
				integerr = result.getInt(7);
				temp.setPriority(integerr);
				integerr = result.getInt(8);
				temp.setType(integerr);
				
				clientList.add(temp);
			}
		} catch (Exception e) {
		      throw e;
	    } finally {
	      close();
	    }
	}
	private void close() {
	    try {
	        if (result != null) {
	        	result.close();
	        }

	        if (statement != null) {
	          statement.close();
	        }

	        if (connect != null) {
	          connect.close();
	        }
	      } catch (Exception e) {

	      }
	    }
	public void saveData(String fileName) {
		fileName += ".csv";
		final String NEW_LINE_SEPARATOR = "\n";
		final String QUOTE = "\"";

		final String FILE_HEADER = "id,name,phone,address,weight,priority,type,proccess";
		FileWriter writer = null;

		try {
			writer = new FileWriter(fileName);
			writer.append(FILE_HEADER.toString());
			writer.append(NEW_LINE_SEPARATOR);

			for (Client temp : clientList) {
				writeToFile(writer, Integer.toString(temp.getID()), QUOTE);
				writeToFile(writer, temp.getName(), QUOTE);
				writeToFile(writer, temp.getPhone(), QUOTE);
				writeToFile(writer, temp.getAdress(), QUOTE);
				writeToFile(writer, Double.toString(temp.getWeight()), QUOTE);
				String i;
				if (temp.getPriority() == 1) {
					i = ("Taip");
				} else {
					i = ("Ne");
				}
				writeToFile(writer, i, QUOTE);

				if (temp.getType() == 0) {
					i = ("Privatus");
				} else {
					i = ("Verslo");
				}
				writeToFile(writer, i, QUOTE);
				if(temp.getProcess()  == 0){
					i = "Atsaukta";
				}
				if(temp.getProcess()  == 1){
					i = "Laukiama";
				}
				if(temp.getProcess()  == 2){
					i = "Patvirtinta";
				}
				writeToFile(writer, i, QUOTE);
				writer.append(NEW_LINE_SEPARATOR);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void writeToFile(FileWriter writer, String dataOut,
			String quote) throws IOException {

		if (dataOut.contains("\"")) {
			writer.append(quote);
			writer.append(dataOut.replace("\"", "\"\""));
			writer.append(quote);

		} else if (dataOut.contains(";")) {
			writer.append(quote);
			writer.append(dataOut);
			writer.append(quote);
		} else {
			writer.append(dataOut);
		}

		writer.append(";");

	}

	/**
	 * Checks if exists client with given ID
	 *
	 *
	 * @param id
	 *            id which needs to be checked
	 * @return true if id exists, otherwise false
	 */
	public boolean checkId(int id) {
		for (Client temp : clientList) {
			if (temp.hashCode() == id) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gives number of clients
	 *
	 *
	 * @return size of clients
	 */
	public int getArraySize() {
		return clientList.size();
	}

	/**
	 * Gives id of client
	 *
	 * @param temp
	 *            client object
	 * @return id of the client
	 */
	public int getId(Client temp) {
		return temp.getID();
	}

	/**
	 * Creating new client
	 *
	 *
	 * @param name
	 *            name of client
	 * @param phone
	 *            phone of client
	 * @param adress
	 *            address of client
	 * @param weight
	 *            weight of package
	 * @param priority
	 *            priority of package
	 * @param type
	 *            type of client
	 * @param process
	 *            process of package. Possible variants "Laukiama", "Atsaukta",
	 *            "Patvirtinta".
	 * 
	 * @return client's ID
	 */
	public int createClient(String name, String phone, String adress,
			double weight, int priority, int type, int process) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int idInteger = rand.nextInt(100000);
		for (Client temp : clientList) {
			if (idInteger == temp.getID()) {
				rand = new Random();
				idInteger = rand.nextInt(100000);
			}
		}

		Client client;
		if (type == 0) {
			client = new PrivateClient();
			client.setType(0);
		} else {
			client = new BussinesClient();
			client.setType(1);
		}

		client.setName(name);
		client.setPhone(phone);
		client.setAdress(adress);
		client.setPriority(priority);
		client.setWeight(weight);
		client.setProcess(process);
		client.setID(idInteger);

		clientList.add(client);

		return idInteger;
	}

	/**
	 * 
	 * Returns client object by index in array. Using for example in listmodel.
	 *
	 * @param index
	 *            index of client in array
	 * @return client object
	 */
	public Client showItem(int index) {
		// TODO Auto-generated method stub
		return clientList.get(index);
	}

	/**
	 * 
	 * Detects client by object and deletes him.
	 *
	 * @param client
	 *            client object
	 * @return true if client deleted, false otherwise
	 * @throws Exception 
	 */
	public boolean deleteClient(Client client) throws Exception {
		// TODO Auto-generated method stub
		Class.forName(driver);
		connect = DriverManager.getConnection(myURL,name,password);
		String query = "delete from martink_DeliveryService.orders where id = ?";
		preparedStmt = connect.prepareStatement(query);
		preparedStmt.setInt   (1, client.getID());
	    
	    preparedStmt.executeUpdate();
	       
	    connect.close();
		if (client == null) {
			return false;
		} else {
			clientList.remove(client);
			return true;
		}

	}

	/**
	 * 
	 * Gives client object by his id.
	 *
	 * @param id
	 *            client id
	 * @return client object or null if client doesn't exist.
	 */
	public Client getClient(int id) {

		for (Client temp : clientList) {
			if (temp.hashCode() == id) {
				return temp;
			}
		}
		return null;
	}

	/**
	 * 
	 * Money which will be possible earned. Otherwise all client's money with
	 * status "Laukiama".
	 *
	 * @return double of money
	 */
	public double predictedMoney() {
		double predictedMoney = 0;
		for (Client temp : clientList) {
			if (temp.getProcess() == 1) {
				predictedMoney += temp.getPrice();
			}
		}

		return predictedMoney;
	}

	/**
	 * 
	 * Money which are earned. Otherwise all client's money with status
	 * "Patvirtinta".
	 *
	 * @return double of money
	 */
	public double ernedMoney() {
		double ernedMoney = 0;
		for (Client temp : clientList) {
			if (temp.getProcess() == 2) {
				ernedMoney += temp.getPrice();
			}
		}

		return ernedMoney;

	}
	public void updateProcess(int i, Client temp) throws Exception {
		// TODO Auto-generated method stub
		int id = temp.getID();
		
		Class.forName(driver);
		connect = DriverManager.getConnection(myURL,name,password);
		String query = "update martink_DeliveryService.orders set process = ? where id = ?";
		preparedStmt = connect.prepareStatement(query);
		preparedStmt.setInt   (1, i);
	    preparedStmt.setInt  (2, id);
	    
	    preparedStmt.executeUpdate();
	       
	    connect.close();
	}

}
