package com.example.fileserver;

import com.edge.dao.server.Log2Service;
import com.edge.dao.server.Name2FastDFSService;
import com.edge.entity.Name2FastDFS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Model3ApplicationTests {
    @Autowired
    Log2Service log2Service;
    @Autowired
    Name2FastDFSService name2FastDFSService;

    @Test
    public void contextLoads() throws Exception {
        String confUrl = Thread.currentThread().getContextClassLoader().getResource("fastdfs_client.conf").getPath();
        FastDFSClient fastDFSClient = new FastDFSClient(confUrl);

        String filename = "tw.jpg";
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File homepath = fsv.getHomeDirectory();

        String fdsid = fastDFSClient.uploadFile(homepath.getPath() + "\\" + filename);

        if (fdsid != null) {
            Name2FastDFS name2FastDFS = new Name2FastDFS();
            name2FastDFS.setName(filename.substring(0, filename.lastIndexOf(".")));
            name2FastDFS.setExt(filename.substring(filename.lastIndexOf("."), filename.length()));
            name2FastDFS.setFdfs(fdsid);
            name2FastDFS.setDate(new Date());
            name2FastDFSService.insert(name2FastDFS);
        }
    }

}
