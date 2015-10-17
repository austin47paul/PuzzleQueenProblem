package puzzle;

import java.util.ArrayList;
import java.util.Random;

import interfaces.Problem;
import interfaces.ProblemSolver;

public class PuzzleProblemSolver implements ProblemSolver {
	
	
	PuzzleProblem[] solutions = new PuzzleProblem[4]; // strategy end points
	
	/**
	 * Constructor
	 * Calls each strategy with a different instance of the Puzzle problem
	 * @param ini	String input from a line of a puzzle test file
	 */
	public PuzzleProblemSolver(String ini, String goal) {
		
		//System.out.println("Starting HillClimbing");
		solutions[0] = (PuzzleProblem) hcSteepestAscent(new PuzzleProblem(ini,goal));
		String str = //"StpAs: " //+ "Init: " + solutions[0].getInitStr() 
			//	+ " End: " + solutions[0].getState().getString(solutions[0].getState().getState())
			//	+ " Goal: " + solutions[0].getState().getString(solutions[0].getState().getGoalState())
			//	+ " Actions: " + solutions[0].getActionSequence()
				//+ "OptSol: " 
				+ solutions[0].getOptCost() 
				+ "\t" //+ "SchCst: " 
				+ solutions[0].getSearchCost() + "\t"  
				//+"\tHrstc " 
				+ solutions[0].getState().getValue();
		System.out.println(str);
		
		//System.out.println("Starting FirstChoice");
		solutions[1] = (PuzzleProblem) hcFirstChoice(new PuzzleProblem(ini,goal));
		 str = //"FirCh: " //+ "Init: " + solutions[1].getInitStr() 
			//	+ " End: " + solutions[1].getState().getString(solutions[1].getState().getState())
			//	+ " Goal: " + solutions[1].getState().getString(solutions[1].getState().getGoalState())
			//	+ " Actions: " + solutions[1].getActionSequence() + " "
				//+ "OptSol: " 
				+ solutions[1].getOptCost() 
				//+ "\tSchCst: " + 
				+ "\t" + solutions[1].getSearchCost() 
				//+ "\t\tHrstc " 
				+ "\t" + solutions[1].getState().getValue();
		System.out.println(str);
		
		//System.out.println("Starting Random Restart");
		solutions[2] = (PuzzleProblem) hcRandomRestart(new PuzzleProblem(ini,goal));
		 str = //"RanRe: " //+ "Init: " + solutions[2].getInitStr() 
			//	+ " End: " + solutions[2].getState().getString(solutions[2].getState().getState())
			//	+ " Goal: " + solutions[2].getState().getString(solutions[2].getState().getGoalState())
			//	+ " Actions: " + solutions[2].getActionSequence() + " \n" 
				//+ "OptSol: " 
				 + solutions[2].getOptCost() 
				//+ "\tSchCst: " 
				+ "\t" + solutions[2].getSearchCost() 
				//+ "\t\tHrstc " 
				+ "\t" + solutions[2].getState().getValue();
		System.out.println(str);
		
		//System.out.println("Starting Simulated Annealing");
		solutions[3] = (PuzzleProblem) simulatedAnnealing(new PuzzleProblem(ini,goal));
		 str = //"SimAn: "//+"Init: " + solutions[3].getInitStr() 
				//+ " End: " + solutions[3].getState().getString(solutions[3].getState().getState())
				//+ " Goal: " + solutions[3].getState().getString(solutions[3].getState().getGoalState())
			//	+ " Actions: " + solutions[3].getActionSequence() + " \n" 
			//+ "OptSol: " 
			+ solutions[3].getOptCost() 
			//+ "\tSchCst: " 
			+ "\t" + solutions[3].getSearchCost() 
			//+ "\t\tHrstc " 
			+ "\t" + solutions[3].getState().getValue();
		System.out.println(str);

		/**/
	}
	
	/**
	 * @return solutions	the array of solved puzzle problems
	 */
	public PuzzleProblem[] getSolutions() 
		{ return solutions; }
	
	/**
	 * Implements the First choice search strategy
	 * @param prob	the initial 8puzzle problem to be solved
	 * @return current	puzzle after search has reached an end
	 */
	@Override
	public Problem hcSteepestAscent(Problem prob) {
		
		PuzzleProblem current = (PuzzleProblem) prob;
		PuzzleState neighbor;
		while(true) {
			neighbor = (PuzzleState)current.getBestNeighbor();
			if ( neighbor.getValue() >= current.getState().getValue() )
				return current;
			current.addAction(neighbor.actstr);
			current.setState(neighbor);
		}
		
	}
	
	/**
	 * Implements the First choice search strategy
	 * @param prob	the initial 8puzzle problem to be solved
	 * @return current	puzzle after search has reached an end
	 */
	@Override
	public Problem hcFirstChoice(Problem prob) {

		PuzzleProblem current = (PuzzleProblem) prob;
		PuzzleState neighbor;
		while(true) {
			neighbor = firstChoice(current.getRandomNeighbors(),current);
			if (neighbor == null) 
				return current;
			current.addAction(neighbor.actstr);
			current.setState(neighbor);
		}
	}
	
	/**
	 * Helper for hcFirstChoice.
	 * Picks first better random neighbor
	 * @param neighbors
	 * @param prob
	 * @return first better neighbor or  null if there are none
	 */
	private PuzzleState firstChoice( ArrayList<PuzzleState> neighbors, PuzzleProblem prob) {
		for ( PuzzleState neighbor: neighbors ) {
			if ( neighbor.getValue() < prob.getState().getValue() ) {
				//System.out.println("Random Neighbor " + neighbor.getString());
				return neighbor;
			}
		}
		return null;	
	}

	/**
	 * Implements the random restart strategy for the puzzle problem
	 * @param prob	the initial 8puzzle problem to be solved
	 * @return current	puzzle after search has reached an end
	 */
	@Override
	public Problem hcRandomRestart(Problem prob) {
		// hill climbing with random restart
		// if value not 0 then randomize state.
		PuzzleProblem current = (PuzzleProblem) prob;
		PuzzleState neighbor;
		
		while(current.getState().getValue() > 0) {
			if (current.getPathCost() > 0)
				current.randomizeState();
			//current.addAction("r");	// r for restart
			//current = (PuzzleProblem) hcSteepestAscent(current);
			//*
			
			while(true) {
				neighbor = (PuzzleState)current.getBestNeighbor();
								if ( neighbor.getValue() >= current.getState().getValue() ) {
					break;
				}
				
				current.setState(neighbor);
			}					
			current.addAction(neighbor.actstr);
			current.getState().getHeuristic();
			//System.out.println("test " + current.getSearchCost() +" "+  neighbor.getValue() + " " + current.getState().getValue());

			// */
		}
		
		return current;
	}

	/**
	 * Implements the simulated annealing search Strategy
	 * @param prob	the initial 8puzzle problem to be solved
	 */
	@Override
	public Problem simulatedAnnealing(Problem p) {

		int t = 1;
		double T;

		PuzzleProblem current = (PuzzleProblem) p;
		PuzzleState next;
		int deltaE;
		while (t < Integer.MAX_VALUE) {
			T = (int)scheduleFunction(t++);
			if (T == 0 || current.getState().getValue() == 0 )
				return current;
		
			next = current.getRandomNeighbors().get(0);
			deltaE = current.getState().getValue() - next.getValue();
			if (deltaE > 0)	{
				current.addAction(next.actstr);
				current.setState(next);
			} else {
				Random rand = new Random();
				double prob = Math.exp(deltaE / T);		// probability of accepting worse state
				//System.out.print(" " + prob + " " + T);
				int num = rand.nextInt(1000+1);			// random integer between 0 and 100
				if ( 0 < num && num < prob*1000) {		// if integer is within probability
					current.addAction(next.actstr);
					current.setState(next);
				}
			}
			current.getState().getHeuristic();
		}
		return current;
	}
	
	/**
	 * schedule function for simulated annealing strategy.
	 * @param t		the current step of the annealing
	 * @return	temp	the current tempurature of the problem
	 */
	@Override
	public double scheduleFunction(int t) {
			double temp =  (10/ Math.log(t));// /(t+1);
			return temp;
	}
}