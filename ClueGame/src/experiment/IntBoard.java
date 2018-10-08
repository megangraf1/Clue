package experiment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	public Map<BoardCell, Set<BoardCell>> adjMatrix;
	public Set<BoardCell> visited;
	public BoardCell[][] grid;	
	public Set<BoardCell> allCells;

	int numRows;		//These are up here so they can be accessed by the calcAdjacencies
	int numColumns;


	public IntBoard(int numRows, int numColumns) {
		super();
		// need to allocate space for all the sets used in the tests
		//use a size of zero so no exception errors
		Set<BoardCell> allCells = new HashSet<BoardCell>();
		BoardCell[][] grid = new BoardCell[numRows + 1][numColumns + 1];
		Map<BoardCell, Set<BoardCell>> adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		for (int i = 0; i <= numRows; i++) {
			for (int j = 0; j <= numColumns; j++) {
				BoardCell myCell = new BoardCell(i,j);
				allCells.add(myCell);
				grid[i][j] = myCell;
			}
		}
		calcAdjacencies();
	}

	public Set<BoardCell> calcAdjacencies(){		
		Set<BoardCell> adjList  = new HashSet<BoardCell>();									//This is inside the method because it will be re-initiated for each cell
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				int myRow = i;
				int myColumn = j;

				if (myRow > 0) adjList.add(grid[myRow + 1][myColumn]);							//adjacent Below
				if (myRow < numRows - 1) adjList.add(grid[myRow - 1][myColumn]); 				//above
				if (myColumn > 0) adjList.add(grid[myRow][myColumn - 1]);						//left
				if (myColumn < numColumns - 1) adjList.add(grid[myRow][myColumn + 1]);			//right	
			}
			System.out.println("hit calcadj");
			
			for (BoardCell a : adjList) System.out.println(a.row + " " + a.column);

		}
		return adjList;
	}
	/*
	public Set<BoardCell> calcTargets(BoardCell startCell, int pathLength){
		boolean moreSteps = true;
		int numOfLoops = 0;											// how many times you've gone through
		//calculate adjacencies as many times as pathlength, for each adjacent cell
		HashSet<BoardCell> targets = new HashSet<BoardCell>();
		Set<BoardCell> tempSet = new HashSet<BoardCell>();
		Set<BoardCell> start = calcAdjacencies(startCell);

		while (moreSteps == true) {
			for (BoardCell thisCell : start) {						//go through the initial adjacents
				tempSet = calcAdjacencies(thisCell);				//get the set of adjacents for each of those
				for (BoardCell thatCell : tempSet) {				//go through each of *those* sets
					targets.add(thatCell);							//add that cell to the new targets loop
				}
			} 
			targets.removeAll(start);								//no backtracking allowed
			numOfLoops++;
			start.clear();
			start.addAll(targets);
			if (numOfLoops == pathLength) moreSteps = false;		//break out of the loop
		}
		return start;
	}
	 */
	public Set<BoardCell> getAdjList(BoardCell cell) {
		return adjMatrix.get(cell);								//get the matching list from the Map directly
	}
	/*public Set<BoardCell> getTargets(BoardCell startCell, int pathLength) {					 
		Set<BoardCell> mySet = calcTargets(startCell, pathLength);
		return mySet;
	}*/
	public BoardCell getCell(int row, int column) {
		BoardCell cell = new BoardCell(row,column);
		//assigns a new cell to have a certain row and column
		//to be then used as the parameter in the targets set
		//to test for containment

		return cell;
	}
	//TODO - visited
	public static void main(String[] args) {
		IntBoard myBoard = new IntBoard(3,3);

	}

}