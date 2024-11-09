import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPostfixAndCalculate {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String expression = reader.readLine().trim();
        if (expression.matches("[0-9]{1,100}+")) {
            writer.write(expression);
            writer.close();
            reader.close();
            return;
        }
        if (!validExp(expression)) {
            writer.write("WRONG");
            writer.close();
            reader.close();
            return;
        }
        List<String> tokens = getTokens(expression);
        String[] data = convertToPostfix(tokens).split("\\s+");
        Stack<Integer> numbers = new Stack<>();
        for (int i = 0; i < data.length; i++) {
            String s = data[i].trim();
            if (s.matches("-?[0-9]+")) {
                numbers.push(Integer.parseInt(s));
            } else if (s.equals("+")) {
                numbers.push(numbers.pop() + numbers.pop());
            } else if (s.equals("-")) {
                int x = numbers.pop();
                int y = numbers.pop();
                numbers.push(y - x);
            } else if (s.equals("*")) {
                numbers.push(numbers.pop() * numbers.pop());
            }
        }
        if (numbers.size() != 1) {
            writer.write("WRONG");
        } else {
            writer.write(Integer.toString(numbers.pop()));
        }
        writer.close();
        reader.close();
    }

    public static int precedence(char ch) {
        if (ch == '+' || ch == '-')
            return 1;
        else if (ch == '*' || ch == '/')
            return 2;
        return 0;
    }

    public static List<String> getTokens(String exp) {
        List<String> tokens = new ArrayList<>();
        String token = "";
        for (int i = 0; i < exp.length(); i++) {
            if (Character.isDigit(exp.charAt(i))) {
                token += exp.charAt(i);
            }
            if (Character.isSpaceChar(exp.charAt(i)) || exp.charAt(i) == '+' || exp.charAt(i) == '-' ||
                    exp.charAt(i) == '*' || exp.charAt(i) == '/' ||
                    exp.charAt(i) == '(' || exp.charAt(i) == ')') {
                if (!token.isBlank()) {
                    tokens.add(token);
                    token = "";
                }
                if (!Character.isSpaceChar(exp.charAt(i))) {
                    if (!tokens.isEmpty() && tokens.get(tokens.size() - 1).length() == 1 &&
                            precedence(tokens.get(tokens.size() - 1).charAt(0)) > 0 &&
                            exp.charAt(i) == '-') {
                        token+= exp.charAt(i);
                    } else {
                        tokens.add(exp.charAt(i) + "");
                    }
                }
            }
            if (!token.isBlank() && i == exp.length() - 1) {
                tokens.add(token);
            }
        }
        return tokens;
    }

    public static boolean validExp(String exp) {
        int count = 0;
        if (precedence(exp.charAt(0)) > 0 || precedence(exp.charAt(exp.length() - 1)) > 0) {
            return false;
        }
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if (Character.isLetter(ch)) {
                return false;
            }
            if (ch == '(') {
                if (i < exp.length() - 1 && precedence(exp.charAt(i + 1)) > 0) {
                    return false;
                }
                count++;
            }
            if (ch == ')') {
                if (i > 0 && i < exp.length() - 1 && precedence(exp.charAt(i - 1)) > 0) {
                    return false;
                }
                count--;
            }
        }
        if (count != 0) {
            return false;
        }
        return true;
    }


    public static String convertToPostfix(List<String> exp) {
        Stack<String> operators = new Stack<>();
        Stack<String> postFix = new Stack<>();
        for (int i = 0; i < exp.size(); i++) {
            String token = exp.get(i);
            if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.peek().equals("(")) {
                    String op = operators.pop();
                    String first = postFix.pop();
                    String second = postFix.pop();
                    String new_postFix = second + "\s" + first + "\s" + op + "\s";
                    postFix.push(new_postFix);
                }
                operators.pop();
            } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.endsWith("/")) {
                while (operators.size() > 0 && !operators.peek().equals("(") && precedence(token.charAt(0))
                        <= precedence(operators.peek().charAt(0))) {
                    String op = operators.pop();
                    String first = postFix.pop();
                    String second = postFix.pop();
                    String new_postFix = second + "\s" + first + "\s" + op + "\s";
                    postFix.push(new_postFix);
                }
                operators.push(token);
            } else {
                postFix.push(token);
            }
        }
        while (operators.size() > 0) {
            String op = operators.pop();
            String first = postFix.pop();
            String second = "";
            if (!postFix.isEmpty()) {
                second = postFix.pop();
            }
            String new_postFix = second + "\s" + first + "\s" + op + "\s";
            postFix.push(new_postFix);
        }
        if (postFix.size() > 1) {
            return "WRONG";
        }
        return postFix.pop().trim();
    }

}
