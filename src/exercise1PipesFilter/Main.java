package exercise1PipesFilter;

import pmp.filter.Source;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static final String ALICE_PATH = Main.class.getClassLoader().getResource("./exercise1PipesFilter/data/aliceInWonderland.txt").getPath();
    private static final String FREQUENT_WORDS_PATH = Main.class.getClassLoader().getResource("./exercise1PipesFilter/data/frequentEnglishWords.txt").getPath();
    private static final int FREQUENT_WORDS_BOUND = 300;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("******** Excercise 1 - a Pipes&Filters Architecture ********");
        System.out.println("Please select the exercise.");
        System.out.println("Enter [a] for exercise a or [b] for excercise b.");
        input = null;
        do {
            if (input != null) {
                System.out.println("Wrong input, please enter [a] or [b].");
            }
            input = scanner.nextLine();
        } while (!(input.equalsIgnoreCase("a") || input.equalsIgnoreCase("b")));
        input = input.toLowerCase();
        String inputPath;
        switch (input) {
            case "a":
                System.out.println("Excercise " + input + ":");
                System.out.println("Please enter the path to the input file or type [alice] for the book \"Alice in Wonderland\"");
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("alice")) {
                    inputPath = ALICE_PATH;
                } else {
                    inputPath = input;
                }
                System.out.println("Creating index file...");
                excerciseA(inputPath);
                System.out.println("File created.");
                System.out.println("******** END ********");
                break;
            case "b":
                System.out.println("Excercise " + input + ":");
                System.out.println("Please enter the path to the input file or type [alice] for the book \"Alice in Wonderland\"");
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("alice")) {
                    inputPath = ALICE_PATH;
                } else {
                    inputPath = input;
                }
                System.out.println("Please enter the desired line length. For example [60]");
                boolean correctInput = false;
                int lineLength = 0;
                do {
                    try {
                        lineLength = Integer.parseInt(scanner.nextLine());
                        correctInput = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong input, please enter a number for the desired line length.");
                    }
                } while (!correctInput);
                System.out.println("Line length: " + lineLength);
                System.out.println("Please enter the desired alignment. [left], [center] or [right]");
                correctInput = false;
                Alignment alignment = null;
                do {
                    input = scanner.nextLine();
                    try {
                        alignment = Alignment.valueOf(input.toLowerCase());
                        correctInput = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Wrong input, please enter [left], [center] or [right]");
                    }
                } while (!correctInput);
                System.out.println("Creating files...");
                exerciseB(inputPath, lineLength, alignment);
                System.out.println("Files created.");
                System.out.println("******** END ********");
                break;
        }
    }

    private static void excerciseA(String inputFilePath) {
        HashMap<String, Integer> map = new HashMap<>();
        fillHashMap(map, FREQUENT_WORDS_BOUND);


        WriteIndexFileSink writer = new WriteIndexFileSink();
        SimplePipe pipeA = new SimplePipe(writer);
        SortFilter sortFilter = new SortFilter(pipeA);
        SimplePipe pipeB = new SimplePipe((Writeable) sortFilter);
        FrequentWordFilter frequentWordFilter = new FrequentWordFilter(pipeB, map);
        SimplePipe pipeC = new SimplePipe((Writeable) frequentWordFilter);
        CircularShiftFilter circularShiftFilter = new CircularShiftFilter(pipeC);
        SimplePipe pipeD = new SimplePipe((Writeable) circularShiftFilter);
        SplitLineFilter splitLineFilter = new SplitLineFilter(pipeD);
        SimplePipe pipeE = new SimplePipe((Writeable) splitLineFilter);
        Source source = new ReadLineSource(pipeE, inputFilePath);
        source.run();
    }

    private static void exerciseB(String inputPath, int lineLength, Alignment alignment) {
        HashMap<String, Integer> map = new HashMap<>();
        fillHashMap(map, FREQUENT_WORDS_BOUND);


        WriteIndexFileSink writer = new WriteIndexFileSink();

        SimplePipe pipeA = new SimplePipe(writer);
        SortFilter sortFilter = new SortFilter(pipeA);

        SimplePipe pipeB = new SimplePipe((Writeable) sortFilter);
        FrequentWordFilter frequentWordFilter = new FrequentWordFilter(pipeB, map);

        SimplePipe pipeC = new SimplePipe((Writeable) frequentWordFilter);
        CircularShiftFilter circularShiftFilter = new CircularShiftFilter(pipeC);

        SimplePipe pipeD = new SimplePipe((Writeable) circularShiftFilter);
        SplitLineFilter splitLineFilter = new SplitLineFilter(pipeD);

        WriteStoryFileSink writer2 = new WriteStoryFileSink();

        DoubleExitPushPipe doubleExitPushPipe = new DoubleExitPushPipe(splitLineFilter, writer2);
        CreateAlignementFilter createAlignementFilter = new CreateAlignementFilter(doubleExitPushPipe, lineLength, alignment);

        SimplePipe pipeH = new SimplePipe((Writeable) createAlignementFilter);
        CreateLineFilter createLineFilter = new CreateLineFilter(pipeH, lineLength);

        SimplePipe pipeG = new SimplePipe((Writeable) createLineFilter);
        CreateWordFilter createWordFilter = new CreateWordFilter(pipeG);

        SimplePipe pipeF = new SimplePipe((Writeable) createWordFilter);
        Source source = new ReadCharacterSource(pipeF, inputPath);
        source.run();
    }

    private static void fillHashMap(HashMap<String, Integer> map, int bound) {
        File frequentWords = new File(FREQUENT_WORDS_PATH);
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(frequentWords));

            String line;
            while ((line = br.readLine()) != null) {
                String[] str = line.split("\t");
                if (str.length > 1) {
                    try {
                        int number = Integer.parseInt(str[2]);
                        if (number > bound) {
                            map.put(str[1], Integer.parseInt(str[2]));
                        }
                    } catch (NumberFormatException e) {
                        //do Nothing
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
