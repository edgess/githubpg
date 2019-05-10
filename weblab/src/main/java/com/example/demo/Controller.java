
package com.example.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class Controller {

    @Autowired
    private LabMapper labMapper;


    // space为多少采样数量取平均值，sc定义x轴中的显示的时间样式
    public String avg(List<Lab> labs, int sapce, String sc) {
        SimpleDateFormat weektime = new SimpleDateFormat(sc);
        List<String> categories = new ArrayList();
        List<String> tp = new ArrayList();
        List<String> hd = new ArrayList();
        for (int i = 0; i < (labs.size() / sapce); i++) {
            int sumt = 0;
            int sumh = 0;
            for (int j = 1; j < sapce + 1; j++) {
                sumt = sumt + Integer.parseInt(labs.get(labs.size() - i * sapce - j).getTp());
                sumh = sumh + Integer.parseInt(labs.get(labs.size() - i * sapce - j).getHd());
            }
            categories.add(weektime.format(labs.get(labs.size() - i * sapce - 1).getCreateTime()));
            tp.add(Integer.toString(sumt / sapce));
            hd.add(Integer.toString(sumh / sapce));
        }
        // xAxisData和seriesList转为json
        JSONObject jsob = new JSONObject();
        jsob.put("categories", categories);
        jsob.put("tp", tp);
        jsob.put("hd", hd);
        return jsob.toString();
    }

    @RequestMapping("getstatus")
    public String getstatus() {
        RestTemplate restTemplate = new RestTemplate();
        String url2 = "http://192.168.10.30/data.php";
        String str = restTemplate.getForEntity(url2, String.class).getBody();
        String strdone = null;
        // 去除字符串中的空格、回车、换行符、制表符
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            strdone = m.replaceAll("");
        }
        String day = strdone.substring(strdone.indexOf("<day>") + "<day>".length(), strdone.lastIndexOf("</day>"));
        String totle = strdone.substring(strdone.indexOf("<totle>") + "<totle>".length(),
                strdone.lastIndexOf("</totle>"));
        String used = strdone.substring(strdone.indexOf("<used>") + "<used>".length(), strdone.lastIndexOf("</used>"));
        String usep = strdone.substring(strdone.indexOf("<usep>") + "<usep>".length(), strdone.lastIndexOf("</usep>"));

        JSONObject jsob = new JSONObject();
        jsob.put("day", day);
        jsob.put("totle", totle);
        jsob.put("used", used);
        jsob.put("usep", usep);
        return jsob.toString();
    }

    @RequestMapping("getonemonthlog")
    public String getonemonthlog() {
        return avg(labMapper.getOneMonthLog(), 36, "dd日HH时");
    }

    @RequestMapping("getoneweeklog")
    public String getoneweeklog() {
        return avg(labMapper.getOneWeekLog(), 9, "dd日HH时");
    }

    @RequestMapping("getonedaylog")
    public String getonedaylog() {
        return avg(labMapper.getOneDayLog(), 1, "HH:mm");
    }

    @RequestMapping("getlastone")
    public String getlastone() {
        SimpleDateFormat baktime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        JSONObject jsob = new JSONObject();
        jsob.put("tp", labMapper.getlastone().getTp());
        jsob.put("hd", labMapper.getlastone().getHd());
        jsob.put("time", baktime.format(labMapper.getlastone().getCreateTime()));
        return jsob.toString();
    }

    @RequestMapping("getbak")
    public String getbak() {
        SimpleDateFormat baktime = new SimpleDateFormat("yyyy-MM-dd");
        List<Log> logs = labMapper.getbak();
        JSONArray array = new JSONArray();

        for (int i = 0; i < logs.size(); i++) {
            JSONObject jsob = new JSONObject();
            jsob.put("host", logs.get(i).getHost());

            jsob.put("file", (logs.get(i).getFile()) == 1 ? "YES" : "NO");
            jsob.put("md5", (logs.get(i).getMd5()) == 1 ? "YES" : "NO");
            jsob.put("mdstr", logs.get(i).getMdstr());
            jsob.put("date", baktime.format(logs.get(i).getDate()));
            array.add(jsob);
        }
        return array.toString();
    }

    @RequestMapping("getbakbypage")
    public String getbakbypage(String page, String limit) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<Log> logs = labMapper.getallbak();
        PageInfo<Log> pageInfo = new PageInfo<>(logs);
        JSONObject result = new JSONObject();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", pageInfo.getTotal());
        result.put("data", JSONArray.toJSON(logs));
        return result.toString();
    }

    @RequestMapping("getoilbypage")
    public String getoilbypage(String page, String limit, String username) {
        Integer idd;
        if (username != null) {
            idd = Integer.parseInt(username);
        } else {
            idd = 1;
        }
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<Oil> oils = labMapper.getalloil(idd);
        PageInfo<Oil> pageInfo = new PageInfo<>(oils);
        JSONObject result = new JSONObject();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", pageInfo.getTotal());
        result.put("data", JSONArray.toJSON(oils));
        return result.toString();
    }

    @RequestMapping("deloilsbyid")
    public String deloilsbyid(String[] ids) {
        if (null != ids) {
            for (String id : ids) {
                labMapper.deloilbyid(Integer.parseInt(id));
            }
            return "success";
        }
        return "error";
    }

    @RequestMapping("insertoil")
    public String insertoil(Oil oil) {
        labMapper.insertOil(oil);
        return "success";
    }

    @RequestMapping("updateoilbyid")
    public String updateoilbyid(Oil oil) {
        labMapper.updateoilbyid(oil);
        return "success";
    }
}
