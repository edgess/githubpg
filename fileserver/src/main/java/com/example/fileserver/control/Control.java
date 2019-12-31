package com.example.fileserver.control;

import com.example.fileserver.Result;
import com.example.fileserver.SaveFile;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class Control {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SaveFile saveFile;


    public static void main(String[] args) throws Exception {

        String filename = "tw.123.jpg";
        System.out.println(filename.substring(0, filename.lastIndexOf(".")));
        System.out.println(filename.substring(filename.lastIndexOf("."),filename.length()));


//        String confUrl = Thread.currentThread().getContextClassLoader().getResource("fastdfs_client.conf").getPath();
//        FastDFSClient fastDFSClient = new FastDFSClient(confUrl);

//        FileInfo file=fastDFSClient.getFile("group1","M00/00/00/wKgKuF4EX2eAZ6beAADkW_3yAvw960.jpg");
//        System.out.println("获取文件信息成功："+file.getFileSize());

//        String  filePath= fastDFSClient.uploadFile("C:\\Users\\Admini\\Desktop\\tw.jpg");
//        System.out.println("返回路径："+filePath);

//        FileSystemView fsv = FileSystemView.getFileSystemView();
//        File com = fsv.getHomeDirectory(); //读取桌面路径
//        int downFlag = fastDFSClient.download_file("group1/M00/00/00/wKgKuF4DES6AOoitAAQ5sg9aABU945.jpg", new BufferedOutputStream(new FileOutputStream(com.getPath() + "\\aedgea.jpg")));
//        System.out.println("下载结果为：" + (downFlag == 0 ? "下载文件成功" : "下载文件失败"));

//        int flag=fastDFSClient.delete_file("group1/M00/00/00/wKgKuF4EX2eAZ6beAADkW_3yAvw960.jpg");
//        System.out.println("删除结果：" +(flag==0?"删除成功":"删除失败"));

    }


//    @RequestMapping("updata2")
//    public String updata2(HttpServletRequest request) throws IOException {
//
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//        if (multipartResolver.isMultipart(request)) {
//            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//            MultipartFile file = multiRequest.getFile(multiRequest.getFileNames().next().toString());
//            if (file != null) {
//                String localPath = "/Users/edge/Desktop/ftp/" + file.getOriginalFilename() + new Date().getTime();
//                // 上传
//                file.transferTo(new File(localPath));
//            }
//        }
//        return "success";
//    }

    //    @RequestMapping("updata")
//    public String updata(MultipartFile file) throws IOException {
////        if (file.isEmpty()) {
////            return "updata error,select file";
////        }
//
//        String fileName = file.getOriginalFilename();
//        System.out.println(fileName);
////        InputStream in = file.getInputStream();
////        String filePath = "/Users/edge/Desktop/ftp/";
////        File dest = new File(filePath + fileName);
////
////        try {
////            file.transferTo(dest);
////            return "updata success";
////        } catch (IOException e) {
////
////        }
//        return "updata error";
//
//    }
    @ApiOperation(value = "删除文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "path", value = "路径", paramType = "query"),
            @ApiImplicitParam(name = "filename", value = "文件名", required = true, paramType = "query")
    })
    @PostMapping("delfile")
    public Result delfile(@RequestParam(value = "path", required = false, defaultValue = "/") String path, String filename) {
        Result result = new Result();
        if (saveFile.existDirectory(path)) {
            if (saveFile.delFile(path, filename)) {
                return result;
            } else {
                result.setSuccess(false);
                result.setMsg("file not exist");
                return result;
            }
        } else {
            result.setSuccess(false);
            result.setMsg("path not exist");
            return result;
        }
    }

    @ApiOperation(value = "删除目录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "path", value = "路径", paramType = "query"),
            @ApiImplicitParam(name = "delpath", value = "目录名", required = true, paramType = "query")
    })
    @PostMapping("deldir")
    public Result deldir(@RequestParam(value = "path", required = false, defaultValue = "/") String path, String delpath) {
        Result result = new Result();
        if (saveFile.existDirectory(path)) {
            if (saveFile.delDirectory(path, delpath)) {
                return result;
            } else {
                result.setSuccess(false);
                result.setMsg("delpath not exist");
                return result;
            }
        } else {
            result.setSuccess(false);
            result.setMsg("path not exist");
            return result;
        }
    }

    @ApiOperation(value = "查看文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "path", value = "路径", paramType = "query"),
    })
    @PostMapping("listfile")
    public Result listfile(@RequestParam(value = "path", required = false, defaultValue = "/") String path) {
        Result result = new Result();

        if (saveFile.existDirectory(path)) {
            result.setData(saveFile.listFiles(path));
            return result;
        } else {
            result.setSuccess(false);
            result.setMsg("path not exist");
            return result;
        }
    }

    @ApiOperation(value = "查看目录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "path", value = "路径", paramType = "query"),
    })
    @PostMapping("listdir")
    public Result listdir(@RequestParam(value = "path", required = false, defaultValue = "/") String path) {
        Result result = new Result();
//        logger.error("listdir logger!");
        if (saveFile.existDirectory(path)) {
            result.setData(saveFile.listDirectory(path));
            return result;
        } else {
            result.setSuccess(false);
            result.setMsg("path not exist");
            return result;
        }
    }

    @ApiOperation(value = "判断文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "path", value = "路径", paramType = "query"),
            @ApiImplicitParam(name = "filename", value = "文件名", required = true, paramType = "query")
    })
    @PostMapping("existfile")
    public Result existfile(@RequestParam(value = "path", required = false, defaultValue = "/") String path, String filename) {
        Result result = new Result();
        if (saveFile.existDirectory(path)) {
            if (saveFile.existFile(path, filename)) {
                return result;
            } else {
                result.setSuccess(false);
                result.setMsg("file not exist");
                return result;
            }
        } else {
            result.setSuccess(false);
            result.setMsg("path not exist");
            return result;
        }
    }

    @ApiOperation(value = "判断目录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "path", value = "路径", paramType = "query"),
    })
    @PostMapping("existdir")
    public Result existdir(@RequestParam(value = "path", required = false, defaultValue = "/") String path) {
        Result result = new Result();
        if (saveFile.existDirectory(path)) {
            return result;
        } else {
            result.setSuccess(false);
            return result;
        }
    }

    @ApiOperation(value = "上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "path", value = "路径", paramType = "query"),
//            @ApiImplicitParam(name = "request", value = "文件流", required = true, paramType = "query"),
//            @ApiImplicitParam(name = "multipartFile", value = "文件", required = true)
    })
    @PostMapping(value = "uploadfile", headers = "content-type=multipart/form-data")
    public Result uploadfile(@RequestParam(value = "path", required = false, defaultValue = "/") String path,
                             MultipartFile file) throws IOException {
        Result result = new Result();
        if (!saveFile.existDirectory(path)) {
            result.setSuccess(false);
            result.setMsg("path not exist");
            return result;
        }
        String filename = file.getOriginalFilename();
        if (saveFile.existFile(path, filename)) {
            result.setSuccess(false);
            result.setMsg("file is exist");
            return result;
        }
        // 上传
        saveFile.uploadFile(path, filename, file.getInputStream());
        return result;
    }

//    @ApiOperation(value = "上传文件")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "path", value = "路径", required = true, paramType = "query"),
//            @ApiImplicitParam(name = "request", value = "文件流", required = true, paramType = "query"),
//            @ApiImplicitParam(name = "multipartFile", value = "路径", required = true, paramType = "query"),
//
//    })
//    @PostMapping(value = "uploadfile")
//    public Result uploadfile(String path, HttpServletRequest request) throws IOException {
//        Result result = new Result();
//
//        if (!saveFile.existDirectory(path)) {
//            result.setSuccess(false);
//            result.setMsg("path not exist");
//            return result;
//        }
//
////        SaveFile2SMB saveFile2SMB =new SaveFile2SMB();
//        // 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
//        CommonsMultipartResolver multipartResolver =
//                new CommonsMultipartResolver(request.getSession().getServletContext());
//        // 检查form中是否有enctype="multipart/form-data"
//        if (multipartResolver.isMultipart(request)) {
//            // 将request变成多部分request
//            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//            // 获取multiRequest 中所有的文件名
//            // Iterator iter = multiRequest.getFileNames();
//            // while (multiRequest.getFileNames().hasNext()) {
//            // 一次遍历所有文件
//            MultipartFile file = multiRequest.getFile(multiRequest.getFileNames().next().toString());
//            if (file != null) {
////              String path2 = "/Users/edge/Desktop/ftp/" + file.getOriginalFilename() + new Date().getTime();
//                String filename = file.getOriginalFilename();
//                // 上传
////              file.transferTo(new File(path2));
//                System.out.println(saveFile.existFile(path, filename));
//                if (!saveFile.existFile(path, filename)) {
//                    saveFile.uploadFile(path, filename, file.getInputStream());
//                } else {
//                    result.setSuccess(false);
//                    result.setMsg("file is exist");
//                    return result;
//
//                }
//            }
//        }
//        result.setData(saveFile.listFiles(path));
//        return result;
//    }

    @ApiOperation(value = "新增目录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "path", value = "路径", paramType = "query"),
            @ApiImplicitParam(name = "createpath", value = "创建目录名", required = true, paramType = "query")
    })
    @PostMapping("createdir")
    public Result createdir(@RequestParam(value = "path", required = false, defaultValue = "/") String path, String createpath) {
        Result result = new Result();
        if (saveFile.existDirectory(path)) {
            if (saveFile.createDirectory(path, createpath)) {
                return result;
            } else {
                result.setSuccess(false);
                result.setMsg("createpath is exist");
                return result;
            }
        } else {
            result.setSuccess(false);
            result.setMsg("path not exist");
            return result;
        }
    }

    @ApiOperation(value = "下载文件", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "path", value = "路径", paramType = "query"),
            @ApiImplicitParam(name = "filename", value = "文件名", required = true, paramType = "query")
    })
    @RequestMapping(value = "downloadfile", method = {RequestMethod.POST, RequestMethod.GET})
    public void downloadfile(@RequestParam(value = "path", required = false, defaultValue = "/") String path, String filename, HttpServletResponse response) {
        try {
            String filenameout = new String(filename.getBytes("UTF-8"), "iso-8859-1");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;filename=" + filenameout);
            OutputStream os = response.getOutputStream();
            saveFile.downloadFile(path, filename, os);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



