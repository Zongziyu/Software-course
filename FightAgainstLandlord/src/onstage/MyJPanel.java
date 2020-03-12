package onstage;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class MyJPanel extends JPanel{
	StartUp start_up = new StartUp();
	GamePlay game_play = new GamePlay();
	GameOver game_over = new GameOver();
	CardLayout layout = new CardLayout(10,10);
	
	public MyJPanel() {
		this.setLayout(layout);
		this.add(start_up,"StartUp");
		this.add(game_play,"GamePlay");
		this.add(game_over,"GameOver");
		
//		TransState();
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
