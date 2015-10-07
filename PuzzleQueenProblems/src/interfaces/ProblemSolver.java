package interfaces;

public interface ProblemSolver {
	public Problem hcSteepestAscent(Problem prob);
	public Problem hcFirstChoice(Problem prob);
	public Problem hcRandomRestart(Problem prob);
	public Problem simulatedAnnealing(Problem p);
	public double scheduleFunction(int t);
	public Problem[] getSolutions();
}
