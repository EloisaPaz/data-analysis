package com.eloisapaz.core.challenge.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileConfig {

    @Autowired
    private FileProcessor fileProcessor;

    private static String homePath = System.getProperty("user.home");
    private static String fileIn = homePath + "/data/in";
    private static String fileOut = homePath + "/data/out/";

    public void run() throws IOException{
        Path path = Paths.get(fileIn);
        List<File> files = Files.walk(path)
                .filter(Files::isRegularFile)
                .filter(s -> s.toString().endsWith(".dat"))
                .map(Path::toFile)
                .collect(Collectors.toList());

        for (int i = 0; i < files.size(); i++){
            fileProcessor.readFile(files.get(i));
        }
        fileProcessor.getReport(fileOut);
    }
}