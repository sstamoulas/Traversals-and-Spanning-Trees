package graphs;

import java.io.*;
import java.util.*;

public class Maze {
	
	Graph g; //We will store the maze internally as a graph
	int startVertex; //one of the vertices in the graph will be the start of the maze
	int endVertex; //another will be the end of the maze
	boolean[] visited;
	List<Move> moves;
	
	/**
	 * We will store an nxn maze in a textfile, and read it in.
	 * 
	 * A maze is simply represented as a textfile with 4 numbers: 0, 1, 2, 3
	 * 
	 * 0s represent walls- this is not a valid part of the maze
	 * 1s represent valid parts of the maze (i.e. blocks you can move to
	 * 2 represents the starting point of the maze
	 * 3 represents the end point of the maze.
	 * 
	 * Our constructor will create the 2d array of integers from the file for you
	 * 
	 */
	public Maze(String filename) throws IOException{
		
		//create the 2d grid from the maze textfile
		int[][] grid = createGrid(filename);
		
		//identify start and end vertices
		int n = grid.length;
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				if (grid[row][col] == 2) {
					startVertex = row*n + col;
				}
				else if (grid[row][col] == 3) {
					endVertex = row*n + col;
				}
			}
		}
		
		//TODO
		//determine how to represent the graph and create it
		//initialize startVertex and endVertex
		g = new Graph(n*n);
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				if((col + 1) < n && grid[row][col] != 0 && grid[row][col + 1] != 0) {
					g.addEdge(row*n + col, row*n + (col + 1));
				}
				if((row + 1) < n && grid[row][col] != 0 && grid[row + 1][col] != 0) {
					g.addEdge(row*n + col, (row + 1)*n + col);
				}		
			}
		}
	}
	
	/**
	 * 
	 * This algorithm should solve the maze output a list of "moves", beginning at the start vertex,
	 * that can be taken to arrive at the end vertex.  You should solve the maze on the graph,
	 * using some sort of graph traversal.
	 * 
	 * More information on figuring out what "move" to output at each step can be found in the writeup!
	 * 
	 * @param g - the graph to traverse
	 * @param startVertex - the starting vertex
	 * @param endVertex - we will stop the traversal and output the list of moves when we hit this vertex
	 * 
	 */
	public List<Move> solveMaze() {
		//TODO
		visited = new boolean[g.size()];
		moves = new LinkedList<Move>();
		dfs(startVertex, endVertex);
		return moves;
	}
	
	private boolean dfs(int start, int elementToFind) {
		if (start == elementToFind) {
			return true;
		} else {
			visited[start] = true;
			for (Integer neighbor : g.neighbors(start)) {
				if (!visited[neighbor] && dfs(neighbor, elementToFind)) {
					int n = (int)Math.sqrt(g.size());
					int startCol = start % n;
					int startRow = (start - startCol) / n;
					int neighborCol = neighbor % n;
					int neighborRow = (neighbor - neighborCol) / n;
					
					if(startRow == neighborRow) {
						if(startCol - neighborCol > 0) {
							moves.add(0, Move.LEFT);
						}
						else if(startCol - neighborCol < 0) {
							moves.add(0, Move.RIGHT);
						}
					}
					else {
						if(startRow - neighborRow > 0) {
							moves.add(0, Move.UP);
						}
						else if(startRow - neighborRow < 0) {
							moves.add(0, Move.DOWN);
						}
					}
		            return true;
				}
			}
			return false;
		}
	}
	
	/**
	 * Move is an enum type- when navigating a maze, you can either move
	 * UP, DOWN, LEFT, or RIGHT
	 */
	public enum Move {
		UP, DOWN, LEFT, RIGHT
	}
	
	/**
	 * Helper function for creating a 2d grid to represent the maze, given a file name
	 * 
	 * @param filename - the name of the file to be read from, containing the maze data
	 */
	public static int[][] createGrid(String filename) throws IOException {
		//create the 2d array from the maze textfile
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line = br.readLine();
		int n = line.length(); //the grid will always be an nxn square
		int[][] grid = new int[n][n];
		int row = 0;
		while (line != null) {
			int col = 0;
			for (char c : line.toCharArray()) {
				int val = Character.getNumericValue(c);
				grid[row][col] = val;
				col++;
			}
			line = br.readLine();
			row++;
		}
		br.close();
		return grid;
	}
	
}
