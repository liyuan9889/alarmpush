package com.tuhui.alarmpush.scheduling;


import com.tuhui.alarmpush.listener.FileListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.tuhui.alarmpush.listener.FileListener.monitor;

@Configuration
public class AlarmSchedule {


    @Value("${dt.dir}")
    private String dir;
    @Value("${dt.suffix}")
    private String suffix;

    @Autowired
    private FileListener fileListener;

    @Scheduled(cron = "0/40 * * * * ?")
    private void configureTasks() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        LocalDate.now().format(pattern);
            try {
                monitor.stop();
                fileListener.start(dir + "2" ,suffix);
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    public static void main(String[] args) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        System.out.println(LocalDate.now().format(pattern));
    }

}
