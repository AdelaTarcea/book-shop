package ro.ubb.bookstore.core.service;

import ro.ubb.bookstore.core.domain.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();

    Client saveClient(Client client);

    Client updateClient(Long id, Client client);

    void deleteById(Long clientId);
}

