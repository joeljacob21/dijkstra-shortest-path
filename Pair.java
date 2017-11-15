package A6_Dijkstra;

public class Pair implements Comparable<Pair> {
	private long weight;
	private DiGraphNode vertex;

	public Pair(long weight, DiGraphNode vertex) {
		this.weight = weight;
		this.vertex = vertex;
	}
	public long getWeight() {
		return weight;
	}
	public DiGraphNode getVertex() {
		return vertex;
	}
	@Override
	public int compareTo(Pair aThat) {
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;

		//this optimization is usually worthwhile, and can
		//always be added
		if (this.getWeight() == aThat.getWeight()) return EQUAL;

		//primitive numbers follow this form
		if (this.getWeight() < aThat.getWeight()) return BEFORE;
		if (this.getWeight() > aThat.getWeight()) return AFTER;
		return EQUAL;
	}


}
