package Managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class CollisionManager implements ContactListener{








    @Override
    public void beginContact(Contact contact) {
        Gdx.app.log("Contact", "");
        Fixture fa = contact.getFixtureA();
        Fixture fb= contact.getFixtureB();
        
        if(fa.getBody().getUserData() instanceof Entity){
            Entity entity = (Entity) fa.getBody().getUserData();
            
            
            
            
        }
        else if(fb.getBody().getUserData() instanceof Entity) {
        Entity entity = (Entity) fb.getBody().getUserData();
        
        }
        



    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
