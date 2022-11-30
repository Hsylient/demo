package com.novax.ex.demo1.provider.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * @author my.miao
 * @date 2021/7/30 11:34
 */
@Slf4j
@Component
@EnableScheduling
public class DemoTask {

    /**
     * 没两秒执行一次
     */
    @Scheduled(fixedDelay = 2000)// 如果设定的是相隔2s执行，当前任务假设需要花费8s，那么下一次的执行的时间为当前任务执行完毕后，相隔2s后再执行。
    private void moveBrick(){
//        log.info("2秒");
    }

    /**
     * 每隔5秒执行一次
     */
    @Scheduled(fixedRate = 5000)//假设在相隔的时间内不能处理完任务；到了相隔的时间下一个任务还是会启动，但会被阻塞到上一个任务执行完毕才会执行当前这个任务。
    private void dexMoveBrick(){
//        log.info("5秒");
    }

    /**
     * 每分钟执行一次
     */
    @Scheduled(cron="0 0/1 * * * ?")
    private void statement(){
//        log.info("一分钟");
    }
}
