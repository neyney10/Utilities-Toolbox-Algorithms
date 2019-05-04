package algorithms.linearprogramming;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Container class for Linear programming problem, have objective function and constrains.
 * @author neyne
 *
 */
public class LPProblem {
	private Equation objective;
	private ArrayList<Equation> constraints;

	public LPProblem() {
		constraints = new ArrayList<>();
	}
	
	public double[][] constructLP() {
		double matrix[][] = new double[constraints.size()+1][objective.getVariables().length+1];
		
		//matrix[0] =  new double[] {2,3,4,5}; //new double[objective.getVariables().length+1];
		for (int i = 0; i < matrix.length-1; i++) {
			Equation cons = constraints.get(i);
			matrix[i] = Arrays.copyOf(cons.getVariables(), objective.getVariables().length+1);
			matrix[i][matrix[i].length-1] =  cons.getB();
		}
		
		double var[] = objective.getVariables();
		for (int i = 0; i < matrix[matrix.length-1].length-1; i++) {
			matrix[matrix.length-1][i] = -var[i];
		}
		
		return matrix;
	}
	
	public void setObjective(Equation objEquation) {
		objective = objEquation;
	}
	
	public void addConstraint(Equation eq) {
		constraints.add(eq);
	}
	
	public Equation getObjective() {
		return objective;
	}
	
	public Equation[] getConstraints() {
		return (Equation[]) constraints.toArray();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Objective function:\n" + objective);
		sb.append("\n");
		
		sb.append("Constraints: \n");
		for (Equation cons : constraints) {
			sb.append(cons + "\n");
		}
		
		return sb.toString();
	}
}
