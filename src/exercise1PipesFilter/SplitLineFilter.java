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

public class SplitLineFilter extends DataTransformationFilter2<String, List<String>> {

    public SplitLineFilter(Writeable<List<String>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected ArrayList<String> process(String entity) {
        ArrayList<String> list = new ArrayList<>();
        String[] words = entity.split(" ");
        String regex = "([\\W\\d_])+";

//        for(String s : words){
//            s.replaceAll(regex,"");
//            if(!s.isEmpty()){
//                list.add(s);
//            }
//        }

        Arrays.stream(words).forEach(item -> {
            item.replaceAll(regex, "");
            if (!item.isEmpty())
                list.add(item);
        });

        return list;
    }
}
