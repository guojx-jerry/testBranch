import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j(topic = "c.Test1")
public class Test {
    @org.junit.Test
    public void testThread(){
        Thread t1 =  new Thread("t1"){
            @Override
            public void run() {
                log.debug("t1打印running");
            }
        };

        t1.start();
        log.debug("main running");
    }
    @org.junit.Test
    public void runnableThread(){
        Runnable runnable = () -> log.debug("t1打印running");
        Thread t1 = new Thread(runnable,"t1");
        t1.start();
        log.debug("main running");
    }

    @org.junit.Test
    public void futureTaskTest() throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("running");
                Thread.sleep(1000);
                return "futuretask线程处理完毕";
            }
        });
        Thread t = new Thread(task,"t1");
        t.start();
        String result = task.get();
        log.debug(result);
    }

    @org.junit.Test
    public void testrun(){
        new Thread(()->{
            while(true){
                log.debug("t1 running");
            }
        }).start();

        new Thread(()->{
            while(true){
                log.debug("t2 running");
            }
        }).start();
    }

    private static void method1(int x){
        Object obj  = method2();
        System.out.println(obj);
    }

    private static Object method2(){
        return new Object();
    }

//    public static void main(String[] args) {
////        Runnable r1 = ()->method1(20);
//////        Runnable r2 = ()->method1(100);
////        Thread t1 = new Thread(r1,"t1");
//////        Thread t2 = new Thread(r2,"t2");
////        t1.start();
//////        t2.start();
////        method1(100);
//
//        Thread t1 = new Thread("t1"){
//            @Override
//            public void run() {
//                log.debug("running...");
//            }
//        };
//
//        System.out.println(t1.getState());
//        t1.start();
//        System.out.println(t1.getState());
//    }

    public static void main(String[] args) {
        String test = new String("original");
        char[] testChar = new char[2];
        testChar[0] = 'g';

//        test = "adjust";
        change(test,testChar);
        System.out.println(test);

    }

    public static void change(String test,char[] testChar){
        test = "adjust";
        testChar[0] = 'u';
    }

    public String testSub(){
        System.out.println("parent");
        return "parent";
    }


}

class TestSub extends Test{
    public  String testSub(){
        return "sub";
    }
}

abstract class TestAbstractPrivate{
    private abstract void  test();
}