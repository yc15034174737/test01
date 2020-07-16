package com.dj.ssm.utils;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class QinniuUtil {

    //设置好账号的ACCESS_KEY和SECRET_KEY
    private static final String ACCESS_KEY = "0_lzXvWulNHqwyjDX5Fn0pXRX0-BY4WIZkISpxg3";
    private static final String SECRET_KEY = "2gH8q9UNh7GHR4todj7Jh5VbClb_PaIY5uWDS5SZ";

    //要上传的空间
    private static String bucketname = "djdemo"; //对应要上传到七牛上 你的那个路径（自己建文件夹 注意设置公开）

    //密钥配置
    private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);


//    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
//    public static String getUpToken(){
//        return auth.uploadToken(bucketname);
//    }

    /**
     *springmvc MultipartFile 上传文件流
     * @param file 上传文件流
     * @return
     * @throws Exception
     */

    public static String updateFile(MultipartFile file) throws Exception {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        //创建上传对象
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = UUID.randomUUID().toString().replace("-", "");
        try {
            InputStream inputStream=file.getInputStream();
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[600]; //buff用于存放循环读取的临时数据
            int rc = 0;
            while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            byte[] uploadBytes  = swapStream.toByteArray();
            try {
                Response response = uploadManager.put(uploadBytes,key,auth.uploadToken(bucketname));
                //解析上传成功的结果
                DefaultPutRet putRet;
                putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return putRet.key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                }
            }
        } catch (UnsupportedEncodingException ex) {
        }
        return null;
    }

}
