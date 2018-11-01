package Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class PhysicsSystem extends IteratingSystem {

    private final float MAX_TIME_STEP = 1/30f; //Locking System to max of 30 FPS, bump it up for smoother movements
    private float accumulator = 0f;
    private World world;
    private Array<Entity> bodiesQueue;

    public PhysicsSystem(World world) {
        super();
        this.world = world;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}



