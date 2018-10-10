package tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import experiment.BoardCell;
import experiment.IntBoard;

public class TestAdjacencies {
	public IntBoard board = new IntBoard(4,4);
	
	
	public TestAdjacencies() {
		super();
	}

//
//	@Before
//	public void beforeAll() {
//		board = new IntBoard(3,3);		
//	}
	
	
	@Test
	public void testAdjacency0_0() 
	{
//		beforeAll();
		BoardCell cell = board.getCell(0,0);
		Set<BoardCell> testList = new HashSet<BoardCell>();
		testList=board.getAdjList(cell);
//		System.out.println("h");
//		for (BoardCell i : testList) {
//			
//			System.out.println(i.getRow());
//		}
		assertTrue(testList.contains(board.getCell(1, 0)));     
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
	}
	public void testAdjacency3_3() 
	{
//		beforeAll();
		BoardCell cell = board.getCell(3,3);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertEquals(2, testList.size());
	}
	
	public void testAdjacency1_3() 
	{
//		beforeAll();
		BoardCell cell = board.getCell(1,3);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(0, 3)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertEquals(3, testList.size());
	}
	
	
	public void testAdjacency3_0() 
	{
//		beforeAll();
		BoardCell cell = board.getCell(3,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2, 0)));
		assertTrue(testList.contains(board.getCell(3, 1)));
		assertEquals(2, testList.size());
	}
	
	public void testAdjacency1_1() 
	{
//		beforeAll();
		BoardCell cell = board.getCell(1,1);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertEquals(4, testList.size());
	}
	
	public void testAdjacency2_2() 
	{
//		beforeAll();
		BoardCell cell = board.getCell(1,1);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertEquals(4, testList.size());
	}
	
	@Test
	public void testTargets0_3(){
//		beforeAll();
		BoardCell cell = board.getCell(0, 3);
		board.findAllTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets(cell, 3);		
//		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(0, 0)));
		assertTrue(targets.contains(board.getCell(1, 1)));
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(3, 3)));
//		
	}

}