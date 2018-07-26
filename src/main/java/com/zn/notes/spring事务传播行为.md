spring有7种事务传播行为,是spring框架的增强特性

1：什么是事务传播行为？
    事务传播行为用来描述某一个事务传播行为修饰的方法嵌套进另一个方法时事务时如何传播？
    
    伪代码说明：
    public void methodA(){
         methodB();
         //doSomething
    }
    
    @Transcation(Propagation=XXX)
    public void methodB(){
         //doSomething
    }
    
代码中methodA()方法嵌套调用了methodB()方法，methodB()的事务传播行为由@Transaction(Propagation=XXX)设置决定。
这里需要注意的是methodA()并没有开启事务，某一个事务传播行为修饰的方法并不是必须要在开启事务的外围方法中调用。

2：spring的7种事务传播行为

    事务传播行为类型	             说明
    PROPAGATION_REQUIRED	     如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择。
    PROPAGATION_SUPPORTS	     支持当前事务，如果当前没有事务，就以非事务方式执行。
    PROPAGATION_MANDATORY	     使用当前的事务，如果当前没有事务，就抛出异常。
    PROPAGATION_REQUIRES_NEW	 新建事务，如果当前存在事务，把当前事务挂起。
    PROPAGATION_NOT_SUPPORTED	 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
    PROPAGATION_NEVER	         以非事务方式执行，如果当前存在事务，则抛出异常。
    PROPAGATION_NESTED	         如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作。
    


