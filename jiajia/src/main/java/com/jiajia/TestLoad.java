package com.jiajia;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.VideoInputFrameGrabber;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;


public class TestLoad {
//    static {
//        System.load("D:\\soft\\openCV3\\opencv\\build\\java\\x64\\opencv_java345.dll");
//    }

    /**
     * 测试javacv
     * <p>Title: testLoadImg</p>
     * <p>Description: </p>
     *
     * @author 陈阳
     * @date 2019年1月25日
     */
    public static void testLoadImg() {
        //读取原始图片
        Mat image = imread("C:\\Users\\edge\\Desktop\\cv\\edge1.jpg");
        if (image.empty()) {
            System.err.println("加载图片出错，请检查图片路径！");
            return;
        }
        //显示图片
        imshow("image", image);

        //无限等待按键按下
        waitKey(0);
    }


    public static void takePhoto() throws IOException, InterruptedException {
        VideoInputFrameGrabber grabber = VideoInputFrameGrabber.createDefault(0);
        grabber.start();

        OpenCVFrameConverter.ToMat convertToMat = new OpenCVFrameConverter.ToMat();
        //加载检测器
        CascadeClassifier face_cascade = new CascadeClassifier("C:\\Users\\edge\\Desktop\\opencv\\data\\haarcascades_cuda\\haarcascade_frontalface_alt.xml");//初始化人脸检测器
//        CascadeClassifier face_cascade = new CascadeClassifier("/Users/edge/Desktop/cv/ttt/opencv/data/haarcascades_cuda/haarcascade_frontalface_alt.xml");//初始化人脸检测器
//        CascadeClassifier eye_cascade = new CascadeClassifier("/Users/edge/Desktop/cv/ttt/opencv/data/haarcascades_cuda/haarcascade_eye_tree_eyeglasses.xml");//初始化眼部检测器

        //定义人脸集合，矩形集合
        opencv_core.RectVector faces = new opencv_core.RectVector();
        System.out.println("start");
        String path = "C:\\Users\\edge\\Desktop\\cv\\edge.jpg";

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            Frame frame = grabber.grab();
            //获取当前帧图片
            Mat mat = convertToMat.convert(frame);
            if (mat.empty()) {
                continue;
            }
            //当前帧图片进行灰度+直方均衡
            Mat videoMatGray = new Mat();
            opencv_imgproc.cvtColor(mat, videoMatGray, opencv_imgproc.COLOR_BGRA2GRAY);
            opencv_imgproc.equalizeHist(videoMatGray, videoMatGray);

            //使用检测器进行检测，把结果放进集合中
            face_cascade.detectMultiScale(videoMatGray, faces);

            //把所有人脸数据绘制到图片中
            File imagePath = new File(path);
            String dir = imagePath.getParent();
            String name = imagePath.getName().substring(0, imagePath.getName().lastIndexOf("."));

            if (faces.size() == 0) {
                i = i - 1;
                continue;
            }
            opencv_core.Rect face = faces.get(0);

            Mat img_region = new Mat(mat, face);
//            imshow("人脸裁剪"+i, img_region);
            Mat resizeimg = new Mat();
            opencv_imgproc.resize(img_region, resizeimg, new opencv_core.Size(100, 100));

            imwrite(dir + File.separator + name + "_face" + i + ".png", resizeimg);
            Thread.sleep(1000);
        }
        System.out.println("end");
    }

    /**
     * 测试摄像头
     * <p>Title: testCamera</p>
     * <p>Description: </p>
     *
     * @throws Exception
     * @throws InterruptedException
     * @author 陈阳
     * @date 2019年1月25日
     */
    public static void testCamera() throws Exception, InterruptedException {
        VideoInputFrameGrabber grabber = VideoInputFrameGrabber.createDefault(0);
        grabber.start();
        CanvasFrame canvasFrame = new CanvasFrame("摄像头");
        canvasFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        canvasFrame.setAlwaysOnTop(true);
        while (true) {
            if (!canvasFrame.isDisplayable()) {
                grabber.stop();
                System.exit(-1);
            }
            Frame frame = grabber.grab();
            canvasFrame.showImage(frame);
            Thread.sleep(50);
        }
    }

    /**
     * 把人像图片的人脸识别并裁剪出来，保存到同目录
     * <p>Title: testFace</p>
     * <p>Description: </p>
     *
     * @param path
     * @throws Exception
     * @throws InterruptedException
     * @author 陈阳
     * @date 2019年1月26日
     */
    public static void testFace(String path) throws Exception, InterruptedException {
        Mat image = imread(path);
        if (image.empty()) {
            System.err.println("加载图片出错，请检查图片路径！");
            return;
        }
//        imshow("显示原始图像", image);

        opencv_core.RectVector faces = new opencv_core.RectVector();

        //加载检测器
        CascadeClassifier face_cascade = new CascadeClassifier("C:\\Users\\edge\\Desktop\\opencv\\data\\haarcascades_cuda\\haarcascade_frontalface_alt.xml");//初始化人脸检测器
//        CascadeClassifier face_cascade = new CascadeClassifier("/Users/edge/Desktop/cv/ttt/opencv/data/haarcascades_cuda/haarcascade_frontalface_alt.xml");//初始化人脸检测器
//        CascadeClassifier eye_cascade = new CascadeClassifier("/Users/edge/Desktop/cv/ttt/opencv/data/haarcascades_cuda/haarcascade_eye_tree_eyeglasses.xml");//初始化眼部检测器

        //当前帧图片进行灰度+直方均衡
        Mat videoMatGray = new Mat();
        opencv_imgproc.cvtColor(image, videoMatGray, opencv_imgproc.COLOR_BGRA2GRAY);
        opencv_imgproc.equalizeHist(videoMatGray, videoMatGray);

        //使用检测器进行检测，把结果放进集合中
        face_cascade.detectMultiScale(videoMatGray, faces);

        //把所有人脸数据绘制到图片中
        File imagePath = new File(path);
        String dir = imagePath.getParent();
        String name = imagePath.getName().substring(0, imagePath.getName().lastIndexOf("."));
        for (int i = 0; i < faces.size(); i++) {
            opencv_core.Rect face = faces.get(i);
            Mat img_region = new Mat(image, face);
//            imshow("人脸裁剪"+i, img_region);
            imwrite(dir + File.separator + name + " face" + i + ".png", img_region);
        }
//        waitKey(0);
    }

    /**
     * 测试自带人脸识别，视频识别
     * <p>Title: detectFace</p>
     * <p>Description: </p>
     *
     * @throws org.bytedeco.javacv.FrameGrabber.Exception
     * @throws InterruptedException
     * @author 陈阳
     * @date 2019年1月25日
     */
    public static void detectFace() throws org.bytedeco.javacv.FrameGrabber.Exception, InterruptedException {
        //连接摄像头
        VideoInputFrameGrabber grabber = VideoInputFrameGrabber.createDefault(0);
        grabber.start();   //开始获取摄像头数据

        OpenCVFrameConverter.ToMat convertToMat = new OpenCVFrameConverter.ToMat();
        //加载检测器
        CascadeClassifier face_cascade = new CascadeClassifier("C:\\Users\\edge\\Desktop\\opencv\\data\\haarcascades\\haarcascade_frontalface_alt.xml");//初始化人脸检测器
//        CascadeClassifier eye_cascade = new CascadeClassifier("C:\\Users\\edge\\Desktop\\opencv\\data\\haarcascades\\haarcascade_eye_tree_eyeglasses.xml");//初始化眼部检测器
//        CascadeClassifier face_cascade = new CascadeClassifier("/Users/edge/Desktop/cv/ttt/opencv/data/haarcascades_cuda/haarcascade_frontalface_alt.xml");//初始化人脸检测器
//        CascadeClassifier eye_cascade = new CascadeClassifier("/Users/edge/Desktop/cv/ttt/opencv/data/haarcascades_cuda/haarcascade_eye_tree_eyeglasses.xml");//初始化眼部检测器

        //定义人脸集合，矩形集合
        opencv_core.RectVector faces = new opencv_core.RectVector();
//        opencv_core.RectVector eyes = new opencv_core.RectVector();

        CanvasFrame canvas = new CanvasFrame("人脸识别", 1);//新建一个窗口
        canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        canvas.setAlwaysOnTop(true);

        //未关闭就一直向窗口刷新图片。就是视频
        while (true) {
            //
            if (!canvas.isVisible()) {
                break;
            }
            Frame frame = grabber.grab();

            //获取当前帧图片
            Mat mat = convertToMat.convert(frame);
            if (mat.empty())
                continue;
            //当前帧图片进行灰度+直方均衡
            Mat videoMatGray = new Mat();
            opencv_imgproc.cvtColor(mat, videoMatGray, opencv_imgproc.COLOR_BGRA2GRAY);
            opencv_imgproc.equalizeHist(videoMatGray, videoMatGray);

            //使用检测器进行检测，把结果放进集合中
            face_cascade.detectMultiScale(videoMatGray, faces);
//            eye_cascade.detectMultiScale(videoMatGray, eyes);

            //把所有人脸数据绘制到图片中
            for (int i = 0; i < faces.size(); i++) {
                opencv_core.Rect face = faces.get(i);
                opencv_imgproc.rectangle(mat, face, opencv_core.Scalar.RED, 1, 8, 0);
                opencv_imgproc.putText(mat, "yujiajun", new opencv_core.Point(face), 3, 1, new opencv_core.Scalar());

            }
//            for (int i = 0; i < eyes.size(); i++) {
//                opencv_core.Rect eye = eyes.get(i);
//                opencv_imgproc.rectangle(mat, eye, opencv_core.Scalar.GREEN, 1, 8, 0);
//            }

            //把图片刷新到窗口
            canvas.showImage(convertToMat.convert(mat));

            Thread.sleep(50);//30毫秒刷新一次图像
        }
    }

//    /**
//     * 直方图比较两个图片是否相似，可以用来比较两个人脸图片是否是同一个人
//     * <p>Title: CmpPic</p>
//     * <p>Description: </p>
//     *
//     * @param src
//     * @param des
//     * @return
//     * @author 陈阳
//     * @date 2019年1月26日
//     */
//    public static double CmpPic(String src, String des) {
//        System.out.println("\n==========直方图比较==========");
//        //自定义阈值
//        //相关性阈值，应大于多少，越接近1表示越像，最大为1
//        double HISTCMP_CORREL_THRESHOLD = 0.7;
//        //卡方阈值，应小于多少，越接近0表示越像
//        double HISTCMP_CHISQR_THRESHOLD = 2;
//        //交叉阈值，应大于多少，数值越大表示越像
//        double HISTCMP_INTERSECT_THRESHOLD = 1.2;
//        //巴氏距离阈值，应小于多少，越接近0表示越像
//        double HISTCMP_BHATTACHARYYA_THRESHOLD = 0.3;
//
//        try {
//
//            long startTime = System.currentTimeMillis();
//
//            Mat mat_src = imread(src);
//            Mat mat_des = imread(des);
//
//            if (mat_src.empty() || mat_des.empty()) {
//                throw new Exception("no file.");
//            }
//
//            Mat hsv_src = new Mat();
//            Mat hsv_des = new Mat();
//
//            // 转换成HSV
//            opencv_imgproc.cvtColor(mat_src, hsv_src, opencv_imgproc.COLOR_BGR2HSV);
//            opencv_imgproc.cvtColor(mat_des, hsv_des, opencv_imgproc.COLOR_BGR2HSV);
//
//            List<Mat> listImg1 = new ArrayList<Mat>();
//            List<Mat> listImg2 = new ArrayList<Mat>();
//            listImg1.add(hsv_src);
//            listImg2.add(hsv_des);
//
////            opencv_core.Range ranges = new opencv_core.Range(0, 255);
//
////            MatOfInt histSize = new MatOfInt(50);
////            MatOfInt channels = new MatOfInt(0);
//
//            Mat histImg1 = new Mat();
//            Mat histImg2 = new Mat();
//
//            //org.bytedeco.javacpp中的方法不太了解参数，所以直接上org.opencv中的方法，所以需要加载一下dll，System.load("D:\\soft\\openCV3\\opencv\\build\\java\\x64\\opencv_java345.dll");
//            //opencv_imgproc.calcHist(images, nimages, channels, mask, hist, dims, histSize, ranges, uniform, accumulate);
//            opencv_imgproc.calcHist();
////            opencv_imgproc.calcHist(listImg1, 0, new Mat(), histImg1, 50,ranges);
////            opencv_imgproc.calcHist(listImg2, channels, new Mat(), histImg2, histSize, ranges);
//
//
//            opencv_core.normalize(histImg1, histImg1, 0d, 1d, opencv_core.NORM_MINMAX, -1, new Mat());
//            opencv_core.normalize(histImg2, histImg2, 0d, 1d, opencv_core.NORM_MINMAX, -1, new Mat());
//
//            double result0, result1, result2, result3;
//            result0 = opencv_imgproc.compareHist(histImg1, histImg2, opencv_imgproc.HISTCMP_CORREL);
//            result1 = opencv_imgproc.compareHist(histImg1, histImg2, opencv_imgproc.HISTCMP_CHISQR);
//            result2 = opencv_imgproc.compareHist(histImg1, histImg2, opencv_imgproc.HISTCMP_INTERSECT);
//            result3 = opencv_imgproc.compareHist(histImg1, histImg2, opencv_imgproc.HISTCMP_BHATTACHARYYA);
//
//            System.out.println("相关性（度量越高，匹配越准确 [基准：" + HISTCMP_CORREL_THRESHOLD + "]）,当前值:" + result0);
//            System.out.println("卡方（度量越低，匹配越准确 [基准：" + HISTCMP_CHISQR_THRESHOLD + "]）,当前值:" + result1);
//            System.out.println("交叉核（度量越高，匹配越准确 [基准：" + HISTCMP_INTERSECT_THRESHOLD + "]）,当前值:" + result2);
//            System.out.println("巴氏距离（度量越低，匹配越准确 [基准：" + HISTCMP_BHATTACHARYYA_THRESHOLD + "]）,当前值:" + result3);
//
//            //一共四种方式，有三个满足阈值就算匹配成功
//            int count = 0;
//            if (result0 > HISTCMP_CORREL_THRESHOLD)
//                count++;
//            if (result1 < HISTCMP_CHISQR_THRESHOLD)
//                count++;
//            if (result2 > HISTCMP_INTERSECT_THRESHOLD)
//                count++;
//            if (result3 < HISTCMP_BHATTACHARYYA_THRESHOLD)
//                count++;
//            int retVal = 0;
//            if (count >= 3) {
//                //这是相似的图像
//                retVal = 1;
//            }
//
//            long estimatedTime = System.currentTimeMillis() - startTime;
//
//            System.out.println("花费时间= " + estimatedTime + "ms");
//
//            return retVal;
//        } catch (Exception e) {
//            System.out.println("例外:" + e);
//        }
//        return 0;
//    }

    public static void main(String[] args) {
        try {
//          testLoadImg();
            detectFace();
//			testCamera();
//			testFace("C:\\Users\\edge\\Desktop\\cv\\face.jpg");
//          System.out.println(CmpPic("C:\\Users\\edge\\Desktop\\cv\\edge1.jpg", "C:\\Users\\edge\\Desktop\\cv\\edge2.jpg"));
//          takePhoto();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
