package Helpers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
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
    private TiledMap map;
    private static final String COLLISION_LAYER = "COLLISION_LAYER";

    public LevelCollisionGenerator(World world, PooledEngine engine) {
        this.world = world;
        this.engine = engine;
    }
    public Entity createCollisionLevel(TiledMap map) {
        this.map=map;
        MapLayer layer = map.getLayers().get(COLLISION_LAYER);

        for(MapObject object : layer.getObjects()) {
            LevelGeometry geometry = null;
            if(object instanceof TextureMapObject) {
                continue;
            }
            Shape shape;
            BodyDef bdef = new BodyDef();
            String type=object.getProperties().get("Type",String.class);

            switch(type) {
                case "StaticBody":
                    bdef.type = BodyDef.BodyType.StaticBody;
                    break;
                case "DynamicBody":
                    bdef.type = BodyDef.BodyType.DynamicBody;
                    break;
                case "KinematicBody":
                    bdef.type = BodyDef.BodyType.KinematicBody;
                    break;
            }
            if(object instanceof RectangleMapObject){
                geometry =
            }

            //bdef.type = object.getProperties().get("Type",String.class);

        }










        Body body;
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        Entity levelEntity = engine.createEntity();




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

    private LevelGeometry getRectangle(RectangleMapObject rectangleMapObject){
        Rectangle rectangle =rectangleMapObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2(rectangle.x+rectangle.width/2,rectangle.y+rectangle.height/2);
        polygon.setAsBox(rectangle.width/2,rectangle.height/2,size,0.0f);

        return new LevelGeometry(polygon);

        /*shape = new PolygonShape();
        ((PolygonShape)shape).setAsBox(dimensions.x,dimensions.y);
        bdef.position.set(position.x+dimensions.x/2, position.y+dimensions.y/2);*/
    }
    private LevelGeometry getPolygon(PolygonMapObject polygonMapObject){
        PolygonShape polygon =polygonMapObject.getPolygon();
       float[]vertices = polygonMapObject.getPolygon().getTransformedVertices();
       //todo fix any errors with polygon shape if there are any
        polygon.set(vertices);

        return new LevelGeometry(polygon);

        /*shape = new PolygonShape();
        ((PolygonShape)shape).setAsBox(dimensions.x,dimensions.y);
        bdef.position.set(position.x+dimensions.x/2, position.y+dimensions.y/2);*/
    }
    private LevelGeometry getPolyline(PolylineMapObject polylineMapObject){
     float[]vertices = polylineMapObject.getPolyline().getTransformedVertices();
     Vector2[] worldVertices = new Vector2[vertices.length/2];
     for(int i=0;i<vertices.length; i++) {
         worldVertices[i] = new Vector2();
         worldVertices[i].x = vertices[i * 2];
         worldVertices[i].y = vertices[(i*2)+1];
     }
        ChainShape chainShape = new ChainShape();
        chainShape.createChain(worldVertices);
        return new LevelGeometry(chainShape);

        /*shape = new PolygonShape();
        ((PolygonShape)shape).setAsBox(dimensions.x,dimensions.y);
        bdef.position.set(position.x+dimensions.x/2, position.y+dimensions.y/2);*/
    }
    private LevelGeometry getCircle(CircleMapObject circleMapObject){
        Circle circle =circleMapObject.getCircle();
        CircleShape circleShape =new CircleShape();
        circleShape.setRadius(circle.radius);
        circleShape.setPosition(circle.x,circle.y);
        Vector2 size = new Vector2(rectangle.x+rectangle.width/2,rectangle.y+rectangle.height/2);
        polygon.setAsBox(rectangle.width/2,rectangle.height/2,size,0.0f);

        return new LevelGeometry(polygon);

        /*shape = new PolygonShape();
        ((PolygonShape)shape).setAsBox(dimensions.x,dimensions.y);
        bdef.position.set(position.x+dimensions.x/2, position.y+dimensions.y/2);*/
    }




public static class LevelGeometry {
        private Shape shape;

    public LevelGeometry(Shape shape) {
        this.shape = shape;
    }

    public Shape getShape() {
        return shape;
    }
}

}
