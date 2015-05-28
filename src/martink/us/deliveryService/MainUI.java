package martink.us.deliveryService;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainUI{
	private DeliveryService service;
	public static  DefaultListModel listModel = new DefaultListModel<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainUI frame = new MainUI();
	}
	public MainUI(){
		
		service = new DeliveryService();
		try {
			service.loadDataSQL();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}			
		int a = service.getArraySize();
		for(int i = 0; i < a; i++)
		{
			Client temp = service.showItem(i);
			listModel.addElement(temp.getName());
		}
		JFrame frame = new JFrame("Delivery service"); 
		JPanel panel = new JPanel(new BorderLayout());
		JPanel PSpanel = new JPanel();
		JPanel Cpanel = new JPanel();
		JPanel PEpanel = new JPanel();

		JLabel nameJLabel = new JLabel("Vardas");
		JLabel passwordJLabel = new JLabel("Slaptazodis");
		
		JButton connect = new JButton("Prisijungti");
		connect.setPreferredSize(new Dimension(100,30));
		
		JTextField nameJTextField = new JTextField();
		nameJTextField.setColumns(10);
		JTextField passwordJTextField = new JTextField();
		passwordJTextField.setColumns(10);
		
		
		JLabel proccess = new JLabel();
		proccess.setVisible(false);
		proccess.setFont (proccess.getFont ().deriveFont (20.0f));
		
		Cpanel.add(nameJLabel);
		Cpanel.add(nameJTextField);		
		Cpanel.add(passwordJLabel);	
		Cpanel.add(passwordJTextField);
		Cpanel.add(connect);
		PEpanel.add(proccess);
		panel.add(PSpanel, BorderLayout.PAGE_START);
		panel.add(Cpanel, BorderLayout.CENTER);
		panel.add(PEpanel, BorderLayout.PAGE_END);
		
	    frame.add(panel);  
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(400,400);
	    frame.setVisible(true);
	    frame.setResizable(false);
	     
	     connect.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	proccess.setVisible(true);
	            	String name = nameJTextField.getText();
	            	String password = passwordJTextField.getText();
	            	
	            	if(name.equals("admin") && password.equals("123")){
	            		connect.setVisible(false);
	            		panel.setVisible(false);
	            		new CompanyUI(frame,panel,service);
	            	}else
	            	{
	            		proccess.setText("Blogas vardas ar slaptazodis.");
	            		proccess.setVisible(true);
	            		nameJTextField.setText("");
	            		passwordJTextField.setText("");
	            	}
	            }
	        });

	
	}


}