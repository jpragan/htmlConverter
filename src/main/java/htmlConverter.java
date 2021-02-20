import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.sun.source.tree.WhileLoopTree;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import javax.print.Doc;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;

public class htmlConverter {

    public static <word> void main(String[] args) throws Exception {

        int wend = 0;
        int dend = 0;

        String content = "";
        Path filePath = Path.of("test.html");

        try {
            String actual = Files.readString(filePath);
            content = new String ( Files.readAllBytes( Paths.get(String.valueOf(filePath)) ) );
        }catch (Exception e){
            System.out.println(e);
        }

        Pattern word = Pattern.compile("<h1>(.+?)</h1>");
        Matcher w = word.matcher(content);
        Pattern definition = Pattern.compile("<def>(.+?)</def>");
        Matcher d = definition.matcher(content);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
       // JsonArray jsonDictionary = new JsonArray();
        String jsonword = "";
        String jsonDictionary = "[";

            while (w.find(dend)) {
                Word worddef = new Word();

            w.find(dend);
            worddef.setWord(w.group(1));
          //  System.out.println("word - " + w.group(1));
            wend = w.end();

            d.find(wend);
            worddef.setDefinition(d.group(1));
          //  System.out.println("def - " + d.group(1));
            dend = d.end();
           // worddef.toString();
            //System.out.println();
           // System.out.println();
            jsonword = gson.toJson(worddef);
           // System.out.println("wend - " + wend);
            if (wend < 20)
                jsonDictionary = jsonDictionary + jsonword;

            jsonDictionary = jsonDictionary + ", " + jsonword;
        }
        jsonDictionary = jsonDictionary + "]";
        System.out.println(jsonDictionary.toString());
    }
}
