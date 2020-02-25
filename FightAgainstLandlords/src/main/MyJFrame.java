package main;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyJFrame extends JFrame{
	private int appW, appH; // 软件窗口的长宽
	JMenuBar menuBar = new JMenuBar();
	
	JMenu fileMenu = new JMenu("文件");
    JMenu editMenu = new JMenu("编辑");
    JMenu viewMenu = new JMenu("视图");
    JMenu aboutMenu = new JMenu("关于");
    
    JMenuItem newMenuItem = new JMenuItem("新建");
    JMenuItem openMenuItem = new JMenuItem("打开");
    JMenuItem exitMenuItem = new JMenuItem("退出");
    
    MyJFrame(int w,int h){
    	this.appW=w; this.appH = h;
		
//		jframe.getContentPane().add(this); // JFrame是最大框架
		
    	// JMenuBar
		menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(aboutMenu);
        
        //JMenu
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.addSeparator();       // 添加一条分割线
        fileMenu.add(exitMenuItem);
        
        this.add(menuBar);
        
        setTitle("Fight Against Landlord");
		setSize(appW, appH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭时释放资源
		setLocation(100, 100);
		setVisible(true);
    }
}
