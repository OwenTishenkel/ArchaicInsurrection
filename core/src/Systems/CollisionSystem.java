package Systems;

import com.archaicinsurrection.ArchaicInsurrection;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;

import Components.CollisionComponent;
import Components.TypeComponent;
import Helpers.Mappers;

public class CollisionSystem extends IteratingSystem {

    private PooledEngine engine;
    private World world;
    private Entity player;
    private ArchaicInsurrection game;

    public CollisionSystem( PooledEngine engine, World world, Entity player, ArchaicInsurrection game) {
        super(Family.all(CollisionComponent.class).get());
        this.engine = engine;
        this.world = world;
        this.player = player;
        this.game = game;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CollisionComponent collision = Mappers.collisionComponent.get(entity);
        Entity collidedEntity = collision.getCollidedEntity();
        TypeComponent thisType = entity.getComponent(TypeComponent.class);

    }
}
