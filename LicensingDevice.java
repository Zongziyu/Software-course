package FightAgainstLandlords;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LicensingDevice {
	private static int give_cards_number = 17;
	private static int landlord_cards_number = 3;
	private List<Card> Cards = new ArrayList<Card>();
	
	public LicensingDevice() {
		for(int j = 0;j < 4;j++)
		{
			for(int i = 0;i < 13;i++) {
				Card new_card = new Card(i+1,j);
				Cards.add(new_card);
			}
		}
		Card joker_0 = new Card(13,4);
		Card joker_1 = new Card(14,5);
		Cards.add(joker_0);
		Cards.add(joker_1);
	}
	
	//用0 1 2 来表示玩家的编号
	public int CreateOrder() {
		Random random = new Random();
		int firstOrder = random.nextInt(3);
		return firstOrder;
	}
	
	//洗牌
	public List<Card> RandCard() {
		Collections.shuffle(this.Cards);
		return this.Cards;
	}
	
	//发牌
	public List<Card> Licensing() {
		List<Card> givecards = new ArrayList<Card>();
		for(int count = 0;count < give_cards_number;count++) {
			Card card = this.Cards.get(0);
			givecards.add(card);
			this.Cards.remove(0);
		}
		return givecards;
	}
	
	//地主牌发放,传入地主的手牌
	public List<Card> GiveLandlord(List<Card> order_cards){
		for(int i = 0;i < landlord_cards_number;i++) {
			Card card = this.Cards.get(0);
			order_cards.add(card);
			this.Cards.remove(0);
		}
		return order_cards;
	}
}
