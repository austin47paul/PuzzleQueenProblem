package interfaces;

public interface State {
	public int[][] init(String str);
	public int[][] getGoalState();
	public int[][] getState();
	public int	   getValue();
	public boolean validAction(String action);
	public void	   getHeuristic();
	public int[][] act(String action);
}
