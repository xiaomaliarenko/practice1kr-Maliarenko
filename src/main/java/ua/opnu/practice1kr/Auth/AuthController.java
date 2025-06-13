package ua.opnu.practice1kr.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.opnu.practice1kr.Auth.Dto.AuthenticationRequest;
import ua.opnu.practice1kr.Auth.Dto.AuthenticationResponse;
import ua.opnu.practice1kr.Auth.Dto.RegisterRequest;
import ua.opnu.practice1kr.Client.Client;
import ua.opnu.practice1kr.Client.ClientService;
import ua.opnu.practice1kr.Jwt.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ClientService clientService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        Client client = Client.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
        Client registeredClient = clientService.registerNewClient(client);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtUtil.generateToken(registeredClient);
        return ResponseEntity.ok(AuthenticationResponse.builder().token(jwtToken).message("Registration successful").build());
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Client authenticatedClient = (Client) authentication.getPrincipal();
        String jwtToken = jwtUtil.generateToken(authenticatedClient);
        return ResponseEntity.ok(AuthenticationResponse.builder().token(jwtToken).message("Authentication successful").build());
    }

    @GetMapping("/oauth2/success")
    public ResponseEntity<AuthenticationResponse> oauth2LoginSuccess(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof Client) {
            Client client = (Client) authentication.getPrincipal();
            String jwtToken = jwtUtil.generateToken(client);
            return ResponseEntity.ok(AuthenticationResponse.builder().token(jwtToken).message("OAuth2 Login successful").build());
        }
        return ResponseEntity.badRequest().body(AuthenticationResponse.builder().message("OAuth2 login failed").build());
    }
}
