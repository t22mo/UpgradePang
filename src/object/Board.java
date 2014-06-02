package object;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Board {
	static Texture tex;
	int spriteNum;
	float timer;
	static Rectangle rect;
	static Gem[][] board;
	
	public Board()
	{
		tex = new Texture(Gdx.files.internal("res/board.png"));
		board = new Gem[10][10];
		rect = new Rectangle(5,30,500,500);
	}
	public void Initialize()
	{
		for(int i=0 ; i<10 ; i++)
		{
			for(int j=0 ; j<10 ; j++)
			{
				board[i][j] = new Gem( Math.abs(new Random().nextInt())%2,i,j);
			}
		}
	}
	public void Spawn()
	{
		Initialize();		//화면에 생성
	}
	public void Update(float elapsedTime)
	{
		//상태 업데이트 
		for(int i=0 ; i<10 ; i++)
		{
			for(int j=0 ; j<10 ; j++)
			{
				board[i][j].Update(elapsedTime);
			}
		}
	}
	public void SetScreenCoord(int x,int y)
	{
		rect.x=x;
		rect.y=y;
	}
	
	public void Draw(SpriteBatch spriteBatch)
	{
		//화면에 그림.
		spriteBatch.draw(tex, rect.x, rect.y, rect.width, rect.height);
		for(int i=0 ; i<10 ; i++)
		{
			for(int j=0 ; j<10 ; j++)
			{
				board[i][j].Draw(spriteBatch);
			}
		}
	}

	public static float getWidth() {
		return rect.width;
	}
	public static float getHeight() {
		return rect.height;
	}
	public static float getScreenX() {
		// TODO Auto-generated method stub
		return rect.x;
	}
	public static float getScreenY() {
		// TODO Auto-generated method stub
		return rect.y;
	}
	public static Gem getGem(int x,int y)
	{
		return board[x][y];
	}
	public static void setGem(Gem gem,int x,int y)
	{
		board[x][y] = gem;
	}
	public static void swap(Vector2 main,Vector2 target)
	{
		Gem temp = board[ (int)main.x ][ (int)main.y ];
		board[ (int)main.x ][ (int)main.y ] = board[ (int)target.x ][ (int)target.y ];
		board[ (int)target.x ][ (int)target.y ] = temp;
		
		board[ (int)main.x ][ (int)main.y ].bx = (int)main.x;
		board[ (int)main.x ][ (int)main.y ].by = (int)main.y;
		board[ (int)target.x ][ (int)target.y ].bx = (int)target.x;
		board[ (int)target.x ][ (int)target.y ].by = (int)target.y;
	}
	
}