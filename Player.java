package FightAgainstLandlords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Player {
	public String role;
	public int score;
	public List<Card> now_card = new ArrayList<Card>();
	public List<Card> keep_card = new ArrayList<Card>();
	public int id;//0，1，2
	
	public Player(int score,int id) {
		role = "farmer";
		this.score = score;
		this.id = id;
	}
	
	public void ObtainCards(List<Card> get_cards) {
		this.keep_card = get_cards;
	}
	
	public List<Card> GetCards(){
		return this.keep_card;
	}
	
	//获取身份
	public String GetRole() {
		return this.role;
	}
	
	//更新分数
	public void UpdateScore(int change) {
		this.score += change;
	}
	
	//获取分数
	public int GetScore() {
		return this.score;
	}
	
	//获取id
	public int GetId() {
		return this.id;
	}
	
	//抢地主
	public boolean RobLandlord() {
		return true;
	}
	
	//理牌
	public void Arrangement() {
		Collections.sort(this.keep_card,new Comparator<Card>(){
			@Override
			public int compare(Card card_1 ,Card card_2) {
				return card_1.GetNum() - card_2.GetNum();
			}
		});
		
		for(Card card:this.keep_card) {
			System.out.print(card.GetNum() + ",");
		}
	}
	
	//出牌提示
	public List<Card> HowToPlay(List<Card> last_cards){
		List<Card> PlayCard =  new ArrayList<Card>();
		return PlayCard;
	}
}





