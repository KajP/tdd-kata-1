public class Calculator {
    public static int add(String input) {
        int index = input.indexOf(",");
        int sum = 0;
        if (input.length() == 0)
            return sum;
        if (index >= 0) {
            int first = Integer.parseInt(input.substring(0, index)),
                    second = Integer.parseInt(input.substring(index+1));
            return first+second;
        }
        return Integer.parseInt(input);
    }
}
