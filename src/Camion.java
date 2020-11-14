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
            if(sites[i].getStock()>sites[i].getBorneSup()){
                stock =stock + (sites[i].getStock()-sites[i].getInit());

            } else if(sites[i].getStock()<sites[i].getBorneInf()){
                stock =stock - (sites[i].getInit()-sites[i].getStock())
            }
            
        }
    }
}
