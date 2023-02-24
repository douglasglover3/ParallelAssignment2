
class GuestThread extends Thread {
    boolean insideLabyrinth = false;
    boolean party = false;
    boolean hungry = true;
    Cupcake cupcake;
    Labyrinth labyrinth;
    synchronized public void run()
    {
        System.out.println("Guest " + this.threadId() + " arrived to party.");
        party = true;
        while(party)
        {
            if(insideLabyrinth) {
                System.out.println("Guest " + this.threadId() + " entered labyrinth.");
                if(hungry) //guest has not eaten cupcake
                {
                    if(cupcake.eaten) // cupcake is absent
                    {
                        cupcake.request();
                        cupcake.eaten = false;
                    }
                    if(!cupcake.eaten) //cupcake is present
                    {
                        cupcake.eaten = true;
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
    int requests = 0;
    public void request(){
        requests++;
        System.out.println("Cupcake requested.");
    }
}
class Labyrinth {
    public boolean full = false;
    public void enter(){
        full = true;
    }
    public void exit(){
        full = false;
    }
}

public class ProblemOne extends Thread{
    public static void main(String[] args)
    {
        //Number of guests
        int n = 5;

        GuestThread guests[] = new GuestThread[n];
        Cupcake labyrinthCupcake = new Cupcake();
        Labyrinth labyrinth = new Labyrinth();

        for (int i = 0; i < n; i++) { 
            guests[i] = new GuestThread();
            guests[i].insideLabyrinth = false;
            guests[i].cupcake = labyrinthCupcake;
            guests[i].labyrinth = labyrinth;
            guests[i].start();
        }
        int randomGuest;
        while(labyrinthCupcake.requests < n - 1) {
            if(!labyrinth.full)
            {
                randomGuest = (int) Math.floor(Math.random() * n);
                System.out.println("Random guest is " + guests[randomGuest].threadId());
                guests[randomGuest].insideLabyrinth = true;
                labyrinth.enter();
            }
        }
        //end the party
        for (int i = 0; i < n; i++) { 
            guests[i].party = false;
        }

        System.out.println("Party ended with " + labyrinthCupcake.requests + " new cupcakes requested, therefore all " + n + " guests ate a cupcake in the labyrinth.");
        //wait for each thread to finish
        for (int i = 0; i < n; i++) { 
            try {
                guests[i].party = false;
                guests[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}