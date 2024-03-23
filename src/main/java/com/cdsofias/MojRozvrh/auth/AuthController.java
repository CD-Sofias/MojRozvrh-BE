package com.cdsofias.MojRozvrh.auth;

import com.cdsofias.MojRozvrh.auth.dto.AuthResponse;
import com.cdsofias.MojRozvrh.auth.dto.SignupRequest;
import com.cdsofias.MojRozvrh.auth.exception.EmailAlreadyTakenException;
import com.cdsofias.MojRozvrh.users.User;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(
            @Valid
            @RequestBody SignupRequest request
    ) {
        AuthResponse authResponse = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody User request,
            HttpServletResponse response
    ) {
        AuthResponse authentication = authService.authenticate(request);
        ResponseCookie cookie = ResponseCookie.from("accessToken", authentication.token())
                .httpOnly(true)
                // 1 day
                .maxAge(60 * 60 * 24)
                .path("/")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return new ResponseEntity<>(
                authentication,
                HttpStatus.OK
        );
    }

    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<?> handleEmailAlreadyTakenException(EmailAlreadyTakenException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
