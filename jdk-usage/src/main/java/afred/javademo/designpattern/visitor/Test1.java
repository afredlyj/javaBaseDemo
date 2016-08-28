package afred.javademo.designpattern.visitor;

/**
 * Created by afred on 16/8/26.
 */
public class Test1 {

    public static void main(String[] args) {
        AbsActor actor = new OldActor();
        Role role = new KungFuRole();
        actor.act(role);

        actor.act(new KungFuRole());

    }


}

