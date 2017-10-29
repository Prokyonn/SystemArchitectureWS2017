package exercise1PipesFilter.Filter;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class CircularShiftFilter extends DataTransformationFilter2<List<String>, List<String>> {
    private int lineCounter = 0;

    public CircularShiftFilter(Writeable<List<String>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected List<String> process(List<String> entity) {

        List<String> words = entity;
        List<String> shiftedLines = new ArrayList<>();
        if (!entity.isEmpty()) {
            String firstWord = words.get(0);

            lineCounter++;
            do {
                shiftedLines.add(shift(words) + "\t" + lineCounter);
            } while (!firstWord.equals(words.get(0)));
        }
        return shiftedLines;
    }

    public String shift(List<String> words) {
        String word = words.remove(0);
        words.add(word);

        StringBuilder sb = new StringBuilder();
        words.forEach(x -> sb.append(x).append(" "));

        return sb.toString();
    }
}
