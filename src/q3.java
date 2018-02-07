public class q3 {
    public static int static_int = 0;
    public static volatile int volatile_int = 0;

    public static void volatile_thread(){
        synchronized (q3.class) {
            //increment variable c
            volatile_int++;
        }
    }
    public static void static_thread(){
        synchronized (q3.class) {
            //increment variable c
            static_int++;
        }
    }

    public static void main(String [] args) {
        long time_start;
        long time_finish;
        long time_avg;

        // part a)
        static_int = 0;
        time_avg = 0;
        for (int i = 0; i < 8; i++) {
            time_start = System.currentTimeMillis();
            for (int j = 0; j < Integer.MAX_VALUE/3; j++) {
                //increment variable a
                static_int++;
                //
                //static_int = static_int +2;
                //static_int = static_int -1;
            }
            time_finish = System.currentTimeMillis();
            /*
            if(i < 1){
                System.out.println(time_finish- time_start);
            }
            */
            //
            if (i > 0) {
                // discarding the time for the first run
                time_avg += (time_finish - time_start);
                //System.out.println(i + ": " + time_avg);
            }
            if (i > 6){
                System.out.println(i + ": " + time_avg);

                time_avg = time_avg / 7;
            }
            static_int = 0;
        }
        System.out.println("Performance of part a: " + time_avg);

        // part b)
        volatile_int = 0;
        time_avg = 0;
        for (int i = 0; i < 8; i++) {
            time_start = System.currentTimeMillis();
            for (int j = 0; j < Integer.MAX_VALUE/3; j++) {
                //increment variable b
                volatile_int++;
            }
            time_finish = System.currentTimeMillis();
            if (i > 0) {
                // discarding the time for the first run
                time_avg += (time_finish - time_start);
                // remove later
                //System.out.println(i + ": " + time_avg);

            }
            if (i > 6){
                time_avg = time_avg / 7;
            }
            volatile_int = 0;
        }
        System.out.println("Performance of part b: " + time_avg);


        //part c)
        static_int = 0;
        time_avg = 0;
        for (int i = 0; i < 8; i++) {
            time_start = System.currentTimeMillis();
            for (int j = 0; j < Integer.MAX_VALUE/3; j++) {
                // synchronized block
                synchronized (q3.class) {
                    //increment variable c
                    static_int++;
                }
            }
            time_finish = System.currentTimeMillis();
            if (i > 0) {
                // discarding the time for the first run
                time_avg += (time_finish - time_start);
                //System.out.println(i + ": " + time_avg);
            }
            // after summing the 7 runs
            if (i > 6){
                time_avg = time_avg / 7;
            }
            static_int = 0;
        }
        System.out.println("Performance of part c: " + time_avg);

        //part d)
        volatile_int = 0;
        time_avg = 0;
        Runnable increment_vInt = new Runnable() {
            @Override
            public void run() {
                while (volatile_int < Integer.MAX_VALUE/3){
                    // with x = 2 , performance - 63s
                    // method defined above to increment volatile int with synchronized block
                    volatile_thread();
                }
            }
        };

        for (int i = 0; i < 8; i++) {
            time_start = System.currentTimeMillis();
            Thread t1 = new Thread(increment_vInt);
            Thread t2 = new Thread(increment_vInt);
            t1.start();
            t2.start();
            try {
                t1.join();
                t2.join();
                time_finish = System.currentTimeMillis();
                if (i > 0) {
                    // discarding the time for the first run
                    time_avg += (time_finish - time_start);
                    // remove later
                    //System.out.println(i + ": " + time_avg);
                }
            }
            catch(InterruptedException e){

            }
            if (i > 6){
                time_avg = time_avg / 7;
            }
            volatile_int = 0;
        }
        System.out.println("Performance of part d: " + time_avg);

        //e
        static_int = 0;
        time_avg = 0;
        Runnable increment_sInt = new Runnable() {
            @Override
            public void run() {
                while (static_int < Integer.MAX_VALUE/3){
                    // method defined above to increment volatile int with synchronized block
                    static_thread();
                }
            }
        };

        for (int i = 0; i < 8; i++) {
            time_start = System.currentTimeMillis();
            Thread t1 = new Thread(increment_sInt);
            Thread t2 = new Thread(increment_sInt);
            t1.start();
            t2.start();
            try {
                t1.join();
                t2.join();
                time_finish = System.currentTimeMillis();
                if (i > 0) {
                    // discarding the time for the first run
                    time_avg += (time_finish - time_start);
                    // remove later
                    //System.out.println(i + ": " + time_avg);
                }
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            if (i > 6){
                time_avg = time_avg / 7;
            }
            static_int = 0;
        }
        System.out.println("Performance of part e: " + time_avg);

    }
}
