package exercise1PipesFilter;

import pmp.filter.Source;
import pmp.interfaces.Writeable;

import java.io.*;
import java.security.InvalidParameterException;

public class ReadCharacterSource extends Source<Character> {
    private String path;
    private BufferedReader br;

    public ReadCharacterSource(Writeable<Character> output, String filePath) throws InvalidParameterException {
        super(output);
        path = filePath;
        try {
            br = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Character read() throws StreamCorruptedException {
        try {
            int charValue = br.read();
            if (charValue == -1) {
                return null;
            } else {
                return (char) charValue;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
