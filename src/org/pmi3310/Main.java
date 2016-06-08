package org.pmi3310;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    String lineContents;
    String[] parts;
    Vector<Pair<String, Integer>> pairs;

    public Main() throws IOException {
        BufferedReader bReader = new BufferedReader(new FileReader("input.txt"));
        while((lineContents = bReader.readLine()) != null){
           // System.out.println(lineContents);
            parts = lineContents.split("\\s+|,\\s*|\\.\\s*");
        }
        bReader.close();
        Map<String, Integer> counts = new LinkedHashMap<>();
        for(String word : parts){
            Integer count = counts.get(word);
            if (count == null) {
                counts.put(word, 1);
            } else {
                counts.put(word, count + 1);
            }
        }
       // ArrayList<Pair<String, Integer>> pairs = new ArrayList<>();
        Pair<String, Integer> pair;
        pairs = new Vector<>();
        for(Map.Entry<String, Integer> entry : counts.entrySet()){
            pair = new Pair<>(entry.getKey(),entry.getValue());
            pairs.add(pair);
        }
        pairs.sort((Pair<String, Integer> o1, Pair<String, Integer> o2) -> o2.getValue() - o1.getValue());
    }

    public static void main(String[] args) {
        try
        {
            Main m = new Main();
            for(Pair<String, Integer> a :m.pairs)
            System.out.println(a);

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}
