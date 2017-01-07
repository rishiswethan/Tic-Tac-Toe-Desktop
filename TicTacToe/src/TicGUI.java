import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicGUI extends JFrame
{
	TicMain tm = new TicMain();

	JFrame mainframe = new JFrame();
	JFrame frame = new JFrame();
	JFrame newframe = new JFrame();
	JPanel ticPanel = new JPanel(new GridLayout(4, 3, 2, 2));
	JPanel startAsk = new JPanel(new FlowLayout(FlowLayout.LEFT));

	JButton but0, but1, but2, but3, but4, but5, but6, but7, but8, butyes, butno;
	JLabel overLabel;

	char usercoin;

	public TicGUI()
	{
		frame.setTitle("Tic Tac Toe");
		frame.setSize(300, 300);
		String buttxt[] = { "", "", "", "", "", "", "", "", "" };

		//create buttons
		butyes = new JButton("Yes");
		butno = new JButton("No");
		but0 = new JButton(buttxt[0]);
		but1 = new JButton(buttxt[1]);
		but2 = new JButton(buttxt[2]);
		but3 = new JButton(buttxt[3]);
		but4 = new JButton(buttxt[4]);
		but5 = new JButton(buttxt[5]);
		but6 = new JButton(buttxt[6]);
		but7 = new JButton(buttxt[7]);
		but8 = new JButton(buttxt[8]);

		TicButtonListener BListener = new TicButtonListener();
		but0.addActionListener(BListener);
		but1.addActionListener(BListener);
		but2.addActionListener(BListener);
		but3.addActionListener(BListener);
		but4.addActionListener(BListener);
		but5.addActionListener(BListener);
		but6.addActionListener(BListener);
		but7.addActionListener(BListener);
		but8.addActionListener(BListener);
		butyes.addActionListener(BListener);
		butno.addActionListener(BListener);

		but0.setFont(new Font("Arial", Font.PLAIN, 40));
		but1.setFont(new Font("Arial", Font.PLAIN, 40));
		but2.setFont(new Font("Arial", Font.PLAIN, 40));
		but3.setFont(new Font("Arial", Font.PLAIN, 40));
		but4.setFont(new Font("Arial", Font.PLAIN, 40));
		but5.setFont(new Font("Arial", Font.PLAIN, 40));
		but6.setFont(new Font("Arial", Font.PLAIN, 40));
		but7.setFont(new Font("Arial", Font.PLAIN, 40));
		but8.setFont(new Font("Arial", Font.PLAIN, 40));

		//add buttons
		ticPanel.add(but0);
		ticPanel.add(but1);
		ticPanel.add(but2);
		ticPanel.add(but3);
		ticPanel.add(but4);
		ticPanel.add(but5);
		ticPanel.add(but6);
		ticPanel.add(but7);
		ticPanel.add(but8);

		JLabel startLabel = new JLabel();
		overLabel = new JLabel();
		ticPanel.add(overLabel);

		String str = "Do you like to start first?   ";
		startLabel.setText(str);

		startAsk.add(startLabel);
		startAsk.add(butyes);
		startAsk.add(butno);

		frame.add(startAsk);
		frame.setLocationRelativeTo(null);
		//frame.add(ticPanel);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setLocationRelativeTo(null);
	}

	class TicButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == but0)
			{
				System.out.println("button 0 pressed");

				tm.setMove(0);
				but0.setText(String.valueOf(tm.getCCoin()));
				if (isOver() == true)
					return;
				getButton(tm.getCMoveLocation()).setText(String.valueOf(tm.getCCoin()));
			}
			else if (e.getSource() == but1)
			{
				System.out.println("button 1 pressed");

				tm.setMove(1);
				but1.setText(String.valueOf(tm.getCCoin()));

				if (isOver() == true)
					return;
				getButton(tm.getCMoveLocation()).setText(String.valueOf(tm.getCCoin()));
			}
			else if (e.getSource() == but2)
			{
				System.out.println("button 2 pressed");

				tm.setMove(2);
				but2.setText(String.valueOf(tm.getCCoin()));

				if (isOver() == true)
					return;
				getButton(tm.getCMoveLocation()).setText(String.valueOf(tm.getCCoin()));
			}
			else if (e.getSource() == but3)
			{
				System.out.println("button 3 pressed");

				tm.setMove(3);
				but3.setText(String.valueOf(tm.getCCoin()));

				if (isOver() == true)
					return;
				getButton(tm.getCMoveLocation()).setText(String.valueOf(tm.getCCoin()));
			}
			else if (e.getSource() == but4)
			{
				System.out.println("button 4 pressed");

				tm.setMove(4);
				but4.setText(String.valueOf(tm.getCCoin()));

				if (isOver() == true)
					return;
				getButton(tm.getCMoveLocation()).setText(String.valueOf(tm.getCCoin()));
			}
			else if (e.getSource() == but5)
			{
				System.out.println("button 5 pressed");

				tm.setMove(5);
				but5.setText(String.valueOf(tm.getCCoin()));

				if (isOver() == true)
					return;
				getButton(tm.getCMoveLocation()).setText(String.valueOf(tm.getCCoin()));
			}
			else if (e.getSource() == but6)
			{
				System.out.println("button 6 pressed");

				tm.setMove(6);
				but6.setText(String.valueOf(tm.getCCoin()));

				if (isOver() == true)
					return;
				getButton(tm.getCMoveLocation()).setText(String.valueOf(tm.getCCoin()));
			}
			else if (e.getSource() == but7)
			{
				System.out.println("button 7 pressed");

				tm.setMove(7);
				but7.setText(String.valueOf(tm.getCCoin()));

				if (isOver() == true)
					return;
				getButton(tm.getCMoveLocation()).setText(String.valueOf(tm.getCCoin()));
			}
			else if (e.getSource() == but8)
			{
				System.out.println("button 8 pressed");

				tm.setMove(8);
				but8.setText(String.valueOf(tm.getCCoin()));

				if (isOver() == true)
					return;
				getButton(tm.getCMoveLocation()).setText(String.valueOf(tm.getCCoin()));
			}
			else if (e.getSource() == butyes)
			{
				usercoin = 'o';
				System.out.println("Yes");
				tm.setCoin('o');

				startAsk.setVisible(false);
				ticPanel.setVisible(true);
				frame.add(ticPanel);
			}
			else if (e.getSource() == butno)
			{
				int move;
				usercoin = 'x';
				System.out.println("No");
				tm.setCoin('x');//so that the inverse x is printed
				do {
                    move =randInt(0,9);
                    System.out.println("loop"+move);
                }while (move!=4&&move!=0&&move!=2&&move==9);
				//move=2;
				tm.ch[0] = 'x';
				tm.ch[1] = String.valueOf(move).charAt(0);
				tm.cnt += 2;
				getButton(move).setText(String.valueOf('x'));//Initial move

				startAsk.setVisible(false);
				ticPanel.setVisible(true);
				frame.add(ticPanel);
			}
		}
		
		public int randInt(int min, int max) {
	        Random rand = new Random();
	        int randomNum = rand.nextInt((max - min) + 1) + min;

	        return randomNum;
	    }

		boolean isOver()
		{
			char ch = 'n';//tm.isOver();
			if (ch == tm.oppchar(usercoin))
			{
				JPanel overPanel = new JPanel(new GridLayout(0, 3, 2, 2));
				overLabel.setText("Woah! You win!");
				overLabel.setFont(new Font("Arial", Font.PLAIN, 28));
				ticPanel.add(overLabel);
				/*overPanel.setVisible(true);
				newframe.setVisible(true);
				overPanel.setSize(getMinimumSize());
				//mainframe.add(newframe);
				//newframe.setSize(width, height);
				newframe.setLocationRelativeTo(null);
				newframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
				newframe.add(overPanel);*/

				return true;
			}
			else if (ch == (usercoin))
			{
				overLabel.setText("I win!");
				JPanel overPanel = new JPanel(new GridLayout(0, 3, 2, 2));
				overLabel.setFont(new Font("Arial", Font.PLAIN, 28));
				ticPanel.add(overLabel);
				/*overPanel.setVisible(true);
				newframe.setVisible(true);
				overPanel.setSize(getSize());
				//mainframe.add(newframe);
				newframe.setMinimumSize(getSize());
				newframe.setLocationRelativeTo(null);
				newframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
				newframe.add(overPanel);*/

				return true;
			}
			else if (ch == 'd')
			{
				overLabel.setText("It's a draw...");
				JPanel overPanel = new JPanel(new GridLayout(4, 3, 2, 2));
				overLabel.setFont(new Font("Arial", Font.PLAIN, 28));
				ticPanel.add(overLabel);
				/*overPanel.add(overLabel);
				overPanel.setVisible(true);
				newframe.add(overPanel);
				newframe.setVisible(true);
				//mainframe.add(newframe);
				*/
				return true;
			}
			else
				return false;
		}
	}

	JButton getButton(int index)
	{
		if (index == 0)
			return but0;
		else if (index == 1)
			return but1;
		else if (index == 2)
			return but2;
		else if (index == 3)
			return but3;
		else if (index == 4)
			return but4;
		else if (index == 5)
			return but5;
		else if (index == 6)
			return but6;
		else if (index == 7)
			return but7;
		else if (index == 8)
			return but8;

		return new JButton();
	}
}
