package FightAgainstLandlords;

public class Card {
	private int Number;
	private int Color;
	public Card(int Number,int Color) {
		this.Number = Number;/*3-0,4-1,5-2,6-3,7-4,8-5,9-6,10-7,J-8,Q-9,
		K-10,A-11,2-12,litter_joker-13,big_joker-14*/
		this.Color = Color;//0红桃，1黑桃，2梅花，3方片,4鬼
	}

	public int GetNum() {
		return this.Number;
	}
	
	public int GetCol() {
		return this.Color;
	}
	
	//0 大于，1小于，2等于
	public int CompareCard(Card card1,Card card2) {
		if(card1.GetNum() > card2.GetNum()) {
			return 0;
		}
		else if(card1.GetNum() < card2.GetNum()){
			return 1;
		}
		else {
			return 2;
		}
	}
}




