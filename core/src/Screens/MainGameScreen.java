package Screens;

import com.archaicinsurrection.ArchaicInsurrection;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import Components.BodyComponent;
import Helpers.Figures;
import Helpers.GameInput;
import Managers.EntityManager;
import Systems.PhysicsDebugSystem;
import Systems.PhysicsSystem;
import Systems.PlayerControlSystem;


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
    private World world;


    //Controls
    private GameInput gameInput;


    //Ashley
    private PooledEngine engine;
    private PhysicsSystem physicsSystem;
    private PhysicsDebugSystem physicsDebugSystem;
    private PlayerControlSystem playerControlSystem;


    //Entity Manager
    private EntityManager entityManager;
    private Entity player;







    public MainGameScreen(ArchaicInsurrection game, SpriteBatch batch) {
        Gdx.app.log(TAG, "In Constructor of MainGameScreen class");

     //Setup
        this.batch = batch;
        this.game = game;
        camera =new OrthographicCamera();
        gameViewport = new FitViewport(Figures.VIRTUALWIDTH,Figures.VIRTUALHEIGHT, camera);
        camera.position.set(gameViewport.getWorldWidth()/2,gameViewport.getWorldHeight()/2,0);
        gameInput=new GameInput(gameViewport);
        engine=new PooledEngine(100,500,300,1000);
        world = new World(Figures.GRAVITATIONAL_FORCES,true);
        initAshleySystems();
        entityManager = new EntityManager(game,world,this.batch,engine);





    }



    public void initAshleySystems(){
        physicsSystem = new PhysicsSystem(world);
        physicsDebugSystem = new PhysicsDebugSystem(world,camera);
        playerControlSystem= new PlayerControlSystem(gameInput);
        engine.addSystem(physicsSystem);
        engine.addSystem(physicsDebugSystem);
        engine.addSystem(playerControlSystem);
    }

    public MainGameScreen() {
    }


    @Override
    public void show() {
        Gdx.app.log(TAG, "In show method of MainGameScreen class");
        Gdx.input
        player=entityManager.spawnEntity("player",8,5);
    }


    @Override
    public void render(float delta) {
        Gdx.app.log(TAG, "In Render method of MainGameScreen class");
        //camera.update();



        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        engine.update(delta);
      //  Gdx.app.log(TAG,player.getComponent(BodyComponent.class).getBody().getPosition().toString());
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
        world.dispose();



    }
}
//https://beepbox.co/#6n31s0kbl00e03t7m0a7g0fj7i0r1o3210T0w1f1d1c0h0v0T0w1f1d1c0h0v0T0w1f1d1c0h0v0T2w1d1v0b4h4h4h4h4h4h4h4h4h4h4h4h4h4h4h4h4h4h4h4h4h4p21uKo-sotjxRkUhvkU1nkTB5jmhRd8Rje5ldlnBmZlX5kKPladzAfijwple4008QNU8Q1F8INMq8waowdJddddddddddddd80