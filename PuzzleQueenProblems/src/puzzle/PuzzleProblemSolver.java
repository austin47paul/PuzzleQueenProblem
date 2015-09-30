package puzzle;

import java.util.Map;

import interfaces.Problem;
import interfaces.ProblemSolver;
import interfaces.State;

public class PuzzleProblemSolver implements ProblemSolver {
	
	
	State[] solutions = new State[4];
	public PuzzleProblemSolver(String ini) {
		PuzzleProblem prob = new PuzzleProblem(ini);
		PuzzleProblem hcprob = new PuzzleProblem(ini);
		PuzzleProblem fcprob = new PuzzleProblem(ini);
		PuzzleProblem rrprob = new PuzzleProblem(ini);
		PuzzleProblem saprob = new PuzzleProblem(ini);
		
		
		
	}
	@Override
	public State hcSteepestAscent(Problem prob) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State hcFirstChoice(Problem prob) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State hcRandomRestart(Problem prob) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State simulatedAnnealing(Problem p, Map<Integer, Integer> schedule) {
		// TODO Auto-generated method stub
		return null;
	}

}
