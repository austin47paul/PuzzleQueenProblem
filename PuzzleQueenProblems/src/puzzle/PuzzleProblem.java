package puzzle;

import java.util.ArrayList;
import java.util.Collections;

import interfaces.Problem;
import interfaces.State;

public class PuzzleProblem implements Problem {
	
	public String initState;
	private State current;
	private String actionSequence = "";
	private final String[] ACTIONS = { "U", "D", "L", "R" };
	public int steps;
	/**
	 * Constructors
	 * @param ini
	 */
	public PuzzleProblem(String ini){
		this.initState = ini;
		this.current = new PuzzleState(ini);
		this.steps = 0;
	}
	public PuzzleProblem(PuzzleProblem prob) {
		this.current = new PuzzleState(prob.getState());
		this.initState = prob.initState;
		this.actionSequence = prob.getActionSequence();
		this.steps = prob.steps;
	}
	
	@Override
	/**
	 * Returns a string of actions taken so far.
	 * @return actionSequence
	 */
	public String getActionSequence() 
		{return actionSequence;}
	
	/**
	 * @return topval	The top neighboring state.
	 */
	public State getBestNeighbor() {
		
		ArrayList<PuzzleState> neighbors = getNeighbors();
		PuzzleState topval = neighbors.get(0);
		for ( PuzzleState state : neighbors ) {
			if (state.getValue() > topval.getValue())	{
				topval = state;
			}
		}
		return topval;
	}
	
	public ArrayList<PuzzleState> getRandomNeighbors() {
		ArrayList<PuzzleState> ns = getNeighbors();
		Collections.shuffle(ns);
		return ns;	
	}	
	
	public ArrayList<PuzzleState> getNeighbors() {
		ArrayList<PuzzleState> neighbors = new ArrayList<PuzzleState>();
		PuzzleState neighbor;
		for (String act: ACTIONS) {
			if (current.validAction(act)){
				neighbor = new PuzzleState(current);
				neighbor.act(act);
				neighbors.add(neighbor);
			}	
		}
		return neighbors;
	}
	@Override
	public String[] getActions()
		{ return ACTIONS; }

	/**
	 * 
	 * @return str string containing the 
	 * 				actions that do not bump into a side
	 */
	public String getValidActions() {
		String str = "";
		for ( String action: ACTIONS ){
			 if ( current.validAction(action) )
				 str += action;
		}
		return str;
	}
	public int getSteps() {return steps;}
	@Override
	public State act(String action) {
		current.act(action);
		steps++;
		return current;
	}

	@Override
	public State getState() 
		{ return current; }
	
	@Override
	public void setState(State state) 
		{ current = state; }
	@Override
	public State chooseAction() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
