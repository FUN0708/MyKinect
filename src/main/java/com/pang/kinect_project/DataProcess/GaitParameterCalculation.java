package com.pang.kinect_project.DataProcess;

import java.util.ArrayList;
import java.util.List;

/**
 * 检测项目一：
 * 基本步态参数的计算
 */
public class GaitParameterCalculation {
    public List<Double[]> getJointPoint() {
        return jointPoint;
    }

    public void setJointPoint(List<Double[]> jointPoint) {
        this.jointPoint = jointPoint;
    }

    private List<Double[]> jointPoint; // 所有关节序列数据
    public GaitParameterCalculation(List<Double[]> jointPoint){
        this.jointPoint = jointPoint;
    }
    /**
     * 计算左右脚的步幅
     * @param pointIndex 关节点序号
     * @return 左脚步幅
     */
    private double stepStride(int pointIndex){
        int angleSize = jointPoint.size();
        int h = 14;
        int[] leftCount = new int[14];
        for (Double[] point : jointPoint){
            for (int j = 0; j < h; j++){
                if((point[pointIndex] >= j * 0.25 +1) && (point[pointIndex] < j * 0.25 +1.25)){
                    leftCount[j]++;
                }
            }
        }
        double[] midPoint = new double[h];
        for(int i = 0; i < h-1; i++){
            if(leftCount[i] - leftCount[i+1] >= 7){
                midPoint[i] = (2.5 * i + 11.25) / 10;
            }
        }
        List<Double> arr2 = new ArrayList<>();
        List<Double> arr4 = new ArrayList<>();
        for(int i = 0; i < h; i++){
            if(midPoint[i] > 0){
                arr4.add(midPoint[i]);
            }
        }
        for(int i = 0; i < arr4.size() - 1; i++){
            double stride = arr4.get(i+1) - arr4.get(i);
            arr2.add(stride);
        }
        double sum = 0;
        for (double i : arr2) {
            sum += i;
        }
        return  sum / arr2.size();
    }

    /**
     * 计算步速
     * @return 返回平均步速
     */
    private double stepSpeed(){
        return 0;
    }

    /**
     * 计算步长
     * @return 返回平均步长
     */
    private double stepLength(){
        Double[] leftAngle = jointPoint.get(57);
        Double[] rightAngle = jointPoint.get(60);
        return 0;
    }

    /**
     * 计算膝关节角度，换关节角度
     * @param spineMid 脊柱SpineMidX
     * @param spineBase SpineBaseX
     * @param hip HipX
     * @param knee KneeX
     * @param ankle AnkleX
     * @return List<Double[]> angle: 第一个表示膝关节角度，第二个表示踝关节角度
     */
    private List<Double[]> angleCaculate(int spineMid, int spineBase, int hip, int knee, int ankle){
//        List<Double> knee_angle = new ArrayList<>();
//        List<Double> ankle_angle = new ArrayList<>();
        List<Double[]> kneeAndAnkleAngle = new ArrayList<>();
        for(Double[] p : jointPoint){
            Double[] angle = new Double[2];
            // SpineMid到SPineBase的空间距离
            double spineToHipX = p[spineMid] - p[spineBase];
            double spineToHipY = p[spineMid + 1] - p[spineBase + 1];
            double spineToHipZ = p[spineMid + 2] - p[spineBase + 2];
            // Hip到Knee的空间位置
            double hipToKneeX = p[hip] - p[knee];
            double hipToKneeY = p[hip + 1] - p[knee + 1];
            double hipToKneeZ = p[hip + 2] - p[knee + 2];
            // Knee到Ankle的距离
            double kneeToAnkleX = p[knee] - p[ankle];
            double kneeToAnkleY = p[knee + 1] - p[ankle + 1];
            double kneeToAnkleZ = p[knee + 2] - p[ankle + 2];
            // 计算膝关节角度
            double A1 = hipToKneeX * kneeToAnkleX + hipToKneeY * kneeToAnkleY + hipToKneeZ * kneeToAnkleZ;
            double B1 = Math.sqrt(Math.pow(hipToKneeX, 2) +Math.pow(hipToKneeY, 2) + Math.pow(hipToKneeY, 2) +
                    Math.pow(kneeToAnkleX, 2) + Math.pow(kneeToAnkleY, 2) +Math.pow(kneeToAnkleZ, 2));
            double kneeAngle = Math.acos(A1/B1) * 180000 / Math.PI;
            if(kneeAngle / 1000 > 0 && kneeAngle / 1000 < 101){
//                knee_angle.add(kneeAngle / 1000);
                angle[0] = kneeAngle;
            }

            // 计算踝关节角度
            double A2 = spineToHipX * hipToKneeX + spineToHipY * hipToKneeY + spineToHipZ * hipToKneeZ;
            double B2 = Math.sqrt(Math.pow(spineToHipX, 2) + Math.pow(spineToHipY, 2) + Math.pow(spineToHipZ, 2) +
                    Math.pow(hipToKneeX, 2) + Math.pow(hipToKneeY, 2) + Math.pow(hipToKneeZ, 2));
            double ankleAngle = Math.acos(A2/B2) * 180000 / Math.PI;
            if(ankleAngle / 1000 > 0 && ankleAngle / 1000 < 101){
//                ankle_angle.add(ankleAngle / 1000);
                angle[1] = kneeAngle;
            }
            kneeAndAnkleAngle.add(angle);
        }
        return kneeAndAnkleAngle;
    }

    /**
     * 计算质心序列
     * @param weight 体重
     * @return 质心移动序列
     */
    private List<Double> baryCenter(double weight){
        //节段长度的百分比(近端，远端)
        List<Double> list = new ArrayList<>();
        double[] segmentLenPercent = {1,0,0.500,0.500,0.436,0.564,0.436,0.564,0.430,0.570,0.430,0.570,0.506,0.494,0.506,0.494,0.105,0.895,
                0.433,0.567,0.433,0.567,0.433,0.567,0.433,0.567,0.500,0.500,0.500,0.500};

        //节段质量/总体质量 百分比
        double[] segmentWeightPercent = {0.081,0.355,0.028,0.028,0.016,0.016,0.006,0.006,0.142,0.100,0.100,0.0465,
                0.0465,0.0145,0.0145};

        double[] segmentWeight = new double[segmentLenPercent.length];
        List<Double[]> footLeft = getJointCoordinate(jointPoint, 45, 27);
        List<Double[]> leftAnkle = getJointCoordinate(jointPoint, 42, 26);
        List<Double[]> footRight = getJointCoordinate(jointPoint, 57, 29);
        List<Double[]> rightAnkle = getJointCoordinate(jointPoint, 54, 28);
//        List<Double[]>
        return list;
    }

    /**
     * 获取某个关节点的三维坐标序列与对应节段比例的结果
     * @param jointPoint： 全部关节点序列数据
     * @param jointIndex： 关节点索引
     * @param index： 对应节段索引
     * @return 关节点坐标与质心节段结果
     */
    private static List<Double[]> getJointCoordinate(List<Double[]> jointPoint, int jointIndex, int index){
        //节段长度的百分比(近端，远端)
        double[] segmentLenPercent = {1,0,0.500,0.500,0.436,0.564,0.436,0.564,0.430,0.570,0.430,0.570,0.506,0.494,0.506,0.494,0.105,0.895,
                0.433,0.567,0.433,0.567,0.433,0.567,0.433,0.567,0.500,0.500,0.500,0.500};
        List<Double[]> list = new ArrayList<>();
        for(Double[] p : jointPoint){
            Double[] coordinate = new Double[3];
            coordinate[0] = p[jointIndex] * segmentLenPercent[index];
            coordinate[1] = p[jointIndex + 1] * segmentLenPercent[index];
            coordinate[2] = p[jointIndex + 2] * segmentLenPercent[index];
            list.add(coordinate);
        }
        return list;
    }

    /**
     * 计算两个关节点的节段的质心坐标
     * @param joint1：关节点1
     * @param joint2： 关节点2
     * @return 质心坐标
     */
    private static List<Double[]> jointCount(List<Double[]> joint1, List<Double[]> joint2){
        List<Double[]> list = new ArrayList<>();
        for(int i = 0; i < joint1.size(); i++){
            double x = joint1.get(i)[0] + joint2.get(i)[0];
            double y = joint1.get(i)[1] + joint2.get(i)[1];
            double z = joint1.get(i)[2] + joint2.get(i)[2];
            list.add(new Double[]{x, y,z});
        }
        return list;
    }
}
