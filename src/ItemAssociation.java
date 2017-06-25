import java.util.*;

/**
 * Created by Yu Wang on 2017-06-24.
 */
public class ItemAssociation {


    private static String[] solution(String[][] input) {
        if (input == null) return null;
        if (input.length < 1) return null;
        for (String[] row : input) {
            if (row.length != 2) return null;
        }

        Map<String, String> unionTable = new HashMap<>();
        for (String[] row : input) {
            unionTable.put(row[0], row[0]);
            unionTable.put(row[1], row[1]);
        }


        for (String[] row : input) {
            String unionKey = null, other = null;
            String key0 = unionTable.get(row[0]);
            String key1 = unionTable.get(row[1]);
            if (key0.compareTo(key1) < 0) {
                unionKey = key0;
                other = row[1];
            } else {
                unionKey = key1;
                other = row[0];
            }
            unionTable.put(other, unionKey);

        }

        Map<String, Integer> counts = new HashMap<>();
       /* for (Map.Entry<String, String> ent : unionTable.entrySet()) {
            counts.put(ent.getValue(), counts.getOrDefault(ent.getValue(), 0) + 1);
        }*/

        for (String str : unionTable.keySet()) {
            counts.put(unionTable.get(str), counts.getOrDefault(unionTable.get(str), 0) + 1);
        }


        int max = -1;
        String maxKey = null;
     /*   for (Map.Entry<String, Integer> ent : counts.entrySet()) {
            if (ent.getValue() > max || (ent.getValue() == max && ent.getKey().compareTo(maxKey) < 0)) {
                max = ent.getValue();
                maxKey = ent.getKey();
            }
        }*/

        for (String str : counts.keySet()) {
            if (counts.get(str) > max || (counts.get(str) == max && str.compareTo(maxKey) < 0)) {
                max = counts.get(str);
                maxKey = str;
            }
        }


        String[] res = new String[max];
   /*     for (Map.Entry<String, String> ent : unionTable.entrySet()) {
            if (ent.getValue().equals(maxKey)) {
                res[--max] = ent.getKey();
            }
        }*/

        for (String str: unionTable.keySet()){
            if (unionTable.get(str).equalsIgnoreCase(maxKey)){
                res[--max] = str;
            }
        }




        Arrays.sort(res);

        return res;
    }


    private static String[] solve(String[][] input) {

        Map<String, Set<String>> table = new HashMap<>();
        for (String[] record : input) {
            if (!table.containsKey(record[0])) {
                Set<String> temp = new HashSet<>();
                if (!table.containsKey(record[1])) {
                    temp.add(record[1]);
                } else {
                    temp.addAll(table.get(record[1]));
                    temp.add(record[1]);
                }

                table.put(record[0], temp);
            } else {
                Set<String> temp = table.get(record[0]);
                temp.add(record[1]);
                table.put(record[0], temp);
                if (table.containsKey(record[1])) {
                    temp.addAll(table.get(record[1]));
                }
            }

            if (!table.containsKey(record[1])) {
                Set<String> temp = new HashSet<>();
                if (!table.containsKey(record[0])) {
                    temp.add(record[0]);
                } else {
                    temp.addAll(table.get(record[0]));
                    temp.add(record[0]);
                }

                table.put(record[1], temp);
            } else {
                Set<String> temp = table.get(record[1]);
                temp.add(record[0]);
                table.put(record[1], temp);
                if (table.containsKey(record[0])) {
                    temp.addAll(table.get(record[0]));
                }
            }


        }

        int max = Integer.MIN_VALUE;
        for (
                String str : table.keySet())

        {
            if (max <= table.get(str).size()) {
                max = table.get(str).size();
            }
        }

        Set<String> result = new HashSet<>();
        for (
                String str : table.keySet())

        {
            if (max == table.get(str).size()) {
                result.addAll(table.get(str));
                result.add(str);
                break;
            }
        }

        String[] array = result.toArray(new String[max]);
        Arrays.sort(array);

        return array;


    }


    public static void main(String[] argus) {
        String[][] input1 = {{"a", "b"}, {"b", "c"}, {"g", "d"}, {"d", "f"}, {"f", "h"}};
        printArray(solve(input1));
        printArray(solution(input1));

        String[][] input2 = {{"a", "b"}, {"b", "c"}, {"g", "d"}, {"d", "f"}};
        printArray(solve(input2));
        printArray(solution(input2));

        System.out.println(Arrays.equals(
                new String[]{"friend3", "friend4", "friend5"}
                , solution(new String[][]{
                        {"friend1", "friend2"},
                        {"friend3", "friend4"},
                        {"friend4", "friend5"},
                })));

        printArray(solution(new String[][]{
                {"friend1", "friend2"},
                {"friend3", "friend4"},
                {"friend4", "friend5"},
        }));

        printArray(solution(new String[][]{
                {"friend1", "friend2"},
                {"friend3", "friend4"},
        }));

        System.out.println(Arrays.equals(
                new String[]{"friend1", "friend2"}
                , solution(new String[][]{
                        {"friend1", "friend2"},
                        {"friend3", "friend4"},
                })));
    }


    private static void printArray(String[] s) {
        for (String x : s) System.out.print(x + " ");
        System.out.println();
    }
}
