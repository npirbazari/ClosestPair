package ClosestPoints;

/*
    Closest Pair
    Nima Tajeli Pirbazari
    09/11/2017  
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    //constants
    public static final String LOCATION = "../test/InputFiles";
    public static final String EXTENSION = ".tsv";

    public static void main(String[] args) {

        //variables
        boolean loopCondition;
        String userInput;
        int userChoice;
        String textFromFile;
        List<Point> points;
        long startTime;
        long endTime;
        FileOperations fileOperations;
        File[] files;
        int menuLevel;

        //initialization
        userChoice = 0;
        startTime = 0;
        endTime = 0;
        menuLevel = 1;
        userInput = "";
        textFromFile = "";
        loopCondition = true;
        points = new ArrayList<Point>();
        fileOperations = new FileOperations();

        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome");
        ///Users/npirbazari/Desktop/sample_input_2_8.tsv
        while (loopCondition) {
            try {
                if (menuLevel == 1) {
                    System.out.println("1) Select input files.");
                    System.out.println("2) Run file by path.");
                    System.out.println("3) Generate new random test file.");
                    System.out.println("4) Run test cases.");
                    System.out.println("0) Exit.");

                    userInput = scan.nextLine();

                    if (tryParseInt(userInput)) {
                        userChoice = Integer.parseInt(userInput);

                        if (userChoice == 0) {
                            loopCondition = false;
                        } else if (userChoice == 1) {
                            menuLevel = 2;
                        } else if (userChoice == 2) {
                            System.out.println("Please enter file path:");
                            userInput = scan.nextLine();
                            File file = new File(userInput);

                            if (file.exists()) {
                                System.out.println(file.getName());
                                System.out.println(getFileExtension(file));
                                try {
                                    if (getFileExtension(file).equals("tsv")) {
                                        String text = new String(Files.readAllBytes(Paths.get(userInput)));
                                        points = fillPointsList(text);
                                        startTime = System.currentTimeMillis();
                                        checkPoints(points);
                                        endTime = System.currentTimeMillis();
                                        System.out.println("Operation took " + (endTime - startTime) + " ms \n");
                                    } else {
                                        System.out.println("File extension should be .tsv.");
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                System.out.println("Couldn't find file in given path.");
                            }

                        } else if (userChoice == 3) {
                            generateRandomFile();
                        } else if (userChoice == 4) {
                            files = fileOperations.getAllTestFiles(LOCATION, EXTENSION);
                            if (files.length > 0) {
                                for (int i = 0; i < files.length; i++) {
                                    startTime = System.currentTimeMillis();
                                    textFromFile = fileOperations.readTextFromFile(files[i].getPath());
                                    points = fillPointsList(textFromFile);
                                    endTime = System.currentTimeMillis();
                                    System.out.println("Reading file took " + (endTime - startTime) + " milliseconds \n");

                                    startTime = System.currentTimeMillis();
                                    checkPoints(points);
                                    endTime = System.currentTimeMillis();
                                    System.out.println("Operation took " + (endTime - startTime) + " ms \n");
                                }
                            } else {
                                menuLevel = 1;
                                System.out.println("There are no files to list.");
                            }
                        } else {
                            System.out.println("Invalid input please select a number from menu.");
                        }
                    } else {
                        System.out.println("Invalid input please select a number from menu.");
                    }
                } else if (menuLevel == 2) {
                    files = fileOperations.findAllFiles(LOCATION, EXTENSION);
                    if (files.length > 0) {
                        System.out.println("Please Select Input File:");

                        for (int i = 0; i < files.length; i++) {
                            System.out.println((i + 1) + ") " + files[i].getName());
                        }
                        System.out.println("0) Back \n");

                        userInput = scan.nextLine();
                        if (tryParseInt(userInput)) {
                            userChoice = Integer.parseInt(userInput);
                            if (userChoice == 0) {
                                menuLevel = 1;
                            } else if (userChoice > files.length || userChoice < 0) {
                                System.out.println("There is no file with number \n" + userChoice);
                            } else {
                                startTime = System.currentTimeMillis();

                                textFromFile = fileOperations.readTextFromFile(files[userChoice - 1].getPath());
                                points = fillPointsList(textFromFile);
                                endTime = System.currentTimeMillis();
                                System.out.println("Reading file took " + (endTime - startTime) + " milliseconds \n");

                                startTime = System.currentTimeMillis();
                                checkPoints(points);
                                endTime = System.currentTimeMillis();
                                System.out.println("Operation took " + (endTime - startTime) + " ms \n");
                            }
                        }
                    } else {
                        menuLevel = 1;
                        System.out.println("There are no files to list.");
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage().toString());
            }
        }

        System.out.println("Closed");
    }

    public static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static List<Point> fillPointsList(String fileContent) {

        List<Point> result = new ArrayList<Point>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new StringReader(fileContent));

            int i = 1;
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {

                List<Double> coordinates = new ArrayList<Double>();
                String[] coordinatesString = line.split("\t");

                for (int j = 0; j < coordinatesString.length; j++) {
                    coordinates.add(Double.parseDouble(coordinatesString[j]));
                }

                Point point = new Point(i, coordinates);
                i = i + 1;
                //System.out.println(point);
                result.add(point);
            }

        } catch (Exception ex) {

        }

        return result;
    }

    public static void printResult(List<Point> points) {

        System.out.println("Result:");
        for (int i = 0; i < points.size(); i++) {
            System.out.println(points.get(i).toString());
        }
        System.out.print("\n");
    }

    public static double calculateDistance(Point p1, Point p2) {
        double sum = 0;

        for (int i = 0; i < p1.coordinates.size(); i++) {
            sum = sum + Math.pow((p1.coordinates.get(i) - p2.coordinates.get(i)), 2);
        }
        // no need to take sqrt as there is no need for distance
        return sum;
    }

    public static List<Point> getMinimumDistance(List<Point> points) {

        List<Point> result = new ArrayList<Point>();

        Point p1 = null;
        Point p2 = null;

        double minDistance = Double.MAX_VALUE;

        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {

                if (checkCoordinateDimensions(points.get(i), points.get(j))) {
                    double distance = calculateDistance(points.get(i), points.get(j));
                    if (distance < minDistance) {
                        p1 = points.get(i);
                        p2 = points.get(j);
                        minDistance = distance;
                    }
                } else {
                    System.out.println("Skiped distance calculation for the following points as they did not have same dimensions \n" + p1.toString() + "\n" + p2.toString() + "\n");
                }

            }
        }

        result.add(p1);
        result.add(p2);
        return result;
    }

    public static boolean checkCoordinateDimensions(Point p1, Point p2) {
        boolean result;
        if (p1.coordinates.size() == p2.coordinates.size()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public static void checkPoints(List<Point> points) {
        if (points.size() < 2) {
            System.out.println("At least 2 points are needed. \n");
        } else if (points.size() == 2) {
            printResult(points);
        } else {
            printResult(getMinimumDistance(points));
        }
    }

    public static String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }

    public static void generateRandomFile() {
        Random random = new Random();
        FileOperations fileOperations = new FileOperations();
        //generate random number between 5-100
        int dimension = random.nextInt(95) + 5;
        int size = random.nextInt(9990) + 10;

        String fileName = LOCATION + "/sample_input_" + dimension + "_" + size + ".tsv";

        String output = "";

        for (int i = 0; i < size; i++) {
            String line = "";
            for (int j = 0; j < dimension; j++) {
                //generate random double between -10000 and 10000
                line = line + "" + (random.nextDouble() * 20000 - 10000) + "\t";
            }
            output = output + line + "\n";
        }

        if (fileOperations.writeTextToFile(output, fileName)) {
            System.out.println("Successfuly created test file. \n");
        } else {
            System.out.println("Error in creating file.\n");
        }
    }
}
