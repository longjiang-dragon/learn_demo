package com.hujiang.mytest.algorithm.Dijkstra;

import java.util.Objects;

/**
 * Date:  2020/8/20
 * Time:  11:16 AM
 * Author: jianglong
 * -----------------------------
 * 边的信息
 */
class PointInfo implements Comparable<PointInfo> {
    private String currentPointName; // 当前点的名字
    private int weight; // 边的权重

    public PointInfo(String currentPointName, int weight) {
        this.currentPointName = currentPointName;
        this.weight = weight;
    }


    @Override
    public int compareTo(PointInfo o) {
        return this.weight - o.weight;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointInfo pointInfo = (PointInfo) o;
        return weight == pointInfo.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }
}
