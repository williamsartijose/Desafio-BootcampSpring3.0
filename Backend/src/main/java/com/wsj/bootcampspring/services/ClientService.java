package com.wsj.bootcampspring.services;

import com.wsj.bootcampspring.dto.ClientDTO;
import com.wsj.bootcampspring.entities.Client;
import com.wsj.bootcampspring.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

public class ClientService {

    private ClientRepository repository;
    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
        Page<Client> list = repository.findAll(pageRequest);
        return list.map(x -> new ClientDTO(x));

    }
}
