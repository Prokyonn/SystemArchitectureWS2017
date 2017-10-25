package exercise1PipesFilter;

import pmp.filter.Sink;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class WriteFileSink extends Sink<List<String>> {
    OutputStream os = null;

    public WriteFileSink() {
        try {
            os = new FileOutputStream("test.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(List<String> value) throws StreamCorruptedException {
        try {
            for (String line : value) {
                os.write(line.getBytes());
                os.write("\n".getBytes());
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
