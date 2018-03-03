package Control;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.teamdev.jxbrowser.chromium.Browser;

import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import Modelo.Sesion;

public class mapsController {

    Sesion ses;
    BufferedWriter bufw;
    BrowserView browserView;

    public mapsController(Sesion ses) {
        this.ses = ses;
        browserView = htmlgenerado();
    }

    public BrowserView htmlgenerado() {

        File ficheroMapas = new File("." + File.separator + "Mapa" + File.separator + "mapas.html");
        File mapaGenerado = new File("mapa-generado.html");
        try {
            Document document = Jsoup.parse(ficheroMapas, "UTF-8");
            //System.out.println(document.outerHtml());
            Elements coincidencias = document.select("head > script:nth-child(3)");
            //System.out.println(coincidencias.get(0).text());

            StringBuilder funcionInitialize = new StringBuilder("  function initialize() {\n"
                    + "      var marcadores = [\n");

            /*
			 * Para mejorar la eficiencia en la concatenacion de String dentro de un for, lo mas
			 * adecuado es utilizar un StringBuilder, en vez de +=
             */
            for (int i = 0; i < ses.getVectorLatitud().size(); i++) {
                //System.out.println("i: "+	i);
                funcionInitialize.append("[");
                if (i == 0) {
                    funcionInitialize.append("'Inicio', ");
                } else if (i == ses.getVectorLatitud().size() - 1) {
                    funcionInitialize.append("'Final', ");
                } else {
                    funcionInitialize.append("'Recorrido', ");
                }
                funcionInitialize.append(ses.getVectorLatitud().get(i)).append(", ").append(ses.getVectorLongitud().get(i)).append("]");
                if (i != ses.getVectorLatitud().size() - 1) {
                    funcionInitialize.append(",\n");
                } else {
                    funcionInitialize.append("\n");
                }
            }
            funcionInitialize.append("];");

            coincidencias.get(0).prepend(funcionInitialize.toString());

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("mapa-generado.html"))) {
                bufferedWriter.write(document.outerHtml());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Browser browser = new Browser();
        browser.setSize(946, 486);
        browser.loadURL("file://" + mapaGenerado.getAbsolutePath());
        BrowserView browserView = new BrowserView(browser);
        final Rectangle correction = new Rectangle(0, 0, 946, 486);
        System.out.println("Resolución correction: " + correction);
       
        //browser.loadURL("www.marca.com");

        return browserView;

    }

    public BrowserView getBrowserView() {
        return browserView;
    }
}
