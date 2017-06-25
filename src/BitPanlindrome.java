/**
 * Created by Yu Wang on 2017-06-24.
 */
public class BitPanlindrome {

    public static boolean solve(int num) {
        System.out.println(Integer.toBinaryString(num));
        if (num > Integer.MAX_VALUE || num < Integer.MIN_VALUE) return false;
        if (num >= 0 && num <= 1) return true;


        int numOfBits = (int) (Math.floor((Math.log(num) / Math.log(2))) + 1);
        int left = 0;
        int right = numOfBits - 1;
        while (left < right) {
            if (isBitSet(num, numOfBits - left) != isBitSet(num, numOfBits - right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private static boolean isBitSet(int num, int index) {
        return (num & (1 << (index - 1))) != 0;
    }

    public static void main(String[] args) {
        System.out.println(solve(2));
        System.out.println(solve(5));
        System.out.println(solve(6));
        System.out.println(solve(93));
        System.out.println(solve(1));

    }
}
