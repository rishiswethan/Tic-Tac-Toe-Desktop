
/*
 * Debug status:-
 * - I found a bigger problem. The tree seems to have another branch,
 *   which is the reason there is always a element left in markTree
 *   
 * Fixed status:-
 *  - oppchar() is perfect
 *  - whatChar() is perfect
 *  - isOver() is perfect
 *  - missingBoxes() is perfect
 *  - *tryAllPossibility() is persumed to be perfect
 *  - markTree() is perfect
 *  - processBestMove() is perfect
 *  - *processRow is to be checked for the latest path bug
 *  - *pathMod() is persumed to be perfect
 *  - subArray() is perfect
 *  - mergeMissing() is perfect
 *  - swap() is perfect
 *  - calcTotalNodes() is perfect
 *  - elementFromPath() is perfent
 *  - noOfNodes() is perfect
 */

import java.util.Arrays;

public class TicSub
{
	int missingp[], length, nodeTreeFlag[], mark = 0;
	boolean flag, headTrue, processRowFlag, test = true;
	String mpath = "0", str, pathp;

	//Returns the opposite char 'x' to 'o' and 'o' to 'x'
	private char oppchar(char ch)
	{
		if (ch == 'x')
			return 'o';
		else
			return 'x';
	}//END OF oppchar() 

	//Returns what character should be at the particular index
	char whatChar(int index)
	{
		if (index == 0 || index % 4 == 0)
			return 'x';
		else if (index == 2 || (index + 2) % 4 == 0)
			return 'o';
		else
			return 'n';
	}//END OF whatChar()

	//Supply the board for it to return 'x' or 'o' or 'd' or 'n' based in the status of the board
	static char isOver(char tic_board[])
	{
		char[] board;
		int k;
		for (k = 2; k <= 18; k += 2)
		{
			board = tic_board.clone();
			board = subArray(board, k);
			int solseq[][] = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 }, { 2, 4, 6 } };
			int temp[], checkd = 0;
			char winchar = 'n';
			boolean check = false, checkw = false;

			char boardcpy[] = new char[9];

			boolean flag;
			for (int i = 0; i <= 8; i++)
			{
				flag = false;
				for (int j = 1; j <= 17; j += 2)
				{
					if (i == Character.getNumericValue(board[j]))
					{
						boardcpy[i] = String.valueOf(board[j - 1]).charAt(0);
						flag = true;
					}
				}
				if (flag == false)
				{
					boardcpy[i] = 'n';
				}
			}

			board = boardcpy.clone();

			checkw = false;
			checkd = 0;
			for (int i = 0; i < 8; i++)
			{
				temp = solseq[i];
				check = (board[temp[0]] != 'n');
				if ((board[temp[0]] == board[temp[1]]) && (board[temp[1]] == board[temp[2]]) && check)//a==b==c
				{
					checkw = true;
					winchar = board[temp[0]];
					//System.out.println("win");
					break;
				}
				if (board[i] != 'n')
					checkd++;
			}
			if (checkw == true)
				return winchar;
			else if (board[8] != 'n' && checkd == 8)
				return 'd';
			else
				board = tic_board.clone();
		}

		return 'n';
	}//END OF isOver()

	//Finds and returns an integer array of the unfilled box's indexes
	int[] missingBoxes(char board[])
	{
		int present[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8' };
		int cnt = 0;
		for (int i = 1; i <= 17; i += 2)//find what numbers are present
		{
			if (Character.isDigit(board[i]))
			{
				present[Character.getNumericValue(board[i])] = 'p';
				cnt++;
			}
			else
				break;
		}
		
		int missing[] = new int[9 - cnt];
		if (cnt == 9)
		{
			int miss[] = {};
			return miss;
		}
		
		int a = 0;
		for (int i = 0; i <= 8; i++)//mark the numbers that are not present in present[]
		{
			if (present[i] != 'p')
			{
				missing[a] = i;
				a++;
			}
		}
		System.out.println("Missing: " + Arrays.toString(missing));
		return missing;
	}//END OF missingBoxes()

	//Function to execute the tryallpossibility() and return the result
	int tryAllPossibility(char board[])
	{
		int pnum = 17;
		System.out.println("Board in tryAllPossibility(): " + String.valueOf(board));
		for (int i = 0; i <= 17; i++)//finding first 'n'
		{
			if (board[i] == 'n')
			{
				pnum = i;
				break;
			}
		}
		missingp = missingBoxes(board.clone());
		flag = true;

		int wtd[] = { 4, 0, 4, 0, 0, 2, 4, 1, 4 };//2nd move makes trees having 8 length, will take a long time and thus hardcoded for speed
		if (pnum == 2)
		{
			return wtd[Character.getNumericValue(board[1])];
		}

		nodeTreeFlag = new int[calcTotalNodes(missingp.length)];
		for (int i = 0; i < calcTotalNodes(missingp.length); i++)//All elements are initialized to 5 so that it's easier
		{														 //to debug in case of an error
			nodeTreeFlag[i] = 5;
		}
		try
		{
			//str = "";
			mpath = "0";
			System.out.println("Processing...");
			headTrue = false;
			markTree(missingp.clone(), board.clone(), 0, whatChar(pnum), 0);//fills nodeTreeFlag
			
			processRowFlag = true;
			nodeTreeFlag[0] = 1;
			System.out.println("After make tree:- " + Arrays.toString(nodeTreeFlag));
			processBestMove(nodeTreeFlag, "0", missingp.length);//proceses the filled nodes

			System.out.println(str);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(Arrays.toString(nodeTreeFlag));//Just a check

		for (int i = 1; i <= missingp.length; i++)//When there are >=2 win possibilities,
		{										  //the one that can make it win in the next move is chosen
			if (nodeTreeFlag[i] == '1' || nodeTreeFlag[i] == '2')
			{
				char brd[] = board.clone();
				brd[pnum] = whatChar(pnum);
				brd[pnum + 1] = String.valueOf(missingp[i]).charAt(0);
				System.out.println("QW:- " + String.valueOf(brd));
				char wi = isOver(brd);
				if (wi == brd[pnum] || wi == 'd')
				{
					System.out.println("Quick win");
					return missingp[i];
				}
			}
		}

		//Win is prioritised. The first row is checked
		for (int i = 0; i < missingp.length; i++)
		{
			if (nodeTreeFlag[elementFromPath("0" + i, missingp.length)] == 1)
			{
				System.out.println("Found win");
				System.out.println(Arrays.toString(missingp));
				return missingp[elementFromPath("0" + i, missingp.length) - 1];
			}
		}
		//if win not found then draw is searched for
		for (int i = 0; i < missingp.length; i++)
		{
			if (nodeTreeFlag[elementFromPath("0" + i, missingp.length)] == 2)
			{
				System.out.println("Found draw");
				System.out.println(Arrays.toString(missingp));
				return missingp[elementFromPath("0" + i, missingp.length) - 1];
			}
		}

		return 9;//The control should not come here. If it does, then 9 will cause it to crash
		//paste 1 in last line

	}//END OF tryAllPossibilities()

	//Runs recursively to check for all possibilities and stores the board status with the path in the tree in each node it encounters
	//Mark the win or lost of all nodes
	void markTree(int missing[], char board[], int pnum, char compch, int iFromLoop) throws InterruptedException
	{
		if (pnum == missing.length)
		{
			return;
		}

		for (int i = pnum; i < missing.length; i++)
		{
			swap(i, pnum, missing);

			//int np = 2000;
			char boardMissMerge[] = mergeMissing(board.clone(), missing.clone());
			/*for (int k = 0; k < board.length; k++)
			{
				if (board[k] == 'n')
				{
					np = k;
					k = 20;
				}
			}*/

			//System.out.println("path:- " + mpath + "," + pnum + ", " + i);
			mpath = pathMod(mpath, pnum, i);
			char realBoard[] = subArray(boardMissMerge.clone(), (18 - (missing.length * 2)) + ((pnum + 1) * 2));//(17 - (missing.length * 2)) + ((pnum + 1) * 2) + 1);

			//if (pnum == 0)
			//System.out.println("path=" + mpath + ", i= " + i + " element:- " + elementFromPath(mpath, missing.length) + "," + isOver(realBoard) + ", " + String.valueOf(realBoard) + "\n");
			//System.out.println(Arrays.toString(realBoard) + "\n" + mpath);
			//if (pnum == 2)
			//System.out.println("Elemment from path:- " + elementFromPath(mpath, missing.length) + ", " + mpath + ", " + isOver(realBoard));

			char ch = isOver(realBoard);
			if (ch == compch && pnum == 0)
			{
				System.out.println(pnum + "," + String.valueOf(realBoard));
			}

			/*if (headTrue == false)
			{
				nodeTreeFlag[0] = 1;
				headTrue = true;
			}*/
			/*else if(pnum % 2 !=0)
			{
				nodeTreeFlag[elementFromPath(mpath, missing.length)] = 1;
			}*/
			/*if (test == true && (missing.length + 1 == mpath.length()))// && nodeTreeFlag[elementFromPath(mpath, missing.length)] == 0)
			{
				mark = elementFromPath(mpath, missing.length);
				Thread.sleep(3000);
				test = false;
			}*/
			if (ch == compch)
			{
				//System.out.println(i + "," + pnum + "," + mpath + "," + elementFromPath(mpath, missing.length) + "true " + String.valueOf(realBoard));
				nodeTreeFlag[elementFromPath(mpath, missing.length)] = 1;
				if (mpath.length() == missing.length)
				{
					//System.out.println("LAST ROW:- " + ch + pnum + 1 + ", " + mpath + ", " + elementFromPath(mpath, missing.length) + "," + i + " " + String.valueOf(realBoard));
				}
			}
			else if (ch == 'd')
			{
				//System.out.println(i + "," + pnum + "," + mpath + "," + elementFromPath(mpath, missing.length) + "draw " + String.valueOf(realBoard));
				nodeTreeFlag[elementFromPath(mpath, missing.length)] = 2;
				if (mpath.length() == missing.length)
				{
					System.out.println("LAST ROW:- " + ch + pnum + 1 + ", " + mpath + ", " + elementFromPath(mpath, missing.length) + "," + i + " " + String.valueOf(realBoard));
				}
			}
			else if (ch == oppchar(compch))
			{
				//System.out.println(i + "," + pnum + "," + mpath + "," + elementFromPath(mpath, missing.length) + "false " + String.valueOf(realBoard));
				nodeTreeFlag[elementFromPath(mpath, missing.length)] = -1;
				if (mpath.length() == missing.length)
				{
					//System.out.println("LAST ROW:- " + ch + pnum + 1 + ", " + mpath + ", " + elementFromPath(mpath, missing.length) + "," + i + " " + String.valueOf(realBoard));
				}
			}
			else
			{
				//System.out.println(i + "," + pnum + "," + mpath + "," + elementFromPath(mpath, missing.length) + "nutral " + String.valueOf(realBoard));
				nodeTreeFlag[elementFromPath(mpath, missing.length)] = 0;
				if (mpath.length() == missing.length + 1)
				{
					System.out.println("LAST ROW ERROR:- " + pnum + 1 + ", " + mpath + ", " + elementFromPath(mpath, missing.length) + "," + i + " " + String.valueOf(realBoard) + "\n");
				}
			}

			markTree(missing.clone(), boardMissMerge.clone(), pnum + 1, compch, i);

			swap(i, pnum, missing);
		}
	}//END OF markTree()

	//Initialize the process the tree from n-1 to 0
	//Run and rows from root to top, to process the winning move in 2nd row. Runs processRow() 
	void processBestMove(int treeArray[], String path, int missingLen) throws InterruptedException
	{
		for (int i = missingLen; i >= 1; i--)
		{
			pathp = "0";
			processRow(missingLen, treeArray, path, i, 0);
		}
	}//END OF processBestMove()

	//Process the said row with the help of the rows below it
	//processes the given row and sets the win status to its parent node
	void processRow(int missingLength, int tree[], String pathc, int row, int pnum) throws InterruptedException
	{
		if (pnum == row + 1)
		{
			//System.out.println("return:- " + pnum);
			return;
		}
		
		int flag = ((row + 1) % 2 == 0) ? -1 : 1;
		for (int i = pnum; i <= missingLength - 1; i++)
		{
			pathp = (pathp.substring(0, pnum + 1) + String.valueOf(i - pnum));
			//pathp = pathMod(pathp, pnum, i);

			if (pnum + 1 == row)
			{
				//System.out.print("path process:- " + pathp + ", " + pnum + ", " + i + ", " + row);
				if ((row + 1) % 2 != 0)//All true check
				{
					int tep = tree[elementFromPath(pathp, missingLength)];
					if (tep == -1)
					{
						flag = -1;
						//System.out.println(" bflag=" + flag);
						break;
					}
					else if (tep == 2)
						flag = 2;
					else if (tep == 0)
					{
						//flag = 0;
						System.out.println("ERROR " + tep + ", " + pathp);
					}
				}
				else//Any one true check
				{
					int tep = tree[elementFromPath(pathp, missingLength)];
					if (tep == 1)
					{
						flag = 1;
						//System.out.println(" bflag=" + flag);
						break;
					}
					else if (tep == 2)
						flag = 2;
					else if (tep == 0 || tep == 5)
					{
						//flag = 0;
						System.out.println("ERROR " + tep + ", " + pathp);
					}
				}
				//System.out.println(" flag=" + flag);
			}
			
			else
			{
				//System.out.println("jump "+pnum+", "+pathp+",i= "+i);
				processRow(missingLength, tree, pathp, row, pnum + 1);//Runs recursively if the row needed has not been reached
			}
		}
		if (pnum + 1 == row)
		{
			tree[elementFromPath(pathp.substring(0, pathp.length() - 1), missingLength)] = flag;
			//System.out.println(elementFromPath(pathp.substring(0, pathp.length() - 1), missingLength) + ", " + pathp.substring(0, pathp.length() - 1) + ", " + pnum + "= " + flag);
		}
	}//END OF processRow()

	//Modify the path of the string according to pnum and i given
	//Modify the path in the tree based on the pnum and i of the loop it's in
	String pathMod(String pathString, int pnum, int i) throws InterruptedException
	{
		String str = (pathString.substring(0, pnum + 1) + String.valueOf(i - pnum));
		return str;
	}//END OF pathMod()

	//Merges the board with the missing elements and returns it
	//Marks all elements from index to the last element as 'n'
	static char[] subArray(char array[], int index)
	{
		char ch[] = array.clone();
		for (int i = index; i < array.length; i++)
		{
			ch[i] = 'n';
		}
		return ch;
	}//END OF subArray()

	//Mergs board with the missing elements
	char[] mergeMissing(char boarde[], int missing[])
	{
		char board[] = boarde.clone();
		int start = (17 - (2 * missing.length)) + 2;
		if (start <= 17)
		{
			board[start - 1] = whatChar(start - 1);
			for (int i = 0; i < missing.length; i++)
			{
				board[start - 1] = whatChar(start - 1);
				board[start] = String.valueOf(missing[i]).charAt(0);
				start += 2;
			}
		}
		return board;
	}//END OF mergeMissing()
	
	//returns the array element of the given path

	//Swap 2 values of an array using call by reference
	void swap(int a, int b, int ch[])
	{
		int temp;
		temp = ch[a];
		ch[a] = ch[b];
		ch[b] = temp;
	}//END OF swap()

	//Calculate the total number of nodes taking the missing  arrays length
	int calcTotalNodes(int missingLength)
	{
		int sum = 0;
		for (int i = 1; i <= missingLength + 1; i++)
		{
			sum += noOfNodesRow(i, missingLength);
		}
		return sum;
	}//END OF calcTotalNode()

	//Returns the element from the path
	
	int elementFromPath(String path, int missingLength)
	{
		//System.out.println("efp:- " + path + " " + missingLength);
		if (path.length() == 1)
			return 0;

		int x = 0;//number of elements in the above row(s) in the tree
		for (int i = 1; i < path.length(); i++)
		{
			x += (noOfNodesRow(i, missingLength));
		}
		x--;//because noOfNodes() returns with the exact number of nodes and head is 0 in index

		//System.out.println("\"" + path + "\"" + " x..............................= " + x);
		int y = 0;//number of elements b4 the given element in the row
		int a = 0;
		for (int i = 0; i < path.length() - 1; i++)
		{
			int f = 1;
			for (int j = missingLength - i; j >= missingLength - path.length() + 2; j--)
			{
				f *= j;
			}
			y += (f * (Character.getNumericValue(path.charAt(a++))));
		}
		y += Character.getNumericValue(path.charAt(path.length() - 1) + 1);//+1 because presence of 0 will need to add an element

		//System.out.println(path + " y..............................= " + y);
		return x + y;
	}//END OF elementFromPath()
	
	//Returns the number of nodes present in the given row

	//Retunrs the numbers of nodes in the given row from the tree
	
	int noOfNodesRow(int row, int missingLength)
	{
		int sum = 1;
		for (int i = 1; i <= row - 1; i++)
		{
			sum *= missingLength--;
		}

		if (row == 1)
			return 1;
		else
			return sum;
	}//END OF noOfNode()

}//END OF CLASS TicSub