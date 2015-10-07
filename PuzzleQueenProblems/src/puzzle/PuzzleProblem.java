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
	public int pathCost;
	
	/**
	 * Constructors
	 * @param ini
	 */
	public PuzzleProblem(String ini){
		this.initState = ini;
		this.current = new PuzzleState(ini);
		this.pathCost = 0;
	}
	public PuzzleProblem(PuzzleProblem prob) {
		this.current = new PuzzleState(prob.getState());
		this.initState = prob.initState;
		this.actionSequence = prob.getActionSequence();
		this.pathCost = prob.pathCost;
	}
	
	public String getInitStr() { return initState; }
	
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
			if (state.getValue() < topval.getValue())	{
				topval = state;
			}
		}
		System.out.println("Best Neighbor: " + topval.getString());
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
		System.out.println("getting neighbors.");
		for (String act: ACTIONS) {
			if (current.validAction(act)){
				//System.out.println("Action: " + act + " on " + current.getString());
				neighbor = new PuzzleState(current.getString());
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
	
	public int getSteps() {return pathCost;}
	
	@Override
	public State act(String action) {
		current.act(action);
		actionSequence.concat(action+",");
		return current;
	}
	
	public void addAction(String act) {
		pathCost++;
		actionSequence.concat(act);
	}

	@Override
	public State getState() 
		{ return current; }
	
	@Override
	public void setState(State state) 
		{ current = new PuzzleState(state.getString()); }
	
	/**
	 * Makes a new puzzle state with a random init state
	 */
	public void randomizeState() {
		
		ArrayList<Integer> lst = new ArrayList<Integer>();
		for(int i = 0; i<9; i++)// adds 0-8 to list
			lst.add(i);
		Collections.shuffle(lst);//randomizes list
		String str="";
		for(Integer i : lst)
			str.concat(String.valueOf(i));
		current = new PuzzleState(str);
	}
	
}
