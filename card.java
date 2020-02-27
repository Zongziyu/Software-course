package mainpro;
import java.util.*;
import java.util.List;
import java.awt.*;
public class card {


	private int Number;//1-13 14 15分别为小王大王
	private int Color;
	boolean IsChoosed=false;
	public card(int N,int C) {
		this.Number=N;
		this.Color=C;
	};
	//获取
	public int getNum() {return this.Number;}
	public int getCol() {return this.Color;}
	//设置
	public void setNum(int a) {this.Number=a;}
	public void setCol(int a) {this.Color=a;}
	}
	

