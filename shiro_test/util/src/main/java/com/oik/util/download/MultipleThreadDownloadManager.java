package com.oik.util.download;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author 15093
 * @description 分片下载
 * @date 2023/1/9 9:08
 */
public class MultipleThreadDownloadManager implements Runnable {
    private String uri;
    private File target;

    public MultipleThreadDownloadManager(String uri, File target) {
        this.uri = uri;
        this.target = target;
        if (target.exists() == false) {
            try {
                target.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        new Thread(this).start();
    }

    /**
     * @return int
     * @Author 15093
     * @Description //根据文件大小计算线程数量
     * @Date 9:12
     * @Param [totalSize]
     **/
    public int threadCount(int totalSize) {
        if (totalSize < 30 * 2014 * 1024) {
            return 1;
        }
        return 30;
    }

    @Override
    public void run() {
        //获取文件总大小
        int totalSize = 0;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(uri).openConnection();
            connection.connect();
            int contentLength = connection.getContentLength();
            totalSize = contentLength;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int threadCount = threadCount(totalSize);
        int perThreadSize = totalSize / threadCount;
        int id = 0;
        int from = 0, to = 0;
        while (totalSize > 0) {
            id++;
            if (totalSize < perThreadSize) {
                from = 0;
                to = totalSize;
            } else {
                from = totalSize;
                to = totalSize + perThreadSize;
            }
        }
        UnitDownloader downloader = new UnitDownloader(from, to, target, uri, id);
        new Thread(downloader).start();
    }
}
