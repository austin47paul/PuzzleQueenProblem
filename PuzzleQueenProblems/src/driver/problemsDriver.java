package driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import interfaces.Problem;
import puzzle.PuzzleProblem;
import puzzle.PuzzleProblemSolver;



public class problemsDriver {

	public static void main(String[] args) throws FileNotFoundException {		
		try {
			Scanner sc = new Scanner(new File("TestCases/8puzzle.txt"));
			
			while ( sc.hasNextLine() ) {
				String init = sc.nextLine();
				System.out.println(init);
				PuzzleProblemSolver pr = new PuzzleProblemSolver(init);
				PuzzleProblem[] probs = (PuzzleProblem[]) pr.getSolutions();
				String[] str = buildStrings(probs);
						
				System.out.println(str[0]);
				System.out.println(str[1]);
				System.out.println(str[2]);
				System.out.println(str[3]);
			}
			
			sc.close();
			
		} catch (FileNotFoundException e) {
			System.out.println(" 8 puzzles file not found.");
		}
			
		
	}
	
	public static String[]  buildStrings( PuzzleProblem[] probs) {
		String[] str = new String[4];
		str[0] = "Steepest Ascent: " + probs[0].getInitStr() 
				+ " " + probs[0].getState().getString()
				+ "\n" + probs[0].getActionSequence() + " " 
				+ " " + probs[0].getSteps() + " " + probs[0].getState().getValue();
		/*
		str[1] = "First Choice: " + probs[1].getInitStr() 
				+ " " + probs[1].getState().toString()
				+ "\n" + probs[1].getActionSequence() + " " 
				+ " " + probs[1].getSteps() + " " + probs[1].getState().getValue();
		str[2] = "Random Restart: " + probs[2].getInitStr() 
				+ " " + probs[2].getState().toString()
				+ "\n" + probs[2].getActionSequence() + " " 
				+ " " + probs[2].getSteps() + " " + probs[1].getState().getValue();
		str[4] = "Simulated Annealing: " + probs[3].getInitStr() 
				+ " " + probs[3].getState().toString()
				+ "\n" + probs[3].getActionSequence() + " " 
				+ " " + probs[3].getSteps() + " " + probs[3].getState().getValue();
		*/
		return str;
	}

}
