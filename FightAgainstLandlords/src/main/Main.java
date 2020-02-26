package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;
import gui.*;

public class Main implements Runnable{
	
	Thread newThread;
	MyJFrame jframe;
//	MyJPanel jpanel;
	public Main(int w, int h) {
		System.out.println("Instantiate a Class Main!");
		jframe = new MyJFrame(w,h);
//		jpanel = new MyJPanel();
//		jframe.add(jpanel);
//		jframe.setContentPane(jpanel);
		
		
		
		// 多线程
		newThread = new Thread(this);
		newThread.start(); // 线程开始一定要start
		
	}

	@Override //pass
	public void run() {
		// TODO Auto-generated method stub
		
		
	}

	public static void main(String[] args) { 
		System.out.println("Create a Class Main!");
		Main m = new Main(500,500);
	}
	
}
