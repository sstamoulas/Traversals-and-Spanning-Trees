package graphs;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.Test;

import java.util.*;

public class MyTests {
	
	@Test
	public void mazeSmallTest() throws IOException {
		Maze m = new Maze("///Users/StamatiosStamoulas/Documents/EclipseWorkspace/Algorithms/resources/graph_files/mazeSmall.txt");
		List<Maze.Move> moves = m.solveMaze();
		assertEquals(2, moves.size());
		assertEquals(Maze.Move.DOWN, moves.get(0));
		assertEquals(Maze.Move.RIGHT, moves.get(1));
	}
	
	@Test
	public void mazeMediumTest() throws IOException {
		Maze m = new Maze("///Users/StamatiosStamoulas/Documents/EclipseWorkspace/Algorithms/resources/graph_files/mazeMedium.txt");
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

}
