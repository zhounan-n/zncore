package com.zn.tree;

/**
 * java实现树结构
 * Created by zhoun on 2018/6/26.
 **/
public class TreeNode {

    public Object val;
    public TreeNode left = null;
    public TreeNode right = null;


    public TreeNode(Object val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
