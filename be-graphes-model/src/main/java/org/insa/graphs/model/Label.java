package org.insa.graphs.model;

public class Label implements Comparable<Label>{
	int sommet_courant;
	boolean marque;
	float cout;
	Arc pere;
	
	int noeud_associe;
	
	public Label() {
		this.sommet_courant = -1;
		this.marque = false;
		this.cout = -1;
		this.pere = null;
		this.noeud_associe=-1;
	}
	
	public Label(int sommet_courant, boolean marque, float cout, Arc pere) {
		this.sommet_courant = sommet_courant;
		this.marque = marque;
		this.cout = cout; 
		this.pere = pere;
		this.noeud_associe=-1;
		
	}
	
	public void Associer(int node) {
		this.sommet_courant=node;
	}
	
	public float getCost() {
		return this.cout;
	}
	public void setCost(float cost) {
		this.cout = cost;
	}
	
	public boolean getMarque() {
		return marque;
	}
	public int getSommet() {
		return sommet_courant;
	}
	public void setMarque(boolean marque) {
		this.marque = marque;
	}
	public void setPere(Arc pere) {
		this.pere = pere;
	}
	public Arc getPere() {
		return this.pere;
	}

	@Override
	public int compareTo(Label arg0) {		
		return Float.compare(this.cout,arg0.cout);
	}

}
