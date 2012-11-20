package test;

import javax.swing.SwingUtilities;

import com.softkiwi.algorithms.graphs.DirectedGraph;
import com.softkiwi.algorithms.graphs.VertexData;

class Node implements VertexData<String> {

	private String label;

	public Node(String label) {
		this.label = label;
	}

	@Override
	public String getVertexId() {
		return label;
	}

}

class StringGraph extends DirectedGraph<String, Node> {

}

public class Main implements Runnable {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Main());
	}

	@Override
	public void run() {
		StringGraph graph = new StringGraph();

		// create nodes
		Node a = new Node("a");
		Node b = new Node("b");
		Node c = new Node("c");
		Node d = new Node("d");
		Node e = new Node("e");
		Node f = new Node("f");
		Node x = new Node("x");
		Node y = new Node("y");

		// add to graph
		graph.addVertex(a, b, c, d, e, f, x, y);

		// connect nodes
		graph.addEdge(b, a);
		graph.addEdge(d, a);
		graph.addEdge(c, b);
		graph.addEdge(b, x);
		graph.addEdge(e, f);

		// sort nodes
		graph.sort();

		System.out.print(graph);
	}
}
