import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Collectors;

class Calculator {
    static int add(String input) {
        int sum = 0;
        if (input.length() == 0)
            return sum;

        char[] delims = {',', '\n'};
        if (input.startsWith("//")) {
            // custom delim
            int index = input.indexOf("\n");
            delims = new char[] {input.charAt(2)};
            input = input.substring(index + 1);
        }
        int prevIndex = 0;
        Queue<Integer> negatives = new ArrayDeque<>();
        for (int index = indexOfAnyDelim(input, delims); index != -1;
             index = indexOfAnyDelim(input, delims, index+1)) {
            int value = Integer.parseInt(input.substring(prevIndex, index));
            sum+=value;
            if (value < 0) {
                negatives.add(value);
            }
            prevIndex = index+1;
        }
        int value = Integer.parseInt(input.substring(prevIndex));
        sum += value;
        if (value < 0) {
            negatives.add(value);
        }
        if (!negatives.isEmpty()) {
            String message = negatives.stream().map(integer -> Integer.toString(integer))
                    .collect(Collectors.joining(", "));
            throw new IllegalArgumentException(message);
        }
        return sum;
    }

    private static int indexOfAnyDelim(String input, char[] delims) {
        return indexOfAnyDelim(input, delims, 0);
    }

    private static int indexOfAnyDelim(String input, char[] delims, int begin) {
        char[] arr = input.toCharArray();
        for (int i = begin; i < arr.length; i++) {
            for (char delim : delims) {
                if (arr[i] == delim)
                    return i;
            }
        }
        return -1;
    }
}
