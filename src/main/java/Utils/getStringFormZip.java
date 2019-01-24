package Utils;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class getStringFormZip {
    private static ArrayList<File> fileToCopy = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\ariel.hazan\\Downloads\\cloud_SupportData Wed Jan 23 2019 07_27_52 IST.zip";
        String sessionId = "64.0.2" ;
        findSessionID(path, sessionId);
    }


    private static void findSessionID(String path, String sessionId) throws IOException {
        System.out.println("---------------------extracting all zips---------------------");
        getStringFormZip.extractFolder(path);
        System.out.println("---------------------Found logs---------------------");
        findSessionIDLoop(path.split(".zip")[0], sessionId);
        System.out.println("---------------------Copy logs to " + sessionId + " folder---------------------");
        copyRelevantFiles(path, sessionId);
    }

    private static void copyRelevantFiles(String path, String sessionId) throws IOException {
        String targetBase = new File(path).getParent();
        for (File filePath : fileToCopy) {
            Path src = filePath.toPath();
            new File(targetBase + "/" + sessionId + "/").mkdir();
            Path target = new File(targetBase + "/" + sessionId + "/" + filePath.getName()).toPath();
            Files.copy(src, target, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private static void findSessionIDLoop(String path, String sessionId) {
        File[] listOfFiles = new File(path).listFiles();
        Arrays.stream(Objects.requireNonNull(listOfFiles)).forEach(file -> {
            if (file.isDirectory()) {
                findSessionIDLoop(file.getAbsolutePath(), sessionId);
            } else if (!file.getName().contains(".zip")) {
                BufferedReader br;
                try {
                    br = new BufferedReader(new FileReader(file));
                    String st;
                    while ((st = br.readLine()) != null)
                        if (st.contains(sessionId)) {
                            System.out.println(file.getName() + " -> " + st);
                            if (!fileToCopy.contains(file))
                                fileToCopy.add(file);
                        }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static void extractFolder(String zipFile) throws IOException {
        System.out.println("extract " + zipFile);
        int BUFFER = 4096;
        File file = new File(zipFile);

        ZipFile zip = new ZipFile(file);
        String newPath = zipFile.substring(0, zipFile.length() - 4);

        new File(newPath).mkdir();
        Enumeration zipFileEntries = zip.entries();

        // Process each entry
        while (zipFileEntries.hasMoreElements()) {
            // grab a zip file entry
            ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
            String currentEntry = entry.getName();
            File destFile = new File(newPath, currentEntry);
            //destFile = new File(newPath, destFile.getName());
            File destinationParent = destFile.getParentFile();

            // create the parent directory structure if needed
            destinationParent.mkdirs();

            if (!entry.isDirectory()) {
                BufferedInputStream is = new BufferedInputStream(zip
                        .getInputStream(entry));
                int currentByte;
                // establish buffer for writing file
                byte data[] = new byte[BUFFER];

                // write the current file to disk
                FileOutputStream fos = new FileOutputStream(destFile);
                BufferedOutputStream dest = new BufferedOutputStream(fos,
                        BUFFER);

                // read and write until last byte is encountered
                while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
                    dest.write(data, 0, currentByte);
                }
                dest.flush();
                dest.close();
                is.close();
            }

            if (currentEntry.endsWith(".zip")) {
                // found a zip file, try to open
                extractFolder(destFile.getAbsolutePath());
            }
        }
    }
}
