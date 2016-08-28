package afred.javademo.designpattern.visitor;

/**
 * Created by afred on 16/8/26.
 */
public class Test2 {

    public static void main(String[] args) {
        AbsActor1 actor = new OldActor1();
        Role1 role = new KunFuRole1();

        role.accept(actor);
    }

}
