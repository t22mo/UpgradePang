package object;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Gem {
		static final Texture[] tex = new Texture[10];
		int spriteNum;
		float timer;
		Rectangle rect;
		int bx,by; //판에서의 xy 좌표. 0,0~10,10
		int type;
		final static int length = 40; // 40픽셀 
		boolean lock; //모션중이거나 하면 해당 객체를 잠금 
		boolean focused;
		boolean anim;
		
		Vector2 destination; // 목표 좌표
		int dx,dy;
		float destTime; // 목표 시간1
		float startTime;
		Vector2 startloc;
		
		public Gem(int type,int bx,int by)
		{
			this.bx = bx;
			this.by = by;
			this.type = type;			
			rect = new Rectangle(Board.getScreenX()+5+bx*50,Board.getScreenY()+5+by*50,length,length);
		}
		public static void LoadTextures()
		{
			tex[0]  = new Texture(Gdx.files.internal("res/fireGem.png"));
			tex[1]  = new Texture(Gdx.files.internal("res/waterGem.png"));
		}
		public void Update(float elapsedTime)
		{
			//상태 업데이트 
			if(anim==true)
			{
			//	System.out.println("t="+elapsedTime+"d="+destTime+rect.toString());
				if(elapsedTime>destTime)
				{
					rect.x = destination.x;
					rect.y = destination.y;
					anim=false;
				}
				else
				{
					float t = (elapsedTime-startTime)/(destTime-startTime);
					rect.x = t*destination.x + (1-t)*startloc.x;
					rect.y = t*destination.y + (1-t)*startloc.y;					
				}
			}
		}
		
		public void SetBoardPos(int x,int y)
		{
			bx = x;
			by = y;
		}
		public void SetScreenPos(float x,float y)
		{
			rect.x = x;
			rect.y = y;
		}
		
		public void Draw(SpriteBatch spriteBatch)
		{
			//화면에 그림.
			
			if(focused==true)
				spriteBatch.draw(tex[type], rect.x-5, rect.y-5, rect.width+10, rect.height+10);
			else
				spriteBatch.draw(tex[type], rect.x, rect.y, rect.width, rect.height);
		}

		public static double getLength() {
			// TODO Auto-generated method stub
			return length;
		}

		public void setFocused(boolean b) {
			// TODO Auto-generated method stub
			focused=b;
		}
		public Vector2 GetScreenCoord()
		{
			return new Vector2(rect.x,rect.y);
		}
		public Vector2 GetBoardCoord()
		{
			return new Vector2(bx,by);
		}
		public float getCenterX() {
			return rect.x+rect.width/2;
		}
		public float getCenterY() {
			return rect.y+rect.height/2;
		}
		public boolean isLocked() {
			return lock;
		}

		public int getType() {
			// TODO Auto-generated method stub
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}

		public boolean isFocused() {
			// TODO Auto-generate'd method stub
			return focused;
		}
		public void setLock(boolean lock) {
			this.lock = lock;
		}
		public void setAnimationDestination(Vector2 dest, float startTime,float endTime,Vector2 st,int dx,int dy)
		{
			destination = dest;
			this.dx = dx;
			this.dy = dy;
			this.startTime = startTime;
			destTime = endTime;
			startloc = st;
			
		}
		public boolean isAnimation() {
			return anim;
		}
		public void setAnimation(boolean anim) {
			this.anim=anim;
		}
		public Gem Clone()
		{
			Gem res = new Gem(this.type,this.bx,this.by);
			res.setFocused(this.focused);
			res.setLock(this.lock);
			return res;
		}
	}
