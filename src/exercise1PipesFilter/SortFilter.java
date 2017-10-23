package exercise1PipesFilter;

import javafx.collections.transformation.SortedList;
import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.List;

public class SortFilter extends DataTransformationFilter2<List<String>, List<String>> {
    private List<String> sortedLines;

    public SortFilter(Writeable<List<String>> output) throws InvalidParameterException {
        super(output);
        sortedLines = new SortedList<>(null);

    }

    @Override
    protected List<String> process(List<String> entity) {

        return null;
    }
}
