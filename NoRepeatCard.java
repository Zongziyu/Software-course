package FightAgainstLandlords;
//无重复手牌数据结构
public class NoRepeatCard {
	int value;
	int number;
	public NoRepeatCard(int value) {
		this.value = value;
		this.number = 1;
	}
	public int GetValue() {
		return this.value;
	}
	public int GetNumber() {
		return this.number;
	}
	public void NumberAdd() {
		this.number += 1;
	}
}
