package Screens;

import com.archaicinsurrection.ArchaicInsurrection;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import Helpers.Figures;

public class MainGameScreen implements Screen {
    private static final String TAG = MainGameScreen.class.getSimpleName();
    private SpriteBatch batch;
    private Texture img;
    private ArchaicInsurrection game;
    //view
    private OrthographicCamera camera;
    private Viewport gameViewport;
    private Box2DDebugRenderer b2dr;


    //box2d

    public MainGameScreen(ArchaicInsurrection game, SpriteBatch batch) {
        this.batch = batch;
        this.game = game;
        camera =new OrthographicCamera();
        gameViewport = new FitViewport(Figures.VIRTUALWIDTH,Figures.VIRTUALHEIGHT, camera);
        camera.position.set(gameViewport.getWorldWidth()/2,gameViewport.getWorldHeight()/2,0);

    }

    public MainGameScreen() {
    }


    @Override
    public void show() {
        Gdx.app.log(TAG, "In show method of MainGameScreen class");
    }

    @Override
    public void render(float delta) {
        Gdx.app.log(TAG, "In Render method of MainGameScreen class");
        camera.update();



        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(TAG, "In RESIZE method of MainGameScreen class");

        gameViewport.update(width,height);

    }

    @Override
    public void pause() {
        Gdx.app.log(TAG, "In pause method of MainGameScreen class");
    }

    @Override
    public void resume() {
        Gdx.app.log(TAG, "In resume method of MainGameScreen class");
    }

    @Override
    public void hide() {
        Gdx.app.log(TAG, "In hide method of MainGameScreen class");
    }

    @Override
    public void dispose() {
        Gdx.app.log(TAG, "In dispose method of MainGameScreen class");
    }
}
