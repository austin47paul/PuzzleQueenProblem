package interfaces;

public interface Problem {
	public String getActionSequence();
	public String getSolutions();
	public String getActions();
	public String getValidActions(String[] a,State s);
	public State act(String action);
	
}
