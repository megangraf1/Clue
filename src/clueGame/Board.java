//John McGroarty
//Meg Graf
package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import clueGame.BoardCell;

public class Board {

	int numRows ;
	int numColumns;
	public static final int MAX_BOARD_SIZE = 40;
	private BoardCell[][] board;
	private Map<Character, String> legend = new HashMap<Character, String>();
	private Map<BoardCell, Set<BoardCell>> adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
	private Set<BoardCell> targets = new HashSet<BoardCell>();
	private String boardConfigFile = "";
	private String roomConfigFile = "";
	
	private Set<BoardCell> visited = new HashSet<BoardCell>();
	// variable used for singleton pattern
	private static Board theInstance = new Board(25, 25);
	// constructor is private to ensure only one can be created
	private Board(int numRows, int numColumns) {
		super();
		// need to allocate space for all the sets used in the tests
		//use a size of zero so no exception errors
		this.numRows = numRows;
		this.numColumns = numColumns;
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		board = new BoardCell[numRows][numColumns];

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {

				board[i][j] = new BoardCell(i,j);
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
					returnSet.add(board[myRow - 1][myColumn]);
				}
				if (myRow + 1 < numRows ) {
					returnSet.add(board[myRow + 1][myColumn]);
				}
				if (myColumn - 1 >= 0) {
					returnSet.add(board[myRow][myColumn - 1]);
				}
				if (myColumn + 1 < numColumns) {
					returnSet.add(board[myRow][myColumn + 1]);
				}			

				adjMatrix.put(board[i][j], returnSet);


			}

		}		
		return adjMatrix;
	}
	

	public String getBoardConfigFile() {
		return boardConfigFile;
	}


	public String getRoomConfigFile() {
		return roomConfigFile;
	}


	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}

	public void setConfigFiles(String boardConfigFile, String roomConfigFile) {
		this.boardConfigFile = boardConfigFile;
		this.roomConfigFile = roomConfigFile;
	}

	public Map<Character, String> getLegend() {
		return legend;
	}

	public void calcTargets(BoardCell thisCell, int pathLength) {
		visited.clear();
		targets.clear();
		visited.add(thisCell);
		findAllTargets(thisCell, pathLength);
	}
	public Set<BoardCell> getAdjList(int x, int y) {
		return adjMatrix.get(board[y][x]);						//get the matching list from the Map directly
	}
	public Set<BoardCell> getTargets(BoardCell startCell, int pathLength) {					 
		calcTargets(startCell, pathLength);
		return targets;
	}
	public BoardCell getCell(int row, int column) {

		return board[row][column];
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
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}

	public void initialize() {
		board = new BoardCell[numRows][numColumns];
		try {
			loadRoomConfig();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			loadBoardConfig();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public BoardCell getCellAt(int row, int column) {
		return board[row][column];
	}



	public void loadRoomConfig() throws FileNotFoundException {
		String line= "";
		char symbol;
		String room = "";
		String[] list;

		FileReader file = new FileReader(this.roomConfigFile);
		Scanner scan = new Scanner(file);
		while(scan.hasNext()) {
			line = scan.nextLine();
			symbol = line.charAt(0);
			list = line.split(", ");

			legend.put(symbol, list[1]);
		}
		scan.close();
	}

	public void loadBoardConfig() throws FileNotFoundException {
		int numRows = 0;
		int numCols = 0;
		FileReader file = new FileReader(this.boardConfigFile);
		Scanner scan = new Scanner(file);
		String line = "";
		String[] list; 
		int row = 0;
		while(scan.hasNext()) {
			line = scan.nextLine();
			list = line.split(",");
			numRows++;
			numCols = list.length;
		}
		this.theInstance = new Board(numRows, numCols);
		this.numColumns = numCols;
		this.numRows = numRows;
		file = new FileReader(this.boardConfigFile);
		scan = new Scanner(file);
		while(scan.hasNext()) {
			line = scan.nextLine();
			list = line.split(",");
			
			for(int i = 0; i < list.length; i++) {
				if(list[i].length()>1) {							//it'll only enter this loop for doors
					System.out.println(board[row][i].toString());

					switch (list[i].charAt(1)) {

					case 'U':
						board[row][i].setDoorDir(DoorDirection.UP);
						break;
					case 'D':
						board[row][i].setDoorDir(DoorDirection.DOWN);
						break;
					case 'L':
						board[row][i].setDoorDir(DoorDirection.LEFT);
						break;
					case 'R':
						board[row][i].setDoorDir(DoorDirection.RIGHT);
						break;
					default: 
						board[row][i].setDoorDir(DoorDirection.NONE);
						System.out.println("hey");
						break;
					}
				}
				else if (list[i].length() == 1){
					board[row][i].setDoorDir(DoorDirection.NONE);
				}

				board[row][i].setInitial(list[i].charAt(0));
			
			}
			row++;
		}	
	}

	public static void main(String[] args) throws FileNotFoundException {
		Board board = getInstance();
		board.setConfigFiles("CTestFiles/OwnBoard", "CTestFiles/Rooms");
		board.loadRoomConfig();
		board.loadBoardConfig();
		System.out.println(board.getLegend().size());
	}
}
