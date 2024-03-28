package com.cdsofias.MojRozvrh.auth;

import com.cdsofias.MojRozvrh.auth.dto.AuthResponse;
import com.cdsofias.MojRozvrh.auth.dto.LoginRequest;
import com.cdsofias.MojRozvrh.auth.dto.LoginResponse;
import com.cdsofias.MojRozvrh.auth.dto.SignupRequest;
import com.cdsofias.MojRozvrh.auth.exception.EmailAlreadyTakenException;
import com.cdsofias.MojRozvrh.auth.exception.UsernameAlreadyTakenException;
import com.cdsofias.MojRozvrh.token.Token;
import com.cdsofias.MojRozvrh.token.TokenRepository;
import com.cdsofias.MojRozvrh.users.CreateUserDto;
import com.cdsofias.MojRozvrh.users.Role;
import com.cdsofias.MojRozvrh.users.User;
import com.cdsofias.MojRozvrh.users.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse register(SignupRequest userDto) {

        if (userService.findByEmail(userDto.email()).isPresent()) {
            throw new EmailAlreadyTakenException("Email is already taken");
        }

        if (userService.findByUsername(userDto.username()).isPresent()) {
            throw new UsernameAlreadyTakenException("Username is already taken");
        }

        CreateUserDto createUserDto = new CreateUserDto(
                userDto.username(),
                userDto.email(),
                passwordEncoder.encode(userDto.password()),
                Role.USER,
                null);
        userService.createUser(createUserDto);

        return new AuthResponse("User registration was successful");
    }

    @Transactional
    public LoginResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        User user = userService.findByUsername(request.username()).orElseThrow();
        String jwt = jwtService.generateToken(user);

        revokeAllTokenByUser(user);
        saveUserToken(jwt, user);

        return new LoginResponse(jwt, "User login was successful");
    }

    private void revokeAllTokenByUser(User user) {
        List<Token> validTokens = tokenRepository.findAllTokensByUser(user.getId());
        if (validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t -> t.setLoggedOut(true));

        tokenRepository.saveAll(validTokens);
    }

    private void saveUserToken(String jwt, User user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }
}
