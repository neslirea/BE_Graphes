/**
 * 
 */
package org.insa.graphs.algorithm.shortestpath;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.List;

import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.BinaryPathReader;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.model.io.PathReader;
import org.junit.Test;

/**
 * @author aubery
 *
 */
public class DijkstraAlgorithmTest {

	/**
	 * Test method for {@link org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm#DijkstraAlgorithm(org.insa.graphs.algorithm.shortestpath.ShortestPathData)}.
	 */
	@Test
	public void testCheminCourt() {
		try {
	        // Visit these directory to see the list of available files on Commetud.
	        final String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/paris.mapgr";
	
	        // Create a graph reader.
	        final GraphReader reader = new BinaryGraphReader(
	                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	
	        // Read the graph.
	        final Graph graph = reader.read();	
	        List<ArcInspector> ais = ArcInspectorFactory.getAllFilters();
	        
	        for(int i=0; i<ais.size();i++) {
		        ArcInspector ai = ais.get(i);
        		
		        Node origin = graph.get((int)(Math.random() * (graph.size()+ 1)));
		        Node destination = graph.get((int)(Math.random() * (graph.size()+ 1)));		               
		        
		        
		        
		        DijkstraAlgorithm da = new DijkstraAlgorithm(new ShortestPathData(graph, origin, destination, ai));	        
		        BellmanFordAlgorithm ba = new BellmanFordAlgorithm(new ShortestPathData(graph, origin, destination, ai));
		        

		        if (origin.getId()!=destination.getId())
		        	assertTrue(ba.run().compareTo(da.run())==0);
		        
	        }


        }
		catch(Exception e) {
			System.out.println("court : "+e.toString());
			e.printStackTrace();
			
		} 
	}
		
	@Test
	public void testCheminNull() {
		try {
	        // Visit these directory to see the list of available files on Commetud.
	        final String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
	
	        // Create a graph reader.
	        final GraphReader reader = new BinaryGraphReader(
	                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	
	        // Read the graph.
	        final Graph graph = reader.read();	

	        ArcInspector ai = ArcInspectorFactory.getAllFilters().get(0);
    		
	        Node origin = graph.get(71);
	        Node destination = graph.get(1026);
	        
	        
	        DijkstraAlgorithm da = new DijkstraAlgorithm(new ShortestPathData(graph, origin, destination, ai));	        
	        BellmanFordAlgorithm ba = new BellmanFordAlgorithm(new ShortestPathData(graph, origin, destination, ai));
	        
	        assertTrue(ba.run().compareTo(da.run())==0);
	     
        }
		catch(Exception e) {
			System.out.println("null : "+e.toString());
			
		}
	}
	
	@Test
	public void testCheminInexistant() {
		try {
	        // Visit these directory to see the list of available files on Commetud.
	        final String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
	
	        // Create a graph reader.
	        final GraphReader reader = new BinaryGraphReader(
	                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	
	        // Read the graph.
	        final Graph graph = reader.read();	

	        ArcInspector ai = ArcInspectorFactory.getAllFilters().get(0);
    		
	        Node origin = graph.get((int)75);
	        Node destination = graph.get((int)1283);		               
	        
	        
	        DijkstraAlgorithm da = new DijkstraAlgorithm(new ShortestPathData(graph, origin, destination, ai));	        
	        BellmanFordAlgorithm ba = new BellmanFordAlgorithm(new ShortestPathData(graph, origin, destination, ai));
	        
	        assertTrue(ba.run().compareTo(da.run())==0);
	     
        }
		catch(Exception e) {
			System.out.println("inexistant : "+e.toString());
			
		}
	}

	
	/**
	 * Test method for {@link org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm#DijkstraAlgorithm(org.insa.graphs.algorithm.shortestpath.ShortestPathData)}.
	 */
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
	        
	        
	        DijkstraAlgorithm da_fast = new DijkstraAlgorithm(new ShortestPathData(graph, origin, destination, ai_fast));	   
	        DijkstraAlgorithm da_short = new DijkstraAlgorithm(new ShortestPathData(graph, origin, destination, ai_short));	     
	        
	        ShortestPathSolution sol_fast = da_fast.run();
	        ShortestPathSolution sol_short = da_short.run();
	        if (sol_fast.isFeasible()){
		        assertTrue(sol_fast.getPath().getLength()>=sol_short.getPath().getLength() );
		        assertTrue(sol_fast.getPath().getMinimumTravelTime()<=sol_short.getPath().getMinimumTravelTime());	        	
	        }
	        


        }
		catch(Exception e) {
			System.out.println("long : "+e.toString());
		}
	}

}
