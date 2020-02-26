package gui;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public abstract class GameState extends JPanel implements Runnable, MouseListener{
	JTextArea text;
	
//	GameState(CardLayout layout){
//		super(layout);
//	}
//	public abstract void paintComponent(Graphics g);
	public abstract void transactionState();
	public abstract void playMusic();
}
