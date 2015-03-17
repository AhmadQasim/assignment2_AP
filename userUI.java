import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class userUI {

	// The window object
	static JFrame frame;
	static Container content;
	public int number_of_station;
	static public JLabel[][] jLs;
	

	public static void main(String[] args) throws IOException {
		userUI gui = new userUI();
		gui.setUp();
	}

	@SuppressWarnings("resource")
	private void setUp() throws IOException{
		String buffer;int i,j;
		BufferedReader br = new BufferedReader(new FileReader("File.txt"));
		buffer=br.readLine();number_of_station = Integer.parseInt(buffer);
		jLs = new JLabel[number_of_station][5];
		frame = new JFrame("Factory Line Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content = frame.getContentPane();  // Get the content pane
		content.setLayout(new GridLayout(number_of_station+1, 4));  	// Set the container layout
		content.setBackground(Color.white);
		for(i =0;i<number_of_station;i++){
			for(j =0;j<5;j++){
				jLs[i][j] = new JLabel();
				content.add(jLs[i][j]);
			}
		}
		ImageIcon icon = new ImageIcon("terminal.png");	
		for(i =0;i<number_of_station;i++){
			jLs[i][0].setIcon(icon);
			jLs[i][4].setIcon(icon);
		}
		back_bone object= new back_bone(this);
		JButton first = new JButton("Start Simulation");  				//Mouse listeners and All buttons simultaneously mentioned.
		first.addMouseListener(object);
		content.add(first);
		JButton second = new JButton("Add Car");  				//Mouse listeners and All buttons simultaneously mentioned.
		second.addMouseListener(object);
		content.add(second);
		frame.pack();				// Size for components
		frame.setVisible(true); 	// Display the window
	}
}
