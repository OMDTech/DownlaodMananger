import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadManager implements Runnable {
    String filePath;
    File f;
    String link;
    String fileType;

    public DownloadManager(String link , String filePath) {
        this.link = link;
        this.filePath = filePath;
        File f = new File(filePath);
        if (!f.exists()){
           boolean s = f.mkdirs();
            System.out.println(s);
        }
    }

    @Override
    public void run() {
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            f = new File(filePath, "file."+fileType);
            BufferedInputStream stream = new BufferedInputStream(connection.getInputStream());
            byte b[] = new byte[1024];
            OutputStream outputStream = new FileOutputStream(f);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream , 1024);
            int Bytes =0 ,downloaded =0 ;
            while ((Bytes=stream.read(b , 0 , 1024)) !=-1){
                bufferedOutputStream.write(b , 0 , 1024);
                downloaded+=Bytes;
                System.out.println(downloaded);
            }
            outputStream.close();
            stream.close();
            bufferedOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
