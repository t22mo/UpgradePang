package proccesor;

import java.util.ArrayList;

import object.Board;
import object.Gem;

import com.badlogic.gdx.math.Vector2;

import data.SwapRecord;

public class SwapProc {
	static final ArrayList<SwapRecord> queue = new ArrayList<SwapRecord>();
	
	public static void doSwap(Gem mainGem,Gem targetGem,float time,int type)
	{	
		mainGem.setAnimation(true);
		mainGem.setLock(true);
		mainGem.setAnimationDestination(targetGem.GetScreenCoord(), time,time+(float)0.15,mainGem.GetScreenCoord(),(int)targetGem.GetBoardCoord().x,(int)targetGem.GetBoardCoord().y);

		targetGem.setLock(true);
		targetGem.setAnimation(true);
		targetGem.setAnimationDestination(mainGem.GetScreenCoord(), time,time+(float)0.15,targetGem.GetScreenCoord(),(int)mainGem.GetBoardCoord().x,(int)mainGem.GetBoardCoord().y);
		
		queue.add(new SwapRecord(time+(float)0.15,(int)mainGem.GetBoardCoord().x,(int)mainGem.GetBoardCoord().y,(int)targetGem.GetBoardCoord().x,(int)targetGem.GetBoardCoord().y,type));
		
	}
	public static void Update(float elapsedTime)
	{
		for(int i=0 ; i<queue.size() ; i++)
		{
			if(queue.get(i).endTime<elapsedTime)
			{
				if(false)
				{
					Gem temp1 = Board.getGem(queue.get(i).mainx,queue.get(i).mainy);
					Gem temp2 = Board.getGem(queue.get(i).targetx,queue.get(i).targety);

					Board.setGem(new Gem(temp2.getType(),queue.get(i).mainx,queue.get(i).mainy),queue.get(i).mainx,queue.get(i).mainy);
					Board.setGem(new Gem(temp1.getType(),queue.get(i).targetx,queue.get(i).targety),queue.get(i).targetx,queue.get(i).targety);
					temp1.setLock(false);
					temp2.setLock(false);
				}
				else
				{
					if(queue.get(i).type==0)
						doSwap(Board.getGem(queue.get(i).mainx,queue.get(i).mainy),Board.getGem(queue.get(i).targetx,queue.get(i).targety),elapsedTime,1);
					else
					{
						Board.getGem(queue.get(i).mainx,queue.get(i).mainy).setLock(false);
						Board.getGem(queue.get(i).targetx,queue.get(i).targety).setLock(false);
					}
				}
				queue.remove(i);
				i--;
			}
		}
	}
	
}
