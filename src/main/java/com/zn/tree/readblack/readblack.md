二叉查找树 如果插入的是随机数则执行效果很好  ，但是如果插入的是有序的数据或者是逆序的数据速度就变得特别慢，因为
当插入的数值有序时，二叉树就是非平衡的了，而对于非平衡二叉树，它快速查找指定数据的能力就没了

解决非平衡树的问题：红黑树，它是增加了某些特点的二叉查找树