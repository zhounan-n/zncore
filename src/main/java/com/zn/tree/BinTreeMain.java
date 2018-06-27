package com.zn.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoun on 2018/6/26.
 **/
public class BinTreeMain {

    private static List<TreeNode> nodeList = new ArrayList<>();
    private static TreeNode root;

    /**
     * 先序遍历
     */
    public static void preOrder(TreeNode treeNode) {
        nodeList.add(treeNode);
        if (treeNode.left != null) {
            preOrder(treeNode.left);
        }
        if (treeNode.right != null) {
            preOrder(treeNode.right);
        }
    }


    /**
     * 中序遍历
     */
    public static void inOrder(TreeNode treeNode) {
        if (treeNode.left != null) {
            inOrder(treeNode.left);
        }
        nodeList.add(treeNode);
        if (treeNode.right != null) {
            inOrder(treeNode.right);
        }
    }

    /**
     * 后序遍历
     */
    public static void postOrder(TreeNode treeNode) {
        if (treeNode.left != null) {
            postOrder(treeNode.left);
        }
        if (treeNode.right != null) {
            postOrder(treeNode.right);
        }
        nodeList.add(treeNode);
    }

    public static void main(String[] args) {
        init();
        postOrder(root);
        for (int i = 0; i < nodeList.size(); i++) {
            System.out.println(nodeList.get(i).val+"");
        }
    }

    /**
     * 树的初始化 先从叶节点开始再到根
     */
    public static void init() {
        TreeNode b = new TreeNode("b", null, null);
        TreeNode a = new TreeNode("a", null, b);
        TreeNode c = new TreeNode("c", a, null);

        TreeNode e = new TreeNode("e", null, null);
        TreeNode g = new TreeNode("g", null, null);
        TreeNode f = new TreeNode("f", e, g);
        TreeNode h = new TreeNode("h", f, null);

        TreeNode d = new TreeNode("d", c, h);

        TreeNode j = new TreeNode("j", null, null);
        TreeNode k = new TreeNode("k", j, null);
        TreeNode m = new TreeNode("m", null, null);
        TreeNode o = new TreeNode("o", null, null);
        TreeNode p = new TreeNode("p", o, null);
        TreeNode n = new TreeNode("n", m, p);
        TreeNode l = new TreeNode("l", k, n);

        root = new TreeNode("i", d, l);

    }

}
