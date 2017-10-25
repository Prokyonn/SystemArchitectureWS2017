package exercise1PipesFilter;

import pmp.filter.DataTransformationFilter1;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class FrequentWordFilter extends DataTransformationFilter1<List<String>> {
    private HashMap<String, Integer> _frequentWords;
    public FrequentWordFilter(Writeable<List<String>> output, HashMap<String,Integer> frequentWords) throws InvalidParameterException {
        super(output);
        _frequentWords =frequentWords;
    }

    @Override
    protected void process(List<String> entity) {
        List<String> toRemove = new LinkedList<>();
        for(String line:entity){
            if(_frequentWords.containsKey(line.trim().split(" ")[0].toLowerCase().replace("\"",""))){
                toRemove.add(line);
            }
        }

        for(String lineToRemove:toRemove){
            entity.remove(lineToRemove);
        }
    }
}
