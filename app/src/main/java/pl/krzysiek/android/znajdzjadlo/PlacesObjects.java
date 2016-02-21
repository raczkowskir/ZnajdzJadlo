package pl.krzysiek.android.znajdzjadlo;

public class PlacesObjects {

    private double naszPunktDl = 0;
    private double naszPunktSzer = 0;

    PlacesTemplate burger = new PlacesTemplate(naszPunktDl, naszPunktSzer);

    //metoda zwracajaca obiekt Klasy PlacesTemplate - ale z tablicami zapelnionymi dla dania "burger"
    public PlacesTemplate getBurger() {
        return burger;
    }

    //metoda ustawiajaca nasza aktualna dlugosc geograficzna
    public void naszPunktDl(double naszPunktDl) {
        this.naszPunktDl = naszPunktDl;
    }
    //metoda ustawiajaca nasza obecna szerokosc geograficzna
    public void setNaszPunktSzer(double naszPunktSzer) {
        this.naszPunktSzer = naszPunktSzer;
    }
    //metoda zapełniajaca tablice obiektu Burger Klasy PlacesTemplate
    public void setBurger() {
        burger.nazwy[0] = "Mc Donald's";
        burger.nazwy[1] = "KFC";
        burger.nazwy[2] = "Rock Burger";

        burger.adresy[0] = "Piłsudskiego 105";
        burger.adresy[1] = "Świdnicka 13";
        burger.adresy[2] = "Szewska 27A";

        burger.telefony[0] ="660 625 749";
        burger.telefony[1] ="71 386 18 70";
        burger.telefony[2] ="733 530 917";

        burger.dlugosci[0]= 17.03621938;
        burger.dlugosci[1]= 17.03208342;
        burger.dlugosci[2]= 17.03537717;

        burger.szerokosci[0]=51.09888799;
        burger.szerokosci[1]=51.10756841;
        burger.szerokosci[2]=51.11132033;

        burger.obliczRoznice();
    }

}
