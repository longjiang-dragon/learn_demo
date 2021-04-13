package com.hujiang.mytest.algorithm.Dijkstra;

import java.util.ArrayList;

/**
 * Date:  2020/8/20
 * Time:  11:31 AM
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
class Entry {

    void dijkstra(Graph G, String startPointName) {
        //已经获取得的启点到顶部的距离
        ArrayList<PointInfo> U = new ArrayList<>(G.pointSize);
        //未获取距离的点
        ArrayList<PointInfo> S = new ArrayList<>(G.pointSize);

        //添加第一个点
        U.add(new PointInfo(startPointName, 0));
        while (U.size() != G.pointSize) {
            PointInfo lastPointInfo = U.get(U.size() - 1);
            //添加与lastPointInfo相邻结点
            addNearPoint(G, lastPointInfo);

        }
    }

    void addNearPoint(Graph G, PointInfo tartPointInfo) {
        int LineIndex=G.pointList.indexOf(tartPointInfo);


    }
}
