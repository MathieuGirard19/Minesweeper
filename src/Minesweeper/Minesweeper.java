package Minesweeper;
import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Minesweeper{
	private JFrame board;
	private String[][] boardArray;
	private int gameRows;
	private int gameCols;
	private int gameBombs;
	private int clicksToWin;
	
	public JFrame getBoard() {
		return board;
	}
	
	public void incrementCounter() {
		clicksToWin--;
		System.out.println(clicksToWin);
		if(clicksToWin == 0) {
			gameWin();
		}
	}
	
	
	public Minesweeper(int rows, int cols, int bombs) {
		gameRows = rows;
		gameCols = cols;
		gameBombs = bombs;
		clicksToWin = (rows * cols) - bombs;
		board = new JFrame();
		createBoardArray();
		for(int i=0; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				if(boardArray[i][j] == "B") {
					new Tiles("B", this);
				}
				else {
					new Tiles(boardArray[i][j], this);
				}
			}
		}
		board.setLayout(new GridLayout(rows, cols));
		board.setSize(500, 500);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setVisible(true);
	}
	
	
	public void createBoardArray(){
		boardArray = new String[gameRows][gameCols];
		//randomly select position of bombs
		Random rand = new Random();
		int bombsPlaced = 0;
		while(bombsPlaced != gameBombs) {
			int row = rand.nextInt(gameRows);
			int col = rand.nextInt(gameCols);
			if(boardArray[row][col] == null) {
				boardArray[row][col] = "B";
				bombsPlaced++;
			}
		}
		
		for(int i=0; i<gameRows; i++) {
			for(int j=0; j<gameCols; j++) {
				int bombCounter = 0;
				if(boardArray[i][j] == "B") {
					continue;
				}
				else {
					int rowUp = i - 1;
					int rowDown = i + 1;
					int colLeft = j - 1;
					int colRight = j + 1;
					//above
					if(rowUp>=0) {
						if(boardArray[rowUp][j] == "B") {
							bombCounter++;
						}
					}
					//below
					if(rowDown<gameRows) {
						if(boardArray[rowDown][j] == "B") {
							bombCounter++;
						}
					}
					//left
					if(colLeft>=0) {
						if(boardArray[i][colLeft] == "B"){
							bombCounter++;
						}
					}
					//right
					if(colRight<gameCols) {
						if(boardArray[i][colRight] == "B") {
							bombCounter++;
						}
					}
					//up-left
					if(rowUp>=0 && colLeft>=0) {
						if(boardArray[rowUp][colLeft] == "B") {
							bombCounter++;
						}
					}
					//up-right
					if(rowUp>=0 && colRight<gameCols) {
						if(boardArray[rowUp][colRight] == "B") {
							bombCounter++;
						}
					}
					//down-left
					if(rowDown<gameRows && colLeft>=0) {
						if(boardArray[rowDown][colLeft] == "B") {
							bombCounter++;
						}
					}
					//down-right
					if(rowDown<gameRows && colRight<gameCols) {
						if(boardArray[rowDown][colRight] == "B") {
							bombCounter++;
						}
					}
				}
				boardArray[i][j] = Integer.toString(bombCounter);
			}
		}
	}
	
	public void gameOver() {
		System.out.println("Gameover");
		System.exit(0);
	}
	
	public void gameWin() {
		System.out.println("Winner");
		System.exit(0);
	}
	
	public static void main(String[] args) {
		Minesweeper game = new Minesweeper(5, 5, 5);
	}

}
