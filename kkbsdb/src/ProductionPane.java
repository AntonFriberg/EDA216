
//s�h�r skriver man f�r att skriva ut meddelanden i understa panelen.
//displayMessage("Pallet was succesfully blocked!");

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ViewportLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * The GUI pane where a user books tickets for movie performances. It contains
 * one list of movies and one of performance dates. The user selects a
 * performance by clicking in these lists. The performance data is shown in the
 * fields in the right panel. The bottom panel contains a button which the user
 * can click to book a ticket to the selected performance.
 */
public class ProductionPane extends BasicPane {
	private static final long serialVersionUID = 1;
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
	 * The text fields where the movie data is shown.
	 */
	private JTextField[] fields;

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

	/**
	 * Create the booking pane.
	 * 
	 * @param db
	 *            The database object.
	 */
	public ProductionPane(Database db) {
		super(db);
	}

	/**
	 * Create the left panel, containing the movie name list and the performance
	 * date list.
	 * 
	 * @return The left panel.
	 */
	public JComponent createLeftPanel() {
		cookieListModel = new DefaultListModel<String>();

		cookieList = new JList<String>(cookieListModel);
		cookieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cookieList.setPrototypeCellValue("123456789012");
		cookieList.addListSelectionListener(new NameSelectionListener());
		JScrollPane p1 = new JScrollPane(cookieList);

		JPanel p = new JPanel();
		p.setLayout(new ViewportLayout());
		p.add(p1);
		return p1;
	}

	/**
	 * Create the top panel, containing the fields with the performance data.
	 * 
	 * @return The top panel.
	 */
	public JComponent createTopPanel() {
		String[] texts = new String[NBR_FIELDS];
		texts[PALLET_NBR] = "Pallet number";
		texts[DATE] = "Date";
		texts[TIME] = "Time";

		fields = new JTextField[NBR_FIELDS];
		for (int i = 0; i < fields.length; i++) {
			fields[i] = new JTextField(20);
			fields[i].setEditable(false);
		}

		JPanel input = new InputPanel(texts, fields);

		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.add(new JLabel("Chosen cookie: "));
		chosenCookieNameLabel = new JLabel("");
		p1.add(chosenCookieNameLabel);

		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.add(p1);
		p.add(input);
		return p;
	}

	/**
	 * Create the bottom panel, containing the book ticket-button and the
	 * message line.
	 * 
	 * @return The bottom panel.
	 */
	public JComponent createBottomPanel() {
		JButton[] buttons = new JButton[1];
		buttons[0] = new JButton("Produce pallet");
		return new ButtonAndMessagePanel(buttons, messageLabel, new ActionHandler());
	}

	/**
	 * Perform the entry actions of this pane: clear all fields, fetch the movie
	 * names from the database and display them in the name list.
	 */
	public void entryActions() {
		clearMessage();
		fillNameList();
		clearFields();
	}

	/**
	 * Fetch movie names from the database and display them in the name list.
	 */
	private void fillNameList() {
		cookieListModel.removeAllElements();
		db.getCookieNames(cookieListModel);
		cookieList.setModel(cookieListModel);
	}

	/**
	 * Clear all text fields.
	 */
	private void clearFields() {
		for (int i = 0; i < fields.length; i++) {
			fields[i].setText("");
		}
	}

	/**
	 * A class that listens for clicks in the name list.
	 */
	// Denna hanterar klick i listan med kakor
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
			if (!e.getValueIsAdjusting()) {
				clearMessage();
				clearFields();

				String cookieName = cookieList.getSelectedValue().toString();
				/* --- insert own code here --- */
				chosenCookieNameLabel.setText(cookieName);
				displayMessage("Click 'Produce pallet' in order to save one pallet of " + cookieName);
			}
		}
	}

	/**
	 * A class that listens for button clicks.
	 */
	// Denna hanterar "produce pallet"-knappen
	class ActionHandler implements ActionListener {
		/**
		 * Called when the user clicks the Book ticket button. Books a ticket
		 * for the current user to the selected performance (adds a booking to
		 * the database).
		 * 
		 * @param e
		 *            The event object (not used).
		 */
		public void actionPerformed(ActionEvent e) {
			if (cookieList.isSelectionEmpty()) {
				return;
			}
			String cookieName = cookieList.getSelectedValue();
			/* --- insert own code here --- */
			Pallet prodPal = db.producePallet(cookieName);
			if (prodPal != null) {
				fields[0].setText(Integer.toString(prodPal.getPalletNbr()));
				fields[1].setText((prodPal.getBakeDate().split(" "))[0]);
				fields[2].setText((prodPal.getBakeDate().split(" "))[1]);
				displayMessage("Your pallet was succesfully produced.");
			} else {
				displayMessage("Something went wrong. It is most likely the saldo or that you are dissconnected. Please try again!");
			}
		}
	}
}
