package A6_Dijkstra;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
public class DiGraph implements DiGraphInterface {

	private int numNodes;
	private int numEdges;
	DiGraphNode root;

	private Map<String, DiGraphNode> graph = new HashMap<String, DiGraphNode>();
	private Set<Long> node_id = new HashSet<Long>();
	private Set<Long> edge_id = new HashSet<Long>();

	// in here go all your data and methods for the graph
	// and the topo sort operation

	public DiGraph ( ) { // default constructor
		// explicitly include this
		// we need to have the default constructor
		// if you then write others, this one will still be there
	}

	@Override
	public boolean addNode(long idNum, String label) {
		if(node_id.add(idNum) == false || idNum < 0) {
			return false;
		} if(graph.containsKey(label)) {
			return false;
		} DiGraphNode value = new DiGraphNode(idNum, label);
		graph.put(label, value);
		numNodes++;
		return true;
	}

	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		if(edge_id.add(idNum) == false || idNum < 0) {
			return false;
		} if(graph.containsKey(sLabel) == false || graph.containsKey(dLabel) == false) {
			return false;
		} if(graph.get(sLabel).out.containsKey(dLabel)) {
			return false;
		} Edge e = new Edge(idNum, graph.get(sLabel), graph.get(dLabel), weight, eLabel);

		graph.get(sLabel).out.put(dLabel, e);
		graph.get(dLabel).in.put(sLabel, e);
		numEdges++;
		return true;
	}

	@Override
	public boolean delNode(String label) {
		if(!graph.containsKey(label))return false;
		for(String i : graph.get(label).in.keySet()) {
			edge_id.remove(graph.get(label).in.get(i).getID());
			graph.get(i).out.remove(label);
		}
		for(String i : graph.get(label).out.keySet()) {
			edge_id.remove(graph.get(label).out.get(i).getID());
			graph.get(i).in.remove(label);
		}
		node_id.remove(graph.get(label).getID());
		graph.remove(label);
		numNodes--;
		return true;
	}

	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		if(graph.containsKey(sLabel) == false) {
			return false;
		}
		if(graph.get(sLabel).out.containsKey(dLabel) == false) {
			return false;
		} edge_id.remove(graph.get(sLabel).out.get(dLabel).getID());
		graph.get(sLabel).out.remove(dLabel);
		graph.get(dLabel).in.remove(sLabel);
		numEdges--;
		return true;
	}

	@Override
	public long numNodes() {
		return numNodes;
	}

	@Override
	public long numEdges() {
		return numEdges;
	}

	@Override
	public String[] topoSort() {
		String[] elements = new String[numNodes];
		Queue<String> q = new Queue<String>();
		int counter = 0;

		for(String i : graph.keySet()) {
			if(graph.get(i).in.size() == 0)q.enqueue(i);
		}

		while(!q.isEmpty()) {
			elements[counter] = q.dequeue();

			for(String i : graph.get(elements[counter]).out.keySet()) {
				if(graph.get(i).in.size() == 1)q.enqueue(i);
			}
			delNode(elements[counter]);
			counter++;
		}
		if(counter != elements.length) {
			return null;
		}
		return elements;
	}

	@Override
	public ShortestPathInfo[] shortestPath(String label) {
		PriorityQueue<Pair> binHeap = new PriorityQueue<>();

		graph.get(label).dv = 0;
		binHeap.add(new Pair(0, graph.get(label)));

		ShortestPathInfo[] out = new ShortestPathInfo[numNodes];

		int m = 0;
		String tempLabel;

		while(!binHeap.isEmpty()) {
			DiGraphNode n = binHeap.peek().getVertex();
			long d = binHeap.peek().getWeight();
			tempLabel = n.getName();
			
			binHeap.remove();
			if(n.known) {
				continue;
			} else {
				n.known = true;

				for(String i : graph.get(tempLabel).out.keySet()) {

					if(!graph.get(i).known) {

						if(graph.get(i).dv > d + graph.get(tempLabel).out.get(i).getWeight()) {
							
							graph.get(i).dv = d + graph.get(tempLabel).out.get(i).getWeight();
							binHeap.add(new Pair(graph.get(i).dv, graph.get(i)));
							
						}
					}
				}
			}

			out[m] = new ShortestPathInfo(tempLabel, d);
			m++;
			
		}

		for(String i : graph.keySet()) {
			if(!graph.get(i).known) {
				out[m] = new ShortestPathInfo(graph.get(i).getName(), -1);
				m++;
			}
		}
		
		return out;
		
	}

	// rest of your code to implement the various operations
}