import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.FileAlreadyExistsException;

public class CopyByFileChannel {

    static class FileCopyFailedException extends Exception {

        public FileCopyFailedException(String message) {
            super(message);
        }

        public FileCopyFailedException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static void main(String[] args) throws FileAlreadyExistsException, FileCopyFailedException {
        File descFile = new File("D:\\AccountingBase\\*.*");
        if (descFile.exists()) {
            throw new FileAlreadyExistsException(" File Already Exists");
        }
        try {
            long startTime = System.currentTimeMillis();
            FileChannel sourceChannel = new FileInputStream("С:\\archive\\1s\\AccountingBase\\*.*").getChannel();
            FileChannel destChannel = new FileOutputStream("D:\\AccountingBase\\*.*").getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

            sourceChannel.close();
            destChannel.close();
            long endTime = System.currentTimeMillis();
            System.out.println("Time of copying ByByte " + (endTime - startTime) + " ms");
        } catch (java.io.IOException e) {
            throw new FileCopyFailedException(e.getMessage());
        }

    }

}

