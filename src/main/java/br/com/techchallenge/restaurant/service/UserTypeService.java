package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.UserType;
import br.com.techchallenge.restaurant.exception.UserTypeNotFoundException;
import br.com.techchallenge.restaurant.repository.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTypeService {
    private final UserTypeRepository userTypeRepository;

    public UserType create(UserType userType){
        return userTypeRepository.save(userType);
    }

    public UserType findById(Long id) {
        return userTypeRepository.findById(id)
                .orElseThrow(() -> new UserTypeNotFoundException(id));
    }

    public List<UserType> findAll() {
        return userTypeRepository.findAll();
    }

    public UserType update(Long id, UserType userType) {
        UserType existing = findById(id);

        existing.setName(userType.getName());

        return userTypeRepository.save(existing);
    }

    public void delete(Long id) {
        UserType userType = findById(id);
        userTypeRepository.delete(userType);
    }
}
