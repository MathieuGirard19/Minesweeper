package Minesweeper;

import java.awt.event.*;
import javax.swing.*;

public class Tiles implements ItemListener{
	private Minesweeper minesweeper;
	private JToggleButton button;
	private String input;
	private int winCounter;
	
	public Tiles(String input, Minesweeper minesweeper){
		this.input = input;
		this.minesweeper = minesweeper;
		setTile();
		setAction();
	}
	
	private void setTile() {
		button = new JToggleButton();
		minesweeper.getBoard().add(button);
	}
	
	private void setAction() {
		button.addItemListener(this);
	}
	
	public void itemStateChanged(ItemEvent eve) {
		if(button.isSelected()) {
			button.setText(input);
			if(input == "B") {
				minesweeper.gameOver();
			}
			minesweeper.incrementCounter();
		}
		if (winCounter == 20) {
			minesweeper.gameWin();
		}
	}
	
}
