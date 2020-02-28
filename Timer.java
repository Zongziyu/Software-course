package FightAgainstLandlords;

public class Timer {
	public int time_limit;
	static final int limit = 30;
	public Timer(){
		this.time_limit = limit;
	}
	public int GetTime() {
		return this.time_limit;
	}
	public void TimeReduce() {
		while(this.time_limit > 0) {
			this.time_limit--;
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				System.out.println(e);
			}
		}
	}
	void TimeInit() {
		this.time_limit = limit;
	}
}
