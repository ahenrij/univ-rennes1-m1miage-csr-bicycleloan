class Site {

    /* Constantes associees au site */
    static final int STOCK_INIT = 5;
    static final int STOCK_MAX = 10;
    static final int BORNE_SUP = 8;
    static final int BORNE_INF = 2;

    private int num;
    private int stock;

    public Site(int num) {
        this.num = num + 1;
        this.stock = STOCK_INIT;
    }

    public int getStock() {
        return this.stock;
    }

    public int getNum() {
        return this.num;
    }

    public synchronized void emprunter() {

        while (this.stock == 0) {
            try {
                wait(); // Attendre qu'un vélo soit restituer
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.stock--;
        notifyAll(); // Dire qu'une place s'est libérée
    }

    public synchronized void restituer() {

        while (this.stock == STOCK_MAX) {
            try {
                wait(); // Attendre qu'une place se libère (notify venant de emprunter)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.stock++;
        notifyAll(); // Dire qu'on a un vélo de plus au cas où il y aurait un wait dans emprunter
    }

    public synchronized void setStock(int stock) {
        this.stock = stock;
        notifyAll();
    }
}