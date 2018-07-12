package com.zn.tree.adt;

/**
 * 二叉查找树
 * 能够快速的定位给定的关键字，并且可以快速的插入和删除数据项
 * Created by zhoun on 2018/7/9.
 **/
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {

    private static class BinaryNode<AnyType> {

        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;

        BinaryNode(AnyType theElemnt) {
            this(theElemnt, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode left, BinaryNode right) {
            element = theElement;
            this.left = left;
            this.right = right;
        }

    }

    private BinaryNode<AnyType> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    /**
     * 左子树向左进行
     *
     * @return
     */
    public AnyType findMin() throws Exception{
        if (isEmpty()) {
            throw new Exception("");
        }
        return findMin(root).element;
    }

    /**
     * 右子树向右进行
     *
     * @return
     */
    public AnyType findMax() throws Exception{
        if (isEmpty()) {
            throw new Exception("");
        }
        return findMax(root).element;
    }


    public void insert(AnyType x) {
        root = insert(x, root);
    }

    public void remove(AnyType x) {
        root = remove(x, root);
    }

    public void printTree() {

    }

    /**
     * 如果树t种中含有x的节点，那么这个操作返回true,如果这样的节点不存在返回false,
     * 否则我们将树t的左子树和右子树进行递归调用
     *
     * @param x
     * @param t
     * @return
     */
    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return false;
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true;
        }

    }

    /**
     * 用递归实现查找最小值
     *
     * @param t
     * @return
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t);
    }

    /**
     * 递归查找最大值和非递归查找
     *
     * @param t
     * @return
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        /*if (t == null) {
            return null;
        } else if (t.right == null) {
            return t;
        }
        return findMax(t);*/
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }
        return t;

    }

    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return new BinaryNode<>(x, null, null);
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            return insert(x, t.left);
        } else if (compareResult > 0) {
            return insert(x, t.right);
        }
        return t;


    }

    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return null;
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {
            t.element = findMax(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }

        return t;
    }


}
