package experiment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	public Map<BoardCell, Set<BoardCell>> adjMatrix;
	public Set<BoardCell> visited;
	public BoardCell[][] grid;	
	public Set<BoardCell> targets = new HashSet<BoardCell>();

	public int numRows;	//These are up here so they can be accessed by the calcAdjacencies
	public int numColumns;


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
//				BoardCell myCell = new BoardCell(i,j);
				grid[i][j] = new BoardCell(i,j);
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
				if (myRow + 1 < numRows ) {
					returnSet.add(grid[myRow + 1][myColumn]);
				}
				if (myColumn - 1 >= 0) {
					returnSet.add(grid[myRow][myColumn - 1]);
				}
				if (myColumn + 1 < numColumns) {
					returnSet.add(grid[myRow][myColumn + 1]);
				}			
//				System.out.println("      " +myRow + "," + myColumn);
//				for(BoardCell b : returnSet) {
					
//					System.out.println(b.row + "," + b.column);
//				}
				adjMatrix.put(grid[i][j], returnSet);
				returnSet.clear();
				
			}
			
		}		
	}

	
	public void calcTargets(BoardCell thisCell, int pathLength){
		Set<BoardCell> visited = new HashSet<BoardCell>();
		Set<BoardCell> adjacentCells = new HashSet<BoardCell>();
		targets.clear();
		visited.add(thisCell);
		adjacentCells = adjMatrix.get(grid[thisCell.row][thisCell.column]);
		for (BoardCell adjCell : adjacentCells) {
			for (BoardCell v : visited) {							//can't compare cells directly
				if ((v.row == adjCell.row) && (v.column == adjCell.column)){
					continue;
				}
			}
			if (pathLength == 1) {
				targets.add(adjCell);
			}
			else {
				calcTargets(adjCell, (pathLength-1));
			}
		}		
		
		for (BoardCell t : targets) {							//remove internal visited cells
			for (BoardCell v : visited) {							//can't compare cells directly
				if ((t.row == v.row) && (t.column == v.column)){
					targets.remove(grid[t.row][t.column]);
				}
			}			
		}
	}
	
	public Set<BoardCell> getAdjList(BoardCell cell) {
//		System.out.println(adjMatrix.get(grid[cell.row][cell.column]));
//		for(BoardCell i : adjMatrix.get(grid[cell.row][cell.column])) {
//			System.out.println(i.row);
//		}
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
		System.out.println(myBoard.getCell(1,1).toString());
		for(BoardCell e: myBoard.getAdjList(cell)) {
			System.out.println(e.toString());
		}
	}

}