package FightAgainstLandlords;

import java.util.ArrayList;
import java.util.List;

class Rome{
	public LicensingDevice ld = new LicensingDevice();
	public List<Player> Players = new ArrayList<Player>();
	public void run() {
		int firstman = ld.CreateOrder();
		ld.RandCard();
		System.out.println("一个拿牌的人:"+firstman);
		for(int j = 0;j < 3;j++)
		{
			Player player = new Player(10,firstman);
			firstman++;
			if(firstman > 2) {
				firstman = 0;
			}
			Players.add(player);
		}
		for(int j = 0;j < 3;j++)
		{
			Players.get(j).setCards(ld.Licensing());
		}
		Players.get(0).setCards(ld.GiveLandlord(Players.get(0).GetCards()));
		for(int j = 0;j < 3;j++) {
			List<Card> keep_cards = Players.get(j).Arrangement(Players.get(j).GetCards());
			Players.get(j).setCards(keep_cards);
			for(Card card:keep_cards) {
				System.out.print(card.GetNum() + ",");
			}
			System.out.print("\n");
		}
		
		//出牌提示测试
		List<Card> last_card = new ArrayList<Card>();
		List<Card> play_card = new ArrayList<Card>();
		Card card_1 = new Card(0,0);
		Card card_2 = new Card(1,1);
		Card card_3 = new Card(2,2);
		Card card_4 = new Card(3,3);
		Card card_5 = new Card(4,1);
		Card card_6 = new Card(5,3);
		last_card.add(card_1);
		last_card.add(card_2);
		last_card.add(card_3);
		last_card.add(card_4);
		play_card = Players.get(0).HowToPlay(last_card);
		for(Card card:play_card) {
			System.out.println(card.GetNum() + " ");
		}
	}
}

