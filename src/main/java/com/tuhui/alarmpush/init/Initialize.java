package com.tuhui.alarmpush.init;

import com.tuhui.alarmpush.listener.FileListener;
import com.tuhui.alarmpush.services.AlarmService;
import com.tuhui.alarmpush.services.FaceService;
import com.tuhui.alarmpush.services.PoliceService;
import com.tuhui.alarmpush.services.PushMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    @Value("${dt.polices}")
    private String polices;

    @Autowired
    private AlarmService alarmService;

    public static AlarmService salarmService;

    @Autowired
    private PoliceService policeService;

    public  static PoliceService spoliceService;

    @Autowired
    private FaceService faceService;

    public  static FaceService sfaceService;


    @Autowired
    private PushMsgService pushMsgService;

    public static PushMsgService spushMsgService;

    @Autowired
    private FileListener fileListener;

    public static String codes ;

    @Override
    public void run(String... args) throws Exception {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        codes = polices;
        spushMsgService = pushMsgService;
        sfaceService = faceService;
        spoliceService = policeService;
        salarmService = alarmService;
        fileListener.start(dir +  LocalDate.now().format(pattern) ,suffix);
        log.info("开始监控");
    }
}
