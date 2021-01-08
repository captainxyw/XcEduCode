package com.xuecheng.manage_cms.dao;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GridFsTest {

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Test
    public void testGridFs() throws FileNotFoundException {
        //要储存的文件
        File file = new File("C:/Users/capta/Desktop/index_banner.ftl");
        //定义输入流
        FileInputStream fileInputStream = new FileInputStream(file);
        //向GridFS存储文件
        ObjectId objectId = gridFsTemplate.store(fileInputStream, "index-banner1222");
        //得到文件ID
        String fileId = objectId.toString();
        System.out.println(fileId);

    }
}