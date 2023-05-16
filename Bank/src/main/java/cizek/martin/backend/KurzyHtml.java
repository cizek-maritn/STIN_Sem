/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cizek.martin.backend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import org.jsoup.Jsoup;

/**
 *
 * @author Dell
 */
public class KurzyHtml {
    public void StahnutiKurzu() throws IOException {
        Calendar kalendar = Calendar.getInstance();
        String Url = UrlBuilder(kalendar);
        String html = Jsoup.connect(Url).get().html();
        HtmlParsovani(html);
    }
    
    public static String UrlBuilder(Calendar kalendar) {
        int den = kalendar.get(Calendar.DATE);
        int mesic = kalendar.get(Calendar.MONTH)+1;
        String mesicNula = "";
        if (mesic>9) {
            mesicNula+=mesic;
        } else {
            mesicNula="0"+mesic;
        }
        int rok = kalendar.get(Calendar.YEAR);
        return "https://www.cnb.cz/cs/financni-trhy/devizovy-trh/kurzy-"
                + "devizoveho-trhu/kurzy-devizoveho-trhu/denni_kurz.txt;"
                + "jsessionid=20E48284AE75A685CD8C35D4A26A9D08?date=" + den +
                "." + mesicNula + "." + rok;
    }
    
    private void HtmlParsovani(String html) throws IOException {
        File txtSoubor=new File("src/main/java/cizek/martin/backend/data/kurzy.txt");
        if (!txtSoubor.exists()) {
            txtSoubor.createNewFile();
        }
        String[] rozdelenyString=html.split("\\s+");
        String output="CZK|1 ";
        for (String str : rozdelenyString) {
            str=str.replace(",", ".");
            String[] temp = str.split("\\|");
            if (temp.length==5) {
                if (!temp[4].equals("kurz")) {
                    float kurzDoCZK=Float.parseFloat(temp[4])/Integer.parseInt(temp[2]);
                    String format=String.format("%.3f", kurzDoCZK);
                    output+=temp[3]+"|"+format+" ";
                }
            }
            if (temp.length==4) {
                float kurzDoCZK=Float.parseFloat(temp[3])/Integer.parseInt(temp[1]);
                    String format=String.format("%.3f", kurzDoCZK);
                    output+=temp[2]+"|"+format+" ";
            }
        }
        try (BufferedWriter bw = Files.newBufferedWriter(txtSoubor.toPath(), StandardOpenOption.WRITE)) {
            bw.write(output);
        }
    }
}
