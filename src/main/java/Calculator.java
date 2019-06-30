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
        for (int index = indexOfAnyDelim(input, delims); index != -1;
             index = indexOfAnyDelim(input, delims, index+1)) {
            int first = Integer.parseInt(input.substring(prevIndex, index));
            prevIndex = index+1;
            sum+=first;
        }
        sum += Integer.parseInt(input.substring(prevIndex));
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
