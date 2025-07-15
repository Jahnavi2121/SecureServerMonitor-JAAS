import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.*;
import javax.security.auth.spi.LoginModule;
import java.security.Principal;
import java.util.*;

public class SecureLoginModule implements LoginModule {
    private Subject subject;
    private CallbackHandler callbackHandler;

    private String username;
    private Set<Principal> principals = new HashSet<>();

    private static final Map<String, String> USERS = new HashMap<>();
    private static final Map<String, String> ROLES = new HashMap<>();

    static {
        USERS.put("admin", "adminpass");
        USERS.put("operator", "op123");
        USERS.put("auditor", "audit123");
        USERS.put("guest", "guestpass");

        ROLES.put("admin", "ADMIN");
        ROLES.put("operator", "OPERATOR");
        ROLES.put("auditor", "AUDITOR");
        ROLES.put("guest", "GUEST");
    }

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler,
                           Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        NameCallback nameCallback = new NameCallback("Username: ");
        PasswordCallback passwordCallback = new PasswordCallback("Password: ", false);
        try {
            callbackHandler.handle(new Callback[]{nameCallback, passwordCallback});
            username = nameCallback.getName();
            String password = new String(passwordCallback.getPassword());

            if (USERS.containsKey(username) && USERS.get(username).equals(password)) {
                return true;
            } else {
                throw new LoginException("Authentication failed");
            }
        } catch (Exception e) {
            throw new LoginException("Error: " + e.getMessage());
        }
    }

    @Override
    public boolean commit() throws LoginException {
        principals.add(new UserPrincipal(username));
        principals.add(new RolePrincipal(ROLES.get(username)));
        subject.getPrincipals().addAll(principals);
        return true;
    }

    @Override public boolean abort() { return true; }
    @Override public boolean logout() {
        subject.getPrincipals().removeAll(principals);
        return true;
    }

    public static class UserPrincipal implements Principal {
        private final String name;
        public UserPrincipal(String name) { this.name = name; }
        public String getName() { return name; }
    }

    public static class RolePrincipal implements Principal {
        private final String role;
        public RolePrincipal(String role) { this.role = role; }
        public String getName() { return role; }
    }
}
