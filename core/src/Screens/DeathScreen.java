package Screens;

import com.archaicinsurrection.ArchaicInsurrection;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DeathScreen implements Screen {
    private static final String TAG= DeathScreen.class.getSimpleName() ;
    private SpriteBatch batch;
    private Texture img;
    private ArchaicInsurrection game;

    public DeathScreen(ArchaicInsurrection game, SpriteBatch batch) {
        this.batch = batch;
        this.game = game;
    }


    @Override
    public void show() {
        Gdx.app.log(TAG, "In SHOW method of DeathScreen class");
    }

    @Override
    public void render(float delta) {
        Gdx.app.log(TAG, "In RENDER method of DeathScreen class");
        Gdx.gl.glClearColor(1, 22, 100, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(TAG, "In RESIZE method of DeathScreen class");
    }

    @Override
    public void pause() {
        Gdx.app.log(TAG, "In PAUSE method of DeathScreen class");
    }

    @Override
    public void resume() {
        Gdx.app.log(TAG, "In RESUME method of DeathScreen class");
    }

    @Override
    public void hide() {
        Gdx.app.log(TAG, "In HIDE method of DeathScreen class");
    }

    @Override
    public void dispose() {
        Gdx.app.log(TAG, "In DISPOSE method of DeathScreen class");
    }
}

