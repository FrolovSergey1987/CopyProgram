import java.io.*;
import java.nio.file.FileAlreadyExistsException;

public class CopyWithOneByte {
    static class FileCopyFailedException extends Exception {

        public FileCopyFailedException(String message) {
            super(message);
        }

        public FileCopyFailedException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static void main(String[] args) throws FileAlreadyExistsException, FileCopyFailedException  {


        long startTime = System.currentTimeMillis();
        File descFile = new File("D:\\AccountingBase\\*.*");
        if (descFile.exists()) {
            throw new FileAlreadyExistsException("File already exist !!!");
        }
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("С:\\archive\\1s\\AccountingBase\\*.*"));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:\\AccountingBase\\*.*"));

            int c = 0;
            while (true) {
                c = bis.read();
                if (c != -1)
                    bos.write(c);
                else
                    break;
            }
            bis.close();
            bos.flush();
            bos.close();
            long endTime = System.currentTimeMillis();
            System.out.println("Time of copying ByByte " + (endTime - startTime) + " ms");
        } catch (IOException e) {
            throw new FileCopyFailedException(e.getMessage());
        }
    }

}


