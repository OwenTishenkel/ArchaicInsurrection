package Managers;

import com.archaicinsurrection.ArchaicInsurrection;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import Components.BodyComponent;
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

    public EntityManager(ArchaicInsurrection archaicInsurrection, World world, SpriteBatch batch,PooledEngine engine) {
        this.archaicInsurrection=archaicInsurrection;
        this.world=world;
        this.batch=batch;
        this.engine=engine;
        generator= new BodyGenerator(world);
        tempPositionVector=Vector2.Zero;
        tempDimensionsVector= Vector2.Zero;

    }

    public Entity spawnEntity(String entityName, int x, int y) {

        Entity entity  = engine.createEntity();
        switch(entityName) {
            case "player":
            addBodyComponent(entity,entityName,x,y);
            addTypeComponent(entity,entityName);
            break;
        }



        engine.addEntity(entity);
        return entity;


    }

    private Entity addBodyComponent(Entity entity,String entityName,int x, int y) {
        tempPositionVector.x=x;
        tempPositionVector.y=y;
        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
        FixtureDef fdef = new FixtureDef();
    //method used to build the body
        switch(entityName) {
            case"player"://Creating Interactions and Body of Player entity
                fdef.filter.categoryBits= Figures.PLAYER;
                fdef.filter.maskBits=Figures.ENEMY |Figures.LEVEL;
                tempDimensionsVector.x=1;
                tempDimensionsVector.y=1;

                bodyComponent.setBody(generator.createBody(entity,tempPositionVector,tempDimensionsVector, BodyDef.BodyType.KinematicBody,1,fdef));
                bodyComponent.setActive(true);
                bodyComponent.getBody().setLinearDamping(3f);
                bodyComponent.getBody().setUserData(entity);//Shouldn't be needed due to entity in setBody but just in case
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
