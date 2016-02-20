package pl.krzysiek.android.znajdzjadlo;

public class PlacesTemplate {

    public PlacesTemplate(double naszPunktDl, double naszPunktSzer) {
        this.naszPunktDl = naszPunktDl;
        this.naszPunktSzer = naszPunktSzer;
    }

    //liczba dostepnych lokali w naczej bazie - tu zmieniamy dla calej aplikacji !
    int liLok = 3;

    //tablice opisujace poszczegolne lokale
    private String[] nazwy = new String[liLok];
    private String[] telefony = new String[liLok];
    private String[] adresy = new String[liLok];

    //tablice zawierajace współrzędne geograficzne lokali i sumeRóżnic - czyli odleflosć do najbliższego lokalu
    private double[] dlugosci = new double[liLok];
    private double[] szerokosci = new double[liLok];
    private double[] sumaZRoznic = new double[liLok];

    //współrzedne miejsca w którym sie znajdujemy
    private double naszPunktDl;
    private double naszPunktSzer;
    //zmienne do obliczenia, ktory lokal jest najblizej nas
    private double roznicaDl;
    private double roznicaSzer;
    private double najmniejszaOdl = 1000;
    //zmienna wskazujaca na pozycje w tablicach zajmowana przez najblizszy lokal
    private int numerKomorki;

    //metoda obliczajaca odleglosci
    private void obliczRoznice() {
        for (int i = 0; i < liLok; i++) {
            roznicaDl = Math.abs(naszPunktDl - dlugosci[i]);
            roznicaSzer = Math.abs(naszPunktSzer - szerokosci[i]);
            sumaZRoznic[i] = roznicaDl + roznicaSzer;
        }
    }

    //metoda wskazujaca pozycje w tablicach zajmowana przez najblizszy lokal
    private int wskazLokal() {
        for (int i = 0; i < liLok; i++) {
            if (sumaZRoznic[i] <= najmniejszaOdl) {
                najmniejszaOdl = sumaZRoznic[i];
                numerKomorki = i;
            }
        }
        return numerKomorki;
    }


}