package javaStudy.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class FileTest {
    public static void main(String[] args) throws IOException, URISyntaxException {

        String dirName = "c:" + File.separator + "SSAFY" + File.separator + "mydir";

        File file1 = new File(dirName);
        file1.mkdir();

        File file2 = new File(dirName, "test2.txt");
        file2.createNewFile();

        File file3 = new File(new URI("file:///C:/SSAFY/mydir/test3.txt"));
        file3.createNewFile();

        //file3.delete();
    }
}
