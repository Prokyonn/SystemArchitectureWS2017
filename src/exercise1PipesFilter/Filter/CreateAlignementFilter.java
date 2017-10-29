package exercise1PipesFilter.Filter;

import exercise1PipesFilter.Alignment;
import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;

public class CreateAlignementFilter extends DataTransformationFilter2<StringBuilder, String> {
    private int _lineLength = 0;
    private Alignment _alignment;

    public CreateAlignementFilter(Writeable<String> output, int lineLength, Alignment alignment) throws InvalidParameterException {
        super(output);
        _lineLength = lineLength;
        _alignment = alignment;
    }

    @Override
    protected String process(StringBuilder builder) {
        //Remove all spaces and then begin with alignment
        String line = builder.toString().trim();

        switch (_alignment) {
            case left:
                line = alignLeft(line);
                break;
            case right:
                line = alignRight(line);
                break;
            case center:
                line = alignCenter(line);
                break;
        }
        return line;
    }

    private String alignRight(String line) {
        StringBuilder builder = new StringBuilder(line);
        while (builder.length() < _lineLength) {
            builder.insert(0, ' ');
        }
        return builder.toString();
    }

    private String alignLeft(String line) {
        StringBuilder builder = new StringBuilder(line);
        while (builder.length() < _lineLength) {
            builder.append(' ');
        }
        return builder.toString();
    }

    private String alignCenter(String line) {
        StringBuilder builder = new StringBuilder(line);
        while (builder.length() < _lineLength) {
            builder.insert(0, ' ');
            if (builder.length() < _lineLength) {
                builder.append(' ');
            }
        }
        return builder.toString();
    }
}
