package Taxi;

import java.io.IOException;

public class Driver
{
    public static void main(String[] args) throws IOException, InterruptedException 
    {
        Dispatcher.reader();
        Thread driver1 = new Thread(new MultiThread());
        Thread driver2 = new Thread(new MultiThread());
        Thread driver3 = new Thread(new MultiThread());
        Thread driver4 = new Thread(new MultiThread());
        Thread driver5 = new Thread(new MultiThread());
        driver1.setName("Driver #1");
        driver2.setName("Driver #2");
        driver3.setName("Driver #3");
        driver4.setName("Driver #4");
        driver5.setName("Driver #5");
        driver1.start();
        driver2.start();
        driver3.start();
        driver4.start();
        driver5.start();
    }
}
