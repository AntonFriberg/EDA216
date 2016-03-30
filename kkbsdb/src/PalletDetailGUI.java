import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PalletDetailGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	public PalletDetailGUI(List<Pallet> selectedValues) {
		setPreferredSize(new Dimension(800,400));
		JPanel panel=new JPanel();
		panel.setLayout(new BorderLayout());
		
		String[] colNames = { "Pallet Id", "Cookie name", "Location", "Bake date", "Blocked", "Bill Id" };
		String[][] palletData = new String[selectedValues.size()][6];

		for (int i = 0; i < selectedValues.size(); i++) {
			palletData[i][0] = Integer.toString(selectedValues.get(i).getPalletNbr());
			palletData[i][1] = selectedValues.get(i).getCookieName();
			palletData[i][2] = selectedValues.get(i).getLocation();
			palletData[i][3] = selectedValues.get(i).getBakeDate();
			palletData[i][4] = selectedValues.get(i).isBlocked() != null ? "Y" : "N";
			palletData[i][5] = "-";
			// palletData[i][5]=Integer.toString(palletListModel.getElementAt(i).getBill().getBillId());
		}
		JTable table = new JTable(palletData, colNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		
		JScrollPane pane=new JScrollPane(table);
		panel.add(pane);
		add(panel);

		pack();
		setVisible(true);
	}
}
