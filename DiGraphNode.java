package A6_Dijkstra;

import java.util.HashMap;

public class DiGraphNode {
	
	private long id;
	public HashMap<String, Edge> in;
	public HashMap<String, Edge> out;
	private String name;
	
	public boolean known = false;
	public long dv = Long.MAX_VALUE;
	
	public DiGraphNode(long idNum, String name) {
		this.name = name;
		id = idNum;
		in = new HashMap<String, Edge>();
		out = new HashMap<String, Edge>();
	}
	public long getID() {
		return id;
	}
	public String getName() {
		return name;
	}
}
