import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class Program {

    public static void main(String[] args) throws IOException {

        int[] array0 = {0,1,2,1,0,1,2,3,0};

        FileOutputStream fos = new FileOutputStream("save.out");
        for (int i = 0; i < 3; i++) {
            byte wr = 0;
            for (int j = 0; j < 3; j++) {
                wr += (byte) (array0[3*i + j] << (j * 2));
            }
            fos.write(wr);
        }
        fos.flush();
        fos.close();

        int[] array1 = new int[9];

        FileInputStream fis = new FileInputStream("save.out");
        int n;
        int i = 0;
        while ((n = fis.read()) != -1){
            for (int j = 0; j < 3; ++j) {
                array1[i++] = n >> (j * 2) & 0x3;
            }
        }
        fis.close();

        System.out.println(Arrays.toString(array1));

        creatBackupCopy();
//        int[] array1 = readFile("src/main/resources/test.txt");
//        for(int elem : array1){
//            System.out.print(elem + " ");
//        }

    }
    public static void writeToFile(String str) throws IOException {

        try (FileWriter fileWriter = new FileWriter("src/main/resources/test.txt")){

            fileWriter.write(str);
        }

    }

    public static String writeTogether(int[] array){
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < array.length - 1; i++) {
            stringBuilder.append(array[i]).append("0");
        }
        stringBuilder.append(array[array.length -1]);
        return stringBuilder.toString();
    }

    public static int[] readFile(String path) throws IOException {
        ArrayList<Integer> array = new ArrayList<>();
        try(FileReader reader = new FileReader(path)){
            int n;
            boolean flag = true;
            while ((n = reader.read()) != -1){
                char temp = (char) n;
                if(temp == '0' && flag)
                    flag = false;
                else {
                    flag = true;
                    try {
                        array.add(Integer.parseInt(String.valueOf(temp)));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        int[] result = new int[array.size()];
        int index = 0;
        for (Integer i : array) {
            result[index++] = i;
        }
        return result;
    }

    public static void creatBackupCopy() throws IOException {
        Files.createDirectory(Path.of("./backup"));

        DirectoryStream<Path> directory = Files.newDirectoryStream(Path.of("."));
        for(Path file : directory){
            if(Files.isDirectory(file)) continue;
            Files.copy(file, Path.of("./backup" + file.toString()));
        }
    }
}
