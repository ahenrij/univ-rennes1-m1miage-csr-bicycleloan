import java.util.concurrent.Semaphore;

public class Camion extends Thread {
    
    private Site[] sites;
    private int stock = 0;

    public Camion(Site[] sites) {
        this.sites = sites;
    }

    public void run() {

        boolean oneClientAlive = false;

        do {
        
            for (int i=0; i < sites.length; i++) {
                
                Site site = sites[i];
                site.startTruckPassage();

                int siteStock = site.getStock();
                
                if (siteStock > Site.BORNE_SUP) {

                    // Collect the surplus stock of bicylces and reset site's stock to initial stock value
                    this.stock += siteStock - Site.STOCK_INIT;
                    site.setStock(Site.STOCK_INIT);

                } else if (siteStock < Site.BORNE_INF) {

                    // Recharge site's stock as much as possible till initial stock value
                    int stockRecharge = (this.stock >= Site.STOCK_INIT) ? Site.STOCK_INIT : this.stock;
                    this.stock -= stockRecharge;
                    site.setStock(stockRecharge);
                }

                site.endTruckPassage();
            }

            
            // rafraichir la valeur de oneAlive avec les clients
        } while (oneClientAlive);
    }
}
