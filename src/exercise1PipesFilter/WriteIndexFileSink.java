package exercise1PipesFilter;

import pmp.filter.Sink;

import java.io.*;
import java.util.List;

public class WriteIndexFileSink extends Sink<List<String>> {
    OutputStream os = null;

    public WriteIndexFileSink() {
        try {
            os = new FileOutputStream("Index.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(List<String> value) throws StreamCorruptedException {
        if (value != null) {
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
}
