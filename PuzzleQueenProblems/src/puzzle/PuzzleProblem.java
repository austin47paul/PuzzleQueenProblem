package puzzle;

import interfaces.Problem;
import interfaces.State;

public class PuzzleProblem implements Problem {
	
	String initState;
	State current;
	String actionSequence = "";
	final String[] ACTIONS = { "U", "D", "L", "R" };
	
	
	@Override
	/**
	 * Returns a string of actions taken so far.
	 * @return actionSequence
	 */
	public String getActionSequence() {
		
		return actionSequence;
	}

	@Override
	public String getSolutions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getActions() {
		
		return ACTIONS;
	}

	@Override
	public String getValidActions(String[] a, State s) {
		String str = "";
		for ( String action: a ){
			 if ( s.validAction(action) )
				 str += action;
		}
		return str;
	}

	@Override
	public State act(String action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State getState() {
		// TODO Auto-generated method stub
		return current;
	}

	@Override
	public void setState(State state) {
		current = state;	
	}

}
