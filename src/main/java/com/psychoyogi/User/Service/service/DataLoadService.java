package com.psychoyogi.User.Service.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psychoyogi.User.Service.entity.User;
import com.psychoyogi.User.Service.repository.UserRepository;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataLoadService implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoadService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${external.api.users.url}")
    private String usersApiUrl;

    @Override
    public void run(String... args) throws Exception {
        loadUsersFromApi();
    }

    @Retry(name = "userApiRetry")
    public void loadUsersFromApi() {
        try {
            logger.info("Loading users from external API: {}", usersApiUrl);

            String response = restTemplate.getForObject(usersApiUrl, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            JsonNode usersNode = root.path("users");

            List<User> users = new ArrayList<>();

            for (JsonNode userNode : usersNode) {
                User user = mapJsonToUser(userNode);
                users.add(user);
            }

            userRepository.saveAll(users);
            logger.info("Successfully loaded {} users into database", users.size());

        } catch (Exception e) {
            logger.error("Error loading users from API", e);
            throw new RuntimeException("Failed to load users from external API", e);
        }
    }

    private User mapJsonToUser(JsonNode userNode) {
        User user = new User();
        user.setId(userNode.path("id").asLong());
        user.setFirstName(userNode.path("firstName").asText());
        user.setLastName(userNode.path("lastName").asText());
        user.setEmail(userNode.path("email").asText());
        user.setPhone(userNode.path("phone").asText());
        user.setAge(userNode.path("age").asInt());
        user.setGender(userNode.path("gender").asText());
        user.setRole(userNode.path("role").asText("user"));
        user.setImage(userNode.path("image").asText());
        user.setSsn(userNode.path("ssn").asText());

        // Parse birth date
        String birthDateStr = userNode.path("birthDate").asText();
        if (!birthDateStr.isEmpty()) {
            try {
                user.setBirthDate(birthDateStr);
            } catch (Exception e) {
                logger.warn("Could not parse birth date: {}", birthDateStr);
            }
        }

        // Map address
        JsonNode addressNode = userNode.path("address");
        if (!addressNode.isMissingNode()) {
            com.psychoyogi.User.Service.entity.Address address = new com.psychoyogi.User.Service.entity.Address();
            address.setAddress(addressNode.path("address").asText());
            address.setCity(addressNode.path("city").asText());
            address.setState(addressNode.path("state").asText());
            address.setStateCode(addressNode.path("stateCode").asText());
            address.setPostalCode(addressNode.path("postalCode").asText());
            address.setCountry(addressNode.path("country").asText());
            user.setAddress(address);
        }

        return user;
    }
}