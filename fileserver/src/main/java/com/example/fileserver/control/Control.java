package com.example.fileserver.control;

import com.edge.dao.server.Name2FastDFSService;
import com.edge.entity.Name2FastDFS;
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

    @Autowired
    Name2FastDFSService name2FastDFSService;

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

    @ApiOperation(value = "下载文件url", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "path", value = "路径", paramType = "query"),
            @ApiImplicitParam(name = "filename", value = "文件名", required = true, paramType = "query")
    })
    @RequestMapping(value = "downloadfileUrl", method = {RequestMethod.POST, RequestMethod.GET})
    public String downloadfileUrl(@RequestParam(value = "path", required = false, defaultValue = "/") String path, String filename, HttpServletResponse response) throws IOException {
//        saveFile.downloadFileUrl(path, filename, os);
        Name2FastDFS name2FastDFS = new Name2FastDFS();
        name2FastDFS.setName(filename.substring(0, filename.lastIndexOf(".")));
        name2FastDFS.setExt(filename.substring(filename.lastIndexOf("."), filename.length()));
        Name2FastDFS name2FastDFS2 = name2FastDFSService.findFdfsbyName(name2FastDFS);
        if (null == name2FastDFS2) {
            return "null";
        } else {
            return "http://192.168.10.184/" + name2FastDFS2.getFdfs();
        }
    }
}



