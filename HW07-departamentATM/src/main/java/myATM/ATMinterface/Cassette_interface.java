package myATM.ATMinterface;
import myATM.Banknote;

public interface Cassette_interface {
    int getNumberOfBanknotes();

    int getDenomination();

    void refreshCassette(int denomination);

    void addBanknote(Banknote banknote);

    Banknote getBanknote();
}
