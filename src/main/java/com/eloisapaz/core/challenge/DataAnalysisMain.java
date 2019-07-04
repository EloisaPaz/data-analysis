package com.eloisapaz.core.challenge;

import com.eloisapaz.core.challenge.config.DataAnalysisConfig;
import com.eloisapaz.core.challenge.file.FileConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataAnalysisMain {

    public static final boolean RUNNING = true;
    private static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DataAnalysisConfig.class);
    private static Logger logger = LoggerFactory.getLogger(DataAnalysisMain.class);

    public static void main(String[] args) {
        try {
            FileConfig fileConfig = (FileConfig) applicationContext.getBean("fileConfig");
            logger.info("Initializing the program...");
            while(RUNNING) {
                fileConfig.run();
                Thread.sleep(500);
            }
        } catch (Exception e){
            logger.error("Closing the program...", e);
        }
    }
}