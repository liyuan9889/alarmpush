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

    @Scheduled(cron = "0 0 1 * * ?")
    private void configureTasks() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                monitor.stop();
                fileListener.start(dir + LocalDate.now().format(pattern),suffix);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
