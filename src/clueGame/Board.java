//John McGroarty
//Meg Graf
package clueGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import clueGame.BoardCell;

public class Board {
	public static final int MAX_BOARD_SIZE = 40;
	
	private int numRows;
	private int numColumns;
	private Map<BoardCell, Set<BoardCell>> adjMatrix;	
	private Map<Character, String> legend;
	private Set<BoardCell> visited;	
	private Set<BoardCell> targets;
	private BoardCell[][] board;
		
	private String boardConfigFile;
	private String roomConfigFile;
	
	// variable used for singleton pattern
	private static Board theInstance = new Board();
	// constructor is private to ensure only one can be created	
	
	private Board() {
		super();
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		legend = new HashMap<Character, String>();
		board = new BoardCell[numRows][numColumns];
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
	}
	
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}
	

	public void setConfigFiles(String boardConfigFile, String roomConfigFile) {
		this.boardConfigFile = boardConfigFile;
		this.roomConfigFile = roomConfigFile;
	}

	public String getBoardConfigFile() {
		return boardConfigFile;
	}

	public String getRoomConfigFile() {
		return roomConfigFile;
	}
	
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
	
	public BoardCell getCellAt(int row, int column) {
		return board[row][column];
	}

	public void initialize() {
		try {
			loadRoomConfig();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BadConfigException b) {
			b.printStackTrace();
		}
		try {
			loadBoardConfig();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BadConfigException b) {
			b.printStackTrace();
		}		

	}

	public void loadRoomConfig() throws FileNotFoundException, BadConfigException {
		String line= "";
		char symbol;
		String[] list;		
		Scanner scan = new Scanner(new File(roomConfigFile));
		
		while(scan.hasNext()) {
			line = scan.nextLine();
			symbol = line.charAt(0);
			list = line.split(", ");
			
			if(!list[2].equals("Card") && !list[2].equals("Other")) {
				break;
			}
			
			legend.put(symbol, list[1]);
		}
		
		scan.close();
	}
	

	public void loadBoardConfig() throws FileNotFoundException, BadConfigException {
		calcDimensions();
				
		this.theInstance = new Board();
		
		board = new BoardCell[numRows][numColumns];
		
		Scanner scan = new Scanner(new File(boardConfigFile));
		String line;
		String[] list = scan.nextLine().split(", ");
	
		if (list.length != numColumns) {
			throw new BadConfigException("Inconsistent number of columns");
		}
		
		while(scan.hasNext()) {
			line = scan.nextLine();
			list = line.split(",");
			
			if ((list[0].isEmpty()) || list[0].length() == 0) {
				continue;
			}
			
			int row = Integer.valueOf(list[0]);
			
			for(int i = 1; i < list.length + 1; i++) {
				board[row][i] = new BoardCell(row, i);
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
						break;
					}
				}
				else if (list[i].length() == 1){
					board[row][i].setDoorDir(DoorDirection.NONE);
				}

				board[row][i].setInitial(list[i].charAt(0));			
			}
		}	
		calcAdjacencies();
		scan.close();
	}
	
	private void calcDimensions() throws FileNotFoundException{
		numColumns = 0;
		numRows = 0;
		Scanner scan = new Scanner (new File(boardConfigFile));
		String[] list = scan.nextLine().split(", ");
		numColumns = list.length;
		numRows++;
		while(scan.hasNextLine()) {
			scan.nextLine();
			numRows++;
		}
		scan.close();
	}

	public void calcAdjacencies(){												//i -> rows
		for (int i = 0; i < numRows; i++) {										//iterate thru grid instead
			for (int j = 0; j < numColumns; j++) {
				//reinitialized for each cell
				//System.out.println(board.toString());
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
	}
	


	public Map<Character, String> getLegend() {
		return legend;
	}

	public void calcTargets(BoardCell cell, int pathLength) {
		BoardCell thisCell = cell;
		visited.clear();
		targets.clear();
		findAllTargets(thisCell, pathLength);
	}
	
	public void calcTargets(int x, int y, int pathLength) {
		BoardCell thisCell = board[y][x];
		visited.clear();
		targets.clear();
		findAllTargets(thisCell, pathLength);
	}
	
	public Set<BoardCell> getAdjList(int x, int y) {
		return adjMatrix.get(board[y][x]);						//get the matching list from the Map directly
	}
	
	public Set<BoardCell> getTargets() {			
		return targets;
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
				calcTargets(cell, pathLength - 1);
				
			}

			visited.remove(cell);
		}		
	}


	public static void main(String[] args) throws FileNotFoundException, BadConfigException {
		Board board = getInstance();
		board.setConfigFiles("src/ExcelBoardGame.csv", "src/ClueRooms.txt");
		board.loadRoomConfig();
		board.loadBoardConfig();
		System.out.println(board.getLegend().size());
	}
}
