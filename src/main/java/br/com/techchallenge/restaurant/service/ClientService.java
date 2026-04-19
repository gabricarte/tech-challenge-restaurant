package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.Client;
import br.com.techchallenge.restaurant.exception.ClientNotFoundException;
import br.com.techchallenge.restaurant.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    @Transactional
    public Client atualizarDados(Long id, Client novosDados) {
        Client client = findById(id);
        client.setName(novosDados.getName());
        client.setEmail(novosDados.getEmail());
        client.setAddress(novosDados.getAddress());
        client.setBirthDate(novosDados.getBirthDate());

        return clientRepository.save(client);
    }

    @Transactional
    public void trocarSenha(Long id, String novaSenha) {
        Client client = findById(id);
        client.setPassword(novaSenha);
        clientRepository.save(client);
    }
}