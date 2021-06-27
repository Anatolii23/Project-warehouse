package com.example.Projectwarehouse.services;

import com.example.Projectwarehouse.entity.ClientEntity;
import com.example.Projectwarehouse.errors.ClientNotFoundException;
import com.example.Projectwarehouse.repositories.ClientCache;
import com.example.Projectwarehouse.repositories.ClientRepository;
import com.example.Projectwarehouse.rest.dto.ClientDTO;
import com.example.Projectwarehouse.until.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServices {
    private final ClientRepository clientRepository;
    private final ClientCache clientCache;

    public ClientDTO addClient(ClientDTO clientDTO) {
        clientRepository.save(EntityDtoMapper.mappedToClientEntity(clientDTO));
        clientCache.saveClientInCache(clientDTO);
        return clientDTO;
    }

    public void deleteClientById(Long id) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(id);
        if (clientEntity.isPresent()) {
            clientRepository.delete(clientEntity.get());
            clientCache.deleteClientFromCache(clientEntity.get().getId());
        }
        else throw new ClientNotFoundException(id);
    }

    public ClientDTO editClient(Long id, ClientDTO clientDTO) {
        Optional<ClientEntity> optionalClientEntity = clientRepository.findById(id);
        if (optionalClientEntity.isPresent()) {
            ClientEntity clientEntity = optionalClientEntity.get();
            BeanUtils.copyProperties(clientDTO, clientEntity);
            clientCache.saveClientInCache(EntityDtoMapper.mappedToClientDTO(clientEntity));
            clientRepository.save(clientEntity);
            return clientDTO;
        }
        else {
            throw new ClientNotFoundException(id);
        }
    }
    public List<ClientDTO> findClientByParam(Long id, String name, String address){
        return clientRepository.findAll().stream()
                .filter(clientEntity -> id==null || clientEntity.getId() ==id)
                .filter(clientEntity -> name==null || clientEntity.getName().equals(name))
                .filter(clientEntity -> address==null || clientEntity.getAddress().equals(name))
                .map(EntityDtoMapper::mappedToClientDTO)
                .collect(Collectors.toList());
    }
}
