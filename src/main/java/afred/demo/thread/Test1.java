package afred.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Afred on 15/7/28.
 */
public class Test1 {


    /**
     * 有四个线程1、2、3、4。
     *
     * 线程1的功能就是输出A，线程2的功能就是输出B，
     *
     * 以此类推......... 现在有四个文件file1,
     * file2,file3, file4。初始都为空。现要让四个文件呈如下格式：
     * file1：A B C D A B....
     * file2：B C D A B C....
     * file3：C D A B C D....
     * file4：D A B C D A....
     *
     * @author Eric
     *
     */

    public static void main(String[] args) {
        FileWriteUtil util = new FileWriteUtil();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new WriteRunnable(util, 1, 'A'));
        service.execute(new WriteRunnable(util, 2, 'B'));
        service.execute(new WriteRunnable(util, 3, 'C'));
        service.execute(new WriteRunnable(util, 4, 'D'));

        service.shutdown();
    }

}







