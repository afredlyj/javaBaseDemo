package afred.javademo.designpattern.visitor;

/**
 * Created by afred on 16/8/26.
 */
public abstract class AbsActor {

    public void act(Role role) {
        System.out.println("演员可以扮演任何角色");
    }

    public void act(KungFuRole role) {
        System.out.println("演员可以扮演任何功夫角色");
    }

}

