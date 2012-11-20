package com.softkiwi.algorithms.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public abstract class DirectedGraph<KEY, T extends VertexData<KEY>> {

	private Map<KEY, T> verticies = new HashMap<KEY, T>(); // vertex mapper

	private Map<KEY, ArrayList<KEY>> adjacencyList = new HashMap<KEY, ArrayList<KEY>>(); // outcoming connections

	private Map<KEY, ArrayList<KEY>> invertedAdjacencyList = new HashMap<KEY, ArrayList<KEY>>(); // incoming connections

	private Map<KEY, Boolean> visited = new HashMap<KEY, Boolean>(); // visited nodes

	private Queue<T> result = new LinkedList<T>(); // result for sorted nodes

	public void addVertex(T... vertex) {
		for (T v : vertex) {
			KEY id = v.getVertexId();

			if (!verticies.containsKey(id)) {
				verticies.put(id, v);
				adjacencyList.put(id, new ArrayList<KEY>());
				invertedAdjacencyList.put(id, new ArrayList<KEY>());
			}
		}
	}

	public void addEdge(KEY from, KEY to) {
		if (adjacencyList.containsKey(from) && adjacencyList.containsKey(to)) {
			ArrayList<KEY> list = (ArrayList<KEY>) adjacencyList.get(from);
			ArrayList<KEY> invertedList = (ArrayList<KEY>) invertedAdjacencyList.get(to);

			if (!list.contains(to)) {
				if (!invertedList.contains(from)) {
					list.add(to);
					invertedList.add(from);
				}
			}
		}
	}

	public void addEdge(T from, T to) {
		addEdge(from.getVertexId(), to.getVertexId());
	}

	public Queue<T> sort() {
		visited.clear();
		result.clear();

		// nodes with no incoming edges
		for (KEY n : invertedAdjacencyList.keySet()) {
			if (invertedAdjacencyList.get(n).isEmpty()) {
				visit(n);
			}
		}

		return result;
	}

	private void markAsVisited(KEY id) {
		visited.put(id, true);
	}

	private boolean isVisited(KEY id) {
		if (visited.containsKey(id))
			return visited.get(id);

		return false;
	}

	private void visit(KEY id) {
		if (!isVisited(id)) {
			markAsVisited(id);
			for (KEY m : adjacencyList.get(id)) {
				visit(m);
			}

			result.offer(verticies.get(id));
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("adjacencyList:");
		sb.append("\n");
		for (KEY v : adjacencyList.keySet()) {
			String x = v + " -> [";
			for (KEY c : adjacencyList.get(v)) {
				x += c + ", ";
			}
			x += "]";
			sb.append(x);
			sb.append("\n");
		}

		sb.append("\n");
		sb.append("\n");

		sb.append("invertedAdjacencyList:");
		sb.append("\n");
		for (KEY v : invertedAdjacencyList.keySet()) {
			String x = v + " <- [";
			for (KEY c : invertedAdjacencyList.get(v)) {
				x += c + ", ";
			}
			x += "]";
			sb.append(x);
			sb.append("\n");
		}
		sb.append("\n");
		sb.append("Sorted:\n");
		for (T head : result) {
			sb.append(head.getVertexId() + ", ");
		}
		sb.append("\n");

		return sb.toString();
	}
}