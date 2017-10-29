package exercise1PipesFilter;

import pmp.interfaces.IOable;
import pmp.interfaces.Writeable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

public class DoubleExitPushPipe implements IOable<String, String> {

    private Writeable<String> _out1;
    private Writeable<String> _out2;

    public DoubleExitPushPipe(Writeable<String> out1, Writeable<String> out2){
        _out1 = out1;
        _out2 = out2;
    }

    @Override
    public String read() throws StreamCorruptedException {
        throw new NotImplementedException();
    }

    @Override
    public void write(String value) throws StreamCorruptedException {
        if (_out1 == null || _out2 == null) {
            throw new InvalidParameterException("output filter can't be null!");
        }
        _out1.write(value);
        _out2.write(value);
    }
}
