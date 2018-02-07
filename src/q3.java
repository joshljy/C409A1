public class q3 {
    public static int static_int = 0;
    public static volatile int volatile_int = 0;
   /*
    public static int var_a = 0;
    public static volatile int var_b = 0;
    public static int var_c = 0;
    public static volatile int var_d = 0;
    public static int var_e = 0;*/

    public static void main(String [] args) {
        long time_start;
        long time_finish;
        long time_avg;
        // a)
        static_int = 0;
        time_avg = 0;
        for (int i = 0; i < 8; i++) {
            time_start = System.currentTimeMillis();
            for (int j = 0; j < Integer.MAX_VALUE/2; j++) {
                //increment variable a
                static_int++;
            }
            time_finish = System.currentTimeMillis();
            if (i > 0) {
                // discarding the time for the first run
                time_avg += (time_finish - time_start);

                System.out.println(i + ": " + time_avg);

            }
            if (i > 6){
                time_avg = time_avg / 7;
            }
            static_int = 0;
        }
        System.out.println("Performance of part a: " + time_avg);

        //b)
        volatile_int = 0;
        time_avg = 0;
        for (int i = 0; i < 8; i++) {
            time_start = System.currentTimeMillis();
            for (int j = 0; j < Integer.MAX_VALUE/2; j++) {
                //increment variable b
                volatile_int++;
            }
            time_finish = System.currentTimeMillis();
            if (i > 0) {
                // discarding the time for the first run
                time_avg += (time_finish - time_start);

                // remove later
                System.out.println(i + ": " + time_avg);

            }
            if (i > 6){
                time_avg = time_avg / 7;
            }
            volatile_int = 0;
        }
        System.out.println("Performance of part b: " + time_avg);


        //c)
        static_int = 0;
        time_avg = 0;

    }
}
