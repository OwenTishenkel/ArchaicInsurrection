package Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Pool;

import java.util.ArrayList;

import Managers.EntityManager;


public class AttackComponent implements Component, Pool.Poolable{

    int attackStrength;
    ArrayList<Entity>attackableEntities;

    public void attack(Entity entity,EntityManager entityManager) {


        /*switch(entity.getComponent(StateComponent.class).getDirection()) {
            case UP:
               // todo use entities array list to make a check for any entites within "x" of the whatever entity is attacking that direction
                for( int i =0; entityManager.getEntities().size()>i; i++) {
                    if(entityManager.getEntities().get(i)!=entity) {
                        if(entityManager.getEntities().get(i).getComponent(TransformComponent.class).getPosition().x<entity.getComponent(TransformComponent.class).getPosition().x+32 && ) {

                        }

                    }
                }
                break;
            case DOWN:

                break;
            case LEFT:

                break;
            case RIGHT:

                break;
                default:
                    for( int i =0; entityManager.getEntities().size()>i; i++) {
                        if(entityManager.getEntities().get(i)!=entity) {

                        }
                    }
                    break;


        }*/

    }




    @Override
    public void reset() {

    }
}
