package exercise1PipesFilter;

import pmp.filter.Sink;

import java.io.*;

public class WriteStoryFileSink extends Sink<String> {
    OutputStream os = null;

    public WriteStoryFileSink() {
        try {
            os = new FileOutputStream("Book.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(String value) throws StreamCorruptedException {
        if (value != null) {
            try {
                os.write(value.getBytes());
                os.write("\n".getBytes());
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
