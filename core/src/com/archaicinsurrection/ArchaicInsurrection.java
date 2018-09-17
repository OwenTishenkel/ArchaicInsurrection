package com.archaicinsurrection;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Hashtable;

import Screens.EndGameScreen;
import Screens.LoadingScreen;
import Screens.MainGameScreen;
import Screens.MainmenuScreen;

public class ArchaicInsurrection extends Game {
    private static final String TAG = ArchaicInsurrection.class.getSimpleName();
    SpriteBatch batch;
    Texture img;
    private LoadingScreen loadingscreen;
    private MainmenuScreen mainmenuscreen;
    private MainGameScreen maingamescreen;
    private EndGameScreen endgamescreen;

    private Hashtable<SCREENTYPE,Screen> screenTable;

    public enum SCREENTYPE {
        LOAD, MENU, END, GAME
    }
    public void createScreen(SCREENTYPE type) {
        Screen screen =null;
        switch(type) {
            case LOAD:
                if(loadingscreen==null) {
                    loadingscreen = new LoadingScreen(this,batch);
                    screenTable.put(SCREENTYPE.LOAD, loadingscreen);
                }
                break;

            case MENU:
                if(mainmenuscreen==null) {
                    mainmenuscreen = new MainmenuScreen(this, batch);
                    screenTable.put(SCREENTYPE.MENU, mainmenuscreen);
                }
                break;


            case GAME:
                if(maingamescreen==null) {
                    maingamescreen = new MainGameScreen(this, batch);
                    screenTable.put(SCREENTYPE.GAME, maingamescreen);
                }
                break;


            case END:
                if(endgamescreen==null) {
                    endgamescreen = new EndGameScreen(this, batch);
                    screenTable.put(SCREENTYPE.END, endgamescreen);
                }
                break;


        }
    }
    public void setScreen(SCREENTYPE type) {
        createScreen(type);
        setScreen(screenTable.get(type));
    }





	@Override
	public void create () {
        screenTable = new Hashtable<SCREENTYPE, Screen>();
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		setScreen(SCREENTYPE.LOAD);
		Gdx.app.log(TAG,"in Create Method of main Game class ");

	}

	/*@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}*/
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
