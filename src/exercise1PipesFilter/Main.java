package exercise1PipesFilter;

import pmp.filter.Source;

public class Main {
    public static void main(String[] args) {
        Source source = new ReadFileSource(new CreateWordFilter(new CircularShiftFilter(new SortFilter(new WriteFileSink()))), "data/aliceInWonderland.txt");
        source.run();
    }
}
