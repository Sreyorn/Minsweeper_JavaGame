package minesweeper;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TestCode extends JFrame {
	private JButton[][] buttons;
	Container pane = getContentPane();
	final int W=600;
    final int H=650;
    
	public TestCode() {
		setTitle("Minesweeper");
		setSize(W, H);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.setLayout(null);
		int i,j;
		int x=0;
		int y=70;
		int minesRow[]= {5,3,8,8,7,3,8,9,1,4};
		int minesCol[]= {4,3,9,7,2,5,2,3,2,5};
		buttons = new JButton[10][10];
		int indexButton[][][]=new int[10][10][2];
		
		for (i = 0; i <buttons.length; i++) {
			for(j=0;j<buttons[i].length;j++) {
				indexButton[i][j][0]=i;
				indexButton[i][j][1]=j;
				buttons[i][j] = new JButton("");
				buttons[i][j].setSize(50, 50);
				buttons[i][j].setLocation(x, y);
				x+=50;
				pane.add(buttons[i][j]);
			}
			x=0;
			y+=50;
		}
		int rowBefore[]= new int[10];
		int rowAfter[]= new int[10];
		int colBefore[]= new int[10];
		int colAfter[]= new int[10];
		for (i = 0; i <buttons.length; i++) {
			for(j=0;j<buttons[i].length;j++) {
					System.out.print("["+indexButton[i][j][0]+","+indexButton[i][j][1]+"] ");
						rowBefore[i]=i-1;
						rowAfter[i]=i+1;
						if(indexButton[i][j][1]==minesCol[i]) {
							colBefore[i]=minesCol[i]-1;
							colAfter[i]=minesCol[i]+1;
							System.out.println("=>["+i+","+j+"]");
							buttons[i][j].setText("*");
						}
					
				}
				System.out.println();
				}//end for loop
		for (i = 0; i <buttons.length; i++) {
			System.out.print(rowBefore[i]+"-"+i+"-"+rowAfter[i]);
			System.out.println("=> "+colBefore[i]+"-"+minesCol[i]+"-"+colAfter[i]);
			
		}
//		System.out.println(rowBefore+" "+minesRow+" "+rowAfter);
//		System.out.println(colBefore+" "+minesCol+" "+colAfter);
	//determine number 1 on tiles
		
		for (i = 0; i <buttons.length; i++) {
			for(j=0;j<buttons[i].length;j++) {
				if(indexButton[i][j][0]==rowBefore[j]) {
					if(indexButton[i][j][1]==colBefore[i]||indexButton[i][j][1]==minesCol[i]||indexButton[i][j][1]==colAfter[i]) {
					System.out.println("=>["+i+","+j+"]");
					buttons[i][j].setText("1");
					}
					
				}//end else
//				else if(indexButton[i][j][0]==row1[1]) {
//					if(indexButton[i][j][1]==col1[0]||indexButton[i][j][1]==col1[2]) {
//						System.out.println("=>["+i+","+j+"]");
//						buttons[i][j].setText("1");
//					}
//				}//end else2
//				else if(indexButton[i][j][0]==row1[2]) {
//					if(indexButton[i][j][1]==col1[0]||indexButton[i][j][1]==col1[1]||indexButton[i][j][1]==col1[2]) {
//						System.out.println("=>["+i+","+j+"]");
//						buttons[i][j].setText("1");
//					}
//				}//end else3
			}
		}//end for loop
	
	
	}
	public static void main(String[] args) {
		new TestCode();
	}
}
