package A6_Dijkstra;

public class Edge {
	private long idNum;
	private DiGraphNode sLabel;
	private DiGraphNode dLabel;
	private long weight;
	private String eLabel;
	
	public Edge(long idNum, DiGraphNode sLabel, DiGraphNode dLabel, long weight, String eLabel) {
		this.idNum = idNum;
		this.sLabel = sLabel;
		this.dLabel = dLabel;
		this.weight = weight;
		this.eLabel = eLabel;
	}
	public DiGraphNode getSLabel() {
		return sLabel;
	}
	public DiGraphNode getDLabel() {
		return dLabel;
	}
	public String getELabel() {
		return eLabel;
	}
	public long getWeight() {
		return weight;
	}
	public long getID() {
		return idNum;
	}
	public void setWeight(long w) {
		weight = w;
	}
	public void setSLabel(DiGraphNode s) {
		sLabel = s;
	}
	public void setDLabel(DiGraphNode s) {
		dLabel = s;
	}
	public void setELabel(String s) {
		eLabel = s;
	}
	
	
}
