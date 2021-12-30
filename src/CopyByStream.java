import java.io.*;
import java.nio.file.FileAlreadyExistsException;

public class CopyByStream {

    static class FileCopyFailedException extends Exception {

        public FileCopyFailedException(String message) {
            super(message);
        }

        public FileCopyFailedException(String message, Throwable cause) {
            super(message, cause);
        }

    }

    public static void main(String[] args) throws FileAlreadyExistsException, FileCopyFailedException {


        long startTime = System.currentTimeMillis();
        File descFile = new File("D:\\AccountingBase\\*.*");
        if (descFile.exists()) {
            throw new FileAlreadyExistsException("File Already Exists !!!");
        }
        try {
            InputStream copyingFile = new FileInputStream("С:\\archive\\1s\\AccountingBase\\*.*");
            OutputStream resultFile = new FileOutputStream("D:\\AccountingBase\\*.*");
            byte[] buffer = new byte[1024];
            int length;
            while ((length = copyingFile.read(buffer)) > 0) {
                resultFile.write(buffer, 0, length);
            }

            copyingFile.close();
            resultFile.flush();
            resultFile.close();
            long endTime = System.currentTimeMillis();
            System.out.println("Time of copying ByByte " + (endTime - startTime) + " ms");
        } catch (IOException e) {
            throw new FileCopyFailedException(e.getMessage());
        }
    }
}
