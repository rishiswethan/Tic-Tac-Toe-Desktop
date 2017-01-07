import java.util.Arrays;

import javax.swing.JLabel;

public class TicMain
{
	static TicSub ts = new TicSub();
	JLabel end=new JLabel();

	int cmove, cnt = 0;
	private char coin;

	static char ch[] = { 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n'};
	
	public static void main(String args[]) throws InterruptedException
	{
		new TicGUI().frame.setVisible(true);
	}

	boolean isOver()
	{
		return false;
	}

	void setMove(int umove) //throws InterruptedException
	{
		System.out.println("coin="+coin);
		ch[cnt] = oppchar(coin);
		//coin=oppchar(coin);
		ch[cnt + 1] = String.valueOf(umove).charAt(0);
		cnt += 2;
		System.out.println(Arrays.toString(ch));
		char ov=ts.isOver(ch);
		if(ov=='o'||ov=='x'||ov=='d')
			return;
		cmove = ts.tryAllPossibility(ch);
		
		//Thread.sleep(1000);
		if(ov=='o'||ov=='x'||ov=='d')
			return;
		ch[cnt] = (coin);
		ch[cnt + 1] = String.valueOf(cmove).charAt(0);
		cnt+=2;
		System.out.println(Arrays.toString(ch));
	}

	void setCoin(char ch)
	{
		coin = ch;
	}

	char getCCoin()
	{
		coin = oppchar(coin);
		return coin;
	}

	int getCMoveLocation()
	{
		return cmove;
	}

	//gives the opposite char (x=>o or o=>x)
	char oppchar(char ch)
	{
		if (ch == 'x')
			return 'o';
		else
			return 'x';
	}//END OF oppchar() 
}//END OF CLASS TicMain
