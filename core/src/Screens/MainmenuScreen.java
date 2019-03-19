package Screens;

import com.archaicinsurrection.ArchaicInsurrection;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Managers.MyAssetManager;

public class MainmenuScreen implements Screen {
    private static final String TAG= MainmenuScreen.class.getSimpleName() ;
    private SpriteBatch batch;
    private Texture img;
    private ArchaicInsurrection game;
    private float timetoWait=2f;
    private MyAssetManager myAssetManager;

    public MainmenuScreen(ArchaicInsurrection game, SpriteBatch batch, MyAssetManager myAssetManager) {
        this.batch = batch;
        this.game = game;
        this.myAssetManager=myAssetManager;
    }


    @Override
    public void show() {
        Gdx.app.log(TAG, "In SHOW method of MainmenuScreen class");
        myAssetManager.loadMapAsset("TestMap.tmx");
    }

    @Override
    public void render(float delta) {
       // Gdx.app.log(TAG, "In RENDER method of MainmenuScreen class");
        Gdx.gl.glClearColor(5, 0, 90, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        timetoWait-=delta;
        myAssetManager.updateAssetLoading();
       // Gdx.app.log(TAG,"Time to wait: "+timetoWait);
        if(timetoWait<=0 &&myAssetManager.isAssetLoaded("TestMap.tmx")) {
            Gdx.app.log(TAG, "IN timetoWait if statement OF RENDER method in MainmenuScreen class");
            Gdx.app.log(TAG, "Jumping to MainGameScreen");
            game.setScreen(ArchaicInsurrection.SCREENTYPE.GAME);
            timetoWait = 2;
        }
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(TAG, "In RESIZE method of MainmenuScreen class");
    }

    @Override
    public void pause() {
        Gdx.app.log(TAG, "In PAUSE method of MainmenuScreen class");
    }

    @Override
    public void resume() {
        Gdx.app.log(TAG, "In RESUME method of MainmenuScreen class");
    }

    @Override
    public void hide() {
        Gdx.app.log(TAG, "In HIDE method of MainmenuScreen class");
    }

    @Override
    public void dispose() {
        Gdx.app.log(TAG, "In DISPOSE method of MainmenuScreen class");
    }
}
