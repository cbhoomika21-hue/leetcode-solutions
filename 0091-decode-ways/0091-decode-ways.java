class Solution {
        public int numDecodings(String s) {
                if (s == null || s.length() == 0 || s.charAt(0) == '0') {
                            return 0;
                                    }

                                            int n = s.length();
                                                    int[] dp = new int[n + 1];
                                                            
                                                                    // Base case: empty string has 1 way to be decoded
                                                                            dp[0] = 1;
                                                                                    // First character
                                                                                            dp[1] = 1;

                                                                                                    for (int i = 2; i <= n; i++) {
                                                                                                                int oneDigit = Integer.parseInt(s.substring(i - 1, i));
                                                                                                                            int twoDigits = Integer.parseInt(s.substring(i - 2, i));

                                                                                                                                        // Check single digit
                                                                                                                                                    if (oneDigit >= 1) {
                                                                                                                                                                    dp[i] += dp[i - 1];
                                                                                                                                                                                }

                                                                                                                                                                                            // Check two digits
                                                                                                                                                                                                        if (twoDigits >= 10 && twoDigits <= 26) {
                                                                                                                                                                                                                        dp[i] += dp[i - 2];
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                            }

                                                                                                                                                                                                                                                    return dp[n];
                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                        }
