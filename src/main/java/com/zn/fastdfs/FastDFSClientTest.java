package com.zn.fastdfs;

import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhoun on 2018/2/9.
 **/
public class FastDFSClientTest {

    @Test
    public void testUpload() {
        File file = new File("G:\\大学课程.jpg");
        Map<String, String> metaList = new HashMap<String, String>();
        metaList.put("width", "1024");
        metaList.put("height", "768");
        metaList.put("author", "zhounan");
        metaList.put("data", "20180209");
        String fid = FastDFSlient.uploadFile(file, file.getName(), metaList);
        System.out.println("upload local file:" + file.getPath() + "ok,field is=" + fid);
    }

    @Test
    public void testDownload() {
        int r = FastDFSlient.download("", new File("download.png"));
        System.out.println(r == 0 ? "下载成功" : "下载失败");
    }

    @Test
    public void getFileMetaData() {
        Map<String, String> metaList = FastDFSlient.getFileMetaData("");
        for (Iterator<Map.Entry<String, String>> iterator = metaList.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, String> entry = iterator.next();
            String name = entry.getKey();
            String value = entry.getValue();
            System.out.println(name + "=" + value);

        }

    }

    @Test
    public void testDelete() {
        int r = FastDFSlient.deleteFile("");
        System.out.println(r == 0 ? "删除成功" : "删除失败");
    }

}
