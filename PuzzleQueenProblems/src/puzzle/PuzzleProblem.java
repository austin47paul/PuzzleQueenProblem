package puzzle;

import java.util.ArrayList;
import java.util.Collections;

import interfaces.Problem;
import interfaces.State;

public class PuzzleProblem implements Problem {
	
	public String initState;		// to save the initial state after actions
	private State current;			// the current state of the problem
	private String actionSequence = "";		// Accumulated after each choice is made
	private final String[] ACTIONS = { "U", "D", "L", "R" };	//possible actions to be made
	public int pathCost;					// amount of actions chosen
	
	/**
	 * Constructors
	 * @param ini
	 */
	public PuzzleProblem(String ini, String goal){
		this.initState = ini;
		this.current = new PuzzleState(ini,goal);
		this.pathCost = 0;
	}
	public PuzzleProblem(PuzzleProblem prob) {
		this.current = new PuzzleState((PuzzleState) prob.getState());
		this.initState = prob.initState;
		this.actionSequence = prob.getActionSequence();
		this.pathCost = prob.pathCost;
	}
	
	/**
	 * 
	 * @return initState	returns the initial string
	 */
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
		//System.out.println("Best Neighbor: " + topval.getString());
		return topval;
	}
	
	/**
	 * gets Neighbors of the currents state and shuffles them
	 * @return	ns neighbors
	 */
	public ArrayList<PuzzleState> getRandomNeighbors() {
		ArrayList<PuzzleState> ns = getNeighbors();
		Collections.shuffle(ns);
		return ns;	
	}	
	
	/**
	 * gets a list of states that are possible given the current state.
	 * @return neighbors	array list of possible successors
	 */
	public ArrayList<PuzzleState> getNeighbors() {
		ArrayList<PuzzleState> neighbors = new ArrayList<PuzzleState>();
		PuzzleState neighbor;
		//System.out.println("getting neighbors.");
		for (String act: ACTIONS) {
			if (current.validAction(act)){
				//System.out.println("Action: " + act + " on " + current.getString());
				neighbor = new PuzzleState(current.getString(current.getState()),
											current.getString(current.getGoalState()));
				neighbor.act(act);
				neighbors.add(neighbor);
			}	
		}
		return neighbors;
	}
	
	/**
	 * @return ACTIONS the array of possible actions
	 */
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
	
	/**
	 * @return pathCost the path cost
	 */
	public int getSteps() {return pathCost;}
	
	/**
	 * Performs an action
	 * @param	action	an action to be performed on the state
	 * @return current the current state of the problem
	 */
	@Override
	public State act(String action) {
		current.act(action);
		return current;
	}
	
	/**
	 * Receives the action that was chosen and ads it to the action sequence
	 * @param act	action that was performed
	 */
	public void addAction(String act) {
		pathCost++;
		actionSequence += act;
		//System.out.println("Action: " + act + " H: " + current.getValue() + " " + current.getString());

	}
	
	/**
	 * @return the current state of the problem
	 */
	@Override
	public State getState() 
		{ return current; }
	
	/**
	 * Given a state, makes the current state identical to it
	 * @param state a given state
	 */
	@Override
	public void setState(State state) 
		{ current = new PuzzleState(state.getString(state.getState()),
									state.getString(state.getGoalState())); 
		}
	
	/**
	 * Makes a new puzzle state with a random init state
	 */
	public void randomizeState() {
		//System.out.print("Randomizing State: ");
		ArrayList<Integer> lst = new ArrayList<Integer>();
		for(int i = 0; i<9; i++)// adds 0-8 to list
			lst.add(i);
		Collections.shuffle(lst);//randomizes list
		String str="";
		for(Integer i : lst)
			str += String.valueOf(i);
		//System.out.println(str);
		current = new PuzzleState(str,current.getString(current.getGoalState()));
		//pathCost++;
	}
	
}
