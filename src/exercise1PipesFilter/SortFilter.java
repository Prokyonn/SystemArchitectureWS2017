package exercise1PipesFilter;

import pmp.filter.DataCompositionFilter;
import pmp.filter.DataTransformationFilter2;
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
            for (int i = 0; i < nextVal.size(); i++) {
//                if (entity.isEmpty()) {
//                    entity.add(nextVal.get(i));
//                    continue;
//                }
//                for (int j = 0; j < entity.size(); j++) {
//                    if (entity.get(j).compareToIgnoreCase(nextVal.get(i)) <= 0) {
//                        if (entity.size() == j + 1) {
//                            entity.add(nextVal.get(i));
//                            break;
//                        }
//                    } else {
//                        entity.add(j, nextVal.get(i));
//                        break;
//                    }
//                }
                entity.add(nextVal.get(i));
            }
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
