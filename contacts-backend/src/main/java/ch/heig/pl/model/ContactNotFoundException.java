package ch.heig.pl.model;

public class ContactNotFoundException extends Exception {
    public ContactNotFoundException(String nom) {
        super("Contact "+nom+" pas trouvé");
    }
}
