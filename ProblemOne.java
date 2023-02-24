
class GuestThread extends Thread {
    boolean insideLabyrinth = false;
    boolean party = true;
    boolean hungry = true;
    Labyrinth labyrinth;
    synchronized public void run()
    {
        System.out.println("Guest " + this.threadId() + " arrived to party.");
        while(party)
        {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(insideLabyrinth) {
                System.out.println("Guest " + this.threadId() + " entered labyrinth.");
                if(hungry) //guest has not eaten cupcake
                {
                    if(labyrinth.cupcake.eaten) // cupcake is absent
                    {
                        labyrinth.cupcake.request();
                        labyrinth.cupcake.eaten = false;
                        System.out.println("Cupcake requested.");
                    }
                    if(!labyrinth.cupcake.eaten) //cupcake is present
                    {
                        labyrinth.cupcake.eaten = true;
                        hungry = false;
                    }
                }
                System.out.println("Guest " + this.threadId() + " exited labyrinth.");
                insideLabyrinth = false;
                labyrinth.exit();
            }
        }
    }
}
class Cupcake {
    public boolean eaten = false;
    public int requests = 0;
    public void request(){
        requests++;
    }
}
class Labyrinth {
    public boolean full = false;
    public GuestThread guests[];
    Cupcake cupcake = new Cupcake();
    public int n;
    public void enter(){
        int randomGuest = (int) Math.floor(Math.random() * n);
        System.out.println("Random guest is " + guests[randomGuest].threadId());
        guests[randomGuest].insideLabyrinth = true;
    }
    public void exit(){
        if(cupcake.requests >= n - 1)
        {
            //end the party
            for (int i = 0; i < n; i++) { 
                guests[i].party = false;
            }

            System.out.println("Party ended with " + cupcake.requests + " new cupcakes requested, therefore all " + n + " guests ate a cupcake in the labyrinth.");
            System.exit(0);
        }
        else //get new guest
            enter();
    }
}

public class ProblemOne extends Thread{
    public static void main(String[] args)
    {
        //Number of guests
        int n = 100;

        GuestThread guests[] = new GuestThread[n];
        Labyrinth labyrinth = new Labyrinth();
        labyrinth.guests = guests;
        labyrinth.n = n;

        for (int i = 0; i < n; i++) { 
            guests[i] = new GuestThread();
            guests[i].insideLabyrinth = false;
            guests[i].labyrinth = labyrinth;
            guests[i].start();
        }

        labyrinth.enter();
    }
}