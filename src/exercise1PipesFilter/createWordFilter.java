package exercise1PipesFilter;

import pmp.filter.AbstractFilter;
import pmp.filter.DataTransformationFilter2;
import pmp.filter.DataTransformationFilter3;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class createWordFilter extends DataTransformationFilter2<String, List<String>> {

    public createWordFilter(Writeable<List<String>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected ArrayList<String> process(String entity) {
        ArrayList<String> list = new ArrayList<>();
        String[] words = entity.split(" ");
        Arrays.stream(words).forEach(item -> list.add(item));

        return list;
    }
}
