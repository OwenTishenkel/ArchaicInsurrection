package Systems;

import com.archaicinsurrection.ArchaicInsurrection;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;

import Components.BodyComponent;
import Components.CollisionComponent;
import Components.PlayerComponent;
import Components.StateComponent;
import Components.TypeComponent;
import Helpers.Figures;
import Helpers.Mappers;

public class CollisionSystem extends IteratingSystem {

    private PooledEngine engine;
    private World world;

    private ArchaicInsurrection game;

    public CollisionSystem( PooledEngine engine, World world, ArchaicInsurrection game) {
        super(Family.all(CollisionComponent.class).get());
        this.engine = engine;
        this.world = world;

        this.game = game;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CollisionComponent collision = Mappers.collisionComponent.get(entity);
        Entity collidedEntity = collision.getCollidedEntity();
        TypeComponent thisType = entity.getComponent(TypeComponent.class);
        PlayerComponent playerComponent = Mappers.playerComponent.get(entity);
        StateComponent state = Mappers.stateComponent.get(entity);
        BodyComponent body= Mappers.bodyComponent.get(entity);
        TypeComponent collidedType;

        String TAG;

        if(thisType.getType()== Figures.PLAYER) {
            TAG="PLAYER COLLISION";
            if(collidedEntity == null) {
                return;
            }

            collidedType = collidedEntity.getComponent(TypeComponent.class);
            if(collidedType==null) {
                return;
            }
            switch(collidedType.getType()) {

                case Figures.ENEMY :
                    Gdx.app.log(TAG,"ENEMY");


                    break;
                case Figures.LEVEL:
                    Gdx.app.log(TAG,"LEVEL");

                    break;

                case Figures.OTHER:
                    Gdx.app.log(TAG,"OTHER");


                    break;
                case Figures.REPAIRPACK:
                    Gdx.app.log(TAG,"REPAIRPACK");
                    break;
                case Figures.ENEMYATTACK:
                    Gdx.app.log(TAG,"ENEMYATTACK");
                    break;



            }
        }

        else if(thisType.getType()==Figures.ENEMY){
            TAG="ENEMY COLLISION";
            if(collidedEntity == null) {
                return;
            }
             collidedType = collidedEntity.getComponent(TypeComponent.class);
            if(collidedType==null) {
                return;
            }
            switch(collidedType.getType()) {

                case Figures.PLAYER :
                    Gdx.app.log(TAG,"PLAYER");


                    break;
                case Figures.ENEMY :
                    Gdx.app.log(TAG,"ENEMY");


                    break;
                case Figures.LEVEL:
                    Gdx.app.log(TAG,"LEVEL");

                    break;

                case Figures.OTHER:
                    Gdx.app.log(TAG,"OTHER");


                    break;

                        case Figures.PLAYERATTACK:
                            Gdx.app.log(TAG,"PLAYERATTACK");
                            break;



            }

        }
        collision.setCollidedEntity(null);

    }
}
