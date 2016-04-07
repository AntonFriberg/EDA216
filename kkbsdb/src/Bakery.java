
/**
 * Bakery is the main class for the Cake production application. It
 * creates a database object and the GUI to interface to the database.
 */
public class Bakery {
	public static void main(String[] args) {
		Database db = new Database();
		new BakeryGUI(db);
	}
}
