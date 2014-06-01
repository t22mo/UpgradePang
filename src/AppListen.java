import object.Board;
import object.Gem;
import object.PlayerProperties;
import proccesor.InputProc;
import proccesor.SwapProc;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class AppListen implements ApplicationListener {

	float elapsedTime=0;
	enum GameState {Intro, Menu, InGame, Result};
	GameState gameState;
	Board board;
	PlayerProperties playerProperties;
	SpriteBatch spriteBatch;	
	
	public void Update()
	{
		board.Update(elapsedTime);
		InputProc.Update(elapsedTime);
		SwapProc.Update(elapsedTime);
	}
	public void Draw()
	{
		spriteBatch.begin();
		board.Draw(spriteBatch);
		spriteBatch.end();
	}
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		spriteBatch = new SpriteBatch();
		board = new Board();
		playerProperties = new PlayerProperties();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		board.Spawn();
		Gem.LoadTextures();
	}
	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		elapsedTime += Gdx.graphics.getDeltaTime();
		Update();
		Draw();
	}

	public Board getBoard()
	{
		return board;
	}
	//-----------------------------------사용 안함-------------------------------------------
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	
	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}
