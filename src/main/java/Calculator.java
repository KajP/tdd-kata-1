class Calculator {
    static int add(String input) {
        int sum = 0;
        if (input.length() == 0)
            return sum;
        int prevIndex = 0;
        for (int index = input.indexOf(","); index != -1; index = input.indexOf(",", index+1)) {
            int first = Integer.parseInt(input.substring(prevIndex, index));
            prevIndex = index+1;
            sum+=first;
        }
        sum += Integer.parseInt(input.substring(prevIndex));
        return sum;
    }
}
