package mainpro;

import java.util.Collections;
import java.util.*;
import java.util.List;
public class licensingdevice {
	static final int heart=0,spade=1,club=2,diamond=3,joker=4;//花色 红桃 黑桃 梅花 方块
	public List<card> Cards=new ArrayList<card>();
	//生成一副牌并洗牌
	public licensingdevice() {
		for(int i=1;i<14;i++) {
			for(int j=0;j<4;j++) {
				Cards.add(new card(i,j));
			}
		}
		Cards.add(new card(14,4));
		Cards.add(new card(15,4));
		Collections.shuffle(Cards);//洗牌
	}
	//给玩家发牌
	public List<card> Licensing(int id,List<card> cards) {
		for(int i=1;i<52;i++) {
			if(i%3==id) {
				cards.add(Cards.get(i-1));
			}
		}
		return cards;
	};
	//给玩家的手牌进行排序
	public void sortcards(List<card> handcards) {
        for (int i = 0; i < handcards.size(); i++) {
             for (int j = 0; j < handcards.size()-1-i; j++) {
                 if (handcards.get(j+1).getNum() < handcards.get(j).getNum()) {
                     int num = handcards.get(j+1).getNum();
                     int col = handcards.get(j+1).getCol();
                     handcards.get(j+1).setNum(handcards.get(j).getNum());
                     handcards.get(j+1).setCol(handcards.get(j).getCol());
                     handcards.get(j).setNum(num);
                     handcards.get(j).setCol(col);
                 }
             }
        }    
	}
	//将三张留牌发给地主
	public List<card> AddCards(List<card> cards){
		cards.add(Cards.get(51));
		cards.add(Cards.get(52));
		cards.add(Cards.get(53));
		System.out.println("底牌分别为"+Cards.get(51).getNum()+" "+Cards.get(52).getNum()+" "+Cards.get(53).getNum());
		return cards;
	}
}
