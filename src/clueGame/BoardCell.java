/*CSCI306: Clue
 * BoardCell Class
 * Authors: Meg Graf and JP McGroarty
 * 
 */
package clueGame;

public class BoardCell {
	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", column=" + column + "]";
	}

	private int row;
	private int column;
	private char initial;
	
	private DoorDirection doorDir;
	
	
	public BoardCell() {
		this.row = 0;
		this.column = 0;
		this.doorDir = DoorDirection.NONE;
	}
	
	public DoorDirection getDoorDir() {
		return doorDir;
	}

	public void setDoorDir(DoorDirection doorDir) {
		this.doorDir = doorDir;
	}

	public void setInitial(char initial) {
		this.initial = initial;
	}

	public BoardCell(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}

	public BoardCell getCell(int row, int column) {
		BoardCell cell = new BoardCell(row, column);
		return cell;
	}

	public boolean isDoorway() {
		// TODO Auto-generated method stub
		return false;
	}

	public DoorDirection getDoorDirection() {
		return doorDir;
	}
	
	public char getInitial() {
		return this.initial;
	}
	
	
}