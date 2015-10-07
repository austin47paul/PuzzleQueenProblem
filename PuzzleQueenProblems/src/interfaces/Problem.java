package interfaces;


public interface Problem {
	public String getActionSequence();
	public String[] getActions();
	public State act(String action);
	public State getState();
	public void setState(State state);
	public State getBestNeighbor();
	public void randomizeState();
}
