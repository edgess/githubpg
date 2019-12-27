package com.example.fileserver;

import com.edge.dao.server.Name2FastDFSService;
import com.edge.entity.Name2FastDFS;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author edge
 * @date 2019/12/27-13:16
 */
public class SaveFile2FDFS implements SaveFile {
    @Autowired
    Name2FastDFSService name2FastDFSService;

    @Override
    public boolean createDirectory(String path, String createpath) {
        return false;
    }

    @Override
    public boolean delDirectory(String path, String delpath) {
        return false;
    }

    @Override
    public boolean delFile(String path, String filename) {
        return false;
    }

    @Override
    public boolean existDirectory(String path) {
        return true;
    }

    @Override
    public boolean existFile(String path, String filename) {
        Name2FastDFS name2FastDFS = new Name2FastDFS();
        name2FastDFS.setName(filename.substring(0, filename.lastIndexOf(".")));
        name2FastDFS.setExt(filename.substring(filename.lastIndexOf("."), filename.length()));
        if (name2FastDFSService.findFdfsbyName(name2FastDFS) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<String> listFiles(String path) {
        List<String> result = new ArrayList<>();
        Set<Name2FastDFS> name2FastDFSs = name2FastDFSService.findall();
        for (Name2FastDFS o : name2FastDFSs) {
            result.add(o.getName() + o.getExt());
        }
        return result;
    }

    @Override
    public List<String> listDirectory(String path) {
        return null;
    }

    public byte[] is2ByeteArray(InputStream is) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        try {
            while ((rc = is.read(buff, 0, 100)) > 0) {
                baos.write(buff, 0, rc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }


    @Override
    public void uploadFile(String path, String filename, InputStream input) {
        String confUrl = Thread.currentThread().getContextClassLoader().getResource("fastdfs_client.conf").getPath();
        FastDFSClient fastDFSClient = null;

        try {
            fastDFSClient = new FastDFSClient(confUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String fdsid = null;
        try {
            fdsid = fastDFSClient.uploadFile(is2ByeteArray(input));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //写对应文件名到数据库
        if (fdsid != null) {
            Name2FastDFS name2FastDFS = new Name2FastDFS();
            name2FastDFS.setName(filename.substring(0, filename.lastIndexOf(".")));
            name2FastDFS.setExt(filename.substring(filename.lastIndexOf("."), filename.length()));
            name2FastDFS.setFdfs(fdsid);
            name2FastDFS.setDate(new Date());
            name2FastDFSService.insert(name2FastDFS);
        }
    }


    @Override
    public void downloadFile(String path, String filename, OutputStream output) {
        Name2FastDFS name2FastDFS = new Name2FastDFS();
        name2FastDFS.setName(filename.substring(0, filename.lastIndexOf(".")));
        name2FastDFS.setExt(filename.substring(filename.lastIndexOf("."), filename.length()));
        Name2FastDFS name2FastDFS2 = name2FastDFSService.findFdfsbyName(name2FastDFS);
        if (name2FastDFS2 != null) {
            String confUrl = Thread.currentThread().getContextClassLoader().getResource("fastdfs_client.conf").getPath();
            FastDFSClient fastDFSClient = null;
            try {
                fastDFSClient = new FastDFSClient(confUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            fastDFSClient.download_file(name2FastDFS2.getFdfs(), new BufferedOutputStream(output));
        }
    }
}
