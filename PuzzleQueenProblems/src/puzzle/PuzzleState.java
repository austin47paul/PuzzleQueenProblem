package puzzle;

import interfaces.State;

public class PuzzleState implements State {
	
	private int[][] current;		// matrix of current puzzle board
	private final int[][] goalState = { { 0, 1, 2 } , { 3, 4, 5 }, { 6, 7, 8} };	// matrix of goal puzzle board
	private int value;				// the heuristic mearsurement of current
	public String actstr;			// a string to keep track of the action that brought this state to existence
	
	/**
	 * Constructor used for initials state
	 * @param ini
	 */
	public PuzzleState(String ini){
		current = new int[3][3];
		this.init(ini);
		this.getHeuristic();
	}
	
	/**
	 * Constructor for copied states
	 * @param st
	 */
	public PuzzleState(State st) {
		current = new int[3][3];
		init(st.getState().toString());
		getHeuristic();
	}
	
	@Override
	/**
	 * Takes a string input that generates the initial state
	 * @param 	str		a line of number 0-8 to be turned into an array.
	 * @return 	current	initialized state
	 */
	public int[][] init(String str) {
		//  Check string size (8) and contains digits
		//		initialize current
		char[] ini = str.toCharArray();
		
		int pos = 0;
		for(int i = 0; i <current.length; i++ ) {
			for(int j = 0; j< current[i].length; j++) {
				current[i][j] = ini[pos]-48;
				pos++;
			}
		}
		return current;
	}

	@Override
	/**
	 * returns goalState 
	 */
	public int[][] getGoalState() 
		{ return goalState; }

	@Override
	/**
	 * returns current
	 */
	public int[][] getState() 
		{ return current; }
	
	public int getValue() { return value; }
	
	@Override
	/**
	 * Determines if given action is valid.
	 * @param action String  U, D, L, or R
	 * @returns true if blank space can be swapped in that direction
	 * 			false if not
	 */
	public boolean validAction(String action) {
		
		int[] zero = findLocation(0);
		
		if (action.equals("U")) {
			return zero[0] != 0;
		} else if (action.equals("D")) {
			return zero[0] != 2;
		} else if (action.equals("L")) {
			return zero[1] != 0;
		} else if (action.equals("R")) {
			return zero[1] != 2;
		} else {
			return false;
		}
	}

	@Override
	/**
	 * Calculates the heuristic and returns it.
	 * The Heuristic Function is the accumulation of each digit from its goal position.
	 * @return	h	the calculated heuristic value.
	 */
	public void getHeuristic() {
		int h = 0;
		//System.out.println("Calc Heuristic.");
		int[] pos = new int[2];
		
		for ( int i = 0; i<current.length; i++ ) {
			for ( int j = 0; j<current[i].length; j++ ) {
				
				pos = findLocation(goalState[i][j]);
				h += Math.abs(i - pos[0]);
				h += Math.abs(j - pos[1]);
			}
		}
		value = h;
	}

	@Override
	/**
	 * Performs a given action {"U","D","L","R"} on the state.
	 * @param	action		String of direction to swap 0
	 * @return	current 	new state
	 */
	public int[][] act(String action) {
		int[] pos = findLocation(0); // location of zero
		int val = 0;
		actstr = action;
		
		if ( action.equals("U") ) {
			
			val = current[pos[0]-1][pos[1]];
			current[pos[0]-1][pos[1]] = 0;
			current[pos[0]][pos[1]] = val;
			
		} else if ( action.equals("D") ) {
			
			val = current[pos[0]+1][pos[1]];
			current[pos[0]+1][pos[1]] = 0;
			current[pos[0]][pos[1]] = val;
			
		} else if ( action.equals("L") ) {
			
			val = current[pos[0]][pos[1]-1];
			current[pos[0]][pos[1]-1] = 0;
			current[pos[0]][pos[1]] = val;
			
		} else if ( action.equals("R") ) {
			
			val = current[pos[0]][pos[1]+1];
			current[pos[0]][pos[1]+1] = 0;
			current[pos[0]][pos[1]] = val;
			
		} else { return null; }
		
		getHeuristic();
		return current;
	}
	
	/**
	 * Given a value return an array length 2 of the coordinates.
	 * @param val
	 * @return location an pair state coordinates
	 */
	public int[] findLocation(int val) {
		
		int[] location = new int[2];
		boolean bool = false;
		for ( int i = 0; i<current.length; i++ ) {
			for ( int j = 0; j<current[i].length; j++ ) {
				
				if ( current[i][j] == val ) {
					location[0] = i;
					location[1] = j;
					bool = true;
					break;
				}
			}
			if ( bool) break;
		}
		return location;
	}
	
	/**
	 * @return str	the string value of the current puzzle board
	 */
	public String getString() {
		String str = "";
		for ( int i = 0; i < current.length; i++) {
			for ( int j = 0; j < current[i].length; j++ ) {
				str += String.valueOf(current[i][j]);
			}
		}
		return str;
	}

}
