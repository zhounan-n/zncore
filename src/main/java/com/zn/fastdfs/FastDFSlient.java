package com.zn.fastdfs;

import com.zn.fastdfs.common.NameValuePair;
import com.zn.fastdfs.fastdfs.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhoun on 2018/2/9.
 **/
public class FastDFSlient {

    private static final String CONFIG_FILENAME = "src/main/resources/fdfs/fdfs_client.conf";

    private static StorageClient1 storageClient1;

    static {
        try {
            ClientGlobal.init(CONFIG_FILENAME);
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            if (trackerServer == null) {
                throw new IllegalStateException("get connection return null");
            }
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            if (storageServer == null) {
                throw new IllegalStateException("get storage return null");
            }
            storageClient1 = new StorageClient1(trackerServer, storageServer);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String uploadFile(File file, String fileName) {
        return uploadFile(file, fileName, null);
    }

    public static String uploadFile(File file, String fileName, Map<String, String> metaList) {
        try {

            byte[] buff = IOUtils.toByteArray(new FileInputStream(file));
            NameValuePair[] nameValuePairs = null;
            if (metaList != null) {
                nameValuePairs = new NameValuePair[metaList.size()];
                int index = 0;
                for (Iterator<Map.Entry<String, String>> iterator = metaList.entrySet().iterator(); iterator.hasNext(); ) {
                    Map.Entry<String, String> entry = iterator.next();
                    String name = entry.getKey();
                    String value = entry.getValue();
                    nameValuePairs[index++] = new NameValuePair(name, value);
                }
            }
            return storageClient1.upload_file1(buff, FilenameUtils.getExtension(fileName), nameValuePairs);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public static Map<String, String> getFileMetaData(String field) {
        try {
            NameValuePair[] metaList = storageClient1.get_metadata1(field);
            if (metaList != null) {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                for (NameValuePair nameValuePair : metaList) {
                    hashMap.put(nameValuePair.getName(), nameValuePair.getValue());
                }
                return hashMap;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int deleteFile(String field) {
        try {
            return storageClient1.delete_file1(field);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int download(String field, File output) {
        FileOutputStream fos = null;
        try {
            byte[] content = storageClient1.download_file1(field);
            fos = new FileOutputStream(output);
            IOUtils.write(content, new FileOutputStream("D://" + UUID.randomUUID().toString() + ".conf"));
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }
}
