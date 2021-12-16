package Taxi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import static Taxi.Dispatcher.pq;

class MultiThread implements Runnable
{
    private String name;//name of cutomer
    private String destination;//destination  point for dropoff
    private String pickUpPoint;//pickup Point for limo
    private int turns=1;
    @Override
    public void run() 
    {  
        try 
        {
            if(turns == 1)
                System.out.println(Thread.currentThread().getName()+" is on Duty");
            Thread.sleep((long)(Math.random() * 1000));
            name = pq.poll();
            destination = pq.poll();
            pickUpPoint = pq.poll();
            System.out.println(name + " has been picked up by " + Thread.currentThread().getName() + " proceeding from " + pickUpPoint + " to " + destination+"(The customer count is "+turns+")");
            turns = turns+1;
            Thread.sleep((long)(Math.random() * 10000));
            System.out.println(name + " was dropped off at "+ destination+ " by " + Thread.currentThread().getName());
            if(turns<=4 && pq.peek()!=null)
                run();
            else
                System.out.println(Thread.currentThread().getName()+" is Off Duty. "+(turns-1)+" customers were picked today.");

        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(MultiThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
public class Dispatcher 
{
    public static Queue<String> pq = new LinkedBlockingQueue<>();
    public static void reader() throws IOException 
    {
        try 
        {
            FileInputStream fis = new FileInputStream("C:/Users/PyroByte/Desktop/New_Order/CustomerList.txt");
            try ( Scanner sc = new Scanner(fis)) 
            {
                while (sc.hasNextLine())
                {
                    String str = sc.nextLine();
                    if (!"no".equals(str)) 
                    {
                        if (!"yes".equals(str)) 
                        {
                            pq.add(str);
                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) 
        {
            Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
