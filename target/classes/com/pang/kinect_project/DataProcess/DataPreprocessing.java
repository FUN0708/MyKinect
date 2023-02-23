package com.pang.kinect_project.DataProcess;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据预处理
 * 滤波算法
 */
public class DataPreprocessing {
    /**
     * 读取文件保存骨骼关节点数据信息
     * 保存所有的每帧数据
     */
    public List<String[]> ReadProjectData(String filePath) {
        String line = "";
        String cvsSplitBy = ",";
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] row = new String[76]; // 一帧的关节点数据为75
                String[] Line = line.split(cvsSplitBy);//将逗号作为分隔符 划分一行 得到数组[]
//                System.out.println("一行数据的长度为："+Line.length);
                for(int i = 0; i < Line.length; i++){ // 每一行的第一个数据为时间
                    row[i] = Line[i]; // 将string类型转为double
                }
                data.add(row);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 获取骨骼关节点数据
     * @param list 读取的原始数据
     * @return 骨骼关节点数据
     */
    public List<double[]> ReadSkeletonData(List<String[]> list){
        List<double[]> data = new ArrayList<>();
        for(String[] s : list){
            double[] row = new double[75]; // 一帧的关节点数据为75
            for(int i = 1; i < s.length; i++){ // 每一行的第一个数据为时间
                row[i-1] = Double.parseDouble(s[i]); // 将string类型转为double
            }
            data.add(row);
        }
        return data;
    }

    /**
     * 获取某一个关节点的空间坐标序列
     * @param list 全部的骨骼数据
     * @param index 某一个关节点的索引
     * @return 某一关节点的三维坐标数据
     */
    public List<double[]> GetJointData(List<double[]> list, int index){
        List<double[]> joint = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){ // 遍历每帧的数据
            double[] point = new double[3]; // x, y, z
            for(int j = 0; j < 3; j++){ // 对应关节点的x,y,z
                point[j] = list.get(i)[index * 3 + j];
            }
            joint.add(point);
        }
        return joint;
    }

    /**
     * 获取选取的两帧之间的时间间隔
     * @param list 每帧的时间+25个骨骼关节点坐标数据
     * @param start 开始帧位置
     * @param end 结束帧位置
     * @return 返回时间间隔
     */
    public double GetTimeSpan(List<String[]> list, int start, int end){
        String t1 = list.get(start)[0];
        String t2 = list.get(end)[0];
        return CalculateTimeSpan(t1, t2);
    }
    /**
     * 计算整个检查的时间跨度
     * 时间以分：秒：毫秒为字符串传入
     * @param start 开始时间
     * @param end 结束时间
     * @return 以秒为单位返回
     */
    public double CalculateTimeSpan(String start, String end){
        // 对开始和结束时间进行划分
        String[] s = start.split(":");
        String[] e = end.split(":");
        // 将分、秒、毫秒转为int
        int[] t1 = new int[3];
        int[] t2 = new int[3];
        for(int i = 0; i < 3; i++){
            t1[i] = Integer.parseInt(s[i]);
            t2[i] = Integer.parseInt(e[i]);
        }
        double time;
        // 需要考虑58:30:211—— 01:2:122这种跨小时的情况
        if(t2[0] < t1[0]){
            int temp = 60 * 60000;
            time = temp - t1[0] * 60000 - t1[1] * 1000 - t1[2] + t2[0] * 60000 + t2[1] * 1000 + t2[2];
        } else { // 没有跨小时的情况,全部转化为毫秒计算
            time = t2[0] * 60000 + t2[1] * 1000 + t2[2] - (t1[0] * 60000 + t1[1] * 1000 + t1[2]);
        }
        return time / 1000.0; // 以秒为结果进行返回
    }
}
