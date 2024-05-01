package unit3;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * The Clock class represents and functions as a clock
 *
 * @author Deni Wisdom Ochiche
 */
public class Clock {

    private Date date;
    {
        date = new Date();
    }

    /**
     * Updates the date to the current date.
     */
    public synchronized void update(){
        date = new Date();
    }

    /**
     * Prints the current date in readable format.
     */
    public synchronized void print(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        System.out.println(sdf.format(date));
    }

    /**
     * Entry point to the program
     * @param args String[] cmd-line args
     */
    public static void main(String[] args) {
        System.out.println("""
                  ____ _     ___   ____ _  __
                 / ___| |   / _ \\ / ___| |/ /
                | |   | |  | | | | |   | ' /\s
                | |___| |__| |_| | |___| . \\\s
                 \\____|_____\\___/ \\____|_|\\_\\
                """);

        Clock clock = new Clock();

        Thread t1 = new Thread(() -> {
            while (true){
                try{
                    TimeUnit.MILLISECONDS.sleep(1000);
                    clock.update();
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true){
                try{
                    TimeUnit.MILLISECONDS.sleep(1000);
                    clock.print();
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });

        t1.setPriority(5);
        t2.setPriority(4);
        t1.start();
        t2.start();
    }
}
