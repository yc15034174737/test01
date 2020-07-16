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
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
//字节数组上传
public class QinniuUtil1 {

    //设置好账号的ACCESS_KEY和SECRET_KEY
    private static final String ACCESS_KEY = "0_lzXvWulNHqwyjDX5Fn0pXRX0-BY4WIZkISpxg3";
    private static final String SECRET_KEY = "2gH8q9UNh7GHR4todj7Jh5VbClb_PaIY5uWDS5SZ";

    //要上传的空间
    private static String bucketname = "djdemo"; //对应要上传到七牛上 你的那个路径（自己建文件夹 注意设置公开）

    //密钥配置
    private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    public static String updateFile(MultipartFile file) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = UUID.randomUUID().toString().replace("-", "");
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        key = key + "." + suffix;
        try {
            byte[] uploadBytes = file.getBytes();
            String upToken = auth.uploadToken(bucketname);
            Response response = uploadManager.put(uploadBytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return putRet.key;
        } catch (Exception e) {
              e.printStackTrace();
        }
        return null;
    }
}
