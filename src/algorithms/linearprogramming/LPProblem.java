package algorithms.linearprogramming;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Container class for Linear programming problem, have objective function and constrains.
 * @author neyne
 *
 */
public class LPProblem {
	
	/**
	 * opt enum defines which types of optimization the problem requests, Min or max.
	 * @author neyne
	 */
	public enum opt {
		MAX,
		MIN
	}
	
	// The objective function equation, TODO: change it to a sub-class of equation called ObjectiveFunction
	private Equation objective;
	
	// list of constraints 
	private ArrayList<Equation> constraints;

	// Optimization problem type
	private opt optimization; 
	
	/**
	 * Constructor
	 */
	public LPProblem() {
		constraints = new ArrayList<>();
		optimization = opt.MAX;
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
		return  constraints.toArray(new Equation[constraints.size()]);
	}
	
	public void setOptimizationType(opt o) {
		optimization = o;
	}
	
	public opt getOptimizationType() {
		return optimization;
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
