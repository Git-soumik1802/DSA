class Solution {
public:
    int minAddToMakeValid(string s) {
        int open_count = 0;
        int close_count = 0;

        for (char c : s) {
            if (c == '(') {
                open_count++;  // Increment for every unmatched '('
            } else if (c == ')') {
                if (open_count > 0) {
                    open_count--;  // Match it with a previous '('
                } else {
                    close_count++;  // No unmatched '(', so this ')' is unmatched
                }
            }
        }
        return open_count + close_count;
    }
};
