
//http://www.codejava.net/java-se/swing/how-to-use-jdatepicker-to-display-calendar-component
//s�h�r skriver man f�r att skriva ut meddelanden i understa panelen.
//displayMessage("Pallet was succesfully blocked!");

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 * The GUI pane where a new user logs in. Contains a text field where the user
 * id is entered and a button to log in.
 */
public class SearchPane extends BasicPane {
	private static final long serialVersionUID = 1;

	private ArrayList<Pallet> pallets;

	/**
	 * The list model for the movie name list.
	 */
	// m�ste d�pas om, kanske om man orkar fixa en modell-klass
	private DefaultListModel<String> cookieListModel;

	/**
	 * The movie name list.
	 */
	// samma som ovan
	private JList<String> cookieList;

	private JPanel chosenPanel;

	public SearchPane(Database db) {
		super(db);
		pallets = new ArrayList<Pallet>();

	}

	/**
	 * Create the top panel, consisting of the text field.
	 * 
	 * @return The top panel.
	 */
	// test
	final static String CHOOSEPANEL = "Please choose searching method";
	final static String TEXTPANEL = "Search by pallet number";
	final static String CALENDERPANEL = "Search pallet by date";
	final static String ALLPANEL = "Search all pallets";

	public JComponent createTopPanel() {
		JPanel p1 = new JPanel();

		JButton b1 = new JButton("Details");
		JButton b2 = new JButton("Block");
		JButton b3 = new JButton("Unblock");

		b1.addActionListener(new DetailActionListener());
		b2.addActionListener(new BlockActionListener());
		b3.addActionListener(new BlockActionListener());

		p1.add(b1);
		p1.add(b2);
		p1.add(b3);

		JPanel p = new JPanel();
		p.add(p1);

		return p;
	}

	public JComponent createMiddlePanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 1));
		cookieListModel = new DefaultListModel<String>();
		cookieList = new JList<String>(cookieListModel);
		cookieList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		cookieList.setPrototypeCellValue("123456789012");
		cookieList.setFont(new Font("MONOSPACED", Font.PLAIN, 14));
		JScrollPane p2 = new JScrollPane(cookieList);
		p2.setPreferredSize(new Dimension(340, 0));
		p.add(p2);
		return p;
	}

	/**
	 * Create the bottom panel, consisting of the login button and the message
	 * line.
	 * 
	 * @return The bottom panel.
	 */
	public JComponent createBottomPanel() {
		JButton[] buttons = new JButton[1];
		buttons[0] = new JButton("Search");
		ActionHandler actHand = new ActionHandler();
		return new ButtonAndMessagePanel(buttons, messageLabel, actHand);
	}

	public JComponent createLeftPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		JPanel comboBoxPane = new JPanel(); // use FlowLayout
		String comboBoxItems[] = { CHOOSEPANEL, TEXTPANEL, CALENDERPANEL, ALLPANEL };
		JComboBox<String> cb = new JComboBox<String>(comboBoxItems);
		cb.setEditable(false);
		cb.addItemListener(new ItemHandler());
		comboBoxPane.add(cb);

		// Create the "cards".
		JPanel card1 = new JPanel();
		card1.setName("Empty");

		JPanel card2 = new JPanel();
		card2.setName("Number");
		card2.setLayout(new GridLayout(2, 1));
		JTextField text = new JTextField("Please enter the pallet number");
		text.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		text.setEditable(false);
		card2.add(text);
		card2.add(new JTextField("", 20));

		JPanel card3 = new JPanel();
		card3.setLayout(new GridLayout(4, 1));
		card3.setName("Date");
		JTextField startText = new JTextField("Date from");
		startText.setEditable(false);
		startText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		JTextField endText = new JTextField("Date to");
		endText.setEditable(false);
		endText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		UtilDateModel startModel = new UtilDateModel();
		JDatePanelImpl startDatePanel = new JDatePanelImpl(startModel);
		JDatePickerImpl startDatePicker = new JDatePickerImpl(startDatePanel);
		UtilDateModel endModel = new UtilDateModel();
		JDatePanelImpl endDatePanel = new JDatePanelImpl(endModel);
		JDatePickerImpl endDatePicker = new JDatePickerImpl(endDatePanel);
		card3.add(startText);
		card3.add(startDatePicker);
		card3.add(endText);
		card3.add(endDatePicker);

		JPanel card4 = new JPanel();
		card4.setName("All");
		JTextField text1 = new JTextField("Search for all pallets by clicking search");
		text1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		text1.setEditable(false);
		card4.add(text1);

		// Create the panel that contains the "cards".
		chosenPanel = new JPanel(new CardLayout());
		chosenPanel.add(card1, CHOOSEPANEL);
		chosenPanel.add(card2, TEXTPANEL);
		chosenPanel.add(card3, CALENDERPANEL);
		chosenPanel.add(card4, ALLPANEL);

		p.add(comboBoxPane, BorderLayout.PAGE_START);
		p.add(chosenPanel, BorderLayout.CENTER);
		// test
		return p;
	}

	/**
	 * Perform the entry actions of this pane, i.e. clear the message line.
	 */
	public void entryActions() {
		clearMessage();
		cookieListModel.removeAllElements();
		pallets = new ArrayList<Pallet>();
	}

	/**
	 * A class which listens for button clicks.
	 */
	// Denna sk�ter searchknappen
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
			// M�ste h�mta fr�n f�lden h�r
			entryActions();
			JPanel card = null;
			for (Component comp : chosenPanel.getComponents()) {
				if (comp.isVisible() == true) {
					card = (JPanel) comp;
				}
			}
			if (card.getName().equals("Date")) {
				Date startDate = (Date) ((JDatePickerImpl) card.getComponent(1)).getModel().getValue();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String startDateFormatted = "";
				if (startDate != null) {
					startDateFormatted = df.format(startDate);
				} else {
					displayMessage("Please choose a date before searching");
					return;
				}
				Date endDate = (Date) ((JDatePickerImpl) card.getComponent(3)).getModel().getValue();
				if (endDate != null) {
					String endDateFormatted = df.format(endDate);
					db.searchByDate(startDateFormatted, endDateFormatted, pallets);
				} else {
					// H�r hanterar vi om man bara s�ker p� ett datum.
					db.searchByDate(startDateFormatted, pallets);
				}

			} else
			// H�mtar textf�ltet n�r man s�ker efter numret
			if (card.getName().equals("Number")) {
				JTextField input = (JTextField) card.getComponent(1);
				db.searchById(input.getText(), pallets);
				input.setText("");
			} else if (card.getName().equals("All")) {
				db.searchByAll(pallets);

			}
			/* --- insert own code here --- */
			for (Pallet p : pallets) {
				String format = "%1$-5d %2$-10s %3$-18s";
				String blocked = p.isBlocked() != null ? "Y" : "N";
				cookieListModel.addElement(String.format(format, p.getPalletNbr(),
						"| " + (p.getBakeDate().split(" "))[0], "| " + p.getCookieName()) + "| " + blocked);
			}
			displayMessage("The search result is on the form \"Pallet ID | Production date | "
					+ "Cookie name | Blocked Y/N? \"");
			cookieList.setModel(cookieListModel);
		}
	}

	// Denna klassen hanterar de olika korten
	class ItemHandler implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			entryActions();
			CardLayout c = (CardLayout) chosenPanel.getLayout();
			c.show(chosenPanel, (String) e.getItem());
			if (chosenPanel.getComponent(2).isVisible()) {
				displayMessage("Please choose a starting date or date interval.");
			}
		}

	}

	// Hanterar b�de block och unblock
	class BlockActionListener implements ActionListener {
		@Override
		//Detta funkar, men knappas effektivt. Det kanske g�r att g�ra model<pallet>
		//och sen en toString metod i pallet som automatiskt anropas? V�rt � testa kanske
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (cookieList.isSelectionEmpty()) {
				return;
			}
			List<String> selectedValues = (List<String>) cookieList.getSelectedValuesList();
			JButton clicked = (JButton) e.getSource();
			int[] selIndexes=cookieList.getSelectedIndices();
			if (clicked.getText().equals("Block")) {
				for (String s : selectedValues) {
					db.blockPallet(s.split("|")[0].trim(), " ");
					for(int i=0;i<selIndexes.length;i++){
						pallets.get(selIndexes[i]).setIsBlocked(" ");
					}
				}
			} else {
				for (String s : selectedValues) {
					db.blockPallet(s.split("|")[0].trim(), null);
					for(int i=0;i<selIndexes.length;i++){
						pallets.get(selIndexes[i]).setIsBlocked(null);
					}
				}
			}
			cookieListModel.removeAllElements();
			for (Pallet p : pallets) {
				String format = "%1$-5d %2$-10s %3$-18s";
				String blocked = p.isBlocked() != null ? "Y" : "N";
				cookieListModel.addElement(String.format(format, p.getPalletNbr(),
						"| " + (p.getBakeDate().split(" "))[0], "| " + p.getCookieName()) + "| " + blocked);
			}		
			displayMessage("The search result is on the form \"Pallet ID | Production date | "
					+ "Cookie name | Blocked Y/N? \"");
			cookieList.setModel(cookieListModel);
		}
	}

	// Hanterar detailsknappen
	class DetailActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			/**
			 * if (cookieList.isSelectionEmpty()) { return; } JList
			 * <String> selectedValues= (JList<String>)
			 * cookieList.getSelectedValuesList();
			 */
			new PalletDetailGUI();

		}

	}

}
