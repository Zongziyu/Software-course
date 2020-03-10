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
		List<NoRepeatCard> last_np = new ArrayList<NoRepeatCard>();
		last_np = this.no_repeat(last_cards, last_np);
		int last_type = this.ChoiceType(last_cards);//类型计算
		int last_imp = this.GetImportCard(last_np);//计算关键牌
		boolean can_play = false;//表示是否有牌可出
		boolean have_bomb = false;//对方是否出炸弹
		if(last_type == 4 || last_type == 5) {
			have_bomb = true;
		}
		else {
			have_bomb = false;
		}
		//单牌
		if(last_type == 0) {
			for(Card card:this.keep_card) {
				if(card.GetNum() > last_cards.get(0).GetNum()) {
					PlayCards.add(card);
					can_play = true;
					break;
				}
			}
		}
		//对
		else if(last_type == 1) {
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
		else if(last_type == 2 || last_type == 3) {
			//选出3张牌
			int flag = 0;
			for(int i = 0;i < this.keep_card.size();i++) {
				if(this.keep_card.get(i).GetNum() > last_imp) {
					PlayCards.add(this.keep_card.get(i));
					flag = i;
				}
				if(PlayCards.size() == 3) {
					if(this.ChoiceType(PlayCards) != 11 ) {
						PlayCards.remove(0);
					}
					else {
						can_play = true;
						break;
					}
				}
			}
			//选带
			if(can_play == true) {
				if(last_type == 2) {
					for(int i = 0;i < this.keep_card.size();i++) {
						if(i < flag - 2 || i > flag) {
							PlayCards.add(this.keep_card.get(i));
							break;
						}
					}
					if(PlayCards.size() < 4) {
						can_play = false;
					}
				}
				else if(last_type == 3) {
					for(int i = 0;i < this.keep_card.size() - 1;i++) {
						if(i < flag - 3 || i > flag) {
							if(this.keep_card.get(i).GetNum() == 
									this.keep_card.get(i + 1).GetNum()) {
								PlayCards.add(this.keep_card.get(i));
								PlayCards.add(this.keep_card.get(i + 1));
								break;
							}
						}
					}
					if(PlayCards.size() < 5) {
						can_play = false;
					}
				}
			}
		}	
		//单顺
		else if(last_type == 6) {
			for(int i = 0;i < this.keep_card.size();i++) {
				if(this.keep_card.get(i).GetNum() > last_cards.get(0).GetNum()) {
					if(PlayCards.size() == 0) {	
						PlayCards.add(this.keep_card.get(i));
					}
					else {
						PlayCards = this.Biger(PlayCards, i);
					}
					if(PlayCards.size() == last_cards.size()) {
						can_play = true;
						break;
					}
				}
			}
		}
		//对顺
		else if(last_type == 7) {
			for(int i = 0;i < this.keep_card.size();i++) {
				if(this.keep_card.get(i).GetNum() > last_cards.get(0).GetNum()) {
					if(PlayCards.size() == 0) {	
						PlayCards.add(this.keep_card.get(i));
					}
					else {
						if(PlayCards.size() % 2 != 0) {
							PlayCards = this.Equal(PlayCards, i);
						}
						else {
							PlayCards = this.Biger(PlayCards, i);
						}
					}
					if(PlayCards.size() == last_cards.size()) {
						can_play = true;
						break;
					}
				}
			}
		}	
		//飞机
		else if(last_type == 8 || last_type == 9 || last_type == 10) {
			List<Integer> flag = new ArrayList<Integer>();
			//飞机数量
			int imp_number = (last_cards.size() / (last_type - 5)) * 3;
			System.out.println("imp_number:" + imp_number);
			//出牌数量
			int number = 0;
			//3连寻找成败
			boolean if_find = false;
			//寻找3联对
			for(int i = 0;i < this.keep_card.size();i++) {
				if(this.keep_card.get(i).GetNum() > last_imp) {
					if(PlayCards.size() == 0) {
						PlayCards.add(this.keep_card.get(i));
						flag.add(i);
					}
					else {
						if(this.keep_card.size() % 3 == 0) {
							number  = PlayCards.size();
							PlayCards = this.Biger(PlayCards, i);
							if(number == PlayCards.size() - 1) {
								flag.add(i);
							}
							else if(PlayCards.size() == 0) {
								flag.clear();
							}
						}
						else {
							number = PlayCards.size();
							PlayCards = this.Equal(PlayCards, i);
							if(number == PlayCards.size() - 1) {
								flag.add(i);
							}
							else if(PlayCards.size() == 0) {
								flag.clear();
							}
						}
					}
					if(PlayCards.size() == imp_number) {
						if_find = true;
						break;
					}
				}
			}
			//寻找带牌
			if(if_find)
			{
				if(last_type == 8) {
					can_play = true;
				}
				//单
				else if(last_type == 9) {
					for(int i = 0;i < this.keep_card.size();i++) {
						boolean ifhave = false;
						for(int j = 0;j < flag.size();j++) {
							if(flag.get(j) == i) {
								ifhave = true;
								break;
							}
						}
						if(ifhave == false) {
							PlayCards.add(this.keep_card.get(i));
						}
						if(PlayCards.size() == last_cards.size()) {
							can_play = true;
							break;
						}
					}
				}
				//对
				else {
					for(int i = 0;i < this.keep_card.size() - 1;i++) {
						boolean ifhave = false;
						for(int j = 0;j < flag.size();j++) {
							if(flag.get(j) == i || flag.get(j) == i + 1) {
								ifhave = true;
								break;
							}
						}
						if(ifhave == false) {
							if(this.keep_card.get(i).GetNum() == 
									this.keep_card.get(i + 1).GetNum()) {
								PlayCards.add(this.keep_card.get(i));
								PlayCards.add(this.keep_card.get(i + 1));
							}
						}
						if(PlayCards.size() == last_cards.size()) {
							can_play = true;
							break;
						}
					}
				}
			}
		}
		if(can_play == false)
			PlayCards.clear();
		
		//出炸弹解决
		if(can_play == false && have_bomb == false) {
			System.out.println("Bomb");
			PlayCards.clear();
			//普通炸弹
			for(int i = 0;i < this.keep_card.size();i++) {
				PlayCards.add(this.keep_card.get(i));
				int play_type = -1;
				if(PlayCards.size() == 4) {
					play_type = this.ChoiceType(PlayCards);
				}
				if(play_type == 4) {
					can_play = true;
					break;
				}
				if(PlayCards.size() == 4) {
					PlayCards.remove(0);
				}
			}		
			//王炸
			if(can_play == false){
				PlayCards.clear();
				PlayCards.add(this.keep_card.get(this.keep_card.size() - 1));
				PlayCards.add(this.keep_card.get(this.keep_card.size() - 2));
				if(this.ChoiceType(PlayCards) == 5) {
					can_play = true;
				}
				else {
					PlayCards.clear();
				}
			}
		}
		//炸弹对炸弹
		//普通炸弹
		if(last_type == 4) {
			for(int i = 0;i < this.keep_card.size();i++) {
				if(this.keep_card.get(i).GetNum() > last_imp) {
					PlayCards.add(this.keep_card.get(i));
					int play_type = -1;
					if(PlayCards.size() == 4) {
						play_type = this.ChoiceType(PlayCards);
					}
					if(play_type == 4) {
						can_play = true;
						break;
					}
					if(PlayCards.size() == 4) {
						PlayCards.remove(0);
					}
				}
			}
			//回王炸
			if(can_play == false){
				PlayCards.clear();
				PlayCards.add(this.keep_card.get(this.keep_card.size() - 1));
				PlayCards.add(this.keep_card.get(this.keep_card.size() - 2));
				if(this.ChoiceType(PlayCards) == 5) {
					can_play = true;
				}
				else {
					PlayCards.clear();
				}
			}
		}
		//王炸
		else if(last_type == 5) {
			can_play = false;
		}
		
		if(can_play == false) {
			PlayCards.clear();
		}
//		play_type = this.ChoiceType(PlayCards);
		System.out.println(can_play);
//		System.out.println("type:" + play_type);
		System.out.println("last_type:"+last_type);
		return PlayCards;
	}
	
	//打牌
	public boolean Play(List<Card> last_cards,List<Card> choice_cards){
		boolean can_play = false;
		can_play = this.Rule(last_cards, choice_cards);
		//移除牌
		if(can_play) {
			for(int i = 0;i < this.keep_card.size();i++) {
				for(int j = 0;j < choice_cards.size();j++) {
					if(choice_cards.get(j) == this.keep_card.get(i)) {
						this.keep_card.remove(i);
					}
				}
			}
		}
		return can_play;
	}	
	
	private boolean Rule(List<Card> last_cards,List<Card> choice_cards) {
		boolean can_play = false;
		last_cards = this.Arrangement(last_cards);
		choice_cards = this.Arrangement(choice_cards);
		//重复归并
		List<NoRepeatCard> last_np = new ArrayList<NoRepeatCard>();
		List<NoRepeatCard> choice_np = new ArrayList<NoRepeatCard>();
		last_np = this.no_repeat(last_cards, last_np);
		choice_np = this.no_repeat(choice_cards, choice_np);
		//第一个出牌
		if(last_cards.size() == 0) {
			if(this.ChoiceType(choice_cards) != -1) {
				can_play = true;
			}
		}
		else {
			int last_type = this.ChoiceType(last_cards);
			int choice_type = this.ChoiceType(choice_cards);
			if(choice_type == -1) {
				can_play = false;
			}
			else {
				int last_imp = this.GetImportCard(last_np);
				int choice_imp = this.GetImportCard(last_np);
				if(last_type != 4 && last_type != 5) {
					//炸弹
					if(choice_type == 4 || choice_type == 5) {
						can_play = true;
					}
					else {
						if(choice_type != last_type ) {
							can_play = false;
						}
						else {
							if(choice_imp > last_imp) {
								can_play = true;
							}
							else {
								can_play = false;
							}
						}
					}
				}
				//普通炸弹
				else if(last_type == 4) {
					if(choice_type != 4 || choice_type != 5) {
						can_play = false;
					}
					//王炸
					else if(choice_type == 5) {
						can_play = true;
					}
					else if(choice_type == 4) {
						if(choice_imp > last_imp) {
							can_play = true;
						}
						else {
							can_play = false;
						}
					}
				}
				else if(last_type == 5) {
					can_play = false;
				}
			}
		}
		return can_play;
	}
	
	//选牌规则
	/*0:单牌, 1:对子, 2:3带1，3：3带2 ，4：普通炸弹,5:王炸,6:单顺，7：双顺,
	 * 8:飞机，9：飞机带单,10:飞机带双 11:3张相同   */
	private int ChoiceType(List<Card> choice_cards) {
		int type = -1;
		choice_cards = this.Arrangement(choice_cards);
		List<NoRepeatCard> choice_np = new ArrayList<NoRepeatCard>();
		choice_np = no_repeat(choice_cards,choice_np);
		//单牌
		if(choice_cards.size() == 1) {
			type = 0;
		}
		//对子
		else if(choice_cards.size() == 2 && choice_np.size() == 1) {
			type = 1;
		}
		//3带1
		else if(choice_cards.size() == 4 && choice_np.size() == 2) {
			type = 2;
		}
		//3带2
		else if(choice_cards.size() == 5 && choice_np.size() == 2) {
			type = 3;
		}
		//普通炸弹
		else if(choice_cards.size() == 4 && choice_np.size() == 1) {
			type = 4;
		}
		//王炸
		else if(choice_cards.get(0).GetNum() == 13 
				&& choice_cards.get(1).GetNum() == 14
				&& choice_cards.size() == 2) {
			type = 5;
		}
		
		//多牌
		else if(choice_cards.size() >= 6) {
			int cards_type = 0;
			for(int i = 0;i < choice_np.size();i++) {
				if(choice_np.get(i).GetNumber() == 2) {
					if(cards_type <= 1)
						cards_type = 1;
				}
				if(choice_np.get(i).GetNumber() == 3) {
					cards_type = 2;
				}
			}
			//单顺
			if(choice_cards.size() == choice_np.size()) {
				if(this.LianRule_1(choice_np)) {
					type = 6;
				}
			}
			//对顺
			if(choice_cards.size() / choice_np.size() == 2 && cards_type == 1) {
				if(this.LianRule_1(choice_np)) {
					type = 7;
				}
			}
			//飞机
			if(choice_cards.size() / choice_np.size() == 3) {
				if(this.LianRule_1(choice_np)) {
					type = 8;
				}
			}
			//飞机带单
			if(choice_cards.size() / choice_np.size() == 2 && cards_type == 2) {
				if(this.LianRule_2(choice_np)) {
					type = 9;
				}
			}
			//飞机带双
			if(choice_cards.size() == 3 * choice_np.size() / 2 + choice_np.size() 
				&& cards_type == 2) {
				if(this.LianRule_2(choice_np)) {
					type = 10;
				}
			}
		}
		//3张相同
		else if(choice_cards.size() == 3 && choice_np.size() == 1) {
			type = 11;
		}
		return type;
	}
	
	//连牌规则1 有序
	private boolean LianRule_1(List<NoRepeatCard> np) {
		boolean on_rule = true;
		for(int i = 0;i < np.size() - 1;i++) {
			if(np.get(i).GetValue() + 1 != np.get(i+1).GetValue()) {
				on_rule = false;
				break;
			}
		}
		return on_rule;
	}
	//连牌规则2 有三连对
	private boolean LianRule_2(List<NoRepeatCard> np) {
		boolean on_rule = true;
		int start = -1;
		for(int i = 0; i < np.size();i++) {
			if(np.get(i).GetNumber() == 3) {
				if(start == -1) {
					start = np.get(i).GetValue();
				}
				else {
					if(np.get(i).GetValue() != start + 1) {
						on_rule = false;
						break;
					}
					start = np.get(i).GetValue();
				}
			}
		}
		return on_rule;
	}
	
	//关键牌提取
	private int GetImportCard(List<NoRepeatCard> np) {
		int imp = -1;
		int maxnum = 0;
		for(int i = 0;i < np.size();i++) {
			if(np.get(i).GetNumber() > maxnum) {
				imp = np.get(i).GetValue();
				maxnum = np.get(i).GetNumber();
			}
		}
		return imp;
	}
	
	//重复归并
	private List<NoRepeatCard> no_repeat(List<Card> cards_list,List<NoRepeatCard> np){
		for(int i = 0;i < cards_list.size();i++) {
			int num = cards_list.get(i).GetNum();
			if(np.isEmpty()) {
				np.add(new NoRepeatCard(num));
			}
			else {
				if(np.get(np.size() - 1).GetValue() == num) {
					np.get(np.size() - 1).NumberAdd();
				}
				else {
					np.add(new NoRepeatCard(num));
				}
			}
		}
		return np;
	}
	
	//差1
	private List<Card> Biger(List<Card> PlayCards,int flag){
		if(this.keep_card.get(flag).GetNum() == 
				PlayCards.get(PlayCards.size() - 1).GetNum() + 1) {
			PlayCards.add(this.keep_card.get(flag));
				}
		else if(this.keep_card.get(flag).GetNum() != 
				PlayCards.get(PlayCards.size() - 1).GetNum() + 1
				&& this.keep_card.get(flag).GetNum() != 
				PlayCards.get(PlayCards.size() - 1).GetNum()) {
			PlayCards.clear();
		}
		return PlayCards;
	}
	
	//等大
	private List<Card> Equal(List<Card> PlayCards,int flag){
		if(this.keep_card.get(flag).GetNum() == 
				PlayCards.get(PlayCards.size() - 1).GetNum()) {
			PlayCards.add(this.keep_card.get(flag));
		}
		else {
			PlayCards.clear();
		}
		return PlayCards;
	}
}





