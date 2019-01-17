package Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class HealthComponent implements Component,Pool.Poolable {
    int health;
    int maxhealth;

    public int getHealth() {
        return health;
    }
    public void addHealth(int a) {
        if(a+health>maxhealth) {
            health=maxhealth;
        }
        else {
            health=a+health;
        }

    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxhealth() {
        return maxhealth;
    }

    public void setMaxhealth(int maxhealth) {
        this.maxhealth = maxhealth;
        health=maxhealth;
    }

    @Override
    public void reset() {

    }
}
