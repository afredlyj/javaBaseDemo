package afred.javademo.thread;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Afred on 15/7/29.
 */

public class FileWriteUtil {

    private int currentThreadNum = 1;

    /** 记录将字符写入文件的次数 */
    private int count = 0;

    // 初始值第一个文件
    private int currentFileNum = 1;

    public void write(char value, int threadNum) {


        generateNextFileNum();

        FileWriter writer = null;
        try {
            writer = new FileWriter("file" + currentFileNum + ".txt", true);
            writer.write(String.valueOf(value));
            System.out.printf(
                    "ThreadNum=%d is executing. %c is written into file file%d.txt \n",
                    currentThreadNum, value, currentFileNum);
            writer.flush();
            count++;
            currentThreadNum = threadNum;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (null != writer) {
            try {
                writer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                writer = null;
            }
        }

        generateNextThreadNum();

    }

    public int getCurrentThreadNum() {
        return currentThreadNum;
    }

    public void setCurrentThreadNum(int currentThreadNum) {
        this.currentThreadNum = currentThreadNum;
    }

    private void generateNextFileNum()
    {
        if (count % 4 == 0) {
            currentFileNum = 1;
        }else
        {
            currentFileNum++;
        }
    }

    private void generateNextThreadNum() {

        if (count % 4 == 0) {
            if (currentThreadNum < 3) {
                currentThreadNum += 2;
            } else {
                currentThreadNum = (currentThreadNum + 2) % 4;
            }
        }else
        {
            if(currentThreadNum == 4)
            {
                currentThreadNum=1;
            }else
            {
                currentThreadNum++;
            }
        }
    }

}