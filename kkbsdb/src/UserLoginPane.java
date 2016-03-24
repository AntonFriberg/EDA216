

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;

/**
 * The GUI pane where a new user logs in. Contains a text field where the user
 * id is entered and a button to log in.
 */
public class UserLoginPane extends BasicPane{
	private static final long serialVersionUID = 1;
	/**
	 * The text field where the user id is entered.
	 */
	private JTextField[] fields;

	/**
	 * The number of the field where the user id is entered.
	 */
	private static final int USER_ID = 0;

	/**
	 * A label showing the name of the current user.
	 */
	private JLabel chosenCookieNameLabel;

	/**
	 * The list model for the movie name list.
	 */
	private DefaultListModel<String> cookieListModel;

	/**
	 * The movie name list.
	 */
	private JList<String> cookieList;

	/**
	 * The number of the movie name field.
	 */
	private static final int PALLET_NBR = 0;

	/**
	 * The number of the performance date field.
	 */
	private static final int DATE = 1;

	/**
	 * The number of the movie theater field.
	 */
	private static final int TIME = 2;



	/**
	 * The total number of fields.
	 */
	private static final int NBR_FIELDS = 3;


	private JPanel chosenPanel;
	public UserLoginPane(Database db) {
		
		super(db);

	}

	/**
	 * Create the top panel, consisting of the text field.
	 * 
	 * @return The top panel.
	 */
	//test
	final static String BUTTONPANEL = "Card with JButtons";
    final static String TEXTPANEL = "Card with JTextField";
    
	public JComponent createMiddlePanel() {
		
		cookieListModel = new DefaultListModel<String>();

		cookieList = new JList<String>(cookieListModel);
		cookieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cookieList.setPrototypeCellValue("123456789012");
		cookieList.addListSelectionListener(new NameSelectionListener());
		JScrollPane p1 = new JScrollPane(cookieList);
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,1));
		p.add(p1);
		return p;
	}
	

	/**
	 * Create the bottom panel, consisting of the login button and the message
	 * line.
	 * 
	 * @return The bottom panel.
	 */
	public JComponent createBottomPanel() {
		//test
		String[] texts = new String[NBR_FIELDS];
		texts[USER_ID] = "User id";
		fields = new JTextField[NBR_FIELDS];
		fields[USER_ID] = new JTextField(20);
		//test
		JButton[] buttons = new JButton[1];
		buttons[0] = new JButton("Login");
		ActionHandler actHand = new ActionHandler();
		fields[USER_ID].addActionListener(actHand);
		return new ButtonAndMessagePanel(buttons, messageLabel, actHand);
	}
	
	public JComponent createLeftPanel(){
	    JPanel p=new JPanel();
	    p.setLayout(new GridLayout(2, 1));
	    JPanel comboBoxPane = new JPanel(); //use FlowLayout
        String comboBoxItems[] = { BUTTONPANEL, TEXTPANEL };
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(new ItemHandler());
        comboBoxPane.add(cb);
        
        //Create the "cards".
        JPanel card1 = new JPanel();
        card1.setLayout(new GridLayout(3, 1));
        card1.add(new JButton("Button 1"));
        card1.add(new JButton("Button 2"));
        card1.add(new JButton("Button 3"));
        
        JPanel card2 = new JPanel();
        card2.add(new JTextField("TextField", 20));
        
        //Create the panel that contains the "cards".
        chosenPanel = new JPanel(new CardLayout());
        chosenPanel.add(card1, BUTTONPANEL);
        chosenPanel.add(card2, TEXTPANEL);
        //p.setLayout(new GridLayout(2, 1));
        p.add(comboBoxPane, BorderLayout.PAGE_START);
        p.add(chosenPanel, BorderLayout.CENTER);
		//test
		return p;
	}

	/**
	 * Perform the entry actions of this pane, i.e. clear the message line.
	 */
	public void entryActions() {
		clearMessage();
	}

	/**
	 * A class which listens for button clicks.
	 */
	class ActionHandler implements ActionListener {
		/**
		 * Called when the user clicks the login button. Checks with the
		 * database if the user exists, and if so notifies the CurrentUser
		 * object.
		 * 
		 * @param e
		 *            The event object (not used).
		 */
		public void actionPerformed(ActionEvent e) {
			String userId = fields[USER_ID].getText();
			/* --- insert own code here --- */
		}
	}
	
	class ItemHandler implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			CardLayout c=(CardLayout) chosenPanel.getLayout();
			c.show(chosenPanel, (String) e.getItem());
		}
		
	}
	
	/**
	 * A class that listens for clicks in the name list.
	 */
	class NameSelectionListener implements ListSelectionListener {
		/**
		 * Called when the user selects a name in the name list. Fetches
		 * performance dates from the database and displays them in the date
		 * list.
		 * 
		 * @param e
		 *            The selected list item.
		 */
		public void valueChanged(ListSelectionEvent e) {
			if (cookieList.isSelectionEmpty()) {
				return;
			}
			String cookieName = cookieList.getSelectedValue();
			/* --- insert own code here --- */
		}
	}
	
}
