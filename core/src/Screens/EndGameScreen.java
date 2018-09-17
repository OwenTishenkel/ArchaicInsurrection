package Screens;

import com.archaicinsurrection.ArchaicInsurrection;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EndGameScreen implements Screen {
    private static final String TAG = EndGameScreen.class.getSimpleName();
    private SpriteBatch batch;
    private Texture img;
    private ArchaicInsurrection game;
    public EndGameScreen(ArchaicInsurrection game, SpriteBatch batch) {
        this.batch = batch;
        this.game = game;
    }

    public EndGameScreen() {
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
