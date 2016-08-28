package afred.javademo.designpattern.visitor;

/**
 * Created by afred on 16/8/26.
 */
public class YoungActor1 extends AbsActor1 {

    @Override
    public void act(KunFuRole1 role) {
        System.out.println("年轻演员喜欢功夫角色");
    }
}
