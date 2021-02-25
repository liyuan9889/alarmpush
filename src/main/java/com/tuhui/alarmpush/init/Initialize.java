package com.tuhui.alarmpush.init;

import com.tuhui.alarmpush.listener.FileListener;
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
    private FileListener fileListener;

    @Override
    public void run(String... args) throws Exception {
        fileListener.start(dir,suffix);
        log.info("开始监控");
    }
}
