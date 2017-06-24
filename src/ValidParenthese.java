import java.util.Stack;

public class ValidParenthese {

    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("([)]"));
    }

    public static boolean isValid(String input) {
        if (input == null) return false;
        if (input.length() <= 1) return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(' || input.charAt(i) == '[' || input.charAt(i) == '{')
                stack.push(input.charAt(i));
            else if (input.charAt(i) == ')' && !stack.empty() && stack.peek() == '(')
                stack.pop();
            else if (input.charAt(i) == ']' && !stack.empty() && stack.peek() == '[')
                stack.pop();
            else if (input.charAt(i) == '}' && !stack.empty() && stack.peek() == '{')
                stack.pop();
            else
                return false;
        }
        return stack.empty();
    }

}
