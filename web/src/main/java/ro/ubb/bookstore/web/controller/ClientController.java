package ro.ubb.bookstore.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.bookstore.core.domain.Client;
import ro.ubb.bookstore.core.service.ClientService;
import ro.ubb.bookstore.web.converter.ClientConverter;
import ro.ubb.bookstore.web.dto.ClientDto;
import ro.ubb.bookstore.web.dto.ClientsDto;

import java.util.List;
import java.util.Set;

@RestController
public class ClientController {

    private static final Logger log =
            LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientConverter clientConverter;


    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    ClientsDto getClients() {
        log.trace("getClients --- method entered");

        List<Client> clients = clientService.getAllClients();
        Set<ClientDto> clientDtos = clientConverter.convertModelsToDtos(
                clients);
        ClientsDto result = new ClientsDto(clientDtos);

        log.trace("getClients: result={}", result);

        return result;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    ClientDto saveClient(@RequestBody ClientDto dto) {
        log.trace("saveClient: dto={}", dto);

        Client client =
                clientService.saveClient(
                        clientConverter.convertDtoToModel(dto));
        ClientDto result = clientConverter.convertModelToDto(client);

        log.trace("saveClient: result={}", result);

        return result;
    }

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.PUT)
    ClientDto updateClient(@PathVariable Long clientId,
                           @RequestBody ClientDto dto) {
        log.trace("updateClient: clientId={}, dto={}", clientId, dto);

        Client client = clientService.updateClient(clientId, clientConverter.convertDtoToModel(dto));
        ClientDto result = clientConverter.convertModelToDto(client);

        log.trace("updateClient result={}", result);

        return result;
    }

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long clientId) {
        log.trace("deleteClient: clientId={}", clientId);

        clientService.deleteById(clientId);

        log.trace("deleteClient --- method finished");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
