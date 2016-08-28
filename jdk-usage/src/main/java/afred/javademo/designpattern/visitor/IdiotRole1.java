package afred.javademo.designpattern.visitor;

/**
 * Created by afred on 16/8/26.
 */
public class IdiotRole1 implements Role1 {

    @Override
    public void accept(AbsActor1 actor) {
        actor.act(this);
    }
}
