package puzzle;

import interfaces.State;

public class PuzzleState implements State {
	
	private int[][] current;
	private final int[][] goalState = { { 0, 1, 2 } , { 3, 4, 5 }, { 6, 7, 8} };
	private int value;
	/**
	 * Constructor used for initials state
	 * @param ini
	 */
	public PuzzleState(String ini){
		this.init(ini);
		this.getHeuristic();
	}
	
	/**
	 * Constructor for copied states
	 * @param st
	 */
	public PuzzleState(State st){
		this.current = st.getState();
	}
	
	@Override
	/**
	 * Takes a string input that generates the initial state
	 * @param 	str		a line of number 0-8 to be turned into an array.
	 * @return 	current	initialized state
	 */
	public int[][] init(String str) {
		// TODO Check string size (8) and contains digits
		//		initialize current
		if ( str.length() != 8 || !str.matches("[0-8]+") )
			return null;
		char[] ini = str.toCharArray();
		int pos = 0;
		for(int i = 0; i <current.length; i++ ) {
			for(int j = 0; j< current[i].length; j++) {
				current[i][j] = ini[pos];
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
	 * @param action String  U,D,L,or R
	 * @returns true if blank space can be swapped in that direction
	 * 			false if not
	 */
	public boolean validAction(String action) {
		// TODO get coordinates of zero
		//
		//		if zero not in top row and action == "U"
		//		if zero not in bottom row and action == "D"
		//		if zero not in left column and action == "L"
		//		or if zero not in right column and action == "R"
		//		else return false
		
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
		// TODO Loop through positions of current state
		//		add the distance (up and over) to goal
		//		position of that value.
		int[] pos;
		for( int i = 0; i<current.length; i++ ){
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
		
		return current;
	}
	
	/**
	 * Given a value return an array length 2 of the coordinates.
	 * @param val
	 * @return location an pair state coordinates
	 */
	public int[] findLocation(int val) {
		int[] location = new int[2];
		
		for ( int i = 0; i<current.length; i++ ) {
			for ( int j = 0; i<current[i].length; j++ ) {
				if ( current[i][j] == val ) {
					location[0] = i;
					location[1] = j;
					return location;
				}
			}
		}
		return null;
	}

}
