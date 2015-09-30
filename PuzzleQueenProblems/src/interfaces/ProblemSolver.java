package interfaces;

import java.util.Map;

public interface ProblemSolver {
	public State hcSteepestAscent(Problem prob);
	public State hcFirstChoice(Problem prob);
	public State hcRandomRestart(Problem prob);
	public State simulatedAnnealing(Problem p, Map<Integer, Integer> schedule);
	
}
