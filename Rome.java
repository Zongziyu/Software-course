package FightAgainstLandlords;

import java.util.ArrayList;
import java.util.List;

//测试用类
public class Rome {
	List<Integer> cards = new ArrayList<Integer>();
	Card card_class = new Card();//牌库中的牌
	LicensingDevice licensing_device_class = new LicensingDevice();
	
	public void InitRoom() {
		cards = card_class.CreateCard();
	}
	
	public void RunThisRoom() {
		ArrayList<Integer>[] players = new ArrayList[3];
		int first_order = licensing_device_class.CreateOrder();
		System.out.print(first_order);
		cards = licensing_device_class.RandCard(cards);
		System.out.print("\n洗过的牌：\n");
		for(Integer integer:cards) {
			System.out.print(integer.toString() + ",");
		}
		int order_id = first_order;
		for(int i = 0;i < 3;i++) {
			System.out.print("\n玩家" + order_id + "的牌:\n");
			players[order_id] = (ArrayList<Integer>) licensing_device_class.Licensing(cards);
			for(Integer integer:players[order_id]) {
				System.out.print(integer.toString() + ",");
			}
			order_id++;
			if(order_id == 3) {
				order_id = 0;
			}
		}
		System.out.print("\n地主牌：\n");
		players[2] = (ArrayList<Integer>) licensing_device_class.
				GiveLandlord(players[2], cards);
		for(Integer integer:players[2]) {
			System.out.print(integer.toString() + ",");
		}
	}
}
