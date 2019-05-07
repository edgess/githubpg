package com.example.fileserver;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface SaveFile {

    public boolean createDirectory(String path, String createpath);

    public boolean delDirectory(String path, String delpath);

    public boolean delFile(String path, String filename);

    public boolean existDirectory(String path);

    public boolean existFile(String path, String filename);

    public List<String> listFiles(String path);

    public List<String> listDirectory(String path);

    public void uploadFile(String path, String filename, InputStream input);

    public void downloadFile(String path, String filename, OutputStream output);

}
