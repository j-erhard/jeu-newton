package client.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Tree {
    public HashMap<String, ArrayList<String>> hashMap = new HashMap<>();

    public Tree() {
        hashMap.put("start", new ArrayList<>(Arrays.asList("case1", "case6", "case19")));
        hashMap.put("case1", new ArrayList<>(Arrays.asList("start", "case2", "case3")));
        hashMap.put("case2", new ArrayList<>(Arrays.asList("case1")));
        hashMap.put("case3", new ArrayList<>(Arrays.asList("case1", "pomme1")));
        hashMap.put("pomme1", new ArrayList<>(Arrays.asList("case3", "case4")));
        hashMap.put("case4", new ArrayList<>(Arrays.asList("pomme1", "case5")));
        hashMap.put("case5", new ArrayList<>(Arrays.asList("case4", "potion1")));
        hashMap.put("potion1", new ArrayList<>(Arrays.asList("case5")));
        hashMap.put("case6", new ArrayList<>(Arrays.asList("start", "case7")));
        hashMap.put("case7", new ArrayList<>(Arrays.asList("case6", "pomme2")));
        hashMap.put("pomme2", new ArrayList<>(Arrays.asList("case7", "case8", "case12")));
        hashMap.put("case8", new ArrayList<>(Arrays.asList("pomme2", "case9")));
        hashMap.put("case9", new ArrayList<>(Arrays.asList("case8", "case10")));
        hashMap.put("case10", new ArrayList<>(Arrays.asList("case9", "case11")));
        hashMap.put("case11", new ArrayList<>(Arrays.asList("case10", "potion2")));
        hashMap.put("potion2", new ArrayList<>(Arrays.asList("case11", "case16")));
        hashMap.put("case12", new ArrayList<>(Arrays.asList("pomme2", "case13", "case17")));
        hashMap.put("case13", new ArrayList<>(Arrays.asList("case12", "case14")));
        hashMap.put("case14", new ArrayList<>(Arrays.asList("case13", "case15")));
        hashMap.put("case15", new ArrayList<>(Arrays.asList("case14", "case16")));
        hashMap.put("case16", new ArrayList<>(Arrays.asList("case15", "potion2")));
        hashMap.put("case17", new ArrayList<>(Arrays.asList("case12", "case18")));
        hashMap.put("case18", new ArrayList<>(Arrays.asList("case17", "potion3")));
        hashMap.put("potion3", new ArrayList<>(Arrays.asList("case18")));
        hashMap.put("case19", new ArrayList<>(Arrays.asList("start", "case20")));
        hashMap.put("case20", new ArrayList<>(Arrays.asList("case19", "case21", "case24")));
        hashMap.put("case21", new ArrayList<>(Arrays.asList("case20", "pomme3")));
        hashMap.put("pomme3", new ArrayList<>(Arrays.asList("case21", "case22")));
        hashMap.put("case22", new ArrayList<>(Arrays.asList("pomme3", "case23")));
        hashMap.put("case23", new ArrayList<>(Arrays.asList("case22", "potion4")));
        hashMap.put("potion4", new ArrayList<>(Arrays.asList("case23")));
        hashMap.put("case24", new ArrayList<>(Arrays.asList("case20", "case25")));
        hashMap.put("case25", new ArrayList<>(Arrays.asList("case24", "case26")));
        hashMap.put("case26", new ArrayList<>(Arrays.asList("case25", "potion5")));
        hashMap.put("potion5", new ArrayList<>(Arrays.asList("case26")));
    }

    public boolean keyHas(String key, String targetValue){
        if (!hashMap.containsKey(key)) return false;
        for(String value:hashMap.get(key)){
            if(value.equals(targetValue)) return true;
        }
        return false;
    }
}
