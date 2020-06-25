package com.cn.redis.clusterlock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

//redis 分布式锁
@RunWith(SpringRunner.class)
@SpringBootTest
public class LockTest {

    @Autowired
    RedisClusterLock redisClusterLock;
    String key="test";
    class  Method implements Runnable{
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println("当前线程名字:" + name);
            boolean lock=redisClusterLock.lock(key);
            if(lock){

                //执行逻辑操作
                try {
                    System.out.println("我是线程："+name+"正在做某事，需要6s");
                    Thread.sleep(600);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

                redisClusterLock.delete(key);
            }else{
                // 设置失败次数计数器, 当到达5次时, 返回失败
                int failCount = 1;
                while(failCount <= 5){
                    System.out.println("重试："+failCount);
                    // 等待100ms重试
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (redisClusterLock.lock(key)){

                        //执行逻辑操作
                        try {
                            System.out.println("我是线程："+name+"正在做某事，需要6s");
                            Thread.sleep(600);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }

                        redisClusterLock.delete(key);
                    }else{
                        failCount ++;
                    }
                }
                throw new RuntimeException("现在创建的人太多了, 请稍等再试");

            }
        }
    }

    @Test
    public  void main2() {
        Method method=new Method();
        for (int i=0;i<2;i++) {
            new Thread(method, "A").start();
            new Thread(method, "B").start();
        }
        try {
            System.in.read();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
