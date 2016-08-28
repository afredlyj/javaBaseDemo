package afred.javademo.designpattern.visitor;

/**
 * Created by afred on 16/8/26.
 */
public class YoungActor extends AbsActor {

    @Override
    public void act(KungFuRole role) {
        System.out.println("年轻演员喜欢功夫角色");
    }
}
