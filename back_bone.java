
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JButton;


public class back_bone implements MouseListener{
	static int[] array_of_times_line1;
	static int[] array_of_times_line2;
	static int number_of_stations;
	static int[] array_of_transitions_line1;
	static int[] array_of_transitions_line2;
	static int number_of_chasis;
	public back_bone(userUI userUI) {
		number_of_stations = userUI.number_of_station;
	}
	@SuppressWarnings("resource")
	public void start() throws IOException {
		String buffer;
		System.out.println("Taking input from the File.");
		BufferedReader br = new BufferedReader(new FileReader("File.txt"));
		array_of_times_line1 = new int[number_of_stations];
		array_of_transitions_line1 = new int[number_of_stations+1];
		array_of_times_line2 = new int[number_of_stations];
		array_of_transitions_line2 = new int[number_of_stations+1];buffer=br.readLine();
		buffer=br.readLine();tokens_generated(buffer, number_of_stations, array_of_times_line1);
		buffer=br.readLine();tokens_generated(buffer, number_of_stations+1, array_of_transitions_line1);
		buffer=br.readLine();tokens_generated(buffer, number_of_stations, array_of_times_line2);
		buffer=br.readLine();tokens_generated(buffer, number_of_stations+1, array_of_transitions_line2);
	}
	static void tokens_generated(String buffer,int length, int[] dump_array){
		String token;StringTokenizer st = new StringTokenizer(buffer, " ");
		for(int i =0;i<length;i++){
			token=st.nextToken();
			dump_array[i]=Integer.parseInt(token);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		JButton temp = (JButton)e.getComponent();
		if (temp.getText().equals("Start Simulation")){
		try {
			start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		}
		else{
			Thread t =new Thread(new chasis());
			t.start();
			number_of_chasis++;
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
