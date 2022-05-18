package org.insa.graphs.model;

public class LabelStar extends Label{
	float cout_estime_destination;
	public LabelStar(float cout_estime_destination) {
		super();
		this.cout_estime_destination = cout_estime_destination;
	}
	
	public LabelStar(int sommet_courant, boolean marque, float cout, Arc pere, float cout_estime_destination) {
		super(sommet_courant, marque, cout, pere);
		this.cout_estime_destination = cout_estime_destination;
	}
	private float getCoutDestination() {		
		return cout_estime_destination;
	}

	@Override
	public float getTotalCost() {		
		return this.getCost() + cout_estime_destination;
	}
	@Override
	public int compareTo(Label arg0) {		
		
		int res = Float.compare(this.getTotalCost(),arg0.getTotalCost());
		if(res==0&&arg0 instanceof LabelStar) {
			res = Float.compare(this.cout_estime_destination, ((LabelStar) arg0).getCoutDestination());				
		
		}
		return res;
	}

}
