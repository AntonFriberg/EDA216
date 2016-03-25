import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class PalletDetailGUI extends JFrame {
	private int rows=5;
	private int cols=5;
	
	private JTextField[][] fields=new JTextField[rows][cols];
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PalletDetailGUI(){
		setLayout(new GridLayout(rows,cols));
		setPreferredSize(new Dimension(350,200));
		fields[0][0]=new JTextField("N�got:");
		fields[1][0]=new JTextField("N�got:");
		fields[2][0]=new JTextField("N�got:");
		fields[3][0]=new JTextField("N�got:");
		fields[4][0]=new JTextField("N�got:	");
		
		for(int i=0;i<rows;i++){
			fields[i][0].setEditable(false);
			add(fields[i][0]);
			add(new JTextField());
		}
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
}
