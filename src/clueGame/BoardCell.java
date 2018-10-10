//John McGroarty
//Meg Graf

package clueGame;

public class BoardCell {
	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", column=" + column + "]";
	}

	int row;
	int column;
	char initial;
	
	public BoardCell() {
		this.row = -1;
		this.column = -1;
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

	public Object getDoorDirection() {
		return null;
	}
	
	public char getInitial() {
		return this.initial;
	}
	
	
}
