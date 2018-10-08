package experiment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	public static Map<BoardCell, Set<BoardCell>> adjMatrix;
	public Set<BoardCell> visited;
	public BoardCell[][] grid;	
	public Set<BoardCell> targets = new HashSet<BoardCell>();

	public static int numRows = 4;		//These are up here so they can be accessed by the calcAdjacencies
	public static int numColumns = 4;


	public IntBoard(int numRows, int numColumns) {
		super();
		// need to allocate space for all the sets used in the tests
		//use a size of zero so no exception errors
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		grid = new BoardCell[numRows + 1][numColumns + 1];

		for (int i = 0; i <= numRows; i++) {
			for (int j = 0; j <= numColumns; j++) {
				BoardCell myCell = new BoardCell(i,j);
				grid[i][j] = myCell;
			}
		}
		calcAdjacencies();
	}

	public void calcAdjacencies(){					//i -> rows
		for (int i = 0; i < numRows; i++) {		//iterate thru grid instead
			for (int j = 0; j < numColumns; j++) {
				Set<BoardCell> returnSet = new HashSet<BoardCell>();	//reinitialized for each cell
				int myRow = i;
				int myColumn = j;
				
				if (myRow - 1 >= 0) {
					returnSet.add(grid[myRow - 1][myColumn]);
				}
				if (myRow + 1 < numRows - 1) {
					returnSet.add(grid[myRow + 1][myColumn]);
				}
				if (myColumn - 1 >= 0) {
					returnSet.add(grid[myRow][myColumn - 1]);
				}
				if (myColumn + 1 < numColumns - 1) {
					returnSet.add(grid[myRow][myColumn + 1]);
				}			
				adjMatrix.put(grid[i][j], returnSet);
			}
		}		
	}

	
	public void calcTargets(BoardCell thisCell, int pathLength){
		Set<BoardCell> visited = new HashSet<BoardCell>();
		Set<BoardCell> adjacentCells;
		targets.clear();
		visited.add(thisCell);
		adjacentCells = adjMatrix.get(thisCell);
		for (BoardCell adjCell : adjacentCells) {
			if (visited.contains(adjCell)) {					//move to the next adjCell if you've already visited this one
				continue;
			}
			if (pathLength == 1) {
				targets.add(adjCell);
			}
			else {
				calcTargets(adjCell, pathLength-1);
			}
		}		
		
		for (BoardCell t : targets) {							//remove internal visited cells
			if (visited.contains(t)) {
				targets.remove(t);
			}
		}
	}
	
	public Set<BoardCell> getAdjList(BoardCell cell) {
		return adjMatrix.get(cell);								//get the matching list from the Map directly
	}
	
	public Set<BoardCell> getTargets(BoardCell startCell, int pathLength) {					 
		calcTargets(startCell, pathLength);
		return targets;
	}
	
	public BoardCell getCell(int row, int column) {
		BoardCell cell = new BoardCell(row,column);
		//assigns a new cell to have a certain row and column
		//to be then used as the parameter in the targets set
		//to test for containment

		return cell;
	}

	public static void main(String []args) {
		IntBoard myBoard = new IntBoard(numRows,numColumns);
	}

}