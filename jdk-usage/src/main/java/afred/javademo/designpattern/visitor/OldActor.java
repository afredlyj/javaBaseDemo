package afred.javademo.designpattern.visitor;

/**
 * Created by afred on 16/8/26.
 */
public class OldActor extends AbsActor {

    @Override
    public void act(KungFuRole role) {
        System.out.println("年纪大了");
    }
}
