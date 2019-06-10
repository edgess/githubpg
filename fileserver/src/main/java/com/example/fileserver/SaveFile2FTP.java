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
    @Value("${fileserver.ftp.ftpHost}")
    private String ftpHost;
    @Value("${fileserver.ftp.ftpUserName}")
    private String ftpUserName;
    @Value("${fileserver.ftp.ftpPassword}")
    private String ftpPassword;
    @Value("${fileserver.ftp.ftpPort}")
    private int ftpPort;

    public boolean createDirectory(String path, String createpath) {
        FTPClient ftpClient = getFTPClient();
        try {
            ftpClient.changeWorkingDirectory(path);
            ftpClient.makeDirectory(createpath);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                ftpClient.disconnect();
            } catch (Exception e) {
                System.out.println("关闭错误");
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean delDirectory(String path, String delpath) {
        FTPClient ftpClient = getFTPClient();
        try {
            ftpClient.changeWorkingDirectory(path);
            System.out.println(ftpClient.removeDirectory(delpath));
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                ftpClient.disconnect();
            } catch (Exception e) {
                System.out.println("关闭错误");
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean delFile(String path, String filename) {
        FTPClient ftpClient = getFTPClient();
        try {
            ftpClient.changeWorkingDirectory(path);
            if (ftpClient.dele(filename) == 250) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            try {
                ftpClient.disconnect();
            } catch (Exception e) {
                System.out.println("关闭错误");
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean existDirectory(String path) {
        FTPClient ftpClient = getFTPClient();
        try {
            if (ftpClient.changeWorkingDirectory(path)) {
                return true;
            } else {
                return false;
            }
        } catch (FileNotFoundException e) {
            System.out.println("没有找到" + path + "文件");
            e.printStackTrace();
        } catch (SocketException e) {
            System.out.println("连接FTP失败.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("文件读取错误。");
            e.printStackTrace();
        } finally {
            try {
                ftpClient.disconnect();
            } catch (Exception e) {
                System.out.println("关闭错误");
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean existFile(String path, String filename) {
        List<String> name = new ArrayList<>();
        FTPClient ftpClient = getFTPClient();
        try {
            if (ftpClient.changeWorkingDirectory(path)) {
                FTPFile[] ff = ftpClient.listFiles();
                for (FTPFile file : ff) {
                    if (file.isFile()) {
                        name.add(file.getName());
                    }
                }
                if (name.contains(filename)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (FileNotFoundException e) {
            System.out.println("没有找到" + path + "文件");
            e.printStackTrace();
        } catch (SocketException e) {
            System.out.println("连接FTP失败.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("文件读取错误。");
            e.printStackTrace();
        } finally {
            try {
                ftpClient.disconnect();
            } catch (Exception e) {
                System.out.println("关闭错误");
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<String> listFiles(String path) {
        List<String> name = new ArrayList<>();
        FTPClient ftpClient = getFTPClient();
        try {
            if (ftpClient.changeWorkingDirectory(path)) {
                FTPFile[] ff = ftpClient.listFiles();
                for (FTPFile file : ff) {
                    if (file.isFile()) {
                        name.add(file.getName());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("没有找到" + path + "文件");
            e.printStackTrace();
        } catch (SocketException e) {
            System.out.println("连接FTP失败.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("文件读取错误。");
            e.printStackTrace();
        } finally {
            try {
                ftpClient.disconnect();
            } catch (Exception e) {
                System.out.println("关闭错误");
                e.printStackTrace();
            }
        }
        return name;
    }

    @Override
    public List<String> listDirectory(String path) {
        List<String> name = new ArrayList<>();
        FTPClient ftpClient = getFTPClient();
        try {
            if (ftpClient.changeWorkingDirectory(path)) {
                FTPFile[] fd = ftpClient.listDirectories();
                for (FTPFile ftpFile : fd) {
                    name.add(ftpFile.getName() + "/");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("没有找到" + path + "文件");
            e.printStackTrace();
        } catch (SocketException e) {
            System.out.println("连接FTP失败.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("文件读取错误。");
            e.printStackTrace();
        } finally {
            try {
                ftpClient.disconnect();
            } catch (Exception e) {
                System.out.println("关闭错误");
                e.printStackTrace();
            }
        }
        return name;
    }

    @Override
    public void uploadFile(String path, String filename, InputStream input) {
        FTPClient ftpClient = getFTPClient();
        try {
            ftpClient.changeWorkingDirectory(path);
            ftpClient.storeFile(filename, input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ftpClient.disconnect();
            } catch (Exception e) {
                System.out.println("关闭错误");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void downloadFile(String path, String filename, OutputStream output) {
        FTPClient ftpClient = getFTPClient();
        try {
            ftpClient.changeWorkingDirectory(path);
            ftpClient.retrieveFile(filename, output);
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
        } finally {
            try {
                output.close();
                ftpClient.disconnect();
            } catch (Exception e) {
                System.out.println("关闭错误");
                e.printStackTrace();
            }
        }
    }

    public FTPClient getFTPClient() {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                System.out.println("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            }
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
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
