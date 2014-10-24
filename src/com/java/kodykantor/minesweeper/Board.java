package com.java.kodykantor.minesweeper;

import java.util.Random;

public class Board {
	private int[][] board;
	private int dimension;
	private int mines;
	private static final int MINE = 100;
	
	public Board(int dimension, int mines) {
		if(dimension < 4 || dimension > 10) {
			throw new IllegalArgumentException("We can't make a board like that " + dimension);
		}
		if(mines < 0 || mines > dimension*dimension) {
			throw new IllegalArgumentException("Invalid mine count " + mines);
		}
		this.mines = mines;
		this.dimension = dimension;
		this.createBoard();
	}
	
	private void createBoard() {
		this.board = new int[this.dimension][this.dimension];
		this.addMines();
	}
	
	private void addMines() {
		int minecount = 0;
		Random rand = new Random();
		int column, row;
		while (minecount < this.mines) {
			do {
				column = rand.nextInt(this.dimension);
				row = rand.nextInt(this.dimension);	
			} while (this.board[row][column] == MINE ); //if we pick a spot that already has a mine, put it somewhere else
			this.board[row][column] = MINE;
			minecount++;
			this.addDanger(row, column);
		}
	}
	
	private void addDanger(int row, int column) {
		// add above (board[row-1][column])
		// add below (board[row+1][column])
		// add left (board[row][column-1])
		// add right (board[row][column+1])
		if(row - 1 >= 0) {
			this.board[row-1][column]++;
			if(column - 1 >= 0) {
				this.board[row-1][column-1]++;
			}
			if(column + 1 < this.dimension) {
				this.board[row-1][column+1]++;
			}
		}
		if(row + 1 < this.dimension) {
			this.board[row+1][column]++;
			if(column - 1 >= 0) {
				this.board[row+1][column-1]++;
			}
			if(column + 1 < this.dimension) {
				this.board[row+1][column+1]++;
			}
		}
		if(column - 1 >= 0) {
			this.board[row][column-1]++;
		}
		if(column + 1 < this.dimension) {
			this.board[row][column+1]++;
		}
		
	}
	
	public String toString() {
		String res = "";
		for (int[] row : this.board) {
			for (int val : row) {
				res += val + ",\t";
			}
			res += "\n";
		}
		
		return res;
	}
}