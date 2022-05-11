package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractInputData.Mode;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Label;
import org.insa.graphs.model.Path;
import org.insa.graphs.algorithm.AbstractSolution;

public class ShortestPathSolution extends AbstractSolution implements Comparable<ShortestPathSolution>{

    // Optimal solution.
    private final Path path;

    /**
     * Create a new infeasible shortest-path solution for the given input and
     * status.
     * 
     * @param data Original input data for this solution.
     * @param status Status of the solution (UNKNOWN / INFEASIBLE).
     */
    public ShortestPathSolution(ShortestPathData data, Status status) {
        super(data, status);
        this.path = null;
    }

    /**
     * Create a new shortest-path solution.
     * 
     * @param data Original input data for this solution.
     * @param status Status of the solution (FEASIBLE / OPTIMAL).
     * @param path Path corresponding to the solution.
     */
    public ShortestPathSolution(ShortestPathData data, Status status, Path path) {
        super(data, status);
        this.path = path;
    }

    @Override
    public ShortestPathData getInputData() {
        return (ShortestPathData) super.getInputData();
    }

    /**
     * @return The path of this solution, if any.
     */
    public Path getPath() {
        return path;
    }

    @Override
    public String toString() {
        String info = null;
        if (!isFeasible()) {
            info = String.format("No path found from node #%d to node #%d",
                    getInputData().getOrigin().getId(), getInputData().getDestination().getId());
        }
        else {
            double cost = 0;
            for (Arc arc: getPath().getArcs()) {
                cost += getInputData().getCost(arc);
            }
            info = String.format("Found a path from node #%d to node #%d",
                    getInputData().getOrigin().getId(), getInputData().getDestination().getId());
            if (getInputData().getMode() == Mode.LENGTH) {
                info = String.format("%s, %.4f kilometers", info, cost / 1000.0);
            }
            else {
                info = String.format("%s, %.4f minutes", info, cost / 60.0);
            }
        }
        info += " in " + getSolvingTime().getSeconds() + " seconds.";
        return info;
    }

	@Override
	public int compareTo(ShortestPathSolution arg0) {
		int res = 0;
		Path path1 = this.getPath();
		Path path2 = arg0.getPath();
		if(path1==null||path2==null) {
			if(this.getStatus()!=arg0.getStatus()) {
				res = -1;
			} else {
				res = 0;
			}
		} else if(Integer.compare(path1.size(), path2.size())==0
				||Float.compare(path1.getLength(),path2.getLength())==0
				||Double.compare(path1.getMinimumTravelTime(),path2.getMinimumTravelTime())==0
				||this.getStatus()==arg0.getStatus()
				) {
			res = 0;
		} else {
			res = -1;
		}
		return res;
	}

}
