package proccesor;

import object.Board;
import object.Gem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class InputProc {
	
	static boolean gemFocused = false;
	static int gx,gy;
	
	public static boolean checkSwap(Vector2 mainGem,Vector2 targetGem)
	{
		if((targetGem.x<0 || targetGem.x>9) || (targetGem.y<0 || targetGem.y>9)) //대상이 범위 밖일 때
			return false;
		if( Board.getGem((int)targetGem.x,(int)targetGem.y).isLocked() ) // 대상이 잠겨 있으면
			return false;
		return true;
	}
	public static Vector2 ScreenToBoard(Vector2 sCoord)
	{
		double minx=Board.getScreenX()+5 ,miny=Board.getScreenY()+5;
		
		Vector2 res = new Vector2();
		
		res.x = (int)((sCoord.x-minx)/50);
		res.y = (int)((sCoord.y-miny)/50);
		if( (minx+res.x*50<=sCoord.x && sCoord.x<= minx+res.x*50+40  ) && (miny+res.y*50<=sCoord.y && sCoord.y<= miny+res.y*50+40  ) )
		{
			return res;
		}
		return new Vector2(-1,-1);
		
	}
	public static boolean CheckBoard(Vector2 sCoord)
	{
		if( (Board.getScreenX()<=sCoord.x && sCoord.x<=Board.getScreenX()+Board.getWidth() ) && 
				(Board.getScreenY()<=sCoord.y && sCoord.y<=Board.getScreenY()+Board.getWidth() )) {
			return true;
		}
		else
			return false;
	}
	public static void Update(float elapsedTime)
	{
		if(Gdx.input.isTouched())
		{
			Vector2 inputCoord = new Vector2(Gdx.input.getX(),Gdx.graphics.getHeight() - Gdx.input.getY());
			if(gemFocused == true)
			{
				if(Board.getGem((int)gx,(int)gy).isFocused()==true)
				{
					if(inputCoord.y< Board.getGem(gx,gy).getCenterY()-40) //위
					{
						Board.getGem((int)gx,(int)gy).setFocused(false);
						if(checkSwap(new Vector2(gx,gy),new Vector2(gx,gy-1)))
								SwapProc.doSwap(Board.getGem(gx,gy),Board.getGem(gx,gy-1),elapsedTime,0);
					}
					else if(inputCoord.x> Board.getGem(gx,gy).getCenterX()+40) //오른쪽
					{
						Board.getGem((int)gx,(int)gy).setFocused(false);
						if(checkSwap(new Vector2(gx,gy),new Vector2(gx+1,gy)))
							SwapProc.doSwap(Board.getGem(gx,gy),Board.getGem(gx+1,gy),elapsedTime,0);
					}
					else if(inputCoord.y> Board.getGem(gx,gy).getCenterY()+40) //아래
					{
						Board.getGem((int)gx,(int)gy).setFocused(false);
						if(checkSwap(new Vector2(gx,gy),new Vector2(gx,gy+1)))
							SwapProc.doSwap(Board.getGem(gx,gy),Board.getGem(gx,gy+1),elapsedTime,0);
					}
					else if(inputCoord.x< Board.getGem(gx,gy).getCenterX()-40) //왼쪽
					{
						Board.getGem((int)gx,(int)gy).setFocused(false);
						if(checkSwap(new Vector2(gx,gy),new Vector2(gx-1,gy)))
							SwapProc.doSwap(Board.getGem(gx,gy),Board.getGem(gx-1,gy),elapsedTime,0);
					}
				}
			}
			else
			{
				if(CheckBoard(inputCoord))
				{
					Vector2 res = ScreenToBoard(inputCoord);
					if(res.x>=0 && res.y >=0)
					{
						if(Board.getGem((int)res.x,(int)res.y).isLocked()==false)
						{
							gemFocused=true;
							Board.getGem((int)res.x,(int)res.y).setFocused(true);
							gx=(int)res.x;
							gy=(int)res.y;
						}
					}
				}
			}
		}
		else
		{
			if(gemFocused==true)
			{
				gemFocused = false;
				Board.getGem((int)gx,(int)gy).setFocused(false);
			}
		}
	}
}
