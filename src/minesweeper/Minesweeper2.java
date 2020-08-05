package minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

public class Minesweeper2 extends JFrame implements ActionListener {
	final static int[][] EASY =new int[10][10];
	final static int [][]MEDIUM = new int[20][10];
	final static int [][]HARD = new int[30][20];
	private JMenuBar menuBar;
	private JMenu game, file, help;
	private JMenuItem start, level, exit, save, about,continu,blog;
	private JButton[][] buttons;
	private JLabel levelB;
	private JLabel timerL;
	private JLabel timeTick;
	JButton easyBtn,mediumBtn,hardBtn;
	Container pane = getContentPane();
	int count=0;
	int m=100;
	int ns=0,sec1=0,min1=0;
	int savedTime;
	String savedLevel;
	Timer timer;
	int savedNum[]=new int[m];
	boolean isSaved=false;
	boolean isContinued=false;
	boolean isStarted=false;
    int selectedLevel;
    final int W=900;
    final int H=950;
    int savedCoverX[]=new int[m];
    int savedCoverY[]=new int[m];
	public Minesweeper2() {
		setTitle("Minesweeper");
		setSize(W, H);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		menuBar = new JMenuBar();
		pane.setLayout(null);
		setJMenuBar(menuBar);
		GameMenu();
		FileMenu();
		HelpMenu();	
		
		//displayGame(selectedLevel);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		JMenuItem item = (JMenuItem) e.getSource();
		if (item == exit)
			System.exit(0);
		else if (item == start) {
			pane.removeAll();
			pane.setSize(400, 450);
			try {
				displayGame(EASY);
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (item == level) {
			pane.removeAll();
			ActionListener l = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					char select;
					String str=String.valueOf(e.getActionCommand());
					select=str.charAt(0);
					switch(select) {
					case 'E':
						pane.removeAll();
						pane.setSize(400, 450);
						try {
							displayGame(EASY);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					case 'M':
						pane.removeAll();
						pane.setSize(450, 850);
						try {
							displayGame(MEDIUM);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					case'H':
						pane.removeAll();
						pane.setSize(850, 950);
						try {
							displayGame(HARD);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				
			};
			pane.setSize(W, H);
			easyBtn=new JButton("EASY");
			mediumBtn=new JButton("MEDIUM");
			hardBtn=new JButton("HARD");
			easyBtn.setSize(250,50);
			mediumBtn.setSize(250,50);
			hardBtn.setSize(250,50);
			easyBtn.setLocation(280,70);
			mediumBtn.setLocation(280,150);
			hardBtn.setLocation(280,230);
			easyBtn.addActionListener(l); 
			mediumBtn.addActionListener(l);
			hardBtn.addActionListener(l);
			add(easyBtn);
			add(mediumBtn);
			add(hardBtn);
		}
		else if(item==save) {
			isSaved=true;
			int i,j;
			System.out.println(isStarted);
			if(isStarted==false)
				JOptionPane.showMessageDialog(null,"PLEASE START THE GAME FIRST");
			else {
			try {
			      FileWriter myWriter = new FileWriter("minesLoc.txt");
			      FileWriter timeWriter=new FileWriter("timerFile.txt");
			      FileWriter levelWriter=new FileWriter("levelFile.txt");
			      FileWriter coverXWriter=new FileWriter("coverX.txt");
			      FileWriter coverYWriter=new FileWriter("coverY.txt");
			      levelWriter.write(savedLevel);
			      timeWriter.write(Integer.toString(savedTime));
			    	  for(j=0;j<m;j++) {
						myWriter.write(Integer.toString(savedNum[j])+" ");
						coverXWriter.write(Integer.toString(savedCoverX[j])+" ");
						coverYWriter.write(Integer.toString(savedCoverY[j])+" ");
					}
			      myWriter.close();
			      timeWriter.close();
			      levelWriter.close();
			      coverXWriter.close();
			      coverYWriter.close();
			      JOptionPane.showMessageDialog(null, "FILE SAVED SUCCESSFULLY");
			      System.out.println("File Saved!");
			    } catch (IOException ee) {
			      System.out.println("An error occurred.");
			      ee.printStackTrace();
			    }//end try
			}
//			
		}//end item==save
		else if(item==continu) {
			isContinued=true;
			if(isStarted) {
				try {
					JOptionPane.showMessageDialog(null, "CONTINUE THE GAME FROM SAVING FILE");
					pane.removeAll();
					System.out.println("savedLev: "+savedLevel);
					if(savedLevel.equals("EASY")) {
						pane.setSize(400, 450);
						displayGame(EASY);
					}
					else if(savedLevel.equals("MEDIUM")) {
						pane.setSize(450, 850);
						displayGame(MEDIUM);
					}
					else {
						pane.setSize(850, 950);
						displayGame(HARD);
					}
						
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Continued Start...!");
			}
			else
				JOptionPane.showMessageDialog(null,"PLEASE START THE GAME FIRST");
			
			
			isContinued=false;
		}//end item==continu
		else if(item==about) {
			pane.removeAll();
			pane.setSize(800,750);
			JLabel aboutL=new JLabel();
			JTextArea aboutT=new JTextArea();
			aboutL.setForeground(Color.black);
			aboutT.setEditable(false);
			aboutT.setSize(400,200);
			aboutT.setLocation(190,250);
			aboutL.setSize(250,100);
			aboutL.setLocation(300,150);
			aboutL.setFont(new Font("Dialog",Font.BOLD,20));
			aboutL.setText("About Developer");
			aboutT.setFont(new Font("Courier",Font.BOLD,20));
			aboutT.setText( "\n    Taught by Prof. 임광수\n"
					+ "    Student: LEN SREYORN(른쓰라이언)\n"
					+ "    ID Number: 12190175\n"
					+ "    PURPOSE:  This game is created \n"
					+ "    for JAVA Class final project\n"
					+ "          Year II Semester 1");
			pane.add(aboutL);
			pane.add(aboutT);
		}
		}
	
	public void displayGame(final int tiles[][]) throws FileNotFoundException {
		isStarted=true;
		int i,j;
		levelB=new JLabel();
		levelB.setFont(new Font("Dialog",Font.BOLD,22));
		timerL=new JLabel("TIMER: ");
		timerL.setFont(new Font("Dialog",Font.BOLD,22));
		levelB.setSize(200,50);
		levelB.setLocation(0,10);
		timerL.setSize(150,50);
		timerL.setLocation(290,10);
		pane.add(levelB);
		pane.add(timerL);
		timeTick=new JLabel("00");
		timeTick.setSize(100,50);
		timeTick.setFont(new Font("Dialog",Font.BOLD,22));
		timeTick.setLocation(380,10);
		
		
				
		int r=0,c=0;
		String lev = null;
		switch(tiles.length){
		case 10:
			r=10;
			c=10;
			m=10;
			lev="EASY";
			break;
		case 20:
			r=15;
			c=10;
			m=20;
			lev="MEDIUM";
			break;
		case 30:
			r=15;
			c=15;
			m=80;
			lev="HARD";
			break;
		}
		levelB.setText("LEVEL: "+lev);
		savedLevel=lev;
		System.out.println(savedLevel);
		int[] minesRow=new int[m];
		int[] minesCol=new int[m];
		final JButton covers[][]=new JButton[r][c];
		//generating 10 mines
				int row,col;
				for(i=0;i<m;i++) {
					row=(int)(Math.random()*(r));
					col=(int)(Math.random()*(c));
					minesRow[i]=row;
					minesCol[i]=col;
					savedNum[i]=col;
					System.out.print(savedNum[i]+" ->");
					//System.out.println("(a,b): "+minesRow[i]+","+minesCol[i]);
				}
				ActionListener ll = new ActionListener() {

					int cnt=-1;
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						int i,j;
						cnt++;
						boolean isGameOver=false;
						for (i = 0; i <buttons.length; i++) {
							for(j=0;j<buttons[i].length;j++) {
								if(e.getSource()==covers[i][j]) {
									if(buttons[i][j].getBackground()==Color.black) {
										isGameOver=true;
										timer.stop();
										pane.remove(covers[i][j]);
										JOptionPane.showMessageDialog(null, "GAME OVER"); 
										pane.removeAll();
										pane.setSize(400, 450);
										try {
											sec1=0;
											timer.start();
											displayGame(EASY);
										} catch (FileNotFoundException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}//end check gameOver
									pane.remove(covers[i][j]);
									System.out.println("i,j : "+i+","+j);
									savedCoverX[cnt]=i;
									savedCoverY[cnt]=j;
									System.out.println("cnt: "+cnt);
									
//									System.out.println("savedCoverX : "+ savedCoverX[cnt]);
								}//end if
								
							}//end forj 
						}//end for i
					}//end actionPerformed

				};//end ActionListener ll
				
				MouseAdapter mm=new MouseAdapter() {
					int i,j;
		               @Override
		               public void mousePressed(MouseEvent ee) {
		            	   for (i = 0; i <buttons.length; i++) {
								for(j=0;j<buttons[i].length;j++) {
				                  if (ee.getButton() == MouseEvent.BUTTON3&&ee.getSource()==covers[i][j]) {
				                     covers[i][j].setBackground(Color.red);
				                  }
								}
		            	   }
		               }
		            };//end MouseAdapter

		int x=0;
		int y=70;
		
		for (i = 0; i <covers.length; i++) {
			for(j=0;j<covers[i].length;j++) {
				covers[i][j] = new JButton("");
				covers[i][j].setSize(50, 50);
				covers[i][j].setLocation(x, y);
				covers[i][j].setBackground(Color.GRAY);
				x+=50;
				pane.add(covers[i][j]);
				covers[i][j].addActionListener(ll);
				covers[i][j].addMouseListener(mm);
		}
			x=0;
			y+=50;
		}
		isSaved=false;
		if(isContinued==true) {
			try {
			      FileReader myObj = new FileReader("minesLoc.txt");
			      FileReader timeObj=new FileReader("timerFile.txt");
			      FileReader levelObj=new FileReader("levelFile.txt");
			      FileReader coverXObj=new FileReader("coverX.txt");
			      FileReader coverYObj=new FileReader("coverY.txt");
			      Scanner myReader = new Scanner(myObj);
			      Scanner timeReader=new Scanner(timeObj);
			      Scanner levelReader=new Scanner(levelObj);
			      Scanner coverXReader=new Scanner(coverXObj);
			      Scanner coverYReader=new Scanner(coverYObj);
			      //read time from file
			      String timeDat=timeReader.next();
			      sec1=Integer.parseInt(timeDat);
			      for(j=0;j<buttons.length;j++) {
//			    	  String xDat=;
//			    	  String yDat=;
			    	  int cx=Integer.parseInt(coverXReader.next());
			    	  int cy=Integer.parseInt(coverYReader.next());
			    	  pane.remove(covers[cx][cy]);
			    	  }
			      for (i = 0; i <buttons.length; i++) {
							String data = myReader.next();
							minesCol[i]=Integer.parseInt(data);
					        System.out.print(data+" ");
					}
			      myReader.close();
			    } catch (FileNotFoundException re) {
			      System.out.println("An error occurred.");
			      re.printStackTrace();
			    }//end catch
			
		}
		x=0;
		y=70;
		int indexButton[][][]=new int[r][c][2];
		buttons = new JButton[r][c];
		for (i = 0; i <buttons.length; i++) {
			for(j=0;j<buttons[i].length;j++) {
				indexButton[i][j][0]=i;
				indexButton[i][j][1]=j;
				buttons[i][j] = new JButton("");
				buttons[i][j].setSize(50, 50);
				buttons[i][j].setLocation(x, y);
				buttons[i][j].setText("0");
				buttons[i][j].setBackground(Color.LIGHT_GRAY);
				x+=50;
				pane.add(buttons[i][j]);
		}
			x=0;
			y+=50;
		}
		
//		int[][] mines=new int[m][2];
		//Placing mines in the board
		int rowBefore[]= new int[100];
		int rowAfter[]= new int[100];
		int colBefore[]= new int[100];
		int colAfter[]= new int[100];
		for (i = 0; i <buttons.length; i++) {
			for(j=0;j<buttons[i].length;j++) {
//					System.out.print("["+indexButton[i][j][0]+","+indexButton[i][j][1]+"] ");
					rowBefore[i]=i-1;
					rowAfter[i]=i+1;
					if(indexButton[i][j][0]==minesRow[i])
						System.out.print("");
						if(indexButton[i][j][1]==minesCol[i]) {
							colBefore[i]=minesCol[i]-1;
							colAfter[i]=minesCol[i]+1;
//							System.out.println("=>["+i+","+j+"]");
						}
					}
//				System.out.println();
				}//end for loop
		//Set number on tiles
		for (i = 0; i <buttons.length; i++) {
			for(j=0;j<buttons[i].length;j++) {
//					System.out.print("["+indexButton[i][j][0]+","+indexButton[i][j][1]+"] ");
						rowBefore[i]=i-1;
						rowAfter[i]=i+1;
						if(indexButton[i][j][1]==minesCol[i]) {
							colBefore[i]=minesCol[i]-1;
							colAfter[i]=minesCol[i]+1;
//							System.out.println("=>["+i+","+j+"]");
//							buttons[i][j].setText("*");
						}
					
				}
//				System.out.println();
				}//end for loop: mines

	//determine number 1 on tiles
		int tmp;
		for (i = 0; i <buttons.length; i++) {
			for(j=0;j<buttons[i].length;j++) {
				//left and right 
				if(indexButton[i][j][1]==colBefore[i]||indexButton[i][j][1]==colAfter[i]) {
					tmp=Integer.parseInt(buttons[i][j].getText());
					if(tmp>=1) {
						tmp++;
						buttons[i][j].setText(Integer.toString(tmp));
					}
					else
						buttons[i][j].setText("1");
//					System.out.println("buttonValue"+(Integer.parseInt(buttons[i][j].getText())+1));
				}

				 if(indexButton[i][j][1]==minesCol[i]) {
					 //up same column
					 if(rowBefore[i]>=0) {
						 if(Integer.parseInt(buttons[rowBefore[i]][minesCol[i]].getText())>=1) {
							 tmp=Integer.parseInt(buttons[rowBefore[i]][minesCol[i]].getText())+1;
							 buttons[rowBefore[i]][minesCol[i]].setText(Integer.toString(tmp));
						 }
						 else
							 buttons[rowBefore[i]][minesCol[i]].setText("1");
					 }
					 if(rowAfter[i]<10)
						 //down same column
						 if(Integer.parseInt(buttons[rowAfter[i]][minesCol[i]].getText())>=1) {
							 tmp=Integer.parseInt(buttons[rowAfter[i]][minesCol[i]].getText())+1;
							 buttons[rowAfter[i]][minesCol[i]].setText(Integer.toString(tmp));
						 }
						 else
							 buttons[rowAfter[i]][minesCol[i]].setText("1");
					 if(rowBefore[i]>=0&&colBefore[i]>=0&&colAfter[i]<10) {
						 //up left corner
						 if(Integer.parseInt(buttons[rowBefore[i]][colBefore[i]].getText())>=1) {
							 tmp=Integer.parseInt(buttons[rowBefore[i]][minesCol[i]].getText())+1;
							 buttons[rowBefore[i]][colBefore[i]].setText(Integer.toString(tmp));
						 }
						 else
							 buttons[rowBefore[i]][colBefore[i]].setText("1");
						 //up right corner
						 if(Integer.parseInt(buttons[rowBefore[i]][colAfter[i]].getText())>=1) {
							 tmp=Integer.parseInt(buttons[rowBefore[i]][colAfter[i]].getText())+1;
							 buttons[rowBefore[i]][colAfter[i]].setText(Integer.toString(tmp));
						 }
						 else
							 buttons[rowBefore[i]][colAfter[i]].setText("1");
					 }
					 if(rowAfter[i]<10&&colBefore[i]>=0&&colAfter[i]<10) {
						 //down left corner
						 if(Integer.parseInt(buttons[rowAfter[i]][colBefore[i]].getText())>=0) {
							 tmp=Integer.parseInt(buttons[rowAfter[i]][colBefore[i]].getText())+1;
							 buttons[rowAfter[i]][colBefore[i]].setText(Integer.toString(tmp));
					 	}
							 buttons[rowAfter[i]][colBefore[i]].setText("1");
						 //down right corner
						 buttons[rowAfter[i]][colAfter[i]].setText("1");
					 }
					 
					 
				}//end if
			}//end for loop j
		}//end for loop i
		
		//set mines on tiles
		
		for (i = 0; i <buttons.length; i++) {
			for(j=0;j<buttons[i].length;j++) {
				if(buttons[i][j].getText().equals("0")) {
					buttons[i][j].setText("");
				}
						if(indexButton[i][j][1]==minesCol[i]) {
							System.out.println("=>["+i+","+j+"]");
							buttons[i][j].setText("*");
							buttons[i][j].setBackground(Color.black);
							
						}
					
				}
				System.out.println();
				}//end for loop: mines
		pane.add(timeTick);
		class MyPanel extends JPanel{
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.black);
				g.fillRect(300, 10, 100, 50);
			
			}
		}//end class paintComponent
		pane.add(new MyPanel());
	} //end displayGame
	public void TimeCounter() {
		//Set time Counter
		timer=new Timer(13,new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ns++;
				if(ns==60) {
					ns=0;
					sec1+=1;
				}
				savedTime=sec1;
				if(sec1<=9)
					timeTick.setText("0"+sec1);
				else
					timeTick.setText(sec1+"");
			}
			
		});
	}
	private void HelpMenu() {
		help = new JMenu("Help");
		menuBar.add(help);
		about = new JMenuItem("About");
		help.add(about);
		about.addActionListener(this);
	}

	private void GameMenu() {
		game = new JMenu("Game");
		menuBar.add(game);
		start = new JMenuItem("Start");
		level = new JMenuItem("Choose Level");
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		game.add(start);
		start.addActionListener(this);
		game.add(level);
		level.addActionListener(this);
		game.add(exit);
		TimeCounter();
		ActionListener sl= new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==start) {
					System.out.println("Timer Started...");
					sec1=0;
					timer.start();
				}
				else if(e.getSource()==level){
					sec1=0;
					timer.start();
				}
			}
			
		};
		start.addActionListener(sl);
		level.addActionListener(sl);
	}

	private void FileMenu() {
		file = new JMenu("File");
		menuBar.add(file);
		save = new JMenuItem("Save");
		blog = new JMenuItem("Blog");
		continu=new JMenuItem("Continue");
		file.add(save);
		file.add(continu);
		file.add(blog);
		save.addActionListener(this);
		continu.addActionListener(this);
	}

	public static void main(String[] args) {
		new Minesweeper();
	}
}
