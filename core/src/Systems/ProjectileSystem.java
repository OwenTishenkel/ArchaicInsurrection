package Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import Components.BodyComponent;
import Components.ProjectileComponent;
import Helpers.Mappers;

public class ProjectileSystem extends IteratingSystem {

    public ProjectileSystem() {
        super(Family.all(ProjectileComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BodyComponent body = Mappers.bodyComponent.get(entity);
        ProjectileComponent projectile = Mappers.projectileComponent.get(entity);

    }
}
