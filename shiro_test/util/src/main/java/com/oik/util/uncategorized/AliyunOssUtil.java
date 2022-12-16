package com.oik.util.uncategorized;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.event.ProgressListener;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyuncs.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;

/**
 * 王坚oss跑龙套
 *
 * @author LEAF
 * @date 2022-09-07
 */
@Component
@Configuration
public class AliyunOssUtil {
//
//    @Value("${oss.access-id}")
//    private String accessKeyId;
//
//    @Value("${oss.access-key}")
//    private String accessKeySecret;
//
//    @Value("${oss.host}")
//    private String host;
//
//    @Value("${oss.end-point}")
//    private String endPoint;
//
//    @Value("${oss.bucket}")
//    private String bucketName;
//
//    private OSS ossClient;
//
//
//    @PostConstruct
//    public void init() {
//        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
//        conf.setMaxConnections(2048);// 允许打开的最大HTTP连接数。默认为1024
//        conf.setConnectionRequestTimeout(10000); // 从连接池中获取连接的超时时间（单位：毫秒）。默认不超时
//        //建立连接
//        ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret, conf);
//    }
//
//    //上传
//    public String uploadFile(String filename, String uploadDirectory, InputStream inputStream, ProgressListener progressListener) {
//        //判断是否存在指定bucket
//        if (!ossClient.doesBucketExist(bucketName)) {
//            ossClient.createBucket(bucketName);
//            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
//            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
//            ossClient.createBucket(createBucketRequest);
//        }
//        //上传成功后 回调
//        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uploadDirectory + "/" + filename, inputStream)
//                .withProgressListener(progressListener);
//
//        ossClient.putObject(putObjectRequest);
//        return host + "/" + uploadDirectory + "/" + filename;
//    }
//
//    //单个文件删除,根据url
//    public String deleteFile(String oldUrl) {
//        if (oldUrl != null && oldUrl.length() > 51) {
//            ossClient.deleteObject(bucketName, oldUrl.substring(51));
//            return "delete picture success";
//        } else return "delete picture false";
//    }
//
//
//    public String getHost() {
//        return host;
//    }
//
//    public void setHost(String host) {
//        this.host = host;
//    }
//
//    public void deleteFileList(List<String> urlList) {
//        urlList.forEach(url -> {
//            if (!StringUtils.isEmpty(url))
//                ossClient.deleteObject(bucketName, url.substring(51));
//        });
//    }
}
