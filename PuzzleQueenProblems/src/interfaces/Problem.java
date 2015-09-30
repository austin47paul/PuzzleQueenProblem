package interfaces;

public interface Problem {
	public String getActionSequence();
	public String getSolutions();
	public String[] getActions();
	public State act(String action);
	public State getState();
	public void setState(State state);
	
}
