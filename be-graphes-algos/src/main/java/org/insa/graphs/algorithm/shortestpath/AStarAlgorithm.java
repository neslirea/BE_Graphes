package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.insa.graphs.algorithm.AbstractSolution;
import org.insa.graphs.algorithm.AbstractInputData.Mode;
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

	ShortestPathData data;
	
    public AStarAlgorithm(ShortestPathData data) {
        super(data);
        this.data = data;
    }
    
    @Override
    protected Label createLabel(int i) {
        float infini = Float.MAX_VALUE;
        float cout_estime=0;
        int destination = data.getDestination().getId();
    	Graph graph = data.getGraph();
    	if(i!=destination){
    		if(data.getMode()==Mode.LENGTH) {
            	cout_estime = (float)Point.distance(graph.get(i).getPoint(), graph.get(destination).getPoint());
    		} else {
            	float distance = (float)Point.distance(graph.get(i).getPoint(), graph.get(destination).getPoint());
            	int speed = graph.getGraphInformation().getMaximumSpeed();
            	cout_estime=distance*((float)3.6/speed);
    		}
    	}
    	return new LabelStar(i, false, infini, null, cout_estime);
    }

}
