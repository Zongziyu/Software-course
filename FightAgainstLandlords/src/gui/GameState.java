package gui;

import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public abstract class GameState extends JPanel implements Runnable, MouseListener{
	JTextArea text;
	
	public abstract void transactionState();
	public abstract void playMusic();
}
