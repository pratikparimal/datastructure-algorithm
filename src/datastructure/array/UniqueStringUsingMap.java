package datastructure.array;

import java.util.LinkedHashMap;
import java.util.Map;

public class UniqueStringUsingMap {

    public Map<String, String> getUnique(String str) {
        Map<String, String > strMap = new LinkedHashMap<>();
        int count = 0;
        String strArray[] = str.split(" ");
        for (String s: strArray) {
            if (!strMap.containsKey(s)) {
                strMap.put(s, "Unique"+count++);
            }
        }
        return strMap;
    }

    public static void main(String[] args) {
        String str = "Ram walks Ram sleeps Ram smiles";
        UniqueStringUsingMap unique = new UniqueStringUsingMap();
        Map<String, String > strMap = unique.getUnique(str);
        System.out.println(strMap);
    }
}


