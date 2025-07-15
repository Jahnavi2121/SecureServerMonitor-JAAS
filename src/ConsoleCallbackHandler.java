import javax.security.auth.callback.*;
import java.io.IOException;
import java.util.Scanner;

public class ConsoleCallbackHandler implements CallbackHandler {
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        Scanner scanner = new Scanner(System.in);
        for (Callback cb : callbacks) {
            if (cb instanceof NameCallback) {
                System.out.print(((NameCallback) cb).getPrompt());
                ((NameCallback) cb).setName(scanner.nextLine());
            } else if (cb instanceof PasswordCallback) {
                System.out.print(((PasswordCallback) cb).getPrompt());
                ((PasswordCallback) cb).setPassword(scanner.nextLine().toCharArray());
            } else {
                throw new UnsupportedCallbackException(cb);
            }
        }
    }
}
