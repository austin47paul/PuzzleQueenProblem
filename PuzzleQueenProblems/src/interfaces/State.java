package interfaces;

public interface State {
	public int[][] init(String str);
	public int[][] getGoalState();
	public int[][] getState();
	public boolean validAction(String action);
	public int	   getHeuristic();
	public int[][] act(String action);
}
