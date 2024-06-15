package com.wsj.bootcampspring.services;

import com.wsj.bootcampspring.dto.ClientDTO;
import com.wsj.bootcampspring.entities.Client;
import com.wsj.bootcampspring.repositories.ClientRepository;
import com.wsj.bootcampspring.services.exceptions.ClientNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    private static final String CLIENT_NOT_FOUND = "Client not found";

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
        Page<Client> list = clientRepository.findAll(pageRequest);
        return list.map(l -> new ClientDTO(l));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> cli = clientRepository.findById(id);
        Client client = cli.orElseThrow(() -> new ClientNotFoundException(CLIENT_NOT_FOUND));
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO create(ClientDTO clientDto) {
        Client client = new Client();
        copyDtoToEntity(clientDto, client);
        client = clientRepository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO clientDto) {
        try {
            Client client = clientRepository.getReferenceById(id);
            copyDtoToEntity(clientDto, client);
            client = clientRepository.save(client);
            return new ClientDTO(client);
        } catch (EntityNotFoundException c) {
            throw new ClientNotFoundException(CLIENT_NOT_FOUND);
        }
    }

    public void delete(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ClientNotFoundException(CLIENT_NOT_FOUND);
        }
    }

    private void copyDtoToEntity(ClientDTO clientDto, Client client) {
        client.setName(clientDto.getName());
        client.setCpf(clientDto.getCpf());
        client.setIncome(clientDto.getIncome());
        client.setBirthDate(clientDto.getBirthDate());
        client.setChildren(clientDto.getChildren());
    }

}