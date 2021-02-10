import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.source.tree.WhileLoopTree;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import javax.print.Doc;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;

public class htmlConverter {

    public static <word> void main(String[] args) throws FileNotFoundException {

        File file = new File("test.html");

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String nextToken = scanner.next();

            scanner.useDelimiter("\\Z");

            Pattern header = Pattern.compile("<h1>(.+?)</h1>");
            Matcher w = header.matcher(nextToken);

            Pattern definition = Pattern.compile("<def>(.+?)</def>");
            Matcher d = definition.matcher(nextToken);
            d.find();

            while (w.find()&&d.find()) {

               System.out.println("word - " + w.group(1));
                   System.out.println("Definition - " + d.group(1));

            }

        }
    }
}
