package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Label;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.insa.graphs.algorithm.AbstractSolution;
import org.insa.graphs.algorithm.AbstractSolution.Status;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

	ArrayList<Label> labels;
	BinaryHeap<Label> tas_labels;
	
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        
        Graph graph = data.getGraph();
        final int nbNodes = graph.size();
        int destination = data.getDestination().getId();
        int origin = data.getOrigin().getId();
        
        labels = new ArrayList<>();
        tas_labels = new BinaryHeap<>();
        float infini = Float.MAX_VALUE;
        
        // INITIALISATION
        for(int i=0; i<nbNodes; i++) {
        	labels.add(new Label(i, false, infini, null));
        }
        labels.get(origin).setCost(0);
        
        BinaryHeap<Node> nodes = new BinaryHeap<>();
        nodes.insert(graph.get(data.getOrigin().getId()));
        
        // TRAITEMENT
        boolean existe_chemin = true;
        Label node = labels.get(origin);
        while(node!=null&&existe_chemin) {
        	node.setMarque(true);
        	// Pour tous les successeurs
        	for(Arc n : graph.get(node.getSommet()).getSuccessors()) {
        		int y = n.getDestination().getId();
        		Label ly = labels.get(y);

        		// Si le sommet n'est pas marqué
        		if(!ly.getMarque()) {
        			float new_cout = (node.getCost()+n.getLength());   
        			// On doit mettre à jour le sommet
        			if(ly.getCost()>new_cout) {
        				if(ly.getCost()!=infini)
        					tas_labels.remove(ly);
        				ly.setCost(new_cout);  
        				tas_labels.insert(ly);
        				ly.setPere(n);
        			}
	        	}
        		
        	}
        	// On vérifie que la liste ne soit pas vide avant de tenter l'extraction
	        if(tas_labels.size()>0) {	        	
	        	node = tas_labels.deleteMin();
	        }
	        else
	        	node = null;
        	
        }
        
        if (labels.get(destination).getMarque()) {
            // CONSTRUCTION DE LA SOLUTION
            List<Node> noeuds = new ArrayList<>();
            Label courant = labels.get(destination);
            // On reconstruit le chemin
            while(courant.getSommet()!=origin) {
            	Node noeud = graph.get(courant.getSommet());
            	noeuds.add(noeud);
            	courant = labels.get(courant.getPere().getOrigin().getId());
            }
        	noeuds.add(graph.get(courant.getSommet()));
            
        	//On oublie pas d'inverser pour 
        	Collections.reverse(noeuds);
        	Path chemin = Path.createShortestPathFromNodes(graph, noeuds);
        	
        	solution = new ShortestPathSolution(data, AbstractSolution.Status.FEASIBLE, chemin);
        } else {
        	solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        

    	
    	
    	
        return solution;
    }
}
