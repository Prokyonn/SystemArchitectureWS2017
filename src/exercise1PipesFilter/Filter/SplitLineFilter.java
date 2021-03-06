package exercise1PipesFilter.Filter;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitLineFilter extends DataTransformationFilter2<String, List<String>> {

    public SplitLineFilter(Writeable<List<String>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected ArrayList<String> process(String entity) {
        ArrayList<String> list = new ArrayList<>();
        String[] words = entity.split(" ");
        String regex = "([\\W\\d_])+";

        Arrays.stream(words).forEach(item -> {
            item.replaceAll(regex, "");
            if (!item.isEmpty()) {
                list.add(item);
            }
        });

        return list;
    }
}
