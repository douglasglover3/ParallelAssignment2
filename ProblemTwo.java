
class GuestThread extends Thread {
    Showroom showroom;

    synchronized public void run()
    {
        while (true)
        {
            System.out.println("Guest "+ this.threadId() + " approaches the showroom.");
            if(!showroom.full) //if the showroom sign says available
            {
                showroom.full = true;
                System.out.println("Guest "+ this.threadId() + " enters the showroom.");
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Guest "+ this.threadId() + " has exited the showroom.");
                showroom.full = false;
                break;
            }
            else //if the showroom sign says full
            {
                System.out.println("The showroom sign says busy.");
                try {
                    sleep(50); //Guest does something else
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
class Showroom {
    public boolean full = false;
}

public class ProblemTwo {
    
    public static void main(String[] args)
    {
        //Number of guests
        int n = 100;
        GuestThread guests[] = new GuestThread[n];
        Showroom showroom = new Showroom();

        for (int i = 0; i < n; i++) { 
            guests[i] = new GuestThread();
            guests[i].showroom = showroom;
            guests[i].start();
        }

        //wait for each guest to finish
        for (int i = 0; i < n; i++) { 
            try {
                guests[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("All guests visited the showroom.");
    }
}
