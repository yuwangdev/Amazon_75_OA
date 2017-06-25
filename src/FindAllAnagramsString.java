import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsString {

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacdbcas", "abc"));
    }

    public static List<Integer> findAnagrams(String big, String small) {
        List<Integer> result = new ArrayList<>();
        if (big == null || small == null) return result;
        if (big.length() < small.length()) return result;
        int[] bucket = new int[256];
        for (char c : small.toCharArray()) {
            bucket[c]++;
        }
        int current = 0;
        int runner = 0;
        int count = small.length();
        while (runner < big.length()) {
            if (bucket[big.charAt(runner++)]-- >= 1) count--;
            if (count == 0) result.add(current);
            if (runner - current == small.length() && bucket[big.charAt(current++)]++ >= 0) count++;
        }
        return result;
    }


}
