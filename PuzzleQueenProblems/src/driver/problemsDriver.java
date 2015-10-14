package driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import puzzle.PuzzleProblem;
import puzzle.PuzzleProblemSolver;



public class problemsDriver {

	public static void main(String[] args) throws FileNotFoundException {		
		try {

			//for(int i = 1;i<9;i++) {
									// for some reason the program chokes up when the line is 123456780
									// in the First choice
									// so I can't really run other case files other than my own
				Scanner sc = new Scanner(new File("TestCases/8puzzle1.txt"));
				
				while ( sc.hasNextLine() ) {
					String init = sc.nextLine();
					String goal = sc.nextLine();
					//System.out.println(init);
					PuzzleProblemSolver pr = new PuzzleProblemSolver(init,goal);
					PuzzleProblem[] probs = (PuzzleProblem[]) pr.getSolutions();
					String[] str = buildStrings(probs);
							
					System.out.println(str[0]);
					System.out.println(str[1]);
					System.out.println(str[2]);
					System.out.println(str[3]);	
					
				}
				
				sc.close();
			//}
			
		} catch (FileNotFoundException e) {
			System.out.println(" 8 puzzles file not found.");
		}
			
		
	}
	/**
	 * Builds the output strings.
	 * @param probs	the end points of the strategies
	 * @return	str	an array of strings displaying strategy results
	 */
	public static String[]  buildStrings( PuzzleProblem[] probs) {
		String[] str = new String[4];
		str[0] = "Steepest Ascent: \nInit: " + probs[0].getInitStr() 
				+ " End: " + probs[0].getState().getString(probs[0].getState().getState())
				+ " Goal: " + probs[0].getState().getString(probs[0].getState().getGoalState())
				+ " " + probs[0].getActionSequence()
				+ " PathCost " + probs[0].getSteps() + " Heuristic " + probs[0].getState().getValue();
		
		str[1] = "First Choice: \nInit: " + probs[1].getInitStr() 
				+ " End: " + probs[1].getState().getString(probs[1].getState().getState())
				+ " Goal: " + probs[1].getState().getString(probs[1].getState().getGoalState())
				+ " " + probs[1].getActionSequence() + " " 
				+ " PathCost " + probs[1].getSteps() + " Heuristic " + probs[1].getState().getValue();
			
		str[2] = "Random Restart: \nInit: " + probs[2].getInitStr() 
				+ " End " + probs[2].getState().getString(probs[2].getState().getState())
				+ " Goal: " + probs[2].getState().getString(probs[2].getState().getGoalState())
				+ " Actions: " + probs[2].getActionSequence() + " \n" 
				+ " PathCost: " + probs[2].getSteps() + " Heuristic " + probs[2].getState().getValue();
					
		str[3] = "Simulated Annealing: \nInit: " + probs[3].getInitStr() 
				+ " End: " + probs[3].getState().getString(probs[3].getState().getState())
				+ " Goal: " + probs[3].getState().getString(probs[3].getState().getGoalState())
				+ " PathCost " + probs[3].getSteps() + " Heuristic " + probs[3].getState().getValue();
		return str;
	}

}
