import javax.security.auth.login.*;
import javax.security.auth.Subject;
import java.security.PrivilegedAction;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.setProperty("java.security.auth.login.config", "login.config");

        try {
            LoginContext context = new LoginContext("SecureLogin", new ConsoleCallbackHandler());
            context.login();

            Subject subject = context.getSubject();
            System.out.println("Login successful! Principals: " + subject.getPrincipals());

            Subject.doAs(subject, (PrivilegedAction<Void>) () -> {
                ServerCommandExecutor.executeFor(subject);
                return null;
            });

            context.logout();
        } catch (LoginException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }
}
