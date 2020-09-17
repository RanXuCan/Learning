package com.rxc.sourcecodelearning;

import lombok.Data;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/16 14:08
 */
public class ThreadTest {
    public static void main(String[] args) {
        MyThread myThread = new MyThread(new Account("201706062001", 3000));
        Thread t1 = new Thread(myThread);
        Thread t2 = new Thread(myThread);

        t1.setName("甲");
        t2.setName("乙");
        t1.start();
        t2.start();

        execute02();

        try {
            execute03();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        try {
            execute04();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyThread implements Runnable {
        private final Account account;

        MyThread(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "的当前账户：" + account.getId());

            for (int i = 0; i < 3; i++) {
                synchronized (this) {
                    System.out.println("下面线程" + Thread.currentThread().getName() + "将存入1000元");
                    double d = account.getMoney();
                    System.out.println("存入前账户余额：" + account.getMoney());
                    account.setMoney(d + 1000);
                    System.out.println("当前账户余额：" + account.getMoney());
                    System.out.println("----------------------------------");
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //第二种方式，继承Thread类
    static class MyThread02 extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "使用继承Thread的方式实现多线程时执行的run方法");
        }
    }

    private static void execute02() {
        MyThread02 mythread02 = new MyThread02();
        mythread02.start();
    }

    //第三种方式，实现Callable接口
    static class Mythread03 implements Callable {

        @Override
        public Object call() {
            return Thread.currentThread().getName() + "实现Callable接口调用线程时执行的call()方法\n";
        }
    }

    private static void execute03() throws ExecutionException, InterruptedException {
        Mythread03 mythread03 = new Mythread03();
        FutureTask futureTask = new FutureTask(mythread03);
        Thread thread = new Thread(futureTask);
        thread.start();
        //如果关心线程的返回值，则继续使用get方法获取
        Object o = futureTask.get();
        System.out.println(o.toString());
    }

    //第四种方式，使用线程池
    private static void execute04() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new MyThread02());//实现Runnable
        Future submit = executorService.submit(new Mythread03());//实现Callable
        Object o = submit.get();
        System.out.println(o);

        executorService.shutdown();
    }

    @Data
    static class Account {
        private String id;
        private double money;

        public Account(String id, double money) {
            this.id = id;
            this.money = money;
        }
    }
}
