package experiment;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> visited;
	private HashSet<BoardCell> targets;
	private BoardCell[][] grid;
	private Set<BoardCell> adjList;
	private Set<BoardCell> allCells;
	
	
	public IntBoard(int numRows, int numColumns) {
	
		
		super();
		// need to allocate space for all the sets used in the tests
		//use a size of zero so no exception errors

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				BoardCell myCell = new BoardCell(i,j);
				allCells.put(myCell);
				myCell.calcAdjacencies();
			}
		}
			
		calcAdjacencies();
	}
	public Set calcAdjacencies(){
		
		return adjList;
	}
	public HashSet calcTargets(BoardCell startCell, int pathLength){
		return targets;
	}
	
	public Set<BoardCell> getAdjList(BoardCell cell) {
		return adjList;
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