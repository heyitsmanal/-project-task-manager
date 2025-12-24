package ma.emsi.ptm.common.security;

import ma.emsi.ptm.common.exception.UnauthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class CurrentUser {

    private CurrentUser() {}

    public static Long id() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null) {
            throw new UnauthorizedException("Unauthorized");
        }

        Object principal = auth.getPrincipal();
        if (principal instanceof Long l) return l;
        if (principal instanceof String s) return Long.parseLong(s);

        throw new UnauthorizedException("Unauthorized");
    }
}
