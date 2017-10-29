package exercise1PipesFilter;

import pmp.filter.DataCompositionFilter;
import pmp.interfaces.Writeable;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

public class CreateWordFilter extends DataCompositionFilter<Character, StringBuilder> {

    public CreateWordFilter(Writeable<StringBuilder> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected boolean fillEntity(Character nextVal, StringBuilder entity) {
        if (nextVal != ' ' && nextVal != '\n' && nextVal != '\t' && nextVal != '\r') {
            entity.append(nextVal);
            return false;
        }
        return true;
    }

    @Override
    protected StringBuilder getNewEntityObject() {
        return new StringBuilder();
    }

    @Override
    public void write(Character value) throws StreamCorruptedException {
        super.write(value);
    }
}
