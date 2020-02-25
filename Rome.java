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
			Players.get(j).ObtainCards(ld.Licensing());
		}
		Players.get(0).ObtainCards(ld.GiveLandlord(Players.get(0).GetCards()));
		for(int j = 0;j < 3;j++) {
			Players.get(j).Arrangement();
			System.out.print("\n");
		}
	}
}

