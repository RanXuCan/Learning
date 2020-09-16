package com.rxc.sourcecodelearning;

import lombok.Data;

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
    }

    static class MyThread implements Runnable {
        private Account account;

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
