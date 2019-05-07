package com.example.fileserver;


import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveFile2SMB implements SaveFile {
    @Value("${fileserver.smbHost}")
    private String smbHost;
    @Value("${fileserver.smbUserName}")
    private String smbUserName;
    @Value("${fileserver.smbPassword}")
    private String smbPassword;
    @Value("${fileserver.defaultPath}")
    private String defaultPath;


    private String getSERVER_ADDRESS() {
        return "smb://" + smbUserName + ":" + smbPassword + "@" + smbHost;
    }

//    public static void main(String[] args) throws MalformedURLException, SmbException, FileNotFoundException {
////测试list
////        listFile("/mailbak/");
////测试上传
////        String filein = "/Users/edge/Desktop/ftp/xb.pdf";
////        InputStream is = new FileInputStream(filein);
////        uploadFile("/mailbak/", "xb.pdf", is);
////测试下载
////        String fileout = "/Users/edge/Desktop/ftp/xb3.pdf";
////        OutputStream os = new FileOutputStream(fileout);
////        downloadFile("/mailbak/", "xb.pdf", os);
//// 测试目录
////        createDirectory("/mailbak/", "zzz/");
//    }

    @Override
    public boolean createDirectory(String path, String createpath) {
        if (!"/".equals(path.substring(path.length() - 1, path.length()))) {
            path = path + "/";
        }
        path = defaultPath + path;
        String SERVER_ADDRESS = getSERVER_ADDRESS();
        try {
            SmbFile remoteSmbFile = new SmbFile(SERVER_ADDRESS + path + createpath);
            remoteSmbFile.mkdirs();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delDirectory(String path, String delpath) {
        if (!"/".equals(path.substring(path.length() - 1, path.length()))) {
            path = path + "/";
        }
        path = defaultPath + path;
        if (!"/".equals(delpath.substring(delpath.length() - 1, delpath.length()))) {
            delpath = delpath + "/";
        }
        String SERVER_ADDRESS = getSERVER_ADDRESS();
        try {
            SmbFile remoteSmbFile = new SmbFile(SERVER_ADDRESS + path + delpath);
            if (remoteSmbFile.exists()) {
                remoteSmbFile.delete();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delFile(String path, String filename) {
        if (!"/".equals(path.substring(path.length() - 1, path.length()))) {
            path = path + "/";
        }
        path = defaultPath + path;
        String SERVER_ADDRESS = getSERVER_ADDRESS();
        try {
            SmbFile remoteSmbFile = new SmbFile(SERVER_ADDRESS + path + filename);
            if (remoteSmbFile.exists()) {
                remoteSmbFile.delete();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public boolean existDirectory(String path) {
        if (!"/".equals(path.substring(path.length() - 1, path.length()))) {
            path = path + "/";
        }
        path = defaultPath + path;
        String SERVER_ADDRESS = getSERVER_ADDRESS();
        try {
            SmbFile file = new SmbFile(SERVER_ADDRESS + path);
            if (file.exists()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean existFile(String path, String filename) {
        if (!"/".equals(path.substring(path.length() - 1, path.length()))) {
            path = path + "/";
        }
        boolean flag =existDirectory(path);
        path = defaultPath + path;

        String SERVER_ADDRESS = getSERVER_ADDRESS();
        if (flag) {
            List<String> name = new ArrayList<>();
            try {
                SmbFile file = new SmbFile(SERVER_ADDRESS + path);
                SmbFile[] aa = file.listFiles();
                for (int i = 0; i < aa.length; i++) {
                    //不是隐藏文件就在list中加入文件名，去掉ip地址和头
                    if (aa[i].isFile() && !aa[i].isHidden()) {
                        name.add(aa[i].toString().replaceAll(SERVER_ADDRESS + path, ""));
                    }
                }

                if (name.contains(filename)) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<String> listFiles(String path) {
        if (!"/".equals(path.substring(path.length() - 1, path.length()))) {
            path = path + "/";
        }
        path = defaultPath + path;
        String SERVER_ADDRESS = getSERVER_ADDRESS();
        List<String> name = new ArrayList<>();
        try {
            SmbFile file = new SmbFile(SERVER_ADDRESS + path);
            SmbFile[] aa = file.listFiles();
            for (int i = 0; i < aa.length; i++) {
                //不是隐藏文件就在list中加入文件名，去掉ip地址和头
                if (aa[i].isFile() && !aa[i].isHidden()) {
                    name.add(aa[i].toString().replaceAll(SERVER_ADDRESS + path, ""));
                }
            }
        } catch (Exception e) {

        }

        return name;
    }

    @Override
    public List<String> listDirectory(String path) {
        if (!"/".equals(path.substring(path.length() - 1, path.length()))) {
            path = path + "/";
        }
        path = defaultPath + path;
        String SERVER_ADDRESS = getSERVER_ADDRESS();
        List<String> name = new ArrayList<>();
        try {
            SmbFile file = new SmbFile(SERVER_ADDRESS + path);
            SmbFile[] aa = file.listFiles();
            for (int i = 0; i < aa.length; i++) {
                //不是隐藏文件就在list中加入文件名，去掉ip地址和头
                if (aa[i].isDirectory() && !aa[i].isHidden()) {
                    name.add(aa[i].toString().replaceAll(SERVER_ADDRESS + path, ""));
                }
            }
        } catch (Exception e) {

        }
        return name;
    }

    @Override
    public void uploadFile(String path, String filename, InputStream input) {
        if (!"/".equals(path.substring(path.length() - 1, path.length()))) {
            path = path + "/";
        }
        path = defaultPath + path;
        String SERVER_ADDRESS = getSERVER_ADDRESS();
        if ((path == null) || ("".equals(path.trim()))) {
            System.out.println("本地文件路径不可以为空");
            return;
        }
        InputStream in = null;
        OutputStream out = null;
        try {
            SmbFile remoteSmbFile = new SmbFile(SERVER_ADDRESS + path + filename);
            //打开一个文件输入流执行本地文件，要从它读取内容
            in = new BufferedInputStream(input);
            //打开一个远程Samba文件输出流，作为复制到的目的地
            out = new BufferedOutputStream(new SmbFileOutputStream(remoteSmbFile));
            //缓冲内存
            byte[] buffer = new byte[1024];
            while (in.read(buffer) != -1) {
                out.write(buffer);
                buffer = new byte[1024];
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {

            }
        }
    }

    @Override
    public void downloadFile(String path, String filename, OutputStream output) {
        if (!"/".equals(path.substring(path.length() - 1, path.length()))) {
            path = path + "/";
        }
        path = defaultPath + path;
        String SERVER_ADDRESS = getSERVER_ADDRESS();
        InputStream in = null;
        OutputStream out = null;
        try {
            SmbFile remoteSmbFile = new SmbFile(SERVER_ADDRESS + path + filename);

            if (!remoteSmbFile.exists()) {
                System.out.println("Samba服务器远程文件不存在");
                return;
            }
            if ((path == null) || ("".equals(path.trim()))) {
                System.out.println("本地目录路径不可以为空");
                return;
            }
            //打开文件输入流，指向远程的smb服务器上的文件，特别注意，这里流包装器包装了SmbFileInputStream
            in = new BufferedInputStream(new SmbFileInputStream(remoteSmbFile));
            //打开文件输出流，指向新创建的本地文件，作为最终复制到的目的地
            out = new BufferedOutputStream(output);
            //缓冲内存
            byte[] buffer = new byte[1024];
            while (in.read(buffer) != -1) {
                out.write(buffer);
                buffer = new byte[1024];
            }
        } catch (Exception e) {

        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {

            }
        }
    }


}
