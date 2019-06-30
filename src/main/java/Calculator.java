import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Collectors;

class Calculator {
    static int add(String input) {
        int sum = 0;
        if (input.length() == 0)
            return sum;

        String[] delims = {",", "\n"};
        if (input.startsWith("//")) {
            // custom delim
            Queue<String> li = new ArrayDeque<>();
            int newlineIndex = input.indexOf("\n");
            boolean bracketNotFound = true;
            for (int i = input.indexOf("["), j = input.indexOf("]", i);
                 i < newlineIndex && i != -1; i = input.indexOf("[", j), j = input.indexOf("]", i)) {
                bracketNotFound = false;
                String delim = input.substring(i + 1, j);
                li.add(delim);
            }
            if (bracketNotFound) {
                li.add(input.substring(2, 3));
            }
            delims = li.toArray(new String[0]);
            input = input.substring(newlineIndex + 1);
        }

        int prevIndex = 0;
        Queue<Integer> negatives = new ArrayDeque<>();
        for (DelimResult index = indexOfAnyDelim(input, delims); index.getBeginIndex() != -1;
             index = indexOfAnyDelim(input, delims, index.getEndIndex() + 1)) {
            int value = Integer.parseInt(input.substring(prevIndex, index.getBeginIndex()));
            if (value <= 1000) {
                sum += value;
            }
            if (value < 0) {
                negatives.add(value);
            }
            prevIndex = index.getEndIndex();
        }

        int value = Integer.parseInt(input.substring(prevIndex));
        if (value <= 1000) {
            sum += value;
        }
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

    private static DelimResult indexOfAnyDelim(String input, String[] delims) {
        return indexOfAnyDelim(input, delims, 0);
    }

    private static DelimResult indexOfAnyDelim(String input, String[] delims, int begin) {
        int result = -1, end = -1;
        for (String delim : delims) {
            int i = input.indexOf(delim, begin);
            if (i != -1) {
                result = i;
                end = i + delim.length();
            }
        }
        return new DelimResult(result, end);
    }

    static class DelimResult {
        private int beginIndex;
        private int endIndex;

        DelimResult(int beginIndex, int endIndex) {
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
        }

        int getBeginIndex() {
            return beginIndex;
        }

        int getEndIndex() {
            return endIndex;
        }

        @Override
        public String toString() {
            return String.format("begin: %d, end: %d", beginIndex, endIndex);
        }
    }
}
