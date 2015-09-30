package puzzle;

import interfaces.Problem;
import interfaces.State;

public class PuzzleProblem implements Problem {
	
	private String initState;
	private State current;
	private String actionSequence = "";
	private final String[] ACTIONS = { "U", "D", "L", "R" };
	
	/**
	 * Constructors
	 * @param ini
	 */
	public PuzzleProblem(String ini){
		this.initState = ini;
		this.current = new PuzzleState(ini);
	}
	public PuzzleProblem(PuzzleProblem prob) {
		this.current = new PuzzleState(prob.getState());
	}
	
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

	
	public String getValidActions(String[] a) {
		String str = "";
		for ( String action: a ){
			 if ( current.validAction(action) )
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
