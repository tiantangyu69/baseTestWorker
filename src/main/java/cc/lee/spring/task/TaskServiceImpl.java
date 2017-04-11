package cc.lee.spring.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by bjlizhitao on 2016/7/13.
 */
@Service
public class TaskServiceImpl implements TaskService {
    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(false);
    ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

//    @Scheduled(fixedDelay = 1000)
    public void heartBeat() {
        System.out.println("peng peng peng");
    }

    @Scheduled(fixedDelay = 5000)
    public void slowWork() {
        try {
            System.out.println("slowWork start..............................");
            Thread.sleep(20000);
            System.out.println("slowWork end..............................");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}