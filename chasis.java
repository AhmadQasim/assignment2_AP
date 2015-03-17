import javax.swing.ImageIcon;

public class chasis implements Runnable {
	static int line_num = 0;
	@Override
	public void run(){
		try {
		ImageIcon icon = new ImageIcon("making1.png");int temp=back_bone.number_of_stations/3;
		ImageIcon wicon = new ImageIcon("white.png");
		int i=0;
		int[] path = shortest_path();
		if (path[0]==back_bone.number_of_stations){
			userUI.jLs[0][3].setIcon(icon);
			userUI.frame.revalidate();userUI.frame.repaint();
			process_wait(back_bone.array_of_times_line2[0], back_bone.array_of_times_line2, 0);
			
		}
		else{
			userUI.jLs[0][1].setIcon(icon);
			userUI.frame.revalidate();userUI.frame.repaint();
			process_wait(back_bone.array_of_times_line1[0], back_bone.array_of_times_line1, 0);
		}
		for(i=1;i<back_bone.number_of_stations;i++){
			if (back_bone.number_of_chasis!=1)
				path = shortest_path();
			if (i/temp==1)
				icon= new ImageIcon("making2.png");
			else if (i/temp==2)
				icon= new ImageIcon("making3.png");
			if (path[i]<back_bone.number_of_stations && path[i-1]<back_bone.number_of_stations){
				userUI.jLs[i-1][1].setIcon(wicon);
				userUI.jLs[i][1].setIcon(icon);
				userUI.frame.revalidate();userUI.frame.repaint();
				process_wait(back_bone.array_of_times_line1[i], back_bone.array_of_times_line1, i);
			}
			else if (path[i]>back_bone.number_of_stations && path[i-1]>back_bone.number_of_stations){
				userUI.jLs[i-1][3].setIcon(wicon);
				userUI.jLs[i][3].setIcon(icon);
				userUI.frame.revalidate();userUI.frame.repaint();
				process_wait(back_bone.array_of_times_line2[i], back_bone.array_of_times_line2, i);
			}
			else if (path[i]<back_bone.number_of_stations && path[i-1]>back_bone.number_of_stations){
				userUI.jLs[i-1][3].setIcon(wicon);
				userUI.jLs[i][2].setIcon(icon);
				userUI.frame.revalidate();userUI.frame.repaint();
				process_wait(back_bone.array_of_transitions_line1[i]);
				userUI.jLs[i][2].setIcon(wicon);
				userUI.jLs[i][1].setIcon(icon);
				userUI.frame.revalidate();userUI.frame.repaint();
				process_wait(back_bone.array_of_times_line2[i], back_bone.array_of_times_line2, i);
			}
			else if (path[i]>back_bone.number_of_stations && path[i-1]<back_bone.number_of_stations){
				userUI.jLs[i-1][1].setIcon(wicon);
				userUI.jLs[i][2].setIcon(icon);
				userUI.frame.revalidate();userUI.frame.repaint();
				process_wait(back_bone.array_of_transitions_line2[i]);
				userUI.jLs[i][2].setIcon(wicon);
				userUI.jLs[i][3].setIcon(icon);
				userUI.frame.revalidate();userUI.frame.repaint();
				process_wait(back_bone.array_of_times_line1[i], back_bone.array_of_times_line1, i);
			}
		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	int[] shortest_path(){
		int[] prev_line1 = new int[back_bone.number_of_stations+1];
		int[] prev_line2 = new int[back_bone.number_of_stations+1];
		int shortest_line1;
		int shortest_line2;
		shortest_line1=back_bone.array_of_times_line1[back_bone.number_of_stations-1]+back_bone.array_of_transitions_line1[back_bone.number_of_stations];prev_line1[0]=0;prev_line1[back_bone.number_of_stations]=-1;
		shortest_line2=back_bone.array_of_times_line2[back_bone.number_of_stations-1]+back_bone.array_of_transitions_line2[back_bone.number_of_stations];prev_line2[0]=back_bone.number_of_stations;prev_line2[back_bone.number_of_stations]=-1;
		for(int i =back_bone.number_of_stations-2;i>=0;i--){
			int temp_1=shortest_line1+back_bone.array_of_times_line1[i], temp_2=shortest_line2+back_bone.array_of_transitions_line2[i+1]+back_bone.array_of_times_line1[i];
			if (temp_1<=temp_2){
				prev_line1[i+1]=i+1;
				shortest_line1=temp_1;
			}
			else{
				prev_line1[i+1]=back_bone.number_of_stations+i+1;
				shortest_line1=temp_2;
			}	
			temp_1=shortest_line2+back_bone.array_of_times_line2[i]; temp_2=shortest_line1+back_bone.array_of_transitions_line1[i+1]+back_bone.array_of_times_line2[i];
			if (temp_1<=temp_2){
				prev_line2[i+1]=back_bone.number_of_stations+i+1;
				shortest_line2=temp_1;
			}
			else{
				prev_line2[i+1]=i+1;
				shortest_line2=temp_2;
			}	
		}
		shortest_line1+=back_bone.array_of_transitions_line1[0];
		shortest_line2+=back_bone.array_of_transitions_line2[0];
		if (shortest_line1>shortest_line2){
			line_num=2;
			return prev_line2;
		}
			line_num=1;
			return prev_line1;
	}
	void process_wait(int sec, int[] time_dec, int ind) throws InterruptedException{
		time_dec[ind]+=sec;
		for(int i=0;i<sec;i++){
			Thread.sleep(1000);
			time_dec[ind]--;
		}
	}
	void process_wait(int sec) throws InterruptedException{
		for(int i=0;i<sec;i++){
			Thread.sleep(1000);
		}
	}
}
