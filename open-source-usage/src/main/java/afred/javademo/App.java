package afred.javademo;

import org.apache.commons.lang.StringEscapeUtils;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main(String[] args) throws IOException {

//        FileSystemResource fileSystemResource = new FileSystemResource("bean/LogUtils.java");
//
//        System.out.println(fileSystemResource.exists());
//
//        fileSystemResource = new FileSystemResource(".idea");
//
//        System.out.println(fileSystemResource.exists());
//
//        System.out.println(fileSystemResource.getPath());
//
//        System.out.println(fileSystemResource.getURL().getPath());
//
//
//        fileSystemResource = new FileSystemResource("classpath:logback.xml");
//
//        System.out.println(fileSystemResource.exists());
//
//        System.out.println(fileSystemResource.getPath());
//
//        System.out.println(fileSystemResource.getURL().getPath());
//
//        System.out.println(StringEscapeUtils.escapeHtml("<script>alert()</script>"));
//        System.out.println(StringEscapeUtils.escapeSql("select * from"));
//
//        System.out.println(StringEscapeUtils.escapeSql("select key_sn,remark,create_date from tb_selogon_key where 1=1 "));

        String sql="1' or '1'='1";
        System.out.println("防SQL注入:"+StringEscapeUtils.escapeSql(sql)); //防SQL注入

    }
}
