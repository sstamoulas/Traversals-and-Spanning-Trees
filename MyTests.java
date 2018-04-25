package graphs;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.Test;

import java.util.*;

public class MyTests {
	
	@Test
	public void mazeSmallTest() throws IOException {
		Maze m = new Maze("resources/graph_files/mazeSmall.txt");
		List<Maze.Move> moves = m.solveMaze();
		assertEquals(2, moves.size());
		assertEquals(Maze.Move.DOWN, moves.get(0));
		assertEquals(Maze.Move.RIGHT, moves.get(1));
	}
	
	@Test
	public void mazeMediumTest() throws IOException {
		Maze m = new Maze("resources/graph_files/mazeMedium.txt");
		List<Maze.Move> moves = m.solveMaze();
		assertEquals(11, moves.size());
		assertEquals(Maze.Move.LEFT, moves.get(0));
		assertEquals(Maze.Move.UP, moves.get(1));
		assertEquals(Maze.Move.UP, moves.get(2));
		assertEquals(Maze.Move.RIGHT, moves.get(3));
		assertEquals(Maze.Move.RIGHT, moves.get(4));
		assertEquals(Maze.Move.DOWN, moves.get(5));
		assertEquals(Maze.Move.RIGHT, moves.get(6));
		assertEquals(Maze.Move.RIGHT, moves.get(7));
		assertEquals(Maze.Move.UP, moves.get(8));
		assertEquals(Maze.Move.UP, moves.get(9));
		assertEquals(Maze.Move.UP, moves.get(10));
	}
	
	@Test
	public void testSmall() throws IOException {
		Graph g = new Graph(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 3);
		assertEquals(2, g.numShortestPaths(0, 3));
		assertEquals(2, g.numShortestPaths(1, 2));
		assertEquals(1, g.numShortestPaths(0, 1));
		assertEquals(1, g.numShortestPaths(2, 3));
	}

	@Test
	public void testCompleteGraph() throws IOException {
		Graph g = new Graph(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 3);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 3);
		assertEquals(1, g.numShortestPaths(0, 1));
		assertEquals(1, g.numShortestPaths(0, 2));
		assertEquals(1, g.numShortestPaths(0, 3));
		assertEquals(1, g.numShortestPaths(1, 2));
		assertEquals(1, g.numShortestPaths(1, 3));
		assertEquals(1, g.numShortestPaths(2, 3));
	}
	
	@Test
	public void testLargeGridGraph() throws IOException {
		Graph g = createGraph("resources/graph_files/largeGridGraph.txt");
		assertEquals(1, g.numShortestPaths(0, 1));
		assertEquals(2, g.numShortestPaths(0, 11));
		assertEquals(1, g.numShortestPaths(0, 9));
		assertEquals(6, g.numShortestPaths(0, 22));
		assertEquals(1, g.numShortestPaths(9, 99));
		assertEquals(1, g.numShortestPaths(90, 99));
	}
	
	
	@Test
	public void testMaxPaths1LevelGraph() throws IOException {
		Graph g = createGraph("resources/graph_files/maxPaths1LevelGraph.txt");
		assertEquals(1, g.numShortestPaths(0, 1));
		assertEquals(1, g.numShortestPaths(0, 2));
		assertEquals(1, g.numShortestPaths(0, 3));
		assertEquals(2, g.numShortestPaths(1, 2));
		assertEquals(2, g.numShortestPaths(1, 3));
		assertEquals(2, g.numShortestPaths(2, 3));
	}
	
	@Test
	public void testMaxPaths5LevelGraph() throws IOException {
		Graph g = createGraph("resources/graph_files/maxPaths5LevelGraph.txt");
		assertEquals(1, g.numShortestPaths(0, 0));
		assertEquals(1, g.numShortestPaths(0, 2));
		assertEquals(10, g.numShortestPaths(0, 11));
		assertEquals(11, g.numShortestPaths(1, 2));
		assertEquals(11, g.numShortestPaths(1, 3));
		assertEquals(100000, g.numShortestPaths(0, 51));
	}
	
	@Test
	public void testTwoCliquesTwoEdges() throws IOException {
		Graph g = createGraph("resources/graph_files/twoCliquesTwoEdges.txt");
		assertEquals(1, g.numShortestPaths(0, 1));
		assertEquals(1, g.numShortestPaths(0, 2));
		assertEquals(1, g.numShortestPaths(0, 3));
		assertEquals(1, g.numShortestPaths(1, 2));
		assertEquals(1, g.numShortestPaths(1, 3));
		assertEquals(1, g.numShortestPaths(2, 3));
	}
	
	/**
	 * Helper function for creating a 2d grid to represent the maze, given a file name
	 * 
	 * @param filename - the name of the file to be read from, containing the maze data
	 */
	public static Graph createGraph(String filename) throws IOException {
		//create the 2d array from the maze textfile
		BufferedReader br = new BufferedReader(new FileReader(filename));
		int n = Integer.parseInt(br.readLine()); //the grid will always be an nxn square
		Graph g = new Graph(n);
		String line = br.readLine();
		while (line != null) {
			String nums[] = line.split(",");
			int u = Integer.parseInt(nums[0]);
			int v = Integer.parseInt(nums[1]);
			
			g.addEdge(u, v);
			line = br.readLine();
		}
		br.close();
		return g;
	}
}
