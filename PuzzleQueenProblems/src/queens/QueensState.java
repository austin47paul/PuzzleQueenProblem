package queens;

import java.util.Random;

import interfaces.State;

public class QueensState implements State {
	
	int[][] current;
	int value;
	
	
	@Override
	public int[][] init(String str) {
		// TODO Auto-generated method stub
		current = stringToQueens(str);
		getHeuristic();
		return current;
	}

	@Override
	public int[][] getGoalState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] getState() {
		// TODO Auto-generated method stub
		return current;
	}

	@Override
	public boolean validAction(String action) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void getHeuristic() {
		int cost = 0;
		int c1,r1,c2,r2; // coordinates for current[r1][c1] coordinates (c1,r1)
		// loop through columns
		for (int i = 0; i < current[0].length-1; i++) {
			
			c1 = i;
			r1 = getRowNum(i);
			// loop though columns to right of first one
			for (int j = i+1; j < current.length; j++) {
				
				c2 = j;
				r2 = getRowNum(j);
				//if the queens are attacking the cost is incremented
				if (isAttacking(r1, r2, c1, c2)) {
					cost++;
				}
			}
		}
		value =  cost;		
	}
	/**
	 * Gives the row number for a queen given the column number.
	 * @param colNum
	 * @return k 	row number
	 */
	public int getRowNum(int colNum) {
		int k;
		for ( k = 0; k < current.length; k++) {
			if (current[k][colNum] == 1)
				break;
		}
		return k;
	}
	
	/**
	 * Checks to see if the two coordinate points attack each other.
	 * @param q1Hor	queen 1 row number
	 * @param q2Hor queen 2 row number
	 * @param q1Ver	queen 1 column number
	 * @param q2Ver	queen 2 column number
	 * @return true if on 
	 */
	public static boolean isAttacking(int q1Hor, int q2Hor, int q1Ver, int q2Ver) {
		//Doubles must be used so rounding isn't an issue
		double q1H = q1Hor;
		double q2H = q2Hor;
		double q1V = q1Ver;
		double q2V = q2Ver;
		
		//queens are in same column
		if (q1Ver == q2Ver) {
			return true;
		}
		//checks if queens are on same diagonal
		if (Math.abs((q1V - q2V) / (q1H - q2H)) == 1) {
			return true;
		}

		return false;
	}

	@Override
	public int[][] act(String action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	
	public static int[][] stringToQueens(String input) {
		int[][] queens = zeroQueens();
		char[]  inp = input.toCharArray();
		//queens =
		
		Random rand = new Random();
		
		//stores each number from the input string into the integer array
		for(int i = 0; i < queens.length; i++) {
			if ( i < input.length()-1 ) // if queen defined in initial state
				queens[i][Integer.parseInt(String.valueOf(inp[i]))] = 1;
			else
				queens[i][rand.nextInt(8)] = 1;		//random queens
		}
				
		return queens;
	}
	private static int[][] zeroQueens(){
		int[][] q = new int[8][8];
		for(int i = 0; i < q.length; i++)
			for ( int j = 0; j<q[i].length; j++)
				q[i][j] = 0;
		return q;
	}
}
