package Helpers;

import com.badlogic.ashley.core.ComponentMapper;

import Components.BodyComponent;
import Components.CollisionComponent;
import Components.PlayerComponent;

public class Mappers {
public static final ComponentMapper<BodyComponent> bodyComponent = ComponentMapper.getFor(BodyComponent.class);
public static final ComponentMapper<CollisionComponent> collisionComponent = ComponentMapper.getFor(CollisionComponent.class);
    public static final ComponentMapper<PlayerComponent> playerComponent = ComponentMapper.getFor(PlayerComponent.class);



}
