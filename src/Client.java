public class Client extends Thread {

    /* Constantes */
    private int dureeUnitaire = 100; // 100ms pour une unité de distance

    private Site siteDepart;
    private Site siteArrivee;

    public Client(Site depart, Site arrivee) {
        this.siteDepart = depart;
        this.siteArrivee = arrivee;
    }

    public void run() {

        System.out.println("Le client " + getName() + " emprunte un vélo du site " + siteDepart.getNum());
        siteDepart.emprunter();

        this.faireTrajet();

        System.out.println("Le client " + getName() + " restituer un vélo sur le site " + siteArrivee.getNum());
        siteArrivee.restituer();
    }

    private int distanceSites() {
        return Math.abs(this.siteDepart.getNum() - this.siteArrivee.getNum());
    }

    private void faireTrajet() {
        try {
            int dureeTrajet = dureeUnitaire * this.distanceSites();
            System.out.println("Le client " + getName() + " fait le trajet pour une durée de " + dureeTrajet + " ms");

            Thread.sleep(dureeTrajet); // Dors pendant une duree proportionnelle entre les sites
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
