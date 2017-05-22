package aima.core.search.informed;

import aima.core.search.framework.QueueBasedSearch;
import aima.core.search.framework.QueueFactory;
import aima.core.search.framework.evalfunc.EvaluationFunction;
import aima.core.search.framework.evalfunc.HeuristicEvaluationFunction;
import aima.core.search.framework.evalfunc.HeuristicFunction;
import aima.core.search.framework.qsearch.QueueSearch;

import java.util.Comparator;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): page 92.<br>
 * <br>
 * Best-first search is an instance of the general TREE-SEARCH or GRAPH-SEARCH
 * algorithm in which a node is selected for expansion based on an evaluation
 * function, f(n). The evaluation function is construed as a cost estimate, so
 * the node with the lowest evaluation is expanded first. The implementation of
 * best-first graph search is identical to that for uniform-cost search (Figure
 * 3.14), except for the use of f instead of g to order the priority queue.
 * 
 * @author Ciaran O'Reilly
 * @author Mike Stampone
 * @author Ruediger Lunde
 */
public class BestFirstSearch extends QueueBasedSearch implements Informed {

	private final EvaluationFunction evalFunc;
	
	/**
	 * Constructs a best first search from a specified search problem and
	 * evaluation function.
	 * 
	 * @param impl
	 *            a search space exploration strategy.
	 * @param ef
	 *            an evaluation function, which returns a number purporting to
	 *            describe the desirability (or lack thereof) of expanding a
	 *            node.
	 */
	public BestFirstSearch(QueueSearch impl, final EvaluationFunction ef) {
		super(impl, QueueFactory.createPriorityQueue(Comparator.comparing(ef::f)));
		evalFunc = ef;
	}

	/** Modifies the evaluation function if it is a {@link HeuristicEvaluationFunction}. */
	@Override
	public void setHeuristicFunction(HeuristicFunction hf) {
		if (evalFunc instanceof HeuristicEvaluationFunction)
			((HeuristicEvaluationFunction) evalFunc).setHeuristicFunction(hf);
	}
}
