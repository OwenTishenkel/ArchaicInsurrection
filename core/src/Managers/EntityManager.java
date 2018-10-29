package Managers;

import com.archaicinsurrection.ArchaicInsurrection;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import Components.BodyComponent;
import Helpers.BodyGenerator;
import Helpers.Figures;

public class EntityManager {

    private ArchaicInsurrection archaicInsurrection;
    private World world;
    private SpriteBatch batch;
    private PooledEngine engine;
    private BodyGenerator generator;

    public EntityManager(ArchaicInsurrection archaicInsurrection, World world, SpriteBatch batch,PooledEngine engine) {
        this.archaicInsurrection=archaicInsurrection;
        this.world=world;
        this.batch=batch;
        this.engine=engine;
        generator= new BodyGenerator(world);

    }

    public Entity spawnEntity(String entityName, int x, int y) {

        Entity entity  = engine.createEntity();


    }

    private Entity addBodyComponent(Entity entity,String entityName,int x, int y) {
        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
        FixtureDef fdef = new FixtureDef();
    //method used to build the body
        switch(entityName) {
            case"player":
                fdef.filter.categoryBits= Figures.PLAYER;

            case"tier1":

            case"mini1":

            case"boss":


        }








        entity.add(bodyComponent);
    }

}
