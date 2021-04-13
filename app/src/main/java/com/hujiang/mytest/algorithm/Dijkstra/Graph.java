package com.hujiang.mytest.algorithm.Dijkstra;

import java.util.ArrayList;

/**
 * Date:  2020/8/20
 * Time:  11:31 AM
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
class Graph {
    public int max;
    public String[] vexs;
    public int pointSize;// 顶点数
    public int[][] matrix;//邻接矩阵
    public ArrayList<PointInfo> pointList;

    public Graph(int max) {
        this.max = max;
        this.matrix = new int[max][max];
    }
}

