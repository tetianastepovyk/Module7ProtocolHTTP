import java.io.*;
import java.net.URL;

public class HttpStatusImageDownloader {
    public void downloadStatusImage(int code) {
        try {
            URL url = new URL(new HttpStatusChecker().getStatusImage(code));
            if (url == null) {
                throw new RuntimeException("Image not found for status code: " + code);
            } else {
                InputStream in = new BufferedInputStream(url.openStream());
                FileOutputStream out = new FileOutputStream("img" + code + ".jpg");
                byte[] buf = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(buf)) != -1) {
                    out.write(buf, 0, bytesRead);
                }
                out.close();
                in.close();
            }
        } catch (IOException e) {
            System.err.println("Image not found for status code: " + code);
        }
    }
}
