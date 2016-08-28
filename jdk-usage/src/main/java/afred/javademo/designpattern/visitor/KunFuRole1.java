package afred.javademo.designpattern.visitor;

/**
 * Created by afred on 16/8/26.
 */
public class KunFuRole1 implements Role1 {

    public void accept(AbsActor1 actor) {
        System.out.println("kunfu role1");
        actor.act(this);

    }

}
