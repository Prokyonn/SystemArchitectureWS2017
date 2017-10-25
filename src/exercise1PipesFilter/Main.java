package exercise1PipesFilter;

import pmp.filter.Source;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;

import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        //AufgabeA();
        AufgabeB();
    }

    private static void AufgabeA() {
        String alice = "data/aliceInWonderland.txt";
        String testfile = "data/testFile.txt";
        HashMap<String, Integer> map = new HashMap<>();
        try {
            fillHashMap(map, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        Source source = new ReadLineSource(pipeE, alice);
        source.run();
        /*        Source source = new ReadLineSource(
                new SplitLineFilter(
                        new CircularShiftFilter(
                                new FrequentWordFilter(
                                        new SortFilter(
                                                new WriteIndexFileSink()), map)
                        )
                ), alice);*/

    }

    private static void AufgabeB() {
        String alice = "data/aliceInWonderland.txt";
        String testfile = "data/testFile.txt";
        HashMap<String, Integer> map = new HashMap<>();
        try {
            fillHashMap(map, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }

        WriteIndexFileSink writer = new WriteIndexFileSink();
        SimplePipe pipeA = new SimplePipe(writer);
        SortFilter sortFilter = new SortFilter(pipeA);
        SimplePipe pipeB = new SimplePipe((Writeable) sortFilter);
        FrequentWordFilter frequentWordFilter = new FrequentWordFilter(pipeB, map);
        SimplePipe pipeC = new SimplePipe((Writeable) frequentWordFilter);
        CircularShiftFilter circularShiftFilter = new CircularShiftFilter(pipeC);
        SimplePipe pipeD = new SimplePipe((Writeable) circularShiftFilter);
        SplitLineFilter splitLineFilter = new SplitLineFilter(pipeD);
        //SimplePipe pipeE = new SimplePipe((Writeable) splitLineFilter);

        WriteStoryFileSink writer2 = new WriteStoryFileSink();

        DoubleExitPushPipe doubleExitPushPipe = new DoubleExitPushPipe(splitLineFilter, writer2);

        CreateAlignementFilter createAlignementFilter = new CreateAlignementFilter(doubleExitPushPipe, 150, Alignment.Right);
        SimplePipe pipeH = new SimplePipe((Writeable) createAlignementFilter);
        CreateLineFilter createLineFilter = new CreateLineFilter(pipeH, 150);
        SimplePipe pipeG = new SimplePipe((Writeable) createLineFilter);
        CreateWordFilter createWordFilter = new CreateWordFilter(pipeG);
        SimplePipe pipeF = new SimplePipe((Writeable) createWordFilter);
        Source source = new ReadCharacterSource(pipeF, alice);
        source.run();
    }

    private static void fillHashMap(HashMap<String, Integer> map, int bound) throws IOException {
        File frequentWords = new File("data/frequentEnglishWords.txt");
        BufferedReader br = new BufferedReader(new FileReader(frequentWords));
        String line;
        while ((line = br.readLine()) != null) {
            String[] str = line.split("\t");
            if (str.length > 1) {
                try {
                    int number = Integer.parseInt(str[2]);
                    if (number > bound)
                        map.put(str[1], Integer.parseInt(str[2]));
                } catch (NumberFormatException e) {
//                    e.printStackTrace();
                }
            }
        }
    }
}
