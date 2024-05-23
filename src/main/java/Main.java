import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(readFile("src/main/resources/test2.txt"));
        exchangeChar("src/main/resources/test2.txt", 's');
        System.out.println(readFile("src/main/resources/test2.txt"));
    }

    public static String readFile(String path) throws IOException {
        String string;
        try(BufferedInputStream stream = new BufferedInputStream(new FileInputStream(path))){
            return new String(stream.readAllBytes());
        }
    }

    public static void exchangeChar (String path, char oldChar) throws IOException {
        String string = readFile(path);
        string = string.replace(oldChar, ' ');
        try (FileWriter writer = new FileWriter(path)){
            writer.write(string);
        }
    }

}
