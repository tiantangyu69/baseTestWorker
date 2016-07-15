package cc.lee.spring.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by bjlizhitao on 2016/7/13.
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Scheduled(cron = "0/1 * * * * ?")
    public void heartBeat() {
        System.out.println("peng peng peng");
    }
}
