package FightAgainstLandlords;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LicensingDevice {
	public int order[] = new int[3];
	private static int cards_number = 54;
	private static int give_cards_number = 17;
	private static int landlord_cards_number = 3;
	//用0 1 2 来表示玩家的编号
	public int CreateOrder() {
		Random random = new Random();
		int firstOrder = random.nextInt(3);
		return firstOrder;
	}
	
	//洗牌,传入cards为ArrayList类型<Integer>
	public List<Integer> RandCard(List<Integer> cards) {
		Collections.shuffle(cards);
		return cards;
	}
	
	//发牌,传入数据类型为ArrayList,表示牌库现有纸牌数量,返回值也为ArrayList类型
	public List<Integer> Licensing(List<Integer> cards) {
		List<Integer> givecards = new ArrayList<Integer>();
		for(int count = 0;count < give_cards_number;count++) {
			int card = cards.get(0);
			givecards.add(card);
			cards.remove(0);
		}
		return givecards;
	}
	
	//地主牌发放,传入地主的手牌，牌库现有牌
	public List<Integer> GiveLandlord(List<Integer> order_cards,List<Integer> cards){
		for(int i = 0;i < landlord_cards_number;i++) {
			int card = cards.get(0);
			order_cards.add(card);
			cards.remove(0);
		}
		return order_cards;
	}
}
