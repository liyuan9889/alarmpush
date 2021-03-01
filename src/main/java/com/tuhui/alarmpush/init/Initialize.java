package com.tuhui.alarmpush.init;

import com.tuhui.alarmpush.listener.FileListener;
import com.tuhui.alarmpush.services.AlarmService;
import com.tuhui.alarmpush.services.PoliceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author lchao
 */
@Slf4j
@Component
@Order(value=1)
public class Initialize  implements CommandLineRunner {

    @Value("${dt.dir}")
    private String dir;
    @Value("${dt.suffix}")
    private String suffix;

    @Autowired
    private AlarmService alarmService;

    public static AlarmService salarmService;

    @Autowired
    private PoliceService policeService;

    public  static PoliceService spoliceService;


    @Autowired
    private FileListener fileListener;

    @Override
    public void run(String... args) throws Exception {
        spoliceService = policeService;
        salarmService = alarmService;
        fileListener.start(dir,suffix);
        log.info("开始监控");
    }
}
