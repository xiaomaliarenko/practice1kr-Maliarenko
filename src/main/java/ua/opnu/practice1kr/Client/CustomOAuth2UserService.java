package ua.opnu.practice1kr.Client;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Lazy;
import ua.opnu.practice1kr.Client.Role.Role;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final ClientRepository clientRepository;
    private final @Lazy PasswordEncoder passwordEncoder;

    public CustomOAuth2UserService(ClientRepository clientRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        Optional<Client> existingClient = clientRepository.findByEmail(email);
        Client client;

        if (existingClient.isPresent()) {
            client = existingClient.get();
            client.setName(name);
            client.setAttributes(oauth2User.getAttributes());
            clientRepository.save(client);
        } else {
            client = Client.builder()
                    .name(name)
                    .email(email)
                    .username(email)
                    .password(passwordEncoder.encode("GENERATED_OAUTH2_PASSWORD"))
                    .role(Role.USER)
                    .attributes(oauth2User.getAttributes())
                    .build();
            clientRepository.save(client);
        }
        return client;
    }
}
