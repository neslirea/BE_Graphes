package org.insa.graphs.algorithm.shortestpath;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import org.junit.Test;

public class AStarAlgorithmTest extends DijkstraAlgorithmTest{

	@Override
	public ShortestPathAlgorithm CreateAlgorithm (ShortestPathData data) {
		return new AStarAlgorithm(data);
	}
	

	/**
	 * Test method for {@link org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm#DijkstraAlgorithm(org.insa.graphs.algorithm.shortestpath.ShortestPathData)}.
	 */
	@Override
	@Test
	public void testCheminLong() {
		try {
	        // Visit these directory to see the list of available files on Commetud.
	        final String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/madagascar.mapgr";
	
	        // Create a graph reader.
	        final GraphReader reader = new BinaryGraphReader(
	                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	
	        // Read the graph.
	        final Graph graph = reader.read();	
	        ArcInspector ai_short = ArcInspectorFactory.getAllFilters().get(0);
	        ArcInspector ai_fast = ArcInspectorFactory.getAllFilters().get(2);
    		
	        Node origin = graph.get((int)(Math.random() * (graph.size()+ 1)));
	        Node destination = graph.get((int)(Math.random() * (graph.size()+ 1)));		               
	        
	        
	        ShortestPathAlgorithm da_fast = new DijkstraAlgorithm(new ShortestPathData(graph, origin, destination, ai_fast));	   
	        ShortestPathAlgorithm da_short = new DijkstraAlgorithm(new ShortestPathData(graph, origin, destination, ai_short));	 
	        
	        ShortestPathAlgorithm as_fast = CreateAlgorithm(new ShortestPathData(graph, origin, destination, ai_fast));	   
	        ShortestPathAlgorithm as_short = CreateAlgorithm(new ShortestPathData(graph, origin, destination, ai_short));	
	        
	        ShortestPathSolution sold_short = da_short.run();
	        
	        ShortestPathSolution solas_short = as_short.run();
	        if (sold_short.isFeasible()){
	        	assertTrue(sold_short.compareTo(solas_short)==0);  	
	        }
	        
        }
		catch(Exception e) {
			System.out.println("long : "+e.toString());
		}
	}
	//*/
}
