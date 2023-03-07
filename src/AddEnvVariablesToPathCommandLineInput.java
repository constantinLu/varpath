import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//TODO: script for adding ENV variables to System Variables and NOT to user Variables (gizet)
//TODO: got to path from cmd C:/user/IdeaProject/varpath/src, javac AddEnvVariablesToPathCommandLineInput
//TODO: java
public class AddEnvVariablesToPathCommandLineInput {

    public static void main(String[] args) {
        if (args.length == 0) {
            log("Please specify the file path as a command-line argument.");
            return;
        }
        String filePath = args[0];
        List<String> paths = readPathsFromFile(filePath);

        // Add each path to the environment variable
        List<String> envPaths = new ArrayList<>();
        for (String path : paths) {
            addPathToEnv(path, envPaths);
        }
    }

    private static List<String> readPathsFromFile(String filePath) {
        List<String> paths = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                paths.add(line.trim());
            }
        } catch (IOException e) {
            log("Error reading file: " + e.getMessage());
        }
        return paths;
    }

    private static void addPathToEnv(String envPath, List<String> envPaths) {
        // Check if the path already exists in the environment variable
        if (envPaths.contains(envPath)) {
            log("Path " + envPath + " already exists in PATH. Skipping...");
            return;
        }

        // Add the path to the environment variable list
        envPaths.add(envPath);
        log("Path " + envPath + " added to PATH.");

        // Update the PATH environment variable
        StringBuilder newPath = new StringBuilder(System.getenv("PATH"));
        newPath.append(";").append(envPath);
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "setx", "PATH", newPath.toString(), "/m");
            pb.inheritIO().start().waitFor();
            log("Environment variable updated successfully.");
        } catch (InterruptedException | IOException e) {
            log("Error updating environment variable: " + e.getMessage());
        }
    }

    private static void log(String message) {
        System.out.println(message);
    }
}
