package martink.us.deliveryService;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.xml.ws.Service;

public class CompanyUI{
	
	private JButton confirm;
	private JButton decline;
	private JButton delete;
	private JButton export;
	private JScrollPane scrollPanel;
	private JList list;
	private JLabel name = new JLabel();
	private JLabel weight= new JLabel();
	private JLabel adress= new JLabel();
	private JLabel number= new JLabel();
	private JLabel priority= new JLabel();
	private JLabel type= new JLabel();
	private JLabel id= new JLabel();
	private JLabel process= new JLabel();
	private String prio = new String();
	private JLabel price = new JLabel();
	DefaultListModel<String> model;
	private JLabel haveMoneyLabel = new JLabel();
	private JLabel predictedMoneyLabel = new JLabel();

	public CompanyUI(JFrame frame, JPanel mainPanel, DeliveryService service) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setVisible(true);		
		frame.add(panel);
		init(panel,mainPanel, service);
	}
	
	private void init(JPanel panel, JPanel mainPanel, DeliveryService service){
		
		confirm = new JButton("Patvirtinti");
		decline = new JButton("Atsaukti");
		delete = new JButton("Istrinti");
		export = new JButton("I CSV");
		
		profit(service);
		confirm.setVisible(false);
		decline.setVisible(false);
		delete.setVisible(false);
		
		confirm.setMaximumSize(new Dimension(Integer.MAX_VALUE, confirm.getMinimumSize().height));
		decline.setMaximumSize(new Dimension(Integer.MAX_VALUE, decline.getMinimumSize().height));
		delete.setMaximumSize(new Dimension(Integer.MAX_VALUE, delete.getMinimumSize().height));
		export.setMaximumSize(new Dimension(Integer.MAX_VALUE, export.getMinimumSize().height));
	
		list = new JList();
		list.setModel(MainUI.listModel);
		list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		scrollPanel = new JScrollPane();
		scrollPanel.setPreferredSize(new Dimension(300, 100));
		scrollPanel.setViewportView(list);
		
		JPanel LSpanel=  new JPanel();
		JPanel PEpanel=  new JPanel();
		JPanel info =  new JPanel();
		
		LSpanel.setLayout(new BoxLayout(LSpanel, BoxLayout.PAGE_AXIS));
		LSpanel.setPreferredSize(new Dimension(94, 200));
		LSpanel.add(confirm);
		LSpanel.add(decline);
		LSpanel.add(delete);
		LSpanel.add(export);
		LSpanel.add(haveMoneyLabel);
		LSpanel.add(predictedMoneyLabel);
	
		PEpanel.setPreferredSize(new Dimension(400,170));
    	info.setLayout(new BoxLayout(info, BoxLayout.PAGE_AXIS));
    	
    	PEpanel.add(info);	
    	info.setVisible(false);  	
		info.add(name);
		info.add(number);
		info.add(adress);
		info.add(weight);
		info.add(priority);
		info.add(price);
		info.add(type);
		info.add(id);	
		info.add(process);
		
		panel.add(PEpanel, BorderLayout.PAGE_END);
		panel.add(scrollPanel, BorderLayout.LINE_END);
		panel.add(LSpanel, BorderLayout.LINE_START);
	decline.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	 String processString = new String("Atsaukta");
        	update(processString,service,0);
        	}
    });
	delete.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	int index = list.getSelectedIndex();
        	Client client =  service.showItem(index);
        	boolean deletedItem = false;
			try {
				deletedItem = service.deleteClient(client);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	profit(service);
        	if(deletedItem == true)
        	{
        			MainUI.listModel = new DefaultListModel<>();
        		    for(int i = 0; i < service.getArraySize(); i++)
        		    {
        		    	Client temp = service.showItem(i);
        		
        		    	MainUI.listModel.addElement(temp.getName());
        		    }    
        		    list.setModel(MainUI.listModel);     
        		    
        	}
        	
        	}
    });
	confirm.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
       	 String processString = new String("Patvirtinta");
       	 update(processString,service,2);   
        	}
    });
	list.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
        public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
        	if(list.getSelectedIndex() >= 0){
        	String string;
        	Client temp = service.showItem(list.getSelectedIndex());
           	
        	
            name.setText("Vardas Pavarde: " + temp.getName());
            number.setText("Gavejo telefono numeris: " + temp.getPhone());
        	adress.setText("Gavejo adresas: " + temp.getAdress());
        	weight.setText("Prekes svoris, kg: " + temp.getWeight() ); 
        	if(temp.getPriority() == 0)
        	{
        		string = "Ne";
        	}else
        	{
        		string = "Taip";
        	}
        	priority.setText("Skubos tvarka: " + string );     				
        	price.setText("Kaina: " + temp.getPrice());
        	if(temp.getType() == 1)
        	{
        		string = "Verlso";
        	}else
        	{
        		string = "Privatus";
        	}        	
        	type.setText("Klientas: " + string);
    		id.setText("ID: " + temp.getID());
        	if(temp.getProcess() == 0)
        	{
        		string = "Atsaukta";
        	}
        	if(temp.getProcess() == 1)
        	{
        		string = "Laukiama";
        	}
        	if(temp.getProcess() == 2)
        	{
        		string = "Patvirtinta";
        	}
        	process.setText("Busena: " +  string);
    		
        	info.setVisible(true);
        	confirm.setVisible(true);
    		decline.setVisible(true);
    		delete.setVisible(true);
        }else
        {
        	name.setText("");
    		weight.setText("");
    		adress.setText("");
    		number.setText("");
    		priority.setText("");
    		process.setText("");
    		price.setText("");
    		type.setText("");
    		id.setText("");
    		info.setVisible(false);
        	confirm.setVisible(false);
    		decline.setVisible(false);
    		delete.setVisible(false);
        }
      }
    });	
	export.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	service.saveData("data");
        	}
    });
	
	}
	private void update(String processString, DeliveryService service, int i)
	{
		Client temp =  service.showItem(list.getSelectedIndex());
		try {
			service.updateProcess(i,temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		temp.setProcess(i);
		process.setText("Busena: " + processString);
		profit(service);
	}
	private void profit(DeliveryService service)
	{
		DecimalFormat numberFormat = new DecimalFormat("0.00");
		haveMoneyLabel.setText("Uzdirbta: " + numberFormat.format(service.ernedMoney()));
		predictedMoneyLabel.setText("Bus: " + numberFormat.format(service.predictedMoney()));
	}
}