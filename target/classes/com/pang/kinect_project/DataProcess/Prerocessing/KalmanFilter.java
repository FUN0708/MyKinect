package com.pang.kinect_project.DataProcess.Prerocessing;

public class KalmanFilter {
    private  double prevData;
    private  double P;
    private  double Q;
    private  double R;

    public KalmanFilter() {
        prevData = 0;
        P = 10;
        Q = 0.0001;
        R = 0.005;
    }
    void Init(double prevData, double P,double Q,double R)
    {
        this.prevData = prevData;
        this.P = P;
        this.Q = Q;
        this.R = R;
    }
    public double filter(double inData){
        //下面的变量仅参与中间计算
        double Kg;//卡尔曼增益
        double currData;//当前时刻的先验估计值
        double currP;//当前时刻的先验估计协方差

        //预测
        currData = prevData;//根据上一时刻的后验估计预测出当前时刻的先验估计
        currP = P + Q;//根据上一时刻的后验估计协方差预测出当前时刻的先验估计协方差

        //更新
        Kg = currP / (currP + R);
        prevData = currData + Kg*(inData - currData);
        P = (1 - Kg)*currP;
        return prevData;
    }
}
