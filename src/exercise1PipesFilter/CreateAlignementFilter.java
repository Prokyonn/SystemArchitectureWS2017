package exercise1PipesFilter;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;

public class CreateAlignementFilter extends DataTransformationFilter2<StringBuilder,String> {
    private int _lineLength = 0;
    private Alignment _alignment;

    public CreateAlignementFilter(Writeable<String> output, int lineLength, Alignment alignment) throws InvalidParameterException {
        super(output);
        _lineLength = lineLength;
        _alignment = alignment;
    }

    @Override
    protected String process(StringBuilder entity) {
        String returnValue = "";
        switch (_alignment){
            case Left:
                break;
            case Right:
                returnValue = alignRight(entity);
                break;
            case Center:
                break;
        }
        return returnValue;
    }

    private String alignRight(StringBuilder builder){
        while(builder.length() < _lineLength){
            builder.append(" ", 0, 1);
        }
        return builder.toString();
    }
}
