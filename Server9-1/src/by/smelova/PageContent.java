package by.smelova;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class PageContent {
    URL url;

    public PageContent(String urlname) throws MalformedURLException {
        url = new URL(urlname);
    }

    public void GetContent(String filename) {
        try {
            FileWriter writer = new FileWriter(filename, true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.url.openStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                writer.write(line);
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
