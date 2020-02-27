package FightAgainstLandlords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

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
	
	//得到发送的牌
	public void ObtainCards(List<Card> get_cards) {
		this.keep_card = get_cards;
	}
	
	//获取手牌
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
		List<Card> PlayCards =  new ArrayList<Card>();
		//整理重复的牌
		HashMap<Integer,Integer> last_hash = new HashMap<Integer,Integer>();
		for(Card card:last_cards) {
			if(last_hash.get(card.GetNum()) != null) {
				Integer integer = last_hash.get(card.GetNum());
				last_hash.put(card.GetNum(),integer + 1);
			}
			else {
				last_hash.put(card.GetNum(), 1);
			}
		}
		int last_number = last_cards.size();
		boolean can_play = false;//表示是否有牌可出
		if(last_number == 1) {
			for(Card card:this.keep_card) {
				if(card.GetNum() > last_cards.get(0).GetNum()) {
					PlayCards.add(card);
					can_play = true;
					break;
				}
			}
		}
		//对
		else if(last_number == 2 && last_hash.size() == 1) {
			for(int i = 0;i < this.keep_card.size() - 1;i++) {
				if(this.keep_card.get(i).GetNum() > last_cards.get(0).GetNum()
						&& this.keep_card.get(i + 1).GetNum() == this.keep_card.get(i).GetNum()) {
					PlayCards.add(this.keep_card.get(i));
					PlayCards.add(this.keep_card.get(i+1));
					can_play = true;
					break;
				}
			}
		}
		//3带
		else if((last_number == 4 && last_hash.size() == 2) || last_number == 5 && last_hash.size() == 2) {
			int usecard = 0;//3带中的带
			for(Entry<Integer,Integer> entry : last_hash.entrySet()) {
				if(entry.getValue() == 3) {
					usecard = entry.getKey();
				}
			}
			for(int i = 0;i < this.keep_card.size() - 2;i++) {
				if(this.keep_card.get(i).GetNum() > usecard && this.keep_card.get(i).GetNum() == this.keep_card.get(i+1).GetNum()
						 && this.keep_card.get(i+1).GetNum() == this.keep_card.get(i+2).GetNum()) {
					PlayCards.add(this.keep_card.get(i));
					PlayCards.add(this.keep_card.get(i+1));
					PlayCards.add(this.keep_card.get(i+2));
					if(last_number == 4) {
						if(i != 0) {
							PlayCards.add(this.keep_card.get(0));
						}
						else {
							PlayCards.add(this.keep_card.get(3));
						}
					}
					else {
						for(int j = 0;j < this.keep_card.size() - 1;j++)
						{
							if(j < i - 1 || j > i + 2) {
								if(this.keep_card.get(j).GetNum() == this.keep_card.get(j+1).GetNum()) {
									PlayCards.add(this.keep_card.get(j));
									PlayCards.add(this.keep_card.get(j + 1));
								}
							}
						}
					}
					break;
				}
			}
			can_play = true;
		}
		//顺子
		else if(last_number >= 6) {
			if(last_number / last_hash.size() == 2) {
				int now_number = 0;
				int i = 0;
				while(i < this.keep_card.size() - 2) {
					if(this.keep_card.get(i).GetNum() == this.keep_card.get(i + 1).GetNum()
							&& this.keep_card.get(i + 2).GetNum() == this.keep_card.get(i+1).GetNum() + 1 &&
							this.keep_card.get(i).GetNum() > last_cards.get(0).GetNum()) {
						PlayCards.add(this.keep_card.get(i));
						PlayCards.add(this.keep_card.get(i+1));
						PlayCards.add(this.keep_card.get(i+2));
						now_number+=3;
					}
					else {
						PlayCards.clear();
						now_number = 0;
						i -= 1;
					}
					i+=2;
					if(now_number == last_number) {
						can_play = true;
						break;
					}
				}
			}
			else {
				int i = 0;
				int now_number = 0;
				while(i < last_number - 1) {
					if(this.keep_card.get(i).GetNum() + 1 == this.keep_card.get(i + 1).GetNum() &&
							this.keep_card.get(i).GetNum() > last_cards.get(0).GetNum()) {
						now_number += 1;
						PlayCards.add(this.keep_card.get(i));
					}
					else {
						now_number = 0;
						PlayCards.clear();
					}
					if(now_number == last_number) {
						can_play = true;
						break;
					}
				}
			}
		}
		
		//出炸弹
		if(can_play == false) {
			//普通炸弹
			for(int i = 0;i < this.keep_card.size() - 3;i++) {
				if(this.keep_card.get(i).GetNum() == this.keep_card.get(i + 1).GetNum() &&
						this.keep_card.get(i + 1).GetNum() == this.keep_card.get(i + 2).GetNum() &&
						this.keep_card.get(i + 2).GetNum() == this.keep_card.get(i + 3).GetNum()) {
					for(int j = i;j < i + 4;j++) {
						PlayCards.add(this.keep_card.get(j));
						can_play = true;
					}
					break;
				}
			}
			
			//王炸
			if(can_play == false) {
				if(this.keep_card.get(this.keep_card.size() - 1).GetCol() == 4 && 
						this.keep_card.get(this.keep_card.size() - 2).GetCol() == 4) {
					PlayCards.add(this.keep_card.get(this.keep_card.size() - 1));
					PlayCards.add(this.keep_card.get(this.keep_card.size() - 2));
				}
			}
		}
		
		//炸弹对炸弹
		if(last_number == 4 && last_hash.size() == 1) {
			int lcd = last_cards.get(0).GetNum();
			boolean ifbig = false;
			for(int i = 0;i < this.keep_card.size() - 3;i++) {
				if(this.keep_card.get(i).GetNum() == this.keep_card.get(i + 1).GetNum() &&
						this.keep_card.get(i + 1).GetNum() == this.keep_card.get(i + 2).GetNum() &&
						this.keep_card.get(i + 2).GetNum() == this.keep_card.get(i + 3).GetNum() &&
						this.keep_card.get(i).GetNum() > lcd) {
					for(int j = i;j < i + 4;j++) {
						PlayCards.add(this.keep_card.get(j));
						ifbig = true;
					}
					break;
				}
			}
			
			//王炸
			if(ifbig == false) {
				if(this.keep_card.get(this.keep_card.size() - 1).GetCol() == 4 && 
						this.keep_card.get(this.keep_card.size() - 2).GetCol() == 4) {
					PlayCards.add(this.keep_card.get(this.keep_card.size() - 1));
					PlayCards.add(this.keep_card.get(this.keep_card.size() - 2));
				}
			}
		}
		return PlayCards;
	}
}





