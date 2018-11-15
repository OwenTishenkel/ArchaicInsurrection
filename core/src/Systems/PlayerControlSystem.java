package Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import Components.PlayerComponent;
import Helpers.GameInput;

public class PlayerControlSystem extends IteratingSystem {
    private GameInput gameInput;



    public PlayerControlSystem(GameInput gameInput) {
        super(Family.all(PlayerComponent.class).get());
        this.gameInput=gameInput;
    }



    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
//This dohicky is how the player moves their character