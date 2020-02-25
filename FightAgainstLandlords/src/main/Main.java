package main;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;
import gui.*;

public class Main extends MyJFrame implements Runnable{
	ArrayList gamestate_list;
	Thread newThread;
	
	
	public Main(int w, int h) {
		super(w,h);
		
		gamestate_list = new ArrayList();
		gamestate_list.add(new StartUp());
		gamestate_list.add(new MainMenu());
		gamestate_list.add(new GamePlay());
		gamestate_list.add(new GameOver());
		
		// 多线程
		newThread = new Thread(this);
		newThread.start(); // 线程开始一定要start
		
		
		
	}

	@Override //pass
	public void run() {
		// TODO Auto-generated method stub
		
		
	}

	public static void main(String[] args) { 
		Main m = new Main(500,500);
	}
	
}
