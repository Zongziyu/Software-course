package FightAgainstLandlords;

import java.util.ArrayList;
import java.util.List;

public class Game {
	int rate;
	int base_rate = 1;
	int game_number;
	int cur_game;//但前进行到第几局
	List<Card> last_cards = new ArrayList<Card>();
	public Game() {
		this.game_number = 3;
		this.cur_game = 0;
	}
	public void BeginGame() {
		this.cur_game++;
	}
	public void SetGameNumber(int GameNumber) {
		this.game_number = GameNumber;
	}
	public void InitBaseRate(int base_rate) {
		this.base_rate = base_rate;
	}
	public void SetLastCards(List<Card> last_cards) {
		this.last_cards = last_cards;
	}
	public List<Card> GetLastCards(){
		return this.last_cards;
	}
	public void SetRate() {
		this.rate = this.rate * 2;
	}
	//结算各自分数，返回赢家身份
	public String Settlement(Player[] players) {
		int winner = 0;
		for(int i = 0;i < 3;i++) {
			if(this.CheckCards(players[i]) == 0) {
				winner = players[i].GetId();
			}
		}
		//地主
		if(players[winner].GetRole() == "landlord") {
			players[winner].SetScore(this.base_rate * this.rate * 2);
			int change = -this.base_rate * this.rate;
			players[(winner + 1)%3].SetScore(change);
			players[(winner + 2)%3].SetScore(change);
		}
		//农民
		else {
			int change = -(this.base_rate * this.rate * 2);
			for(int i = 0;i < 3;i++) {
				if(players[i].GetRole() == "framer") {
					players[i].SetScore(this.base_rate * this.rate);
				}
				else {
					players[i].SetScore(change);
				}
			}
		}
		return players[winner].GetRole();
	}
	
	//0没牌，1一张牌，2两张牌,3多张牌
	public int CheckCards(Player player){
		List<Card> keep_cards = player.GetCards();
		int cards_number  = keep_cards.size();
		if(cards_number == 0)
			return 0;
		else if(cards_number == 1)
			return 1;
		else if(cards_number == 2)
			return 2;
		else
			return 3;
	}
}
