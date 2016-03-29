
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

/**
 * MovieGUI is the user interface to the movie database. It sets up the main
 * window and connects to the database.
 */
public class BakeryGUI {
	/**
	 * db is the database object
	 */
	private Database db;

	/**
	 * tabbedPane is the contents of the window. It consists of two panes, User
	 * login and Book tickets.
	 */
	private JTabbedPane tabbedPane;

	/**
	 * Create a GUI object and connect to the database.
	 * 
	 * @param db
	 *            The database.
	 */
	public BakeryGUI(Database db) {
		this.db = db;

		JFrame frame = new JFrame("Cookie baking");
		tabbedPane = new JTabbedPane();

		SearchPane searchPane = new SearchPane(db);
		tabbedPane.addTab("Pallet search", null, searchPane, "Used to search for one or more pallets");

		ProductionPane prodPane = new ProductionPane(db);
		tabbedPane.addTab("Add pallet", null, prodPane, "Add a produced pallet to the db");

		tabbedPane.setSelectedIndex(0);

		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		tabbedPane.addChangeListener(new ChangeHandler());
		frame.addWindowListener(new WindowHandler());

		frame.setSize(600, 500);
		frame.setVisible(true);

		searchPane.displayMessage("Connecting to database ...");

		/* --- change code here --- */
		/* --- change xxx to your user name, yyy to your password --- */
		if (db.openConnection("db01", "dinmamma1")) {
			searchPane.displayMessage("Connected to database");
		} else {
			searchPane.displayMessage("Could not connect to database");
		}
	}

	/**
	 * ChangeHandler is a listener class, called when the user switches panes.
	 */
	class ChangeHandler implements ChangeListener {
		/**
		 * Called when the user switches panes. The entry actions of the new
		 * pane are performed.
		 * 
		 * @param e
		 *            The change event (not used).
		 */
		public void stateChanged(ChangeEvent e) {
			BasicPane selectedPane = (BasicPane) tabbedPane.getSelectedComponent();
			selectedPane.entryActions();
			if (tabbedPane.getSelectedIndex() == 1)
				((BasicPane) tabbedPane.getSelectedComponent())
						.displayMessage("Please pick the cookie you'd like to produce");
		}
	}

	/**
	 * WindowHandler is a listener class, called when the user exits the
	 * application.
	 */
	class WindowHandler extends WindowAdapter {
		/**
		 * Called when the user exits the application. Closes the connection to
		 * the database.
		 * 
		 * @param e
		 *            The window event (not used).
		 */
		public void windowClosing(WindowEvent e) {
			db.closeConnection();
			System.exit(0);
		}
	}
}
