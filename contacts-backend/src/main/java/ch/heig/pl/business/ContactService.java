package ch.heig.pl.business;

import ch.heig.pl.model.Contact;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
@Singleton
public class ContactService {
    private List<Contact>  contacts;

    public ContactService() {
        contacts = new ArrayList<>();
        contacts.add(new Contact("Pierre",1234));
        contacts.add(new Contact("Sylvie",1111));
    }

    synchronized public List<Contact> getContacts() {
        return contacts;
    }

    synchronized public int add(Contact contact) {
        contacts.add(contact);
        return contacts.size()-1;
    }
}
