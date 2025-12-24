package ma.emsi.ptm.user;

import ma.emsi.ptm.common.security.CurrentUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeController {

    @GetMapping("/me")
    public Long me() {
        return CurrentUser.id();
    }
}
