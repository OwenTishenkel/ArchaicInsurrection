package Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;

public class AttackComponent implements Component, Pool.Poolable{

    public void attack(Entity entity) {

        switch(entity.getComponent(StateComponent.class).getDirection()) {
            case UP:
               // todo use entities array list to make a check for any entites within "x" of the whatever entity is attacking that direction
                break;
            case DOWN:

                break;
            case LEFT:

                break;
            case RIGHT:

                break;


        }

    }




    @Override
    public void reset() {

    }
}
