package com.problems.dp;

public class KnapSack01 {
    public static void main(String[] args) {
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int capacity = 12;

        KnapSack01 sack = new KnapSack01();
        System.out.println(sack.solveKnapSack(weights, profits, capacity));
        System.out.println(sack.solveKnapSackDP(weights, profits, capacity));
    }

    public int solveKnapSack(int[] weights, int[] profits, int capacity) {
        // currIndex is required for picking up a value from the weights and profits array
        return getMaxProfit(weights, profits, capacity, 0);
    }

    // We need to calculate the max profit by adding weights into the knapsack so that max profit is achieved
    // This can be achieved by calculating the profit with all the combinations
    public int getMaxProfit(int[] weights, int[] profits, int capacity, int currIndex) {
        if(capacity <= 0 || currIndex == profits.length) {
            return 0;
        }
        int profit1 = 0;
        // If the weight is more than the capacity, then we don't want to consider that weight
        if (weights[currIndex] <= capacity) {
            // Here we are considering all the combinations considering the weight at currentIndex
            profit1 = profits[currIndex] + getMaxProfit(weights, profits, capacity - weights[currIndex], currIndex + 1);
        }
        // Here we are considering all the combinations without considering the weight at currentIndex
        int profit2 = getMaxProfit(weights, profits, capacity, currIndex + 1);
        return Math.max(profit1, profit2);
    }

    public int solveKnapSackDP(int[] weights, int[] profits, int capacity) {
        // currIndex is required for picking up a value from the weights and profits array
        // If you see weights and profits are constant. Only capacity and index are changing. So we need to
        // create a two dimensional array of size [profits.length][capacity + 1]. For example, capacity here is 7.
        // To store the value of 7, the size should be 8. So we are initializing to capacity + 1.
        // Consider array of Integer type instead on int, as some times we may have value 0 in a location.
        // So, the best way to start computing a solution if the value in the array is null.
        capacity = capacity < 0 ? 0 : capacity;
        Integer[][] dp = new Integer[profits.length][capacity + 1];
        return getMaxProfitDP(weights, profits, capacity, 0, dp);
    }

    // We need to calculate the max profit by adding weights into the knapsack so that max profit is achieved
    // This can be achieved by calculating the profit with all the combinations
    public int getMaxProfitDP(int[] weights, int[] profits, int capacity, int currIndex, Integer[][] dp) {
        // We are storing the already computed values in the Integer array dp. For example, if the value for
        // index 1 and capacity is already computed, it is stored at [1][4] location. We don't need to re compute
        // the solution. We retrieve it from the array. Otherwise, we compute and store in the array.
        if(dp[currIndex][capacity] != null) {
            return dp[currIndex][capacity];
        }

        if(capacity <= 0 || currIndex == profits.length) {
            return 0;
        }
        int profit1 = 0;
        // If the weight is more than the capacity, then we don't want to consider that weight
        if (weights[currIndex] <= capacity) {
            // Here we are considering all the combinations considering the weight at currentIndex
            profit1 = profits[currIndex] + getMaxProfit(weights, profits, capacity - weights[currIndex], currIndex + 1);
        }
        // Here we are considering all the combinations without considering the weight at currentIndex
        int profit2 = getMaxProfit(weights, profits, capacity, currIndex + 1);
        dp[currIndex][capacity] = Math.max(profit1, profit2);
        return dp[currIndex][capacity];
    }
}
