package com.hujiang.mytest.fragment.algorithm.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Date:  2020-01-10
 * Time:  13:59
 * Author: jianglong
 * -----------------------------
 * 广度优先
 */
public class BFSAlgorithm {
    public static List<Integer> BFSByQueue(TreeNode root) {
        if (null == root) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        //存放遍历结果，然后返回
        List<Integer> result = new ArrayList<>();
        TreeNode tempNode;
        while (!queue.isEmpty()) {
            tempNode = queue.poll();
            result.add(tempNode.getVal());
            if (tempNode.getLeftNode() != null) {
                queue.add(tempNode.getLeftNode());
            }
            if (tempNode.getRightNode() != null) {
                queue.add(tempNode.getRightNode());
            }
        }
        return result;
    }

}
