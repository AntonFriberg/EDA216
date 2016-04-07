
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
		JOptionPane conOptionPane=new JOptionPane();
		new Thread(new Runnable() {
			public void run() {
				ImageIcon icon=null;
		        try {
					icon = new ImageIcon(new URL("http://www.archisevilla.org/wp-content/themes/archisevilla/images/loading.gif"));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				conOptionPane.showOptionDialog(null,  "Connecting to database...", "Krusty Cookies AB",JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, icon, new Object[] {},null );
			}
		}).start();

		/* --- change code here --- */
		/* --- change xxx to your user name, yyy to your password --- */
		if (db.openConnection("db01", "dinmamma1")) {
			conOptionPane.getRootFrame().dispose();
			JFrame frame = new JFrame("Krusty Cookies AB");
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
		} else {
			conOptionPane.getRootFrame().dispose();
			JOptionPane.showMessageDialog(new JFrame(), "Could not connect to server, please try again", "Krusty Cookies AB",
					JOptionPane.WARNING_MESSAGE);
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
