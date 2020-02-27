package mainpro;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
public class mainstage {
	static Scanner sc=new Scanner(System.in);//键盘接收
	static List<player> Players=new ArrayList<player>();//初始化玩家
	static licensingdevice ld=new licensingdevice();//发牌器
	static mainstage ms=new mainstage();
	//给玩家发牌
	public void licensingcards() {
		for(int i=0;i<3;i++) {
			 player pl=new player(i);
			 pl.HandCards=ld.Licensing(i,pl.HandCards);
			 ld.sortcards(pl.HandCards);
			 Players.add(pl);	 
		}
	}
	//抢地主
	public void ChooseLandLord() {
		for(int i=0;i<3;i++) {
			System.out.println("玩家"+i+"是否选择抢地主，是请输入1，否则输入0:");
			String str=sc.next();
			if(str.equals("1")) {
				System.out.println("玩家"+i+"抢地主");
				Players.get(i).HandCards=ld.AddCards(Players.get(i).HandCards);
				Players.get(i).IsLord=true;
				break;
			}
		}
	}
	//展示玩家手牌情况
	public void showcards() {
			for(int i=0;i<3;i++) {
				System.out.println("玩家"+i+"的手牌情况:");
				for(int j=0;j<Players.get(i).HandCards.size();j++) {
					System.out.print(Players.get(i).HandCards.get(j).getNum()+" ");
				}
				System.out.println();
			}
	}
	//运行主程序
	public void Run() {
		this.licensingcards();
		this.ChooseLandLord();
		this.showcards();
		
	}
	
	
	public static void main(String[] args) {
		ms.Run();
	}

}


