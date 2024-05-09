package uz.pdp.g34springbootinit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.g34springbootinit.domain.Client;
import uz.pdp.g34springbootinit.exception.DataNotFoundException;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private List<Client> clients = new ArrayList<>();

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        Client client = clients.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException("Client not found with id - " + id));
        return ResponseEntity.ok(client);
    }

    @PostMapping(
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"}
    )
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        client.setId(new Random().nextLong(Long.MAX_VALUE));
        clients.add(client);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(client);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getClients() {
        return ResponseEntity.ok(clients);
    }

    /*@DeleteMapping("/{id}")

    @ResponseStatus(code = HttpStatus.NO_CONTENT, value = HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Long id) {
        Client client = clients.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException("Client not found with id - " + id));
        clients.remove(client);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        Client client = clients.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException("Client not found with id - " + id));
        clients.remove(client);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatingClient) {
        Client client = clients.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException("Client not found with id - " + id));
        if (updatingClient.getName() != null) {
            client.setName(updatingClient.getName());
        }
        if (updatingClient.getEmail() != null) {
            client.setEmail(updatingClient.getEmail());
        }
        if (updatingClient.getPhone() != null) {
            client.setPhone(updatingClient.getPhone());
        }
        return ResponseEntity.ok(client);
    }

}
