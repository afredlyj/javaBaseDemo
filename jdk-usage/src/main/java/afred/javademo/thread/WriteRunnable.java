package afred.javademo.thread;

/**
 * Created by Afred on 15/7/29.
 */
public class WriteRunnable implements Runnable {

        private FileWriteUtil util = null;

        private int threadNum = 1;

        private char value = ' ';

        /**
         * @param util
         */
        public WriteRunnable(FileWriteUtil util, int threadNum, char value) {
            super();
            this.util = util;
            this.threadNum = threadNum;
            this.value = value;
        }

        public void run() {
            /**
             * 假设循环16次， 一直循环可以使用while(true) 或者 for(;;)
             */
            for (int i = 0; i < 16; i++) {
                synchronized (util) {
                    while (threadNum != util.getCurrentThreadNum()) {
                        try {
                            util.wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    util.write(value, threadNum);

                    util.notifyAll();

                }
            }

        }

}
