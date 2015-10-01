package puzzle;

import java.util.ArrayList;
import java.util.Map;

import interfaces.Problem;
import interfaces.ProblemSolver;
import interfaces.State;

public class PuzzleProblemSolver implements ProblemSolver {
	
	
	State[] solutions = new State[4];
	Map<Integer,Integer> sched;
	
	public PuzzleProblemSolver(String ini) {
		
		System.out.println("Starting HillClimbing");
		solutions[0] = hcSteepestAscent(new PuzzleProblem(ini));
		
		System.out.println("Starting FirstChoice");
		solutions[1] = hcFirstChoice(new PuzzleProblem(ini));
		
		System.out.println("Starting Random Restart");
		solutions[2] = hcRandomRestart(new PuzzleProblem(ini));
		
		System.out.println("Starting Simulated Annealing");
		solutions[3] = simulatedAnnealing(new PuzzleProblem(ini),sched);
	}
	
	public State[] getSolutions() {
		return solutions;
	}
	@Override
	public State hcSteepestAscent(Problem prob) {
		
		PuzzleProblem current = (PuzzleProblem) prob;
		PuzzleState neighbor;
		while(true) {
			neighbor = (PuzzleState)current.getBestNeighbor();
			if ( neighbor.getValue() >= current.getState().getValue() )
				return current.getState();
			current.setState(neighbor);
		}
		
	}

	@Override
	public State hcFirstChoice(Problem prob) {

		PuzzleProblem current = (PuzzleProblem) prob;
		PuzzleState neighbor;
		while(true) {
			neighbor = firstChoice(current.getRandomNeighbors(),current);
			if (neighbor == null) 
				return current.getState();
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
	public State hcRandomRestart(Problem prob) {
		// hillclimb
		// if value not 0 then randomize state.
		return null;
	}

	@Override
	public State simulatedAnnealing(Problem p, Map<Integer, Integer> schedule) {
		// TODO Auto-generated method stub
		return null;
	}

}
