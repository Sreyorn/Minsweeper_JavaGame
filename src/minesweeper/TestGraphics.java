package minesweeper;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TestGraphics extends JFrame {
	JButton start;
	JButton stop;
	JLabel sec;
	JLabel n_sec;
	JLabel min;
	int count=0;
	int ns=0,sec1=0,min1=0;
	Timer timer;
	TestGraphics(){
		setTitle("Graphics");
		setSize(900,900);
//		JButton buttons=new JButton();
//		JButton coverbtn=new JButton();
//		buttons.setSize(50,50);
//		buttons.setLocation(20,20);
//		buttons.setText("*");
//		coverbtn.setSize(50,50);
//		coverbtn.setLocation(20,20);
//		coverbtn.setBackground(Color.blue);
		final Container pane=getContentPane();
		
		final JButton [][]buttons = new JButton[5][5];
		final JButton [][]covers=new JButton[5][5];
		ActionListener l=new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i,j;
//				System.out.println(e.getSource());
				for (i = 0; i <buttons.length; i++) {
					for(j=0;j<buttons[i].length;j++) {
//						System.out.println(covers[i][j].getLocation());
						
						if(e.getSource()==covers[i][j]) {
							System.out.println(buttons[i][j].getBackground());
							if(buttons[i][j].getBackground()==Color.green) {
								System.out.println("Game Over");
							}
							pane.remove(covers[i][j]);
						}
							
					}
				}
//				remove(coverbtn);
			}
			
		};//end ActionListen
		
		int i,j;
		int x=0;
		int y=10;
		for (i = 0; i <buttons.length; i++) {
			for(j=0;j<buttons[i].length;j++) {
				covers[i][j] = new JButton("");
				covers[i][j].setSize(50, 50);
				covers[i][j].setLocation(x, y);
				covers[i][j].setBackground(Color.black);
				x+=50;
				pane.add(covers[i][j]);
				covers[i][j].addActionListener(l);
		}
			x=0;
			y+=50;
		}
		x=0;
		y=10;
		
		for (i = 0; i <buttons.length; i++) {
			for(j=0;j<buttons[i].length;j++) {
				buttons[i][j] = new JButton("");
				buttons[i][j].setSize(50, 50);
				buttons[i][j].setLocation(x, y);
				buttons[i][j].setText("0");
				buttons[i][j].setBackground(null);
				x+=50;
				pane.add(buttons[i][j]);
		}
			x=0;
			y+=50;
		}
		buttons[2][3].setBackground(Color.green);
		timer =new Timer(20,new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ns+=1;
				if(ns==60) {
					ns=0;
					sec1+=1;
				}
//				if(sec1==60) {
//					sec1=0;
//					min1+=1;
//				}
//				if(ns<=9) {
//					n_sec.setText("0"+ns);
//				}else {
//					n_sec.setText(""+ns);
//				}
				if(sec1<=9) {
					sec.setText("0"+sec1+":");
				}
				else {
					sec.setText(sec1+":");
				}
//				if(min1<=9) {
//					min.setText("0"+min1+":");
//				}
////				else {
//					min.setText(min1+":");
//				}
			}
			
		});
		start=new JButton("Start");
		start.setLocation(300,10);
		start.setSize(100,40);
		stop=new JButton("Stop");
		stop.setLocation(300,50);
		stop.setSize(100,40);
		ActionListener sl= new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==start) {
					System.out.println("start selected");
//					start.setText("Stop");
					timer.start();
					
					
				}
				else {
					System.out.println("stop selected");
					timer.stop();
					sec1=0;
				}
			}
			
		};
		start.addActionListener(sl);
		stop.addActionListener(sl);
		pane.add(stop);
		pane.add(start);
		sec=new JLabel("00");
		sec.setSize(100,50);
		sec.setLocation(300,90);
		pane.add(sec);
		n_sec=new JLabel("00");
		n_sec.setSize(100,50);
		n_sec.setLocation(360,90);
		pane.add(n_sec);
//		coverbtn.addActionListener(l);
//		add(coverbtn);
//		add(buttons);
		
		class MyPanel extends JPanel{
			int timex=310;
			int timey=120 ;
 			public void paintComponent(Graphics g) {
				super.paintComponent(g);
//				g.drawLine(50, 10, 150, 50);
//				g.drawRect(90, 100, 50, 50);
				g.setColor(Color.red);
//				g.fillRect(90, 90, 50, 50);
				g.fillRect(300, 90, 150, 50);
				g.setColor(Color.white);
			
			}
		}//end class paintComponent
		add(new MyPanel());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setVisible(true);
	}
	public static void main(String[] args) {
		new TestGraphics();
	}
}
