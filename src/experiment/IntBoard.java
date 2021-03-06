//John McGroarty
//Meg Graf

package experiment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> visited = new HashSet<BoardCell>();
	private BoardCell[][] grid;	
	private Set<BoardCell> targets = new HashSet<BoardCell>();

	private int numRows;	//These are up here so they can be accessed by the calcAdjacencies
	private int numColumns;
	
	public IntBoard(int numRows, int numColumns) {
		
		super();
		// need to allocate space for all the sets used in the tests
		//use a size of zero so no exception errors
		this.numRows = numRows;
		this.numColumns = numColumns;
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		grid = new BoardCell[numRows][numColumns];

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {

				grid[i][j] = new BoardCell(i,j);
			}
		}
		calcAdjacencies();
	}

	public Map<BoardCell, Set<BoardCell>> calcAdjacencies(){					//i -> rows
		for (int i = 0; i < numRows; i++) {										//iterate thru grid instead
			for (int j = 0; j < numColumns; j++) {
																				//reinitialized for each cell
				int myRow = i;	
				int myColumn = j;
				Set<BoardCell> returnSet = new HashSet<BoardCell>();

				if (myRow - 1 >= 0) {
					returnSet.add(grid[myRow - 1][myColumn]);
				}
				if (myRow + 1 < numRows ) {
					returnSet.add(grid[myRow + 1][myColumn]);
				}
				if (myColumn - 1 >= 0) {
					returnSet.add(grid[myRow][myColumn - 1]);
				}
				if (myColumn + 1 < numColumns) {
					returnSet.add(grid[myRow][myColumn + 1]);
				}			

				adjMatrix.put(grid[i][j], returnSet);
				
				
			}
			
		}		
		return adjMatrix;
	}

	
	public void findAllTargets(BoardCell thisCell, int pathLength){
			
		visited.add(thisCell);
		for (BoardCell cell : adjMatrix.get(thisCell)) {
			if (visited.contains(cell)) {
				continue;
			}
			
			visited.add(cell);
			if (pathLength == 1) {
				targets.add(cell);
			}
			else {
				findAllTargets(cell, pathLength - 1);
			}
			
			visited.remove(cell);
		}		
		}
	
	public void calcTargets(BoardCell thisCell, int pathLength) {
		visited.clear();
		targets.clear();
		visited.add(thisCell);
		findAllTargets(thisCell, pathLength);
	}
	public Set<BoardCell> getAdjList(BoardCell cell) {
		return adjMatrix.get(cell);								//get the matching list from the Map directly
	}
	
	public Set<BoardCell> getTargets(BoardCell startCell, int pathLength) {					 
		calcTargets(startCell, pathLength);
		return targets;
	}
	
	public BoardCell getCell(int row, int column) {

		return grid[row][column];
	}
	

	public static void main(String []args) {
		IntBoard myBoard = new IntBoard(4,4);
		BoardCell cell = myBoard.getCell(1, 1);
		Set<BoardCell> targets = myBoard.getTargets(cell, 3);
		for (BoardCell t : targets) {
			System.out.println(t.toString());
		}
		
	}

}