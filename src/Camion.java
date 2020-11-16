
public class Camion extends Thread {
    
    private Site[] sites;
    private int stock = 10;

    public Camion(Site[] sites) {
        this.sites = sites;
        setDaemon(true); //Is killed by JVM when all others processes are done
    }

    public void run() {

        while (true) {
        
            for (int i=0; i < sites.length; i++) {
                
                Site site = sites[i];

                synchronized(site) {
                    int siteStock = site.getStock();

                    if (siteStock > Site.BORNE_SUP) {

                        // Collect the surplus stock of bicylces and reset site's stock to initial stock
                        // value
                        this.stock += siteStock - Site.STOCK_INIT;
                        site.setStock(Site.STOCK_INIT);
                        System.out.println("Passage camion sur le site " + site.getNum() + ". stock : " + siteStock
                                + " vélos, remis à " + Site.STOCK_INIT);

                    } else if (siteStock < Site.BORNE_INF) {

                        // Recharge site's stock as much as possible till initial stock value
                        int stockRecharge = Site.STOCK_INIT - siteStock;
                        if (this.stock < stockRecharge) {
                            stockRecharge = this.stock;
                        }
                        this.stock -= stockRecharge;
                        site.setStock(siteStock + stockRecharge);
                        System.out.println("Passage camion sur le site " + site.getNum() + ". stock : " + siteStock
                                + " vélos, remis à " + (siteStock + stockRecharge));
                    }
                }
            }
        }
    }
}
