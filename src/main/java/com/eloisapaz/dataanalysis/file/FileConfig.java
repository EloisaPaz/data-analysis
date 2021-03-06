package com.eloisapaz.dataanalysis.file;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Component
@Configuration
@EnableScheduling
public class FileConfig {

    @Autowired
    private FileProcessor fileProcessor;

    private static Logger logger = LoggerFactory.getLogger(FileConfig.class);

    private static String homePath = System.getProperty("user.home");
    private static String fileIn = homePath + "/data/in";
    private static String fileOut = homePath + "/data/out/";

    @PostConstruct
    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void run() {

        try {
            logger.info("Initializing the program...");

            createDirectory();

            Path path = Paths.get(fileIn);

            List<File> files = Files.walk(path)
                    .filter(Files::isRegularFile)
                    .filter(s -> s.toString().endsWith(".dat"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());

            if(files.isEmpty()) {
                logger.error("Directory is empty, insert files to generate the report.");
            }
            for (File file : files) {
                fileProcessor.readFile(file);
            }
            fileProcessor.getReport(fileOut);
        }catch (Exception e){
            logger.error("Closing the program...", e);
        }

    }

    private void createDirectory() {
        new File(fileIn).mkdirs();
        new File(fileOut).mkdirs();
    }
}