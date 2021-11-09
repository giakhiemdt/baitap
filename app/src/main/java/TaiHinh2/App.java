/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package TaiHinh2;

// public class App {
//     public String getGreeting() {
//         return "Hello World!";
//     }

//     public static void main(String[] args) {
//         System.out.println(new App().getGreeting());
//     }
// }

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * NHT
 */
public class App {
    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect("https://kissmanga.org/chapter/manga-ee981361/chapter-1").timeout(20000).get();
        List<String> img = new ArrayList<>();
        Elements im = doc.getElementsByTag("img");
        for (int i = 0; i < im.size(); i++) {
            String url = im.get(i).absUrl("src");
            if (url.equals("")) {
                continue;
            }
            if (url.contains("blazefast")) {
                img.add(url);
            }
        }

        PrintWriter pw = new PrintWriter(new File("link.txt"));
        pw.print(img);
        pw.close();
        layLink(img);
    }

    public static void layLink(List<String> img) {
        for (int a = 0; a < img.size(); a++) {
            String name = "" + (a + 1) + ".jpg";
            String link = img.get(a);
            taiAnh(link, name);
        }
    }

    public static void taiAnh(String link, String name) {
        try {
            URL url = new URL(link);

            InputStream in = url.openStream();
            OutputStream out = new BufferedOutputStream(
                    new FileOutputStream("C:\\Users\\khiem\\Desktop\\Img" + "\\" + name));
            for (int b; (b = in.read()) != -1;) {
                out.write(b);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can not Dowload File !");
        }
    }
}