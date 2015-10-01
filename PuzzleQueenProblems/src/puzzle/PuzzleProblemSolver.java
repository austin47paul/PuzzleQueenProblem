package puzzle;

import java.util.Map;

import interfaces.Problem;
import interfaces.ProblemSolver;
import interfaces.State;

public class PuzzleProblemSolver implements ProblemSolver {
	
	
	State[] solutions = new State[4];
	Map<Integer,Integer> sched;
	public PuzzleProblemSolver(String ini) {
		
		PuzzleProblem hcprob = new PuzzleProblem(ini);
		PuzzleProblem fcprob = new PuzzleProblem(ini);
		PuzzleProblem rrprob = new PuzzleProblem(ini);
		PuzzleProblem saprob = new PuzzleProblem(ini);
		
		solutions[0] = hcSteepestAscent(hcprob);
		solutions[1] = hcFirstChoice(fcprob);
		solutions[2] = hcRandomRestart(rrprob);
		solutions[3] = simulatedAnnealing(saprob,sched);
	}
	
	public State[] getSolutions() {
		return solutions;
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
