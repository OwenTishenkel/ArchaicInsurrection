package Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import Components.AttackComponent;
import Components.HealthComponent;
import Helpers.Mappers;

public class AttackSystem extends IteratingSystem{


    public AttackSystem() {
        super(Family.all(HealthComponent.class, AttackComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        //if an entity is within a certain distance from where the player is facing they are attackable and should be added to the list
        AttackComponent attackComponent = Mappers.attackComponent.get(entity);
    }
}
