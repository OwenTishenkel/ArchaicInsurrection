package Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;

public class TransformComponent implements Component, Pool.Poolable {//Holds Position, Dimensions, Rotation etc.

private Vector3 position=new Vector3();
private Vector2 scale = new Vector2(2f,2f);
private float rotation= 0.0f;
private Boolean isHidden= false;

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }
    public void setPosition(Vector2 position) {
        this.position.x=position.x;
        this.position.y=position.y;
    }

    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Boolean getHidden() {
        return isHidden;
    }

    public void setHidden(Boolean hidden) {
        isHidden = hidden;
    }

    @Override
    public void reset() {
        position=Vector3.Zero;
        scale.set(1f,1f);
        rotation=0.0f;
        isHidden=false;

    }
}
