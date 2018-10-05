package experiment;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> visited;
	private HashSet<BoardCell> targets;
	private BoardCell[][] grid;	
	private Set<BoardCell> allCells;
	
	int numRows = 4;		//These are up here so they can be accessed by the calcAdjacencies
	int numColumns = 4;
	
	
	public IntBoard(int numRows, int numColumns) {
	
		
		super();
		// need to allocate space for all the sets used in the tests
		//use a size of zero so no exception errors

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				BoardCell myCell = new BoardCell(i,j);
				allCells.add(myCell);
				adjMatrix.put(myCell, calcAdjacencies(myCell));
			}
		}
			
	}
	public Set<BoardCell> calcAdjacencies(BoardCell myCell){
		Set<BoardCell> adjList  = new HashSet<BoardCell>();		//I moved this to inside the method because it will be re-inititated for each cell
		int myRow = myCell.row;
		int myColumn = myCell.column;
		if (myRow > 0) {
			BoardCell up = new BoardCell(myRow - 1, myColumn);
			adjList.add(up);
		}
		if (myRow < numRows - 1) {								// - 1 since we start counting from 0
			BoardCell down = new BoardCell(myRow + 1, myColumn);
			adjList.add(down);
		}
		if (myColumn > 0) {
			BoardCell left = new BoardCell(myRow, myColumn - 1);
			adjList.add(left);
		}
		if (myColumn < numColumns - 1) {
			BoardCell right = new BoardCell(myRow, myColumn + 1);
			adjList.add(right);
		}		
		return adjList;
	}
	public HashSet calcTargets(BoardCell startCell, int pathLength){
		return targets;
	}
	
	public Set<BoardCell> getAdjList(BoardCell cell) {
		return adjMatrix.get(cell);								//get the matching list from the Map directly
	}
	public HashSet<BoardCell> getTargets() {
		return targets;
	}
	public BoardCell getCell(int row, int column) {
		BoardCell cell = new BoardCell(row,column);
		//assigns a new cell to have a certain row and column
		//to be then used as the parameter in the targets set
		//to test for containment
		
		return cell;
	}
	
}