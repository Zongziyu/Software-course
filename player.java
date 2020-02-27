package mainpro;

import java.util.ArrayList;
import java.util.List;


public class player {
	public int id;//0-2
	public boolean IsLord;//是否为地主
	public List<card> HandCards=new ArrayList<card>();
	public player(int a) {
		this.id=a;
		this.IsLord=false;
	}
	
	
}
