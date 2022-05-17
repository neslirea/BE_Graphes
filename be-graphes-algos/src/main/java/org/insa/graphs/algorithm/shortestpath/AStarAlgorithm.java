package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.insa.graphs.algorithm.AbstractSolution;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Label;
import org.insa.graphs.model.LabelStar;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.Point;

public class AStarAlgorithm extends DijkstraAlgorithm {

	int destination;
	Graph graph;
	
    public AStarAlgorithm(ShortestPathData data) {
        super(data);
        destination = data.getDestination().getId();
        graph = data.getGraph();
    }
    
    
    protected Label createLabel(int i) {
        float infini = Float.MAX_VALUE;
        float cout_estime=0;
    	if(i!=destination){
        	cout_estime = (float)Point.distance(graph.get(i).getPoint(), graph.get(destination).getPoint());
    	}
    	return new LabelStar(i, false, infini, null, cout_estime);
    }

}
