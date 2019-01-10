package Screens;

import com.archaicinsurrection.ArchaicInsurrection;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import Components.BodyComponent;
import Helpers.Figures;
import Helpers.GameInput;
import Helpers.LevelCollisionGenerator;
import Managers.CollisionManager;
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
    private CollisionManager collisionManager;


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

    //Level Generator
    private LevelCollisionGenerator levelCollisionGenerator;
    private Entity ground;
    private OrthogonalTiledMapRenderer mapRenderer;
    private TiledMap map;

    //temp variables
    private Vector2 tempPosition;
    private Vector2 tempDimensions;






    public MainGameScreen(ArchaicInsurrection game, SpriteBatch batch) {
        Gdx.app.log(TAG, "In Constructor of MainGameScreen class");

     //Setup
        this.batch = batch;
        this.game = game;


        tempDimensions=new Vector2(Vector2.Zero);
        tempPosition=new Vector2(Vector2.Zero);





       // Gdx.app.log(TAG, "In Constructor of MainGameScreen class before camera");
        camera =new OrthographicCamera();
        gameViewport = new FitViewport(Figures.VIRTUALWIDTH,Figures.VIRTUALHEIGHT, camera);
        camera.position.set(gameViewport.getWorldWidth()/2,gameViewport.getWorldHeight()/2,0);
        //Gdx.app.log(TAG, "In Constructor of MainGameScreen class after camera is set");

        gameInput=new GameInput(gameViewport);
        engine=new PooledEngine(100,500,300,1000);
        world = new World(Figures.GRAVITATIONAL_FORCES,false);
       // Gdx.app.log(TAG, "In Constructor of MainGameScreen class after world, engine, and game input are created");
        collisionManager =new CollisionManager();
        world.setContactListener(collisionManager);
      //  Gdx.app.log(TAG, "In Constructor of MainGameScreen class after collision manager setup");
        
        initAshleySystems();
       // Gdx.app.log(TAG, "In Constructor of MainGameScreen class after ashley systems set up");
        entityManager = new EntityManager(game,world,this.batch,engine);
       // Gdx.app.log(TAG, "In Constructor of MainGameScreen class after entity manager");
        levelCollisionGenerator=new LevelCollisionGenerator(world,engine);
      //  Gdx.app.log(TAG, "In Constructor of MainGameScreen class after level collision generator");


        // todo need to change how map is loaded when implementing asset management
        //todo Fix this broken piece of shit
        map = new TmxMapLoader().load("TestMap.tmx");
       // Gdx.app.log(TAG, "In Constructor of MainGameScreen class after map loading");
        mapRenderer = new OrthogonalTiledMapRenderer(map,1/16f,this.batch);
        Gdx.app.log(TAG, "In Constructor of MainGameScreen class after map renderer");
        levelCollisionGenerator.createCollisionLevel(map);






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
        Gdx.input.setInputProcessor(gameInput);

        player=entityManager.spawnEntity("player",6,5);
        //temp test of level collisions
        tempPosition.x= 0;
            tempPosition.y = 1;
        tempDimensions.x = gameViewport.getWorldWidth();
        tempDimensions.y= 1;
        
        Gdx.app.log(TAG," "+camera.viewportWidth);
        
       // ground=levelCollisionGenerator.createCollisionLevel(tempPosition,tempDimensions, BodyDef.BodyType.StaticBody,1);

    }


    @Override
    public void render(float delta) {
        //Gdx.app.log(TAG, "In Render method of MainGameScreen class");

        camera.position.set(player.getComponent(BodyComponent.class).getBody().getPosition().x,player.getComponent(BodyComponent.class).getBody().getPosition().y,0);
        camera.update();


        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


      /*  mapRenderer.getBatch().begin();
        mapRenderer.renderTileLayer((TiledMapTileLayer)map.getLayers().get("Tiled number from bottom"));*/
        //mapRenderer.setView(camera);
        mapRenderer.setView((OrthographicCamera)gameViewport.getCamera());
      mapRenderer.render();


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
