package ro.ubb.bookstore.client.ui;

import org.springframework.web.client.RestTemplate;
import ro.ubb.bookstore.web.dto.BookDto;
import ro.ubb.bookstore.web.dto.BooksDto;
import ro.ubb.bookstore.web.dto.ClientDto;
import ro.ubb.bookstore.web.dto.ClientsDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientConsole {

    private RestTemplate restTemplate;

    public ClientConsole(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public void runConsole() {
        printMenu();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String option = scanner.next();
            if (option.equals("x")) {
                break;
            }
            switch (option) {
                case "1":
                    addBooks();
                    break;
                case "2":
                    printAllBooks();
                    break;
                case "3":
                    deleteBook();
                    break;
                case "4":
                    updateBook();
                    break;
                case "5":
                    addClient();
                    break;
                case "6":
                    printAllClients();
                    break;
                case "7":
                    deleteClient();
                    break;
                case "8":
                    updateClient();
                    break;

                default:
                    System.out.println("this option is not yet implemented");
            }
            printMenu();
        }
    }

    private void printMenu() {
        System.out.println(
                "\n............................................................\n" +
                        "1 - Add Book                     5 - Add client\n" +
                        "2 - Print all books              6 - Print all clients\n" +
                        "3 - Delete book                  7 - Delete client\n" +
                        "4 - Update book                  8 - Update client\n" +
                        "...........................................................");

    }

    private BookDto readBook() {
        System.out.println("Read book {id, name, author, year, stock, price}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {

            Long id = Long.valueOf(bufferRead.readLine());
            String name = bufferRead.readLine();
            String author = bufferRead.readLine();
            Integer year = Integer.valueOf(bufferRead.readLine());
            int stock = Integer.valueOf(bufferRead.readLine());
            int price = Integer.valueOf(bufferRead.readLine());

            BookDto booksDto = new BookDto(name, author, year, stock, price);
            booksDto.setId(id);
            return booksDto;


        } catch (Exception ex) {
            System.out.println("Error while reading book.");
            ex.getMessage();
            return null;
        }

    }

    private void addBooks() {

        BookDto saved = readBook();
        restTemplate.postForObject(
                "http://localhost:8090/api/books", saved, BookDto.class);


    }

    private void updateBook() {

        BookDto updateBook = readBook();

        restTemplate.put("http://localhost:8090/api/books/{id}",
                updateBook, updateBook.getId());


    }

    private void printAllBooks() {
        BooksDto result = restTemplate.getForObject(
                "http://localhost:8090/api/books", BooksDto.class);

        System.out.println(result);


    }

    private void deleteBook() {
        BooksDto result = restTemplate.getForObject(
                "http://localhost:8090/api/books", BooksDto.class);

        System.out.println("Id:");
        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();
        BookDto deleteDto = new ArrayList<BookDto>(result.getBooks()).get(0);
        deleteDto.setId(id);
        restTemplate.delete("http://localhost:8090/api/books/{id}",
                deleteDto.getId());


    }

    private ClientDto readClient() {
        System.out.println("Read client {Id, name, cnp}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {

            Long id = Long.valueOf(bufferRead.readLine());
            String name = bufferRead.readLine();
            Integer cnp = Integer.valueOf(bufferRead.readLine());

            ClientDto clientDto = new ClientDto(name, cnp);
            clientDto.setId(id);
            return clientDto;

        } catch (Exception ex) {
            System.out.println("Error while reading client.");
            ex.getStackTrace();
            return null;
        }
    }

    private void addClient() {

        ClientDto client = readClient();

        restTemplate.postForObject(
                "http://localhost:8090/api/clients",
                client, ClientDto.class);


    }

    private void updateClient() {

        ClientDto updateClient = readClient();

        restTemplate.put("http://localhost:8090/api/clients/{id}",
                updateClient, updateClient.getId());


    }

    private void printAllClients() {

        ClientsDto result = restTemplate.getForObject(
                "http://localhost:8090/api/clients", ClientsDto.class);
        System.out.println(result);
    }

    private void deleteClient() {
        ClientsDto result = restTemplate.getForObject(
                "http://localhost:8090/api/clients", ClientsDto.class);

        System.out.println("Id:");
        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();

        ClientDto deleteDto = new ArrayList<ClientDto>(result.getClients()).get(0);
        deleteDto.setId(id);
        restTemplate.delete("http://localhost:8090/api/clients/{id}",
                deleteDto.getId());

    }
}
