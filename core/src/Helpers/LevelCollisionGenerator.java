package Helpers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import Components.BodyComponent;
import Components.TransformComponent;
import Components.TypeComponent;

public class LevelCollisionGenerator {

    public static final String TAG =LevelCollisionGenerator.class.getSimpleName();
    private World world;
    private PooledEngine engine;

    public LevelCollisionGenerator(World world, PooledEngine engine) {
        this.world = world;
        this.engine = engine;
    }
    public Entity createCollisionLevel(Vector2 position, Vector2 dimensions, BodyDef.BodyType type, int bodyType) {
        Body body;
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        Entity levelEntity = engine.createEntity();


            switch(type) {
                case StaticBody:
                    bdef.type = BodyDef.BodyType.StaticBody;
                    break;
                case DynamicBody:
                    bdef.type = BodyDef.BodyType.DynamicBody;
                    break;
                case KinematicBody:
                    bdef.type = BodyDef.BodyType.KinematicBody;
                    break;
            }

            Gdx.app.log("Body Generator:",bdef.position.toString());
            bdef.gravityScale = 1;


            Shape shape=new CircleShape();

            switch(bodyType) {
                case 0:
                default:
                    shape = new CircleShape();
                    shape.setRadius(dimensions.x/2);
                    bdef.position.set(position.x+dimensions.x/2, position.y+dimensions.y/2);
                    break;
                case 1:
                    shape = new PolygonShape();
                    ((PolygonShape)shape).setAsBox(dimensions.x,dimensions.y);
                    bdef.position.set(position.x+dimensions.x/2, position.y+dimensions.y/2);
                    break;

            }

            body = world.createBody(bdef);

            //fdef needs to collide with all dynamic entities
            fdef.filter.categoryBits=Figures.LEVEL;
            fdef.filter.maskBits=Figures.PLAYER |Figures.ENEMY;




            fdef.shape = shape;
            fdef.density = 1f;
            fdef.restitution = 0.5f;
            fdef.friction= 0.1f;

            fdef.isSensor = false;

            body.createFixture(fdef).setUserData(levelEntity);

            //Add components to level entity.

            BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
            bodyComponent.setBody(body);
            TypeComponent typeComponent = engine.createComponent(TypeComponent.class);
            typeComponent.setType(Figures.LEVEL);

            levelEntity.add(bodyComponent);
            levelEntity.add(typeComponent);




            shape.dispose();


            engine.addEntity(levelEntity);
        return levelEntity;
    }
}
