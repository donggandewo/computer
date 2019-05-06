package com.zzh.util;

import java.util.Date;

public class Recommend {
    //全部参数
    public static double getRecommend(double newPrice, double oldPrice, double performance, double media, int mediaNum, int sales, Date addTime) {

        double sale = (double) sales / 10000;
        double newPrice2 = newPrice / 100;
        int days = getDays(addTime);

        double recommend = performance / newPrice2 * 6;
        recommend += sale / days * 12;
        recommend += (1 - (newPrice / oldPrice)) * 38;
        recommend += media / mediaNum * 0.3;
        return recommend;
    }

    //只需要折扣力度和销量
    public static double getRecommend(double newPrice, double oldPrice, int sales, Date addTime) {
        double sale = sales / 10000;
        int days = getDays(addTime);
        double recommend = sale / days * 12 + (1 - (newPrice / oldPrice)) * 10;
        return recommend;
    }

    public static int getDays(Date addTime) {
        Date date = new Date();
        int day = (int) ((date.getTime() - addTime.getTime()) / (1000 * 3600 * 24));
        //不满一天按照一天计算
        if (day == 0) {
            day = 1;
        }
        return day;
    }
}
