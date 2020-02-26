package main;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.JPanel;

import gui.GameOver;
import gui.GamePlay;
import gui.MainMenu;
import gui.StartUp;

public class MyJPanel extends JPanel{
	ArrayList gamestate_list;
	StartUp start_up = new StartUp();
	MainMenu main_menu = new MainMenu();
	GamePlay game_play = new GamePlay();
	GameOver game_over = new GameOver();
	CardLayout layout = new CardLayout(10,10);
	
	public MyJPanel() {
		this.setLayout(layout);
		add(start_up,"StartUp");
		add(main_menu,"MainMenu");
		add(game_play,"GamePlay");
		add(game_over,"GameOver");
		
		TransState();
	}

	private void TransState() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		layout.next(this);
		TransState();
	}
	
}
