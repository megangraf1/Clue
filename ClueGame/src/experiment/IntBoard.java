package experiment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> visited;
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
		Set<BoardCell> adjList  = new HashSet<BoardCell>();									//This is inside the method because it will be re-inititated for each cell
		int myRow = myCell.row;
		int myColumn = myCell.column;
		
		if (myRow > 0) adjList.add(new BoardCell(myRow - 1, myColumn));						//adjacent above
		if (myRow < numRows - 1) adjList.add(new BoardCell(myRow + 1, myColumn)); 			//below
		if (myColumn > 0) adjList.add(new BoardCell(myRow, myColumn - 1));					//left
		if (myColumn < numColumns - 1) adjList.add(new BoardCell(myRow, myColumn + 1));		//right	
		
		return adjList;
	}
	
	public HashSet calcTargets(BoardCell startCell, int pathLength){
		//calculate adjacencies as many times as pathlength, for each adjacent cell
		HashSet<BoardCell> targets = new HashSet<BoardCell>();
		
		/*int i = 1;												//check if this should start at 0 or 1
		while (i <= pathLength) {
			Set <BoardCell> adjacents = adjMatrix.get(startCell);	//get the adjacents for where you start
			Iterator<BoardCell> iter = adjacents.iterator();
			while (iter.hasNext()) {
				BoardCell keyCell = iter;
				
				
			} 
			
			//don't add if adjacents.contains()
			
			i++;
		} */
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