package com.example.fileserver;

import org.springframework.beans.factory.annotation.Value;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author edge
 * @date 2019/6/10-8:45
 */

public class SaveFile2SYS implements SaveFile {

    @Value("${fileserver.sys.defaultPath}")
    private String defaultPath;

    @Override
    public boolean createDirectory(String path, String createpath) {
        if (!"/".equals(path.substring(path.length() - 1, path.length()))) {
            path = path + "/";
        }
        path = defaultPath + path;
        try {
            File file = new File(path + createpath);
            file.mkdirs();
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
        try {
            File file = new File(path + delpath);
            if (file.exists()) {
                file.delete();
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
        try {
            File file = new File(path + filename);
            if (file.exists()) {
                file.delete();
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
        try {
            File file = new File(path);
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
        boolean flag = existDirectory(path);
        path = defaultPath + path;
        if (flag) {
            List<String> name = new ArrayList<>();
            try {
                File file = new File(path);
                File[] aa = file.listFiles();
                for (int i = 0; i < aa.length; i++) {
                    //不是隐藏文件就在list中加入文件名，去掉文件夹名
                    if (aa[i].isFile() && !aa[i].isHidden()) {
                        //判断操作系统类型
                        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
                            path = path.replaceAll("/", "\\\\");
                            name.add(aa[i].toString().replace(path, ""));
                        } else {
                            name.add(aa[i].toString().replace(path, ""));
                        }
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
        List<String> name = new ArrayList<>();
        try {
            File file = new File(path);
            File[] aa = file.listFiles();
            for (int i = 0; i < aa.length; i++) {
                //不是隐藏文件就在list中加入文件名，去掉文件夹名
                if (aa[i].isFile() && !aa[i].isHidden()) {
                    //判断操作系统类型
                    if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
                        path = path.replaceAll("/", "\\\\");
                        name.add(aa[i].toString().replace(path, ""));
                    } else {
                        name.add(aa[i].toString().replace(path, ""));
                    }
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
        List<String> name = new ArrayList<>();
        try {
            File file = new File(path);
            File[] aa = file.listFiles();
            for (int i = 0; i < aa.length; i++) {
                //不是隐藏文件夹就在list中加入
                if (aa[i].isDirectory() && !aa[i].isHidden()) {
                    //判断操作系统类型
                    if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
                        path = path.replaceAll("/", "\\\\");
                        name.add(aa[i].toString().replace(path, "") + "/");
                    } else {
                        name.add(aa[i].toString().replace(path, ""));
                    }
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
        if ((path == null) || ("".equals(path.trim()))) {
            System.out.println("local path not null");
            return;
        }
        InputStream in = null;
        OutputStream out = null;
        try {
            File file = new File(path + filename);
            //打开一个文件输入流执行本地文件，要从它读取内容
            in = new BufferedInputStream(input);
            //打开一个远程Samba文件输出流，作为复制到的目的地
            out = new BufferedOutputStream(new FileOutputStream(file));
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
        InputStream in = null;
        OutputStream out = null;
        try {
            File file = new File(path + filename);

            if (!file.exists()) {
                System.out.println("file not exist");
                return;
            }
            if ((path == null) || ("".equals(path.trim()))) {
                System.out.println("local path not null");
                return;
            }
            //打开文件输入流，指向远程的smb服务器上的文件，特别注意，这里流包装器包装了SmbFileInputStream
            in = new BufferedInputStream(new FileInputStream(file));
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
