package Screens;

import com.archaicinsurrection.ArchaicInsurrection;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Managers.MyAssetManager;

public class LoadingScreen implements Screen {
    private SpriteBatch batch;
    private float timetoWait=2f;
   private Texture img;
   private ArchaicInsurrection game;
   private MyAssetManager myAssetManager;
    public LoadingScreen(ArchaicInsurrection game, SpriteBatch batch, MyAssetManager myAssetManager) {
        this.game = game;
        this.batch=batch;
        this.myAssetManager= myAssetManager;
        //img = new Texture(""); //It Can't seem to figure out what this is

    }

    private static final String TAG = LoadingScreen.class.getSimpleName();
    @Override
    public void show() {
        Gdx.app.log(TAG,"IN SHOW METHOD OF LoadingScreen CLASS");
        loadingMapAssets();

    }
//
    @Override
    public void render(float delta) {
        Gdx.app.log(TAG,"IN RENDER METHOD OF LoadingScreen CLASS");
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        /*batch.begin();
        batch.draw(img, 0, 0);
        batch.end();*/

timetoWait-=delta;
     //   Gdx.app.log(TAG,"Time to wait: "+timetoWait);
//Currently blocking for loading maps
        myAssetManager.updateAssetLoading();


if(timetoWait<=0 && myAssetManager.isAssetLoaded("TestMap.tmx")) {
    Gdx.app.log(TAG,"IN timetoWait if statement OF RENDER method in LoadingScreen class");
    game.setScreen(ArchaicInsurrection.SCREENTYPE.MENU);
    timetoWait=2;
}

    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(TAG,"IN RESIZE METHOD OF LoadingScreen CLASS");

    }

    @Override
    public void pause() {
        Gdx.app.log(TAG,"IN PAUSE METHOD OF LoadingScreen CLASS");

    }

    @Override
    public void resume() {
        Gdx.app.log(TAG,"IN RESUME METHOD OF LoadingScreen CLASS");

    }

    @Override
    public void hide() {
        Gdx.app.log(TAG,"IN HIDE METHOD OF LoadingScreen CLASS");

    }

    @Override
    public void dispose() {
        Gdx.app.log(TAG,"IN DISPOSE METHOD OF LoadingScreen CLASS");
        img.dispose();

    }
    private void loadingMapAssets(){
        myAssetManager.loadMapAsset("TestMap.tmx"); //Dosen't work debug it
        Gdx.app.log(TAG,"Loading:"+myAssetManager.loadCompleted());



    }
}
