package puzzle;

import java.util.ArrayList;
import java.util.Random;

import interfaces.Problem;
import interfaces.ProblemSolver;
import interfaces.State;

public class PuzzleProblemSolver implements ProblemSolver {
	
	
	PuzzleProblem[] solutions = new PuzzleProblem[4];
	
	public PuzzleProblemSolver(String ini) {
		
		System.out.println("Starting HillClimbing");
		solutions[0] = (PuzzleProblem) hcSteepestAscent(new PuzzleProblem(ini));
		/*
		System.out.println("Starting FirstChoice");
		solutions[1] = hcFirstChoice(new PuzzleProblem(ini));
		
		System.out.println("Starting Random Restart");
		solutions[2] = hcRandomRestart(new PuzzleProblem(ini));
		
		System.out.println("Starting Simulated Annealing");
		solutions[3] = simulatedAnnealing(new PuzzleProblem(ini));
		*/
	}
	
	public PuzzleProblem[] getSolutions() 
		{ return solutions; }
	
	
	@Override
	public Problem hcSteepestAscent(Problem prob) {
		
		PuzzleProblem current = (PuzzleProblem) prob;
		PuzzleState neighbor;
		while(true) {
			neighbor = (PuzzleState)current.getBestNeighbor();
			if ( neighbor.getValue() >= current.getState().getValue() )
				return current;
			current.setState(neighbor);
			current.addAction(neighbor.actstr);
		}
		
	}
	
	@Override
	public Problem hcFirstChoice(Problem prob) {

		PuzzleProblem current = (PuzzleProblem) prob;
		PuzzleState neighbor;
		while(true) {
			neighbor = firstChoice(current.getRandomNeighbors(),current);
			if (neighbor == null) 
				return current;
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
			if ( neighbor.getValue() <= prob.getState().getValue() ) {
				return neighbor;
			}
		}
		return null;	
	}

	@Override
	public Problem hcRandomRestart(Problem prob) {
		// hill climbing with random restart
		// if value not 0 then randomize state.
		while(hcSteepestAscent(prob).getState().getValue() > 0) 
			prob.randomizeState();
		
		return prob;
	}

	@Override
	public Problem simulatedAnnealing(Problem p) {

		int t = 1;
		double T;
		PuzzleState next;
		int deltaE;
		while (t < Integer.MAX_VALUE) {
			T = scheduleFunction(t);
			if (T == 0)
				return p;
			next = ((PuzzleProblem) p).getRandomNeighbors().get(0);
			deltaE = next.getValue() - p .getState().getValue();
			if (deltaE > 0)	{
				p.setState(next);
			} else {
				Random rand = new Random();
				double prob = Math.exp(deltaE / T);		// probability of accepting worse state
				int num = rand.nextInt(100+1);			// random integer between 0 and 100
				if ( 0 < num && num < prob*100) 		// if integer is within probability
					p.setState(next);
			}
		}
		return p;
	}
	
	@Override
	public double scheduleFunction(int t) {
			return ( .999 / (1+t) );
	}

}