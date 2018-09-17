package Screens;

import com.archaicinsurrection.ArchaicInsurrection;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainmenuScreen implements Screen {
    private static final String TAG= MainmenuScreen.class.getSimpleName() ;
    private SpriteBatch batch;
    private Texture img;
    private ArchaicInsurrection game;

    public MainmenuScreen(ArchaicInsurrection game, SpriteBatch batch) {
        this.batch = batch;
        this.game = game;
    }


    @Override
    public void show() {
        Gdx.app.log(TAG, "In SHOW method of MainmenuScreen class");
    }

    @Override
    public void render(float delta) {
        Gdx.app.log(TAG, "In RENDER method of MainmenuScreen class");
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
