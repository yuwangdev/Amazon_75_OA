import java.util.Stack;

public class BaseballScoring {

    public static int solve(String str) {
        if (str == null) return 0;
        if (str.length() == 0) return 0;
        if (str.length() == 1 && !Character.isDigit(str.charAt(0))) return 0;

        Stack<Integer> stack = new Stack<>();
        int i = 0;
        while (i < str.length()) {
            char current = str.charAt(i);
            if (current == '-') {
                stack.push(-1 * Integer.parseInt((Character.toString(str.charAt(i + 1)))));
                i += 2;
            } else {
                if (Character.isDigit(current)) stack.push(Integer.parseInt((Character.toString(str.charAt(i)))));
                if (current == 'Z' || current == 'z') stack.pop();
                if (current == 'X' || current == 'x') {
                    if (stack.peek() == null) {
                        stack.push(0);
                    } else {
                        stack.push(2 * stack.peek());
                    }
                }
                if (current == '+') {
                    int t1 = stack.peek() != null ? stack.pop() : 0;
                    int t2 = stack.peek() != null ? stack.pop() : 0;
                    stack.push(t2);
                    stack.push(t1);
                    stack.push(t1 + t2);
                }
                i++;
            }
        }

        int sum = 0;
        while (!stack.isEmpty()) sum += stack.pop();
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(solve("5-24zx9++") + "==27");
        System.out.println(solve("524zx9++") + "==55");

    }


}
