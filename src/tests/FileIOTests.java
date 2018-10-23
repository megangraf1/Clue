/*CSCI306: Clue
 * FileIOTest Class
 * Authors: Meg Graf and JP McGroarty
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.Map;


import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;
import clueGame.BadConfigException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;

public class FileIOTests {
	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 25;
	public static final int NUM_COLUMNS = 25;
	public static final int NUM_DOORS = 14;


	private static Board board;

	@BeforeClass
	public static void setUp() throws BadConfigException{
		board = Board.getInstance();
		board.setConfigFiles("ExcelBoardGame.csv", "ClueRooms.txt");
		board.initialize();
	}


	@Test
	public void testRooms(){
		Map<Character, String> legend = board.getLegend();
		assertEquals(LEGEND_SIZE, legend.size());
		assertEquals("Hotel", legend.get('H'));				//first
		assertEquals("Saloon", legend.get('S'));
		assertEquals("Sheriff's Office", legend.get('O'));	//Space in legend
		assertEquals("Stable", legend.get('A'));
		assertEquals("Bank", legend.get('K')); 				//last entry	
	}

	@Test
	public void testBoardDimensions() {
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
	}

	@Test
	public void testDoorDirections() {
		BoardCell room = board.getCellAt(5,1); //Sheriff's office
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.DOWN, room.getDoorDirection());
		room = board.getCellAt(9,5);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.UP, room.getDoorDirection());
		room = board.getCellAt(10,18);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.LEFT, room.getDoorDirection());
		room = board.getCellAt(20,16);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getCellAt(16,0);
		assertTrue(room.isDoorway());		//Should fail, not a door
	}

	@Test 
	public void testNumDoors() {
		int doorCount = 0;
		for (int row=0; row<board.getNumRows(); row++) {			//iterates through the board and counts doors
			for (int col=0; col<board.getNumColumns(); col++) {
				BoardCell cell = board.getCellAt(row, col);
				if (cell.isDoorway()) {
					doorCount++;
				}
			}
		}
		assertEquals(NUM_DOORS, doorCount);
	}


	public void testRoomInitials() {
		// Test first cell in room
		assertEquals('O', board.getCellAt(0, 0).getInitial());
		assertEquals('T', board.getCellAt(0, 6).getInitial());
		assertEquals('A', board.getCellAt(9, 18).getInitial());
		// Test last cell in room
		assertEquals('O', board.getCellAt(5, 4).getInitial());
		assertEquals('H', board.getCellAt(24, 4).getInitial());
		assertEquals('K', board.getCellAt(24, 24).getInitial());
		// Test a walkway
		assertEquals('W', board.getCellAt(17, 13).getInitial());
		// Test the closet
		assertEquals('X', board.getCellAt(12, 12).getInitial());
	}

}

