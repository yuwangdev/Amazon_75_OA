import java.util.*;

/**
 * Created by Yu Wang on 2017-06-24.
 */
public class MenuJoin {

    public static List<String[]> solve(List<String[]> list1, List<String[]> list2) {

        Map<String, Set<String>> list2Map = new HashMap<>();
        Set<String> allDistinctList2Value = new HashSet<>();
        for (String[] record : list2) {
            allDistinctList2Value.add(record[1]);
            if (!list2Map.containsKey(record[0])) {
                Set<String> temp = new HashSet<>();
                temp.add(record[1]);
                list2Map.put(record[0], temp);
            } else {
                Set<String> temp = list2Map.get(record[0]);
                temp.add(record[1]);
                list2Map.put(record[0], temp);
            }
        }

        List<String[]> result = new ArrayList<>();
        for (String[] record : list1) {

            String keyList1 = record[0];
            String valueList1 = record[1];

            if (list2Map.containsKey(valueList1)) {
                Set<String> valueList2Set = list2Map.get(valueList1);
                for (String str : valueList2Set) {
                    String[] temp = {keyList1, str};
                    result.add(temp);
                }
            } else {
                if (valueList1.equalsIgnoreCase("*")) {
                    for (String str : allDistinctList2Value) {
                        String[] temp = {keyList1, str};
                        result.add(temp);
                    }
                }
            }
        }

        return result;
    }


    public static void main(String[] argus) {
        List<String[]> list1 = new ArrayList<>();
        list1.add(new String[]{"zhangsan", "China"});
        list1.add(new String[]{"lisi", "america"});
        list1.add(new String[]{"wangwu", "japan"});
        list1.add(new String[]{"xiaoming", "*"});

        List<String[]> list2 = new ArrayList<>();
        list2.add(new String[]{"China", "yuxiaorousi"});
        list2.add(new String[]{"China", "shuizhuu"});
        list2.add(new String[]{"america", "burger"});

        List<String[]> result = solve(list1, list2);
        for (String[] row : result) {
            printArray(row);
        }

    }


    private static void printArray(String[] s) {
        for (String x : s) System.out.print(x + " ");
        System.out.println();
    }


}
