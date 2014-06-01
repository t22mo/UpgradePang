package data;

public class SwapRecord {
	public float endTime;
	public int mainx,mainy;
	public int targetx,targety;
	public int type;
	
	public SwapRecord(float endTime,int mainx,int mainy,int targetx,int targety,int type)
	{
		this.endTime = endTime;
		this.mainx = mainx;
		this.mainy = mainy;
		this.targetx = targetx;
		this.targety = targety;
		this.type = type;
	}
}
