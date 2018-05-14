# multi-thread project

under multithread package
多线程，高并发知识点积累

algorithm:简单算法测试案例

under the jdk8 package 
examples to show the new fetures f java8


------------java8 predict------------
    函数式借口标注@FunctionalInterface
    java.util.function包下有大量的函数式接口，主要分为以下几个类别：
        Function  输入参数为类型T， 输出为类型R， 记作 T -> R
        Consumer  输入参数为类型T， 输出为void， 记作 T -> void
        Supplier  没有输入参数， 输出为类型T， 记作 void -> T
        Predicate 输入参数为类型T， 输出为类型boolean， 记作 T -> boolean
        
    Runnable r = () -> {
                System.out.println("one line");
                System.out.println("two line");
     };
     
    自定义函数式接口：
     @FunctionalInterface
     public interface SimpleInterface {
         //只有一个抽象方法，这里我们声明一个有参方法
         public void doAdd(int a,int b);
     }
     
     用lambda表达式实现这个接口：
     public class UseSimpleInterface {
         public static void main(String[] args) {
             SimpleInterface obj = (v1,v2) -> {
                 int result =  v1 v2;
                 System.out.println(  "result:" result );
             };
              obj.doAdd(2,3);
         }
     }
     
     使用java8内置的函数式接口：
     
     Runnable使用后：
        Runnable r1 = () ->{
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 System.out.println("Running Thread 1");
             };
             Runnable r2 = () ->{
                 System.out.println("Running Thread 2");
             };
             new Thread(r1).start();
             new Thread(r2).start();
      
     Comparator接口：
      Comparator<String> comparator = (str1,str2) ->{
                  return str1.compareToIgnoreCase(str2);
              };
              Collections.sort(strings, comparator);
              
      
      使用predict接口过滤集合：
        Predicate<Person> pred = new Predicate<Person>() {
                  @Override
                  public boolean test(Person person) {
                      return (person.getAge() >= 65 );
                  }
              };
              for (Person person:  people) {
                  if ( pred.test(person)){
                      System.out.println(person.toString());
                  }
              }
         Predicate<Person> pred = p -> (p.getAge() >= 65 );
                people.forEach( p -> {
                    if (pred.test(p)){
                        System.out.println(p.toString());
                    }
                });
                
      
      使用方法引用：
      
      接口中的默认方法：
            在java 8以前，如果一个接口要是需要添加方法，那么所有已经实现了这个接口的类就必须全部修改。为了避免这个问题，在java 8中，我们可以使用默认方法来实现添加方法，同时又不会影响已经实现类。因为其他的实现类可以重写这个默认方法，也可以不重写。
        
        
       
