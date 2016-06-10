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
    Vector<Pair<String, Integer>> collocations;

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
        Map<String, Integer> countsPairs = new LinkedHashMap<>();
        String previos = parts[0];
        for(int i = 1; i<parts.length-1; ++i){
            if(parts[i].equalsIgnoreCase(pairs.firstElement().getKey())) {
                Integer count = countsPairs.get(parts[i-1]);
                if (count == null) {
                    countsPairs.put(parts[i-1], 1);
                } else {
                    countsPairs.put(parts[i-1], count + 1);
                }
                count = counts.get(parts[i+1]);
                if (count == null) {
                    countsPairs.put(parts[i+1], 1);
                } else {
                    countsPairs.put(parts[i+1], count + 1);
                }
            }
        }
        collocations = new Vector<>();
        for(Map.Entry<String, Integer> entry : countsPairs.entrySet()){
            pair = new Pair<>(entry.getKey(),entry.getValue());
            collocations.add(pair);
        }
        collocations.sort((Pair<String, Integer> o1, Pair<String, Integer> o2) -> o2.getValue() - o1.getValue());
    }

    public static void main(String[] args) {
        try
        {
            Main m = new Main();
            /*for(Pair<String, Integer> a : m.pairs)*/
            System.out.println(m.pairs.firstElement());
            for(Pair<String, Integer> a : m.collocations)
                System.out.println(a.getKey()+" "+a.getValue());

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}
