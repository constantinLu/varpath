import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Set the Docker variable in the path C:\Program Files\Java\jdk-17\bin
        ProcessBuilder pb1 = new ProcessBuilder("cmd.exe", "/c", "setx PATH \"%PATH%;C:\\Program Files\\Java\\jdk-17\bin\\\"");
        pb1.inheritIO();
        Process p1 = pb1.start();
        p1.waitFor();

        // Set the Java variable in the path
//            ProcessBuilder pb2 = new ProcessBuilder("cmd.exe", "/c", "setx PATH \"%PATH%;C:\\Program Files\\Java\\jdk-17.0.1\\bin\"");
//            pb2.inheritIO();
//            Process p2 = pb2.start();
//            p2.waitFor();
    }
}