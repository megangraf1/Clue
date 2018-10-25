//John McGroarty
//Meg Graf


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
		this.initial = 'V';
		this.doorDir = DoorDirection.NONE;
	}
	
	public BoardCell(int row, int col) {
		this.row = row;
		this.column = col;
		this.initial = 'V';
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

	public int getRow() {
		return row;
	}	
	public int getColumn() {
		return column;
	}


	public boolean isDoorway() {
		if (this.doorDir != DoorDirection.NONE) {
			return true;
		}
		return false;
	}

	public DoorDirection getDoorDirection() {
		return doorDir;
	}
	
	public char getInitial() {
		return this.initial;
	}
	
	
}
