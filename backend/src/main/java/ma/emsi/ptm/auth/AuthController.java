package ma.emsi.ptm.auth;

import jakarta.validation.Valid;
import ma.emsi.ptm.auth.dto.LoginRequest;
import ma.emsi.ptm.auth.dto.LoginResponse;
import ma.emsi.ptm.common.exception.UnauthorizedException;
import ma.emsi.ptm.user.User;
import ma.emsi.ptm.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest req) {
        User user = userRepository.findByEmail(req.email())
                .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));

        if (!passwordEncoder.matches(req.password(), user.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }

        return new LoginResponse(jwtService.generateToken(user.getId()));
    }
}
