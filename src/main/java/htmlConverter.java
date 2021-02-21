import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//package com.amazonaws.codesamples.gsg;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
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

        String jsonword = "";
        //String jsonDictionary = "{\"Dictionary\":[{";
        String Word = "";
        String Def = "";

        File Dict = new File("JSONDictionary.json");

        while (w.find(dend)) {
            String jsonDictionary = "{\"Dictionary\":[{";
            for (int i=0; i<25; i++) {
                Word worddef = new Word();

                w.find(dend);
                worddef.setWord(w.group(1));
                Word = w.group(1);
                wend = w.end();

                d.find(wend);
                worddef.setDefinition(d.group(1));
                Def = d.group(1);
                dend = d.end();
                jsonDictionary = jsonDictionary + "\"PutRequest\": {\n" +
                        "                \"Item\": {\n" +
                        "                    \"Word\": {\n" +
                        "                        \"S\": \"" + Word + "\"\n" +
                        "                    },\n" +
                        "                    \"Definition\": {\n" +
                        "                        \"S\": \" " + Def + "\"}}}}";
                if (i < 24 && w.find(dend) )
                    jsonDictionary = jsonDictionary + ",{";
                if (!w.find(dend))
                    break;
            }
            jsonDictionary = jsonDictionary + "]}";
            System.out.println(jsonDictionary.toString());

            BufferedWriter writer = new BufferedWriter(new FileWriter("JSONDictionary.json"));
            writer.write(jsonDictionary);
            writer.close();
        }
       // jsonDictionary = jsonDictionary + "]}";
        //System.out.println(jsonDictionary.toString());
        //File Dict = new File("JSONDictionary.json");
        //BufferedWriter writer = new BufferedWriter(new FileWriter("JSONDictionary.json"));
        //writer.write(jsonDictionary);
        //writer.close();
    }
}


//if (w.find(dend))
 //       jsonDictionary = jsonDictionary + ",{";