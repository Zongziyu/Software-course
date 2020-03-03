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
	public void setCards(List<Card> get_cards) {
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
	
	public void SetScore(int add_score) {
		this.score += add_score;
	}
	
	//理牌
	public List<Card> Arrangement(List<Card> keep_cards) {
		Collections.sort(keep_cards,new Comparator<Card>(){
			@Override
			public int compare(Card card_1 ,Card card_2) {
				return card_1.GetNum() - card_2.GetNum();
			}
		});
		return keep_cards;
	}
	
	//出牌提示
	public List<Card> HowToPlay(List<Card> last_cards){
		last_cards = this.Arrangement(last_cards);
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
						&& this.keep_card.get(i + 1).GetNum() == 
						this.keep_card.get(i).GetNum()) {
					PlayCards.add(this.keep_card.get(i));
					PlayCards.add(this.keep_card.get(i+1));
					can_play = true;
					break;
				}
			}
		}
		//3带
		else if((last_number == 4 && last_hash.size() == 2) ||
				last_number == 5 && last_hash.size() == 2) {
			int usecard = 0;//3带中的带
			for(Entry<Integer,Integer> entry : last_hash.entrySet()) {
				if(entry.getValue() == 3) {
					usecard = entry.getKey();
				}
			}
			for(int i = 0;i < this.keep_card.size() - 2;i++) {
				if(this.keep_card.get(i).GetNum() > usecard && 
						this.keep_card.get(i).GetNum() == 
						this.keep_card.get(i+1).GetNum()
						 && this.keep_card.get(i+1).GetNum() == 
						 this.keep_card.get(i+2).GetNum()) {
					PlayCards.add(this.keep_card.get(i));
					PlayCards.add(this.keep_card.get(i+1));
					PlayCards.add(this.keep_card.get(i+2));
					//带1
					if(last_number == 4) {
						if(i != 0) {
							PlayCards.add(this.keep_card.get(0));
						}
						else {
							PlayCards.add(this.keep_card.get(3));
						}
					}
					//带2
					else {
						for(int j = 0;j < this.keep_card.size() - 1;j++)
						{
							if(j < i - 1 || j > i + 2) {
								if(this.keep_card.get(j).GetNum() ==
										this.keep_card.get(j+1).GetNum()) {
									PlayCards.add(this.keep_card.get(j));
									PlayCards.add(this.keep_card.get(j + 1));
									break;
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
			//对顺
			if(last_number / last_hash.size() == 2) {
				for(int i = 0;i < this.keep_card.size();i++) {
					if(this.keep_card.get(i).GetNum() > last_cards.get(0).GetNum()) {
						if(PlayCards.size() == 0) {
							PlayCards.add(this.keep_card.get(i));
						}
						else if(PlayCards.size() %2 == 0) {
							if(this.keep_card.get(i).GetNum() - 1 
									== PlayCards.get(PlayCards.size() - 1).GetNum()) {
								PlayCards.add(this.keep_card.get(i));
							}
							else if(this.keep_card.get(i).GetNum()
									== PlayCards.get(PlayCards.size() - 1).GetNum()) {
								//空跑
							}
							else
								PlayCards.clear();
								
						}
						else if(PlayCards.size()%2 != 0) {
							if(this.keep_card.get(i).GetNum() 
									== PlayCards.get(PlayCards.size() - 1).GetNum()) {
								PlayCards.add(this.keep_card.get(i));
							}
							else
								PlayCards.clear();
						}
						if(PlayCards.size() == last_number) {
							can_play = true;
							break;
						}
					}
				}
			}
			//单顺
			else {
				for(int i = 0;i < this.keep_card.size();i++) {
					if(this.keep_card.get(i).GetNum() > last_cards.get(0).GetNum())
					{
						if(PlayCards.size() == 0) {
							PlayCards.add(this.keep_card.get(i));
						}
						else if(this.keep_card.get(i).GetNum() - 1 == 
								PlayCards.get(PlayCards.size() - 1).GetNum()) {
							PlayCards.add(this.keep_card.get(i));
						}
						else if(this.keep_card.get(i).GetNum() == 
								PlayCards.get(PlayCards.size() - 1).GetNum()) {
							//空跑
						}
						else {
							PlayCards.clear();
						}
						if(PlayCards.size() == last_number) {
							can_play = true;
							break;
						}
					}
				}
			}
		}
		if(can_play == false)
			PlayCards.clear();
		//出炸弹
		if(can_play == false) {
			//普通炸弹
			for(int i = 0;i < this.keep_card.size() - 3;i++) {
				if(this.keep_card.get(i).GetNum() ==
						this.keep_card.get(i + 1).GetNum() &&
						this.keep_card.get(i + 1).GetNum() == 
						this.keep_card.get(i + 2).GetNum() &&
						this.keep_card.get(i + 2).GetNum() == 
						this.keep_card.get(i + 3).GetNum()) {
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
					can_play = true;
				}
			}
		}
		
		//炸弹对炸弹
		if(last_number == 4 && last_hash.size() == 1) {
			int lcd = last_cards.get(0).GetNum();
			boolean ifbig = false;
			for(int i = 0;i < this.keep_card.size() - 3;i++) {
				if(this.keep_card.get(i).GetNum() == 
						this.keep_card.get(i + 1).GetNum() &&
						this.keep_card.get(i + 1).GetNum() == 
						this.keep_card.get(i + 2).GetNum() &&
						this.keep_card.get(i + 2).GetNum() == 
						this.keep_card.get(i + 3).GetNum() &&
						this.keep_card.get(i).GetNum() > lcd) {
					for(int j = i;j < i + 4;j++) {
						PlayCards.add(this.keep_card.get(j));
						ifbig = true;
						can_play = true;
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
					can_play = true;
				}
			}
		}
		if(can_play == false) {
			PlayCards.clear();
		}
		return PlayCards;
	}
	
	//打牌
	public boolean Play(List<Card> choice_cards,List<Card> last_cards){
		choice_cards = this.Arrangement(choice_cards);
		last_cards = this.Arrangement(last_cards);
		boolean can_play = false;
		boolean haveBomb = false;
		int choice_number = choice_cards.size();
		int last_number = last_cards.size();
		//重复归并统计
		HashMap<Integer,Integer> last_hash = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> choice_hash = new HashMap<Integer,Integer>();
		for(Card card:last_cards) {
			if(last_hash.get(card.GetNum()) != null) {
				Integer integer = last_hash.get(card.GetNum());
				last_hash.put(card.GetNum(),integer + 1);
			}
			else {
				last_hash.put(card.GetNum(), 1);
			}
		}
		for(Card card:choice_cards) {
			if(choice_hash.get(card.GetNum()) != null) {
				Integer integer = choice_hash.get(card.GetNum());
				choice_hash.put(card.GetNum(),integer + 1);
			}
			else {
				choice_hash.put(card.GetNum(), 1);
			}
		}
		//单
		if(last_number == 1 && choice_number == 1) {
			if(choice_cards.get(0).GetNum() > last_cards.get(0).GetNum())
				can_play = true;
			else
				can_play = false;
		}
		//对子
		else if(last_number == 2 && choice_number == 2) {
			if(last_hash.size() == 2) {
				can_play = false;
				haveBomb = true;
			}
			else {
				if(choice_hash.size() == 1) {
					if(choice_cards.get(0).GetNum() > last_cards.get(0).GetNum())
						can_play = true;
					else
						can_play = false;
				}
				else {
					can_play = false;
				}
			}
		}
		//3带
		else if((last_number == 4 && last_hash.size() == 2) || last_number == 5 
				&& last_hash.size() == 2 && last_number == choice_number) {
			int lusecard = 0;//3带中的带
			int cusecard = 0;
			for(Entry<Integer,Integer> entry : last_hash.entrySet()) {
				if(entry.getValue() == 3) {
					lusecard = entry.getKey();
				}
			}
			for(Entry<Integer,Integer> entry : choice_hash.entrySet()) {
				if(entry.getValue() == 3) {
					cusecard = entry.getKey();
				}
			}
			if(cusecard > lusecard)
				can_play = true;
			else
				can_play = false;
		}
		//顺子
		else if(last_number >= 6) {
			if(last_number != choice_number) {
				can_play = false;
			}
			else {
				if(choice_cards.get(0).GetNum() <= last_cards.get(0).GetNum()) {
					can_play = false;
				}
				else {
					//双
					if(last_number / last_hash.size() == 2) {
						int count = 0;
						boolean ifok = true;
						while(count < choice_cards.size() - 2) {
							if(choice_cards.get(count).GetNum() != 
									choice_cards.get(count+1).GetNum() ||
									choice_cards.get(count+1).GetNum()+1 !=
									choice_cards.get(count+2).GetNum()) {
								can_play = false;
								ifok = false;
								break;
							}
							count+=2;
						}
						if(ifok){
							can_play = true;
						}
					}
					//单
					else {
						boolean ifok = true;
						for(int i = 0; i < choice_number - 1;i++) {
							if(choice_cards.get(i).GetNum() + 1 != 
									choice_cards.get(i + 1).GetNum()) {
								can_play = false;
								ifok = false;
								break;
							}
						}
						if(ifok) {
							can_play = true;
						}
					}
				}
			}
		}
		
		//炸弹对炸弹
		else if(last_number == 4 && last_hash.size() == 1) {
			haveBomb = true;
			if(choice_number == 4) {
				if(choice_hash.size() != 1) {
					can_play = false;
				}
				else {
					if(choice_cards.get(0).GetNum() > last_cards.get(0).GetNum()) {
						can_play = true;
					}
					else {
						can_play = false;
					}
				}
			}
			if(choice_number == 2 && choice_cards.get(0).GetNum() == 13
					&& choice_cards.get(1).GetNum() == 14) {
				can_play = true;
			}
		}
		
		//炸弹解决问题
		if(haveBomb == false && can_play == false) {
			if(choice_number == 4 && choice_hash.size() == 1) {
				can_play = true;
			}
			if(choice_number == 2 && choice_cards.get(0).GetNum() == 13
					&& choice_cards.get(1).GetNum() == 14) {
				can_play = true;
			}
		}
		//将出的牌从手牌中移除
		if(can_play)
		{
			for(int i = 0;i < this.keep_card.size();i++) {
				for(int j = 0; j < choice_cards.size();j++) {
					if(this.keep_card.get(i) == choice_cards.get(j)) {
						this.keep_card.remove(i);
					}
				}
			}
		}
		return can_play;
	}	
}





