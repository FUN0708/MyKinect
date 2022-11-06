package com.pang.kinect_project.contronller;

import com.pang.kinect_project.contronller.RequestDto.TestItemDto;
import com.pang.kinect_project.pojo.PatientTestItem;
import com.pang.kinect_project.result.Result;
import com.pang.kinect_project.service.PatientTestItemService;
import com.pang.kinect_project.utils.FileUtils;
import com.pang.kinect_project.utils.IdentityCheckUtils;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PatientTestItemRestController {
    // 注入文件保存路径
    @Value("${file.location}")
    public String fileLocation;
    @Autowired
    PatientTestItemService patientTestItemService;

    /**
     * 获取患者所有检测信息，包括检测日期和基本信息
     * 不包括检测的项目和数据文件，后续可以连接两个表进行联合查询
     * @param patientID
     * @return
     */

    @RequestMapping("/patientTest/getAllTestItemInfo")
    public List<PatientTestItem> getTestInfo(@RequestBody String patientID){
        List<PatientTestItem> list = new ArrayList<>();
        // 传入身份证号长度以及校验不通过，直接返回
        if(patientID.length() != 18 || !IdentityCheckUtils.idCardValidate(patientID)) return list;
        list = patientTestItemService.selectAllByPatientID(patientID);
        return list;
    }

    /**
     * 保存患者检测信息，以对象形式上传
     * 数据库只存储文件在服务器保存路径
     * 服务器实际存储位置根据文件名进行存储
     * @param testItemDto
     * @return
     * @throws IOException
     */

    @RequestMapping("/patientTest/saveTestItem")
    @ResponseBody
    public Result savePatientTestItem(TestItemDto testItemDto) throws IOException {
        Result result = new Result(400, "保存失败");
        // 身份校验不通过，直接返回
        if(!IdentityCheckUtils.idCardValidate(testItemDto.getPatientID())) return result;
        // 处理接收的文件信息
        InputStream inputStream = null;
        FileOutputStream fileOut = null;
        // 转换日期格式为yyyyMMdd去掉横杠作为文件保存路径之一
        String testDate = testItemDto.getTestDate();
        String[] date = testDate.split("-");
        StringBuffer sb = new StringBuffer();
        for(String s : date) sb.append(s);

        // 指定文件保存路径和文件名,以.txt格式保存
        String fileUrl = fileLocation + testItemDto.getPatientID()+"_"+sb.toString()+"_"+testItemDto.getProjectID()+".txt";
        System.out.println(fileUrl);
        MultipartFile file = testItemDto.getProjectFile();
        try {
            inputStream = file.getInputStream(); // 读取文件数据
            fileOut = new FileOutputStream(fileUrl); // 指定文件保存路径
            IOUtils.copy(inputStream, fileOut); // 文件上传保存
            fileOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭文件流
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOut != null) {
                    fileOut.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 转换为对象进行保存
        PatientTestItem ptItem = new PatientTestItem();
        ptItem.setPatientID(testItemDto.getPatientID());
        ptItem.setTestDate(testItemDto.getTestDate());
        ptItem.setProjectID(testItemDto.getProjectID());
        ptItem.setProjectFileUrl(fileUrl);
        // 插入患者信息
        result = patientTestItemService.insertPatientTestItem(ptItem);
        return result;
    }

    /**
     * 只接收文件信息，通过文件名保存检测项目信息
     * 上传的原格式为： PatientID_testDate_projectID.csv
     * @param file
     * @return
     */
    @RequestMapping("/patientTest/saveProjectFile")
    @ResponseBody
    public String saveProjectFile(MultipartFile file, HttpServletRequest request){
        // 还可以从文件名直接获取patientID, testDate,projectID
        // 上传的原格式为： PatientID_testDate_projectID.csv
        System.out.println("开始保存文件啦");
        System.out.println("完整文件名 = " + file.getOriginalFilename());
        // 获取上传文件的文件名
        String fileFullName = file.getOriginalFilename();
        String[] fileSplit = fileFullName.split("\\."); // 这里切分需要转义
//        System.out.println(fileSplit[0]);
        String fileSuffix = fileSplit[1]; // 文件后缀名
        String fileName = fileSplit[0]; // 文件名
        String[] fileUrl = fileName.split("_"); // 划分每个文件名
        String url = fileLocation + ""+fileUrl[0] + "/"+fileUrl[1] +"/project"+fileUrl[2] + "."+fileSuffix; // 保存在服务器的文件地址
        System.out.println(url);
        System.out.println("文件名转换成功");
        // 转换为对象进行保存
        PatientTestItem ptItem = new PatientTestItem();
        ptItem.setPatientID(fileUrl[0]); // 获取PatientID
        ptItem.setTestDate(fileUrl[1]); // 获取检测日期
        ptItem.setProjectID(Integer.parseInt(fileUrl[2])); // 将字符串转为projectID
        ptItem.setProjectFileUrl(url); // 文件在服务器的地址

        // 创建以patientID为名的子文件夹
        FileUtils.createDir(fileLocation +""+fileUrl[0]);
        // System.out.println("文件夹patientID创建成功");
        // 在patientID里面创建以testDate为名的子文件夹
        FileUtils.createDir(fileLocation +""+fileUrl[0]+"/"+fileUrl[1]);
        // System.out.println("文件夹testDate创建成功");

        // 保存文件
        InputStream inputStream = null;
        FileOutputStream fileOut = null;
        try {
            inputStream = file.getInputStream();
            // 文件在服务器的保存路径
            fileOut = new FileOutputStream(url);//这里可以改路径
            IOUtils.copy(inputStream, fileOut);
            fileOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return "文件保存失败";
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOut != null) {
                    fileOut.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 保存患者单个项目的检查信息
        Result result = patientTestItemService.insertPatientTestItem(ptItem);
        if(result.getCode() == 200) return "文件保存成功";
        else return "文件保存失败";
    }
}
