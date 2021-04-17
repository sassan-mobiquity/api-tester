package com.mobiquity.assignment;


import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Converts the cucumber JSON reports to html reports.
 * The visual features of the reports is configurable
 */
@Slf4j
public class TestReportGenerator {

    public static Properties metadata = new Properties();
    @Setter
    private static String reportPath;

    @SneakyThrows
    public static void generate() {
        log.info("starting test report generation");
        String userDir = System.getProperty("user.dir");
        String dirOldPath = userDir + "/target/cucumber-reports";
        String newDirPath = userDir + "/" + reportPath;
        File oldDir = new File(dirOldPath.replace("\\", "/"));
        File newDir = new File(newDirPath.replace("\\", "/"));

        FilenameFilter jsonFilter = (dir, fileName) -> fileName.endsWith(".json");
        if (oldDir.exists()) {
            oldDir.renameTo(newDir);
        }


        File[] files = newDir.listFiles(jsonFilter);
        // iterate through all the cucumber JSON reports
        List<String> jsonFiles = new ArrayList<>();
        for (File jsonfile : files) {
            BufferedReader brTest = new BufferedReader(new FileReader(jsonfile));
            // exclude empty files
            if (!brTest.readLine().equals("[]")) {
                jsonFiles.add(jsonfile.getAbsolutePath());
            } else {
                jsonfile.deleteOnExit();
            }
            brTest.close();
        }

        File reportOutputDirectory = new File(reportPath);

        Configuration configuration = new Configuration(reportOutputDirectory, "Typicode");
        configuration.setBuildNumber("1");

        File f = new File(newDirPath + "/reportmetadata.properties");
        OutputStream out = new FileOutputStream(f);
        metadata.store(out, "This is an optional header comment string");
        List<String> classificationFiles = new ArrayList<>();
        classificationFiles.add(newDirPath + "/reportmetadata.properties");
        configuration.addClassificationFiles(classificationFiles);

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }

    /**
     * Cleaning up after previous unfinished tests; deleting their report directory
     */
    public static void deleteExistingReportDir() {
        String reportPath = "/target/cucumber-reports";
        log.info("deleting pre-existing report directories in " + reportPath);
        File dir = new File(System.getProperty("user.dir") + reportPath);
        if (dir.isDirectory()) {
            String[] entries = dir.list();
            for (String s : entries) {
                File currentFile = new File(dir.getPath(), s);
                currentFile.delete();
            }
            dir.delete();
        }

    }

}
