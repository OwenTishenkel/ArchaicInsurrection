package Managers;

import com.archaicinsurrection.ArchaicInsurrection;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import Helpers.BodyGenerator;

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

    private Entity addBodyComponent() {
        
    }

}
