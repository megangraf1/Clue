package clueGame;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import clueGame.BoardCell;

public class Board {

	int numRows;
	int numColumns;
	public static final int MAX_BOARD_SIZE = 40;
	private BoardCell[][] board = new BoardCell[numRows][numColumns];
	private Map<Character, String> legend = new HashMap<Character, String>();
	private Map<BoardCell, Set<BoardCell>> adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
	private Set<BoardCell> targets = new HashSet<BoardCell>();
	private String boardConfigFile = "";
	private String roomConfigFile = "";
	

	// variable used for singleton pattern
	private static Board theInstance = new Board();
	// constructor is private to ensure only one can be created
	private Board() {}
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}

	public void setConfigFiles(String boardConfig, String roomConfig) {
		
	}
	
	public Map<Character, String> getLegend() {
		Map<Character, String> legend = new HashMap<Character, String>();
		return legend;
	}
	
	
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
	
	public void initialize() {
		
	}
	
	public BoardCell getCellAt(int row, int column) {
		return board[row][column];
	}
	


	public void loadRoomConfig() {
		
	}
	
	public void loadBoardConfig() {
		
	}
	
	public void calcAdjacencies() {
		
	}
	
	public void calcTargets(BoardCell cell, int pathLength) {
		
	}

}
