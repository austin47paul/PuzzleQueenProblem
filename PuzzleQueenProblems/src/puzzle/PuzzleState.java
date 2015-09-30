package puzzle;

import interfaces.State;

public class PuzzleState implements State {
	
	int[][] current;
	final int[][] goalState = { { 0, 1, 2 } , { 3, 4, 5 }, { 6, 7, 8} };
	
	@Override
	/**
	 * Takes a string input that generates the initial state
	 * @param 	str		a line of number 0-8 to be turned into an array.
	 * @return 	current	intialized state
	 */
	public int[][] init(String str) {
		// TODO Check string size (8) and contains digits
		//		initialize current
		return current;
	}

	@Override
	/**
	 * returns goalState 
	 */
	public int[][] getGoalState() {
		// TODO Auto-generated method stub
		return goalState;
	}

	@Override
	/**
	 * returns current
	 */
	public int[][] getState() {
		return current;
	}

	@Override
	/**
	 * Determines if given action is valid.
	 * returns true if blank space can be swapped in that direction
	 * 			false if not
	 */
	public boolean validAction(String action) {
		// TODO get coordinates of zero
		//
		//		if zero in top row and action == "U"
		//		if zero in bottom row and action == "D"
		//		if zero in left column and action == "L"
		//		or if zero in right column and action == "R"
		//		else return true
		return false;
	}

	@Override
	/**
	 * Calculates the heuristic and returns it.
	 * The Heuristic Function is the accumulation of each digit from its goal position.
	 * @return	h	the calculated heuristic value.
	 */
	public int getHeuristic() {
		int h ;
		// TODO Loop through positions of current state
		//		add the distance (up and over) to goal
		//		position of that value.
		return 0;
	}

	@Override
	/**
	 * Performs a given action {"U","D","L","R"} on the state.
	 * @param	action		String of direction to swap 0
	 * @return	current 	new state
	 */
	public int[][] act(String action) {
		// TODO	Swap the zero with the corresponding 
		// 		value in that direction. 
		return current;
	}

}
