package com.psychoyogi.User.Service.service;


import com.psychoyogi.User.Service.dto.SearchResponse;
import com.psychoyogi.User.Service.dto.UserDto;
import com.psychoyogi.User.Service.entity.User;
import com.psychoyogi.User.Service.excpetion.UserNotFoundException;
import com.psychoyogi.User.Service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public SearchResponse<UserDto> searchUsers(String query) {
        logger.debug("Searching users with query: {}", query);

        List<User> users = userRepository.searchUsers(query);
        List<UserDto> userDtos = users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new SearchResponse<>(userDtos, userDtos.size(), query);
    }

    public UserDto getUserById(Long id) {
        logger.debug("Finding user by id: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        return convertToDto(user);
    }

    public UserDto getUserByEmail(String email) {
        logger.debug("Finding user by email: {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        return convertToDto(user);
    }

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setAge(user.getAge());
        dto.setGender(user.getGender());
        dto.setRole(user.getRole());
        dto.setBirthDate(user.getBirthDate());
        dto.setImage(user.getImage());
        dto.setSsn(user.getSsn());

        if (user.getAddress() != null) {
            UserDto.AddressDto addressDto = new UserDto.AddressDto();
            addressDto.setAddress(user.getAddress().getAddress());
            addressDto.setCity(user.getAddress().getCity());
            addressDto.setState(user.getAddress().getState());
            addressDto.setStateCode(user.getAddress().getStateCode());
            addressDto.setPostalCode(user.getAddress().getPostalCode());
            addressDto.setCountry(user.getAddress().getCountry());
            dto.setAddress(addressDto);
        }

        return dto;
    }
}