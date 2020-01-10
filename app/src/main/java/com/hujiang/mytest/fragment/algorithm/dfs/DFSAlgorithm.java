package com.hujiang.mytest.fragment.algorithm.dfs;

import com.hujiang.mytest.fragment.algorithm.bfs.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Date:  2020-01-10
 * Time:  14:10
 * Author: jianglong
 * -----------------------------
 * 深度优先
 */
public class DFSAlgorithm {

    public static List<Integer> DFSByRecursion(TreeNode root) {
        if (null == root) return null;
        Queue queue = new LinkedList();
        List<Integer> result = new ArrayList<>();
        DFSByRecursionInner(queue, result, root);
        return result;
    }

    private static void DFSByRecursionInner(Queue queue, List<Integer> result, TreeNode node) {
        queue.add(node);
        result.add(node.getVal());
        if (node.getLeftNode() != null) {
            DFSByRecursionInner(queue, result, node.getLeftNode());
        }
        if (node.getRightNode() != null) {
            DFSByRecursionInner(queue, result, node.getRightNode());
        }
    }

}
