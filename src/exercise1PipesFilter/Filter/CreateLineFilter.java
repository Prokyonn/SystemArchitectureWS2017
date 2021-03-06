package exercise1PipesFilter.Filter;

import pmp.filter.DataCompositionFilter;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;

public class CreateLineFilter extends DataCompositionFilter<StringBuilder, StringBuilder> {

    private StringBuilder _builder;
    private int _lineLength = 0;

    public CreateLineFilter(Writeable<StringBuilder> output, int lineLength) throws InvalidParameterException {
        super(output);
        _lineLength = lineLength;
        _builder = new StringBuilder();
    }

    @Override
    protected boolean fillEntity(StringBuilder nextVal, StringBuilder entity) {
        if (nextVal != null) {
            if (entity.length() + 1 + nextVal.length() <= _lineLength) {
                if (nextVal.length() > 0) {
                    entity.append(" ").append(nextVal);
                }
                return false;
            } else {
                _builder = new StringBuilder(nextVal);
                return true;
            }
        } else {
            return true;
        }
    }

    @Override
    protected StringBuilder getNewEntityObject() {
        return _builder;
    }
}
