package exercise1PipesFilter.Filter;

import pmp.filter.DataCompositionFilter;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

public class SortFilter extends DataCompositionFilter<List<String>, List<String>> {

    public SortFilter(Writeable<List<String>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected boolean fillEntity(List<String> nextVal, List<String> entity) {
        if (nextVal != null) {
            entity.addAll(nextVal);
            return false;
        } else {
            entity.sort(null);
            return true;
        }
    }

    @Override
    protected List<String> getNewEntityObject() {
        return new LinkedList<>();
    }
}
