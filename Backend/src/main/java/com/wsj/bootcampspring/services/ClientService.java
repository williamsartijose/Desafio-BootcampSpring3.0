package com.wsj.bootcampspring.services;

import com.wsj.bootcampspring.dto.ClientDTO;
import com.wsj.bootcampspring.entities.Client;
import com.wsj.bootcampspring.repositories.ClientRepository;
import com.wsj.bootcampspring.services.exceptions.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
}
