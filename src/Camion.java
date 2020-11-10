import java.util.concurrent.Semaphore;

public class Camion extends Thread {
    
    private Site[] sites;
    private int stock = 0;

    public Camion(Site[] sites) {
        this.sites = sites;
    }

    public void run() {

        for (int i=0; i < sites.length; i++) {
            Site site = sites[i];
            
            
        }
    }
}
