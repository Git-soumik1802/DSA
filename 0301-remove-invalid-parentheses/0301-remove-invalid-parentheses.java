import java.util.*;

class Solution {

    public List<String> removeInvalidParentheses(String s) {

        int left = 0;
        int right = 0;

        // Find minimum number of '(' and ')' to remove
        for (char c : s.toCharArray()) {

            if (c == '(') {
                left++;
            } 
            else if (c == ')') {

                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }

        Set<String> result = new HashSet<>();

        backtrack(s, 0, 0, 0, left, right, new StringBuilder(), result);

        return new ArrayList<>(result);
    }

    private void backtrack(
            String s,
            int index,
            int open,
            int close,
            int removeLeft,
            int removeRight,
            StringBuilder current,
            Set<String> result) {

        if (index == s.length()) {

            if (removeLeft == 0 &&
                removeRight == 0 &&
                open == close) {

                result.add(current.toString());
            }

            return;
        }

        char c = s.charAt(index);

        // Remove current character
        if (c == '(' && removeLeft > 0) {

            backtrack(
                s, index + 1,
                open, close,
                removeLeft - 1,
                removeRight,
                current,
                result
            );
        }

        if (c == ')' && removeRight > 0) {

            backtrack(
                s, index + 1,
                open, close,
                removeLeft,
                removeRight - 1,
                current,
                result
            );
        }

        // Keep current character
        current.append(c);

        if (c != '(' && c != ')') {

            backtrack(
                s, index + 1,
                open, close,
                removeLeft, removeRight,
                current, result
            );

        } else if (c == '(') {

            backtrack(
                s, index + 1,
                open + 1, close,
                removeLeft, removeRight,
                current, result
            );

        } else if (close < open) {

            backtrack(
                s, index + 1,
                open, close + 1,
                removeLeft, removeRight,
                current, result
            );
        }

        current.deleteCharAt(current.length() - 1);
    }
}