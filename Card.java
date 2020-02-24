package FightAgainstLandlords;

import java.util.ArrayList;
import java.util.List;

public class Card {
	public List<Integer> number = new ArrayList<Integer>();
	/*用[1~54]表示每一张卡牌
	 * [1~13]红桃
	 * [14~26]方片
	 * [27~39]草花
	 * [40~52]黑桃
	 * 53小王，54大王
	 * */
	public List<Integer> CreateCard(){
		for(int i = 0; i < 54;i++){
			number.add(i + 1);
		}
		return number;
	}
	
	//返回花色0代表红桃，1方片，2草花，3黑桃，4王
	public int Suit(int card) {
		if(card >= 1 && card < 14)
			return 0;
		else if(card >= 14 && card < 27)
			return 1;
		else if(card >= 27 && card < 40)
			return 2;
		else if(card >= 40 && card < 53)
			return 3;
		else
			return 4;		
	}
	
    //牌大小返回
	private int GetCardNumber(int card) {
		if(card < 52){
			int number = card % 13 - 2;//将A，K,2的大小用数值表示
			if(number == -2) {
				number  = 11;
			}
			if(number == -1) {
				number = 12;
			}
			if(number == 0) {
				number = 13;
			}
			return number;
		}
		else {
			return card;
		}
	}
	
	//扑克牌大小比较
	public int Order(int card_1,int card_2) {
		card_1 = this.GetCardNumber(card_1);
		card_2 = this.GetCardNumber(card_2);
		if(card_1 > card_2)
			return 1;
		else if(card_1 < card_2)
			return 0;
		else
			return 2;		
	}
	
	//牌面大小获取
	public int GetCard(int card) {
		if(card < 53) {
			int number;
			number = card % 13;
			if(number == 0)
				number = 13;
			return number;
		}
		else
			return card;
	}
}
