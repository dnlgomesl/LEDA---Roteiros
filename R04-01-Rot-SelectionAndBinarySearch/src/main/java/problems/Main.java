package problems;

import orderStatistic.OrderStatisticsSelectionImpl;

public class Main {
   public static void main(String[] args) {
      OrderStatisticsSelectionImpl<Integer> o = new OrderStatisticsSelectionImpl<>();
      Integer[] array = { 1, 2, 4, 5, 6, 7, 9, 10, 23 };
      System.out.println(o.getOrderStatistics(array, 3));
   }
}
