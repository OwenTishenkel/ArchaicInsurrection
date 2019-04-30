package Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.utils.Array;

import Components.RenderableComponent;

public class RenderSystem extends IteratingSystem {
    private SpriteBatch batch;
    private Array<Entity> bodiesQueue;
    private OrthographicCamera camera;

    public RenderSystem(SpriteBatch batch,OrthographicCamera camera) {
        super(Family.all(RenderableComponent.class).get());
        this.batch=batch;
        this.camera=camera;

    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
