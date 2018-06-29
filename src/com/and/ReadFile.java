package com.and;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ReadFile {

    private static String filenameRead = "log.log";
    private static String filenameWrite = "result.csv";
    private static ArrayList<String> logFile;
    private static Map<String, MeasSeries> finalFile = new HashMap<>();
    private static Set<MeasSeries> measurements = new HashSet<>();


    public static void main(String[] args) throws IOException {

        Path path = Paths.get(filenameRead);
        BufferedReader br = Files.newBufferedReader(path);
        String input;
        logFile = new ArrayList<>();
//        logFile = new LinkedList<>();

        try {
            while ((input = br.readLine()) != null) {
                if (input.contains("T-PWR") || input.contains("Value")) {
                    logFile.add(input);
                } else {
                    continue;
                }
            }

        } finally {
            if (br != null) {
                br.close();
            }
        }
        int j = 1;

        MeasSeries temp = new MeasSeries("test");

        for (int i = 0; i < logFile.size(); i++) {

            if (logFile.get(i).contains("T-PWR")) {

                finalFile.put(logFile.get(i), temp);
                measurements.add(temp);

                System.out.println("added" + temp.getMeasurement());

            } else if ((logFile.get(i).contains("Value")) && (logFile.get(i + 1).contains("Value"))) {
                String current = logFile.get(i);
                StringBuilder util = new StringBuilder();
                current = (util.append(current).insert((current.lastIndexOf("Value") + 5), j)).toString();
                j = j + 1;
                logFile.set(i, current);

                // add to Set

                temp.addMeasurement(new MeasSeries(logFile.get(i)));
                System.out.println("added" + temp.getMeasurement());


            } else if ((i != 0) && (logFile.get(i - 1).contains("Value")) && (logFile.get(i + 1).contains("T-PWR")) && (logFile.get(i).contains("Value"))) {
                String current = logFile.get(i);
                StringBuilder util = new StringBuilder();
                current = (util.append(current).insert((current.lastIndexOf("Value") + 5), j)).toString();
                j = 1;
                logFile.set(i, current);

                // add to Set

                temp.addMeasurement(new MeasSeries(logFile.get(i)));
                System.out.println("added" + temp.getMeasurement());

            }
        }
        for (int i = 0; i < logFile.size(); i++) {

            System.out.println(logFile.get(i));
        }

        System.out.println("MeasSeries");
        for(MeasSeries measurement : measurements) {
            System.out.println("\t" + measurement.getMeasurementName());
        }
//
//        HeavenlyBody body = solarSystem.get("Mars");
//        System.out.println("Moons of " + body.getName());
//        for(HeavenlyBody jupiterMoon: body.getSatellites()) {
//            System.out.println("\t" + jupiterMoon.getName());
//        }
//
//        Set<HeavenlyBody> moons = new HashSet<>();
//        for(HeavenlyBody planet : planets) {
//            moons.addAll(planet.getSatellites());
//        }
//
//        System.out.println("All Moons");
//        for(HeavenlyBody moon : moons) {
//            System.out.println("\t" + moon.getName());


//        String test = "Value: 12312";
//        int k = test.lastIndexOf("Value") + 5;
//        System.out.println(k);
//        StringBuilder utilitary = new StringBuilder();
//        current = (utilitary.append(current).insert(k,"1")).toString();
//        System.out.println(test);

//        WriteFile();

    }

/*
    public static void WriteFile() throws IOException {

        Path pathWrite = Paths.get(filenameWrite);
        BufferedWriter bw = Files.newBufferedWriter(pathWrite);
        try {
            bw.write(String.format("%s\t%s\t%s",
                    "\t",
                    filenameRead,
                    "\t"));
            bw.newLine();
            for (String line : logFile)
                for (int i = 0; i < logFile.size(); i++) {
                    while (!line.isEmpty()) {
                        if (line.contains("T-PWR")) {
                            bw.write(String.format("%s\t%s\t%s",
                                    line,
                                    "\t",
                                    "\t"));

                            System.out.println("if1");

                            bw.newLine();
                        } else {
                            if (line.contains("Value")) {
                                bw.write(String.format("%s\t%s\t%s",
                                        "\t",
                                        line,
                                        "\t"));
                                bw.newLine();

                                System.out.println("if2");

                            }
                        }
                    }

                    System.out.println("while");

                }
//            while (logFileIterator.hasNext()) {
//                logFileIterator.next();
//                if (logFileIterator.next().equals("T-PWR")) {
//                    bw.write(String.format("%s\t%s\t%s",
//                            logFileIterator.toString(),
//                            "\t",
//                            "\t"));
//                } else {
//                    if (logFileIterator.next().equals("Value")) {
//                        bw.write(String.format("%s\t%s\t%s",
//                                "\t",
//                                logFileIterator.toString(),
//                                "\t"));
//                    }
//                    bw.newLine();
//                }

        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }

*/

}