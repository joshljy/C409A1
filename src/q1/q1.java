package q1;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class q1 {

    // The image constructed
    public static BufferedImage img;

    // Image dimensions; you can modify these for bigger/smaller images
    public static int width = 1920;
    public static int height = 1080;

    public static int r;
    public static int c;
    public static boolean multithreaded;
    public static AtomicInteger turn = new AtomicInteger();


    public static synchronized boolean thread_draw(AtomicInteger remaining) {
        if(remaining.getAndDecrement() >= 1) {
            return true;
        }
        return false;
    }
    public static void set_turn(int n){
        synchronized (q1.class) {
            turn.set(n);
        }
    }

    public static void main(String[] args) {
        try {
            if (args.length<3)
                throw new Exception("Missing arguments, only "+args.length+" were specified!");
            // arg 0 is the max radius
            r = Integer.parseInt(args[0]);
            // arg 1 is count
            c = Integer.parseInt(args[1]);
            // arg 2 is a boolean
            multithreaded = Boolean.parseBoolean(args[2]);

            // create an image and initialize it to all 0's
            img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
            for (int i=0;i<width;i++) {
                for (int j=0;j<height;j++) {
                    img.setRGB(i,j,0);
                }
            }
            //System.out.println(multithreaded);

            // YOU NEED TO ADD CODE HERE AT LEAST!
            AtomicBoolean[] flag = new AtomicBoolean[2];
            flag[0] = new AtomicBoolean(false);
            //System.out.println(flag[0]);
            flag[1] = new AtomicBoolean(false);

            AtomicInteger remaining_circles = new AtomicInteger();
            // sets remaining_circles to arg 1
            remaining_circles.set(c);
            turn.set(1);

            //multithreaded case
            if(multithreaded){
                Thread t1 = new Thread(){
                    public void run(){
                        try {
                            while (thread_draw(remaining_circles)) {
                                flag[0].set(true);
                                int temp = turn.get();
                                AtomicBoolean temp2 = new AtomicBoolean();
                                temp2.set(true);
                                while ((flag[1] == temp2) && (temp == 2)) {
                                    Thread.yield();
                                }
                                System.out.println("Thread 1");
                                System.out.println("1 : " + remaining_circles);
                                set_turn(2);
                                flag[0].set(false);

                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                Thread t2 = new Thread(){
                    public void run(){
                        try {
                            while (thread_draw(remaining_circles)) {
                                flag[1].set(true);

                                int temp4 = turn.get();
                                AtomicBoolean temp3 = new AtomicBoolean();
                                temp3.set(true);
                                while((flag[0] == temp3) && (temp4 == 1)) {
                                    Thread.yield();
                                }

                                System.out.println("Thread 2");
                                System.out.println("2 : " + remaining_circles);
                                set_turn(1);
                                flag[1].set(false);

                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                long time_start = System.currentTimeMillis();
                t1.start();
                t2.start();


                t1.join();
                t2.join();
                long time_finish = System.currentTimeMillis();

                long time_execution = time_finish - time_start;
            }
            else{
                //single threaded case
                long time_start = System.currentTimeMillis();

            }



            // Write out the image
            File outputfile = new File("outputimage.png");
            ImageIO.write(img, "png", outputfile);

        } catch (Exception e) {
            System.out.println("ERROR " +e);
            e.printStackTrace();
        }
    }
}
