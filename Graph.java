package graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * A undirected graph class
 * 
 * Also includes functions for running dfs and bfs
 *
 */
public class Graph {
	
	private int n; //number of vertices
	private LinkedList<Integer>[] adj; //adjacency list
	
	/**
	 * Constructs a graph with n vertices (containing no edges)
	 * 
	 * @param n - the number of vertices in the graph
	 */
	@SuppressWarnings("unchecked")
	public Graph(int n) {
		this.n = n;
		adj = (LinkedList<Integer>[]) new LinkedList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new LinkedList<>();
		}
	}
	
	/**
	 * add an edge between vertices v and w
	 */
	public void addEdge(int v, int w) {
		adj[v].add(w); //add w to v's adjacency list
		adj[w].add(v);
	}
	
	/**
	 * outputs the neighbors of a vertex v
	 */
	public List<Integer> neighbors(int v) {
		return adj[v];
	}
	
	/**
	 * @return the number of vertices in the graph
	 */
	public int size() {
		return n;
	}
	
	/**
	 * returns the number of shortest paths from s to t
	 * 
	 * ex. if the shortest path from s to t is of length 4, and there
	 * are two distinct paths from s to t of length 4, then you should return 2
	 * 
	 * @param s the source vertex
	 * @param t the destination vertex
	 * @return
	 */
	public int numShortestPaths(int s, int t) {
		//TODO
		return bfs(s, t);
	}
	
	public int bfs(int start, int elementToFind) {
		if (start == elementToFind) {
			return 1;
		}

		int[] count = new int[size()];
		int[] val = new int[size()];
		Queue<Integer> toExplore = new LinkedList<Integer>();
		
		count[start] = 1;
		toExplore.add(start);
		while (!toExplore.isEmpty()) {
			int current = toExplore.remove();
			for (int neighbor : neighbors(current)) {
				if (count[neighbor] == 0) {
					toExplore.add(neighbor);
					val[neighbor] = val[current] + 1;
					count[neighbor] = count[current];
				}
				else if(val[neighbor] == val[current] + 1) {
					count[neighbor] = count[neighbor] + count[current];
				}
				else if(val[neighbor] > val[current] + 1) {
					val[neighbor] = val[current] + 1;
					count[neighbor] = count[current];
				}
			}
		}
		
		return count[elementToFind];
	}
}
