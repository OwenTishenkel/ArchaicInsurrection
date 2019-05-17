package Managers;

import com.archaicinsurrection.ArchaicInsurrection;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

import Components.AnimationComponent;
import Components.AttackComponent;
import Components.BodyComponent;
import Components.CollisionComponent;
import Components.HealthComponent;
import Components.PlayerComponent;
import Components.RenderableComponent;
import Components.StateComponent;
import Components.TextureComponent;
import Components.TransformComponent;
import Components.TypeComponent;
import Helpers.BodyGenerator;
import Helpers.Figures;

public class EntityManager {

    private ArchaicInsurrection archaicInsurrection;
    private World world;
    private SpriteBatch batch;
    private MyAssetManager myAssetManager;
    private TextureAtlas atlas;
    private PooledEngine engine;
    private BodyGenerator generator;
    private Vector2 tempPositionVector;
    private Vector2 tempDimensionsVector;
    private String TAG = EntityManager.class.getSimpleName();
    private ArrayList<Entity> entities;

    public EntityManager(ArchaicInsurrection archaicInsurrection, World world, SpriteBatch batch, PooledEngine engine, MyAssetManager myAssetManager) {
        this.archaicInsurrection = archaicInsurrection;
        this.world = world;
        this.batch = batch;
        this.engine = engine;
        generator = new BodyGenerator(world);
        tempPositionVector = new Vector2(Vector2.Zero);
        tempDimensionsVector = new Vector2(Vector2.Zero);
        entities = new ArrayList<Entity>();
        this.myAssetManager = myAssetManager;
        atlas = myAssetManager.getTextureAsset("Sprites/Output/ArchaicInsurrectionAtlas.atlas");//!Check Here if atlas can't be found!

    }

    public float playerLocationX(String entityName, TiledMap map) {
        MapLayer layer = map.getLayers().get("MAP_SPAWN_LAYER");
        float x = 0;
        for (MapObject object : layer.getObjects()) {
            if (object.getProperties().get("Spawn", String.class) == entityName) {
                x = object.getProperties().get("x", Float.class);

            }

        }
        return x;

    }

    public float playerLocationY(String entityName, TiledMap map) {
        MapLayer layer = map.getLayers().get("MAP_SPAWN_LAYER");
        float y = 0;
        for (MapObject object : layer.getObjects()) {
            if (object.getProperties().get("Spawn", String.class) == entityName) {
                y = object.getProperties().get("y", Float.class);

            }

        }
        return y;

    }

    public void spawnEntities(TiledMap map) {

        MapLayer layer = map.getLayers().get("Object Layer 2");
        for (MapObject object : layer.getObjects()) {
            String entityName = object.getProperties().get("Spawn", String.class);

            int x = object.getProperties().get("x", Float.class).intValue();
            int y = object.getProperties().get("y", Float.class).intValue();
            entities.add(spawnEntity(entityName, x / Figures.PPM, y / Figures.PPM));

        }


    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }


    public Entity spawnEntity(String entityName, int x, int y) {

        Entity entity = engine.createEntity();
        Gdx.app.log(EntityManager.class.getSimpleName(), "Created: " + entityName);
        switch (entityName) {
            case "player":
                addBodyComponent(entity, entityName, x, y);
                addTransformComponent(entity, x, y);
                addTypeComponent(entity, entityName);
                addCollisionComponent(entity);
                addStateComponent(entity, entityName);
                addPlayerComponent(entity);
                addHealthComponent(entity);
                addAttackComponent(entity);
                addAnimationComponent(entity, entityName);
                addRenderableComponent(entity);
                addTextureComponent(entity, entityName);


                break;
            case "tier1":
                addBodyComponent(entity, entityName, x, y);
                addTransformComponent(entity, x, y);
                addTypeComponent(entity, entityName);
                addCollisionComponent(entity);
                addStateComponent(entity, entityName);
                addHealthComponent(entity);
                addAttackComponent(entity);
               addAnimationComponent(entity, entityName);
                 addTextureComponent(entity,entityName);
                 addRenderableComponent(entity);


                break;
            case "repairPack":
                addBodyComponent(entity, entityName, x, y);
                addTransformComponent(entity, x, y);
                addTypeComponent(entity, entityName);
                addCollisionComponent(entity);
                //addAnimationComponent(entity, entityName);
               // addRenderableComponent(entity);
              // addTextureComponent(entity,entityName);


                break;
        }


        engine.addEntity(entity);
        return entity;


    }

    private Entity addStateComponent(Entity entity, String entityName) {
        StateComponent stateComponent = engine.createComponent(StateComponent.class);
        switch (entityName) {
            case "player":
                stateComponent.setDirection(StateComponent.DIRECTION.DOWN);
                stateComponent.setState(StateComponent.STATE.IDLE);
                break;
            default:
                stateComponent.setDirection(StateComponent.DIRECTION.DOWN);
                stateComponent.setState(StateComponent.STATE.IDLE);
                break;


        }


        entity.add(stateComponent);
        return entity;
    }

    private Entity addPlayerComponent(Entity entity) {
        PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);
        entity.add(playerComponent);
        return entity;
    }

    private Entity addHealthComponent(Entity entity) {
        HealthComponent healthComponent = engine.createComponent(HealthComponent.class);
        entity.add(healthComponent);
        return entity;
    }


    private Entity addCollisionComponent(Entity entity) {
        CollisionComponent collisionComponent = engine.createComponent(CollisionComponent.class);
        entity.add(collisionComponent);


        return entity;
    }

    private Entity addAttackComponent(Entity entity) {
        AttackComponent attackComponent = engine.createComponent(AttackComponent.class);
        entity.add(attackComponent);


        return entity;
    }

    private Entity addBodyComponent(Entity entity, String entityName, int x, int y) {
        tempPositionVector.x = x;
        tempPositionVector.y = y;
        //  Gdx.app.log("Entity Manager: ",tempPositionVector.toString());
        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
        FixtureDef fdef = new FixtureDef();
        //method used to build the body
        switch (entityName) {
            case "player"://Creating Interactions and Body of Player entity
                fdef.filter.categoryBits = Figures.PLAYER;
                fdef.filter.maskBits = Figures.LEVEL | Figures.ENEMY | Figures.OTHER | Figures.REPAIRPACK | Figures.ENEMYATTACK;
                // Gdx.app.log(TAG, "After Player mask and category bits");

                tempDimensionsVector.x = 1;
                tempDimensionsVector.y = 1;


                bodyComponent.setBody(generator.createBody(entity, tempPositionVector,
                        tempDimensionsVector, BodyDef.BodyType.DynamicBody, 1, fdef));
                // Gdx.app.log("Entity Manager after body is set: ",tempPositionVector.toString()+" Temp Position Vector: "+tempDimensionsVector.toString());
                bodyComponent.setActive(true);
                bodyComponent.getBody().setLinearDamping(3f);
                bodyComponent.getBody().setUserData(entity);//Shouldn't be needed due to entity in setBody but just in case
                // Gdx.app.log(TAG, "In Entity Manager User data set is"+bodyComponent.getBody().getUserData()+" and "+fdef.filter.categoryBits+" "+fdef.filter.maskBits);


                break;

            case "tier1":
                fdef.filter.categoryBits = Figures.ENEMY;
                fdef.filter.maskBits = Figures.LEVEL | Figures.ENEMY | Figures.PLAYER | Figures.PLAYERATTACK;
                // Gdx.app.log(TAG, "After Player mask and category bits");

                tempDimensionsVector.x = 1;
                tempDimensionsVector.y = 1;


                bodyComponent.setBody(generator.createBody(entity, tempPositionVector,
                        tempDimensionsVector, BodyDef.BodyType.DynamicBody, 1, fdef));
                // Gdx.app.log("Entity Manager after body is set: ",tempPositionVector.toString()+" Temp Position Vector: "+tempDimensionsVector.toString());
                bodyComponent.setActive(true);
                bodyComponent.getBody().setLinearDamping(3f);
                bodyComponent.getBody().setUserData(entity);
                break;

            case "mini1":
                break;

            case "boss":
                break;
            case "repairPack":
                fdef.filter.categoryBits = Figures.REPAIRPACK;
                fdef.filter.maskBits = Figures.LEVEL | Figures.PLAYER;
                // Gdx.app.log(TAG, "After Player mask and category bits");

                tempDimensionsVector.x = 1;
                tempDimensionsVector.y = 1;


                bodyComponent.setBody(generator.createBody(entity, tempPositionVector,
                        tempDimensionsVector, BodyDef.BodyType.DynamicBody, 1, fdef));
                // Gdx.app.log("Entity Manager after body is set: ",tempPositionVector.toString()+" Temp Position Vector: "+tempDimensionsVector.toString());
                bodyComponent.setActive(true);
                bodyComponent.getBody().setLinearDamping(3f);
                bodyComponent.getBody().setUserData(entity);
                break;


        }


        entity.add(bodyComponent);
        return entity;
    }

    private Entity addTypeComponent(Entity entity, String entityName) {
        TypeComponent typeComponent = engine.createComponent(TypeComponent.class);
        short type;
        switch (entityName) {
            case "player":
                type = Figures.PLAYER;
                break;
            case "tier1":
                type = Figures.ENEMY;
                break;
            case "repairPack":
                type = Figures.REPAIRPACK;
            default:
                type = Figures.OTHER;
                break;
        }
        typeComponent.setType(type);
        entity.add(typeComponent);
        return entity;
    }

    private Entity addAnimationComponent(Entity entity, String entityName) {
        AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);

        switch (entityName) {
            case "player"://adding Animations to player
                animationComponent
                        .addAnimation(AnimationComponent.ANIMATIONSTATE.UP,
                                new Animation(0.25f, atlas.findRegions("Player_Back")))
                        .addAnimation(AnimationComponent.ANIMATIONSTATE.DOWN, new Animation(0.25f, atlas.findRegions("Player_Front")))
                        .addAnimation(AnimationComponent.ANIMATIONSTATE.LEFT, new Animation(0.25f, atlas.findRegions("Player_Left")))
                        .addAnimation(AnimationComponent.ANIMATIONSTATE.RIGHT, new Animation(0.25f, atlas.findRegions("Player_Right")));


                break;
            case "tier1":
                animationComponent
                        .addAnimation(AnimationComponent.ANIMATIONSTATE.UP,
                                new Animation(0.25f, atlas.findRegions("1hpEnemy_Back")))
                        .addAnimation(AnimationComponent.ANIMATIONSTATE.DOWN, new Animation(0.25f, atlas.findRegions("1hpEnemy_Front")))
                        .addAnimation(AnimationComponent.ANIMATIONSTATE.LEFT, new Animation(0.25f, atlas.findRegions("1hpEnemy_Left")))
                        .addAnimation(AnimationComponent.ANIMATIONSTATE.RIGHT, new Animation(0.25f, atlas.findRegions("1hpEnemy_Right")));

                break;
            case "mini1":
                break;
            case "boss":
                break;
            case "repairPack":
                animationComponent
                        .addAnimation(AnimationComponent.ANIMATIONSTATE.UP,
                                new Animation(0.25f, atlas.findRegions("RepairKit")))
                .addAnimation(AnimationComponent.ANIMATIONSTATE.DOWN,
                    new Animation(0.25f, atlas.findRegions("RepairKit")))
                .addAnimation(AnimationComponent.ANIMATIONSTATE.RIGHT,
                    new Animation(0.25f, atlas.findRegions("RepairKit")))
                .addAnimation(AnimationComponent.ANIMATIONSTATE.LEFT,
                    new Animation(0.25f, atlas.findRegions("RepairKit")));

                break;
        }
        entity.add(animationComponent);
        return entity;


    }

    private Entity addTextureComponent(Entity entity, String entityName) {
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        switch (entityName) {
            case "player":
                textureComponent.setRegion((TextureRegion) entity
                        .getComponent(AnimationComponent.class)
                        .getAnimation(AnimationComponent.ANIMATIONSTATE.DOWN).getKeyFrames()[0]);



                break;
            case "tier1":
                textureComponent.setRegion((TextureRegion) entity
                        .getComponent(AnimationComponent.class)
                        .getAnimation(AnimationComponent.ANIMATIONSTATE.DOWN).getKeyFrames()[0]);
                break;
            case "repairPack":
                textureComponent.setRegion((TextureRegion) entity
                        .getComponent(AnimationComponent.class)
                        .getAnimation(AnimationComponent.ANIMATIONSTATE.DOWN).getKeyFrames()[0]);

                break;
        }
        entity.add(textureComponent);
        return entity;
    }

    private Entity addRenderableComponent(Entity entity) {
        RenderableComponent renderableComponent = engine.createComponent(RenderableComponent.class);
        entity.add(renderableComponent);
        return entity;

    }

    private Entity addTransformComponent(Entity entity, int x, int y) {
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        tempPositionVector.set(x, y);
        transformComponent.setPosition(new Vector2(0, 0));

        entity.add(transformComponent);
        return entity;
    }

}
