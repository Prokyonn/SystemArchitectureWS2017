package exercise1PipesFilter;

import pmp.filter.Source;
import pmp.interfaces.Writeable;

import java.io.*;
import java.security.InvalidParameterException;

public class ReadLineSource extends Source<String> {
    private BufferedReader br;

    public ReadLineSource(Writeable<String> output, String filePath) throws InvalidParameterException {
        super(output);
        String path = filePath;
        try {
            br = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String read() throws StreamCorruptedException {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
