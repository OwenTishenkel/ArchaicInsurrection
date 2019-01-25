package Managers;

import com.archaicinsurrection.ArchaicInsurrection;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

import Components.AttackComponent;
import Components.BodyComponent;
import Components.CollisionComponent;
import Components.HealthComponent;
import Components.PlayerComponent;
import Components.StateComponent;
import Components.TypeComponent;
import Helpers.BodyGenerator;
import Helpers.Figures;

public class EntityManager {

    private ArchaicInsurrection archaicInsurrection;
    private World world;
    private SpriteBatch batch;
    private PooledEngine engine;
    private BodyGenerator generator;
    private Vector2 tempPositionVector;
    private Vector2 tempDimensionsVector;
    private String TAG=EntityManager.class.getSimpleName();
    private ArrayList<Entity> entities;

    public EntityManager(ArchaicInsurrection archaicInsurrection, World world, SpriteBatch batch,PooledEngine engine) {
        this.archaicInsurrection=archaicInsurrection;
        this.world=world;
        this.batch=batch;
        this.engine=engine;
        generator= new BodyGenerator(world);
        tempPositionVector= new Vector2(Vector2.Zero);
        tempDimensionsVector= new Vector2(Vector2.Zero);
        entities = new ArrayList<Entity>();

    }
    public float playerLocationX(String entityName,TiledMap map){
        MapLayer layer= map.getLayers().get("MAP_SPAWN_LAYER");
        float x=0;
        for(MapObject object:layer.getObjects()) {
        if(object.getProperties().get("Spawn",String.class)==entityName) {
             x = object.getProperties().get("x",Float.class);

        }

        }
        return x;

    }
    public float playerLocationY(String entityName,TiledMap map){
        MapLayer layer= map.getLayers().get("MAP_SPAWN_LAYER");
        float y=0;
        for(MapObject object:layer.getObjects()) {
            if(object.getProperties().get("Spawn",String.class)==entityName) {
                y = object.getProperties().get("y",Float.class);

            }

        }
        return y;

    }
    public void spawnEntities(TiledMap map){

        MapLayer layer= map.getLayers().get("MAP_SPAWN_LAYER");
        for(MapObject object:layer.getObjects()) {
           String entityName= object.getProperties().get("Spawn",String.class);

           int x = object.getProperties().get("x",Float.class).intValue();
            int y = object.getProperties().get("y",Float.class).intValue();
            entities.add(spawnEntity(entityName,x/Figures.PPM,y/Figures.PPM));

        }


    }
    public ArrayList<Entity> getEntities(){
        return entities;
    }


    public Entity spawnEntity(String entityName, int x, int y) {

        Entity entity  = engine.createEntity();
        switch(entityName) {
            case "player":
            addBodyComponent(entity,entityName,x,y);
            addTypeComponent(entity,entityName);
            addCollisionComponent(entity);
            addStateComponent(entity,entityName);
            addPlayerComponent(entity);
            addHealthComponent(entity);
            addAttackComponent(entity);


            break;
            case "testenemy":



                break;
        }



        engine.addEntity(entity);
        return entity;


    }

    private Entity addStateComponent(Entity entity,String entityName) {
        StateComponent stateComponent = engine.createComponent(StateComponent.class);
        switch(entityName) {
            case "player":
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
        CollisionComponent collisionComponent =engine.createComponent(CollisionComponent.class);
        entity.add(collisionComponent);


        return entity;
    }
    private Entity addAttackComponent(Entity entity) {
        AttackComponent attackComponent =engine.createComponent(AttackComponent.class);
        entity.add(attackComponent);


        return entity;
    }

    private Entity addBodyComponent(Entity entity,String entityName,int x, int y) {
        tempPositionVector.x=x;
        tempPositionVector.y=y;
      //  Gdx.app.log("Entity Manager: ",tempPositionVector.toString());
        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
        FixtureDef fdef = new FixtureDef();
    //method used to build the body
        switch(entityName) {
            case "player"://Creating Interactions and Body of Player entity
                fdef.filter.categoryBits= Figures.PLAYER;
                fdef.filter.maskBits= Figures.LEVEL;//|Figures.ENEMY);
                Gdx.app.log(TAG, "After Player mask and category bits");

                tempDimensionsVector.x=1;
                tempDimensionsVector.y=1;



                bodyComponent.setBody(generator.createBody(entity,tempPositionVector,
                        tempDimensionsVector, BodyDef.BodyType.DynamicBody,1,fdef));
               // Gdx.app.log("Entity Manager after body is set: ",tempPositionVector.toString()+" Temp Position Vector: "+tempDimensionsVector.toString());
                bodyComponent.setActive(true);
                bodyComponent.getBody().setLinearDamping(3f);
                bodyComponent.getBody().setUserData(entity);//Shouldn't be needed due to entity in setBody but just in case
               Gdx.app.log(TAG, "In Entity Manager User data set is"+bodyComponent.getBody().getUserData()+" and "+fdef.filter.categoryBits+" "+fdef.filter.maskBits);


                break;

            case"tier1":

            case"mini1":

            case"boss":


        }






        entity.add(bodyComponent);
        return entity;
    }
    private Entity addTypeComponent(Entity entity, String entityName) {
        TypeComponent typeComponent= engine.createComponent(TypeComponent.class);
        short type;
        switch(entityName) {
            case "player":
                type=Figures.PLAYER;
            default:
                type=Figures.OTHER;
        }
        typeComponent.setType(type);
        entity.add(typeComponent);
        return entity;
    }

}
