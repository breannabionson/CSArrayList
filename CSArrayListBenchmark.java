public class CSArrayListBenchmark {
    public static void main(String[] args) {
        int[] sizes = {100_000, 200_000, 500_000, 1_000_000};
        System.out.printf("%-12s %-20s %-20s %-20s %-20s\n",
                "N",
                "CSArrayList - append",
                "ArrayList - append",
                "CSArrayList - get",
                "ArrayList = get");

        for (int N : sizes) {
            long csAppend = testAppendCS(N);
            long jAppend = testAppendJava(N);
            long csGet = testRandomGetCs(N);
            long jGet = testRandomGetJava(N);

            System.out.printf("\"%-12d %-20d %-20d %-20d %-20d\n" +
                    N, csAppend, jAppend, csGet, jGet);
        }
    }

    private static long testAppendCS(int N) {
        CSArrayList<Integer> list = new CSArrayList<>();
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) list.add(i);
        return (System.nanoTime() - start / 1_000_000);
        }
        private static long testAppendJava(int N) {
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) list.add(i);
        return (System.nanoTime() - start / 1_000_000);
    }
    private static long testRandomGetCs(int N) {
        CSArrayList<Integer> list = new CSArrayList<>();
        for (int i = 0; i < N; i++) list.add(i);

        long start = System.nanoTime();
        for (int i = 0; i < 100_000; i++) {
            int idx = (int) (Math.random() * N);
            list.get(idx);
        }
        return (System.nanoTime() - start / 1_000_000);
    }
    private static long testRandomGetJava(int N) {
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
        for (int i = 0; i < N; i++) list.add(i);
        long start = System.nanoTime();
        for (int i = 0; i < 100_000; i++) {
            int idx = (int) (Math.random() * N);
            list.get(idx);
        }
        return (System.nanoTime() - start / 1_000_000);
    }
}
