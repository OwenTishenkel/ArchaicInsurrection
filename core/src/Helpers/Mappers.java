package Helpers;

import com.badlogic.ashley.core.ComponentMapper;

import Components.AnimationComponent;
import Components.AttackComponent;
import Components.BodyComponent;
import Components.CollisionComponent;
import Components.HealthComponent;
import Components.PlayerComponent;
import Components.ProjectileComponent;
import Components.StateComponent;
import Components.TextureComponent;
import Components.TransformComponent;
import Components.TypeComponent;

public class Mappers {
public static final ComponentMapper<BodyComponent> bodyComponent = ComponentMapper.getFor(BodyComponent.class);
public static final ComponentMapper<CollisionComponent> collisionComponent = ComponentMapper.getFor(CollisionComponent.class);
    public static final ComponentMapper<PlayerComponent> playerComponent = ComponentMapper.getFor(PlayerComponent.class);
    public static final ComponentMapper<StateComponent> stateComponent = ComponentMapper.getFor(StateComponent.class);
    public static final ComponentMapper<TransformComponent> transformComponent = ComponentMapper.getFor(TransformComponent.class);
    public static final ComponentMapper<TypeComponent> typeComponent = ComponentMapper.getFor(TypeComponent.class);
    public static final ComponentMapper<AttackComponent> attackComponent = ComponentMapper.getFor(AttackComponent.class);
    public static final ComponentMapper<HealthComponent> healthComponent = ComponentMapper.getFor(HealthComponent.class);
    public static final ComponentMapper<ProjectileComponent> projectileComponent = ComponentMapper.getFor(ProjectileComponent.class);
    public static final ComponentMapper<AnimationComponent> animationComponent = ComponentMapper.getFor(AnimationComponent.class);
    public static final ComponentMapper<TextureComponent> textureComponent = ComponentMapper.getFor(TextureComponent.class);
}
