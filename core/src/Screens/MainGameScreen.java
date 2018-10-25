package Screens;

import com.archaicinsurrection.ArchaicInsurrection;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class MainGameScreen implements Screen {
    private static final String TAG = MainGameScreen.class.getSimpleName();
    private SpriteBatch batch;
    private Texture img;
    private ArchaicInsurrection game;
    //view
    private OrthographicCamera camera;
    
    private Box2DDebugRenderer b2dr;


    //box2d

    public MainGameScreen(ArchaicInsurrection game, SpriteBatch batch) {
        this.batch = batch;
        this.game = game;
    }

    public MainGameScreen() {
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
