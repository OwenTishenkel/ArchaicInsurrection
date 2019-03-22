package Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Pool.Poolable;

public class AnimationComponent implements Component, Poolable {

    public enum ANIMATIONSTATE{
        UP,DOWN,LEFT,RIGHT,HIT,ATTACKING,PREPARING_TO_ATTACK,DYING,IMMOBILE
    }
    private float time=0.0f;
    private boolean isLooping=false;

    private ArrayMap<ANIMATIONSTATE,Animation> animations=new ArrayMap<>();

    public AnimationComponent addAnimation(ANIMATIONSTATE state,Animation animation) {
        this.animations.put(state,animation);
        return this;
    }



    @Override
    public void reset() {

    }
}
