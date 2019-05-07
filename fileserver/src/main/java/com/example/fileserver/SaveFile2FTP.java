package com.example.fileserver;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;


public class SaveFile2FTP implements SaveFile {
    @Value("${fileserver.ftpHost}")
    private String ftpHost;
    @Value("${fileserver.ftpUserName}")
    private String ftpUserName;
    @Value("${fileserver.ftpPassword}")
    private String ftpPassword;
    @Value("${fileserver.ftpPort}")
    private int ftpPort;

    public boolean createDirectory(String path, String createpath) {
        FTPClient ftpClient = null;
        try {
            ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(path);
            ftpClient.makeDirectory(createpath);
            ftpClient.logout();
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delDirectory(String path, String delpath) {
        return false;
    }



    @Override
    public boolean delFile(String path, String filename) {
        FTPClient ftpClient = null;


        try {

            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean existDirectory(String path) {
        return false;
    }

    @Override
    public boolean existFile(String path, String filename) {
        return false;
    }

    public List<String> listFiles(String path) {
        FTPClient ftpClient = null;
        List<String> name = new ArrayList<>();

        try {
            ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//            ftpClient.enterLocalPassiveMode();
            System.out.println(path);
            if (ftpClient.changeWorkingDirectory(path)) {

                FTPFile[] ff = ftpClient.listFiles();
                for (FTPFile file : ff) {
                    if(file.isFile()){
                        name.add(file.getName());
                    }
                }
            }

            ftpClient.logout();

        } catch (FileNotFoundException e) {
            System.out.println("没有找到" + path + "文件");
            e.printStackTrace();
        } catch (SocketException e) {
            System.out.println("连接FTP失败.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取错误。");
            e.printStackTrace();
        }

        return name;
    }

    public List<String> listDirectory(String path) {
        FTPClient ftpClient = null;
        List<String> name = new ArrayList<>();

        try {
            ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//            ftpClient.enterLocalPassiveMode();
            if (ftpClient.changeWorkingDirectory(path)) {
                FTPFile[] fd = ftpClient.listDirectories();
                for (FTPFile ftpFile : fd) {
                    name.add(ftpFile.getName() + "/");
                }
            }

            ftpClient.logout();

        } catch (FileNotFoundException e) {
            System.out.println("没有找到" + path + "文件");
            e.printStackTrace();
        } catch (SocketException e) {
            System.out.println("连接FTP失败.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取错误。");
            e.printStackTrace();
        }
        return name;
    }

    public void uploadFile(String path, String filename, InputStream input) {
        FTPClient ftpClient = null;
        try {
            int reply;
            ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
            }
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//            ftpClient.enterLocalPassiveMode();

            ftpClient.changeWorkingDirectory(path);
            ftpClient.storeFile(filename, input);

            input.close();
            ftpClient.logout();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }

    }

    public void downloadFile(String path, String filename, OutputStream output) {
        FTPClient ftpClient = null;
        try {
            ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(path);
            ftpClient.retrieveFile(filename, output);
            output.close();
            ftpClient.logout();
        } catch (FileNotFoundException e) {
            System.out.println("没有找到" + path + "文件");
            e.printStackTrace();
        } catch (SocketException e) {
            System.out.println("连接FTP失败.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取错误。");
            e.printStackTrace();
        }
    }


    /**
     * 获取FTPClient对象
     *
     * @param ftpHost     FTP主机服务器
     * @param ftpPassword FTP 登录密码
     * @param ftpUserName FTP登录用户名
     * @param ftpPort     FTP端口 默认为21
     * @return
     */
    public FTPClient getFTPClient(String ftpHost, String ftpUserName,
                                  String ftpPassword, int ftpPort) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                System.out.println("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }
}
