import java.security.Principal;
import java.util.Scanner;
import java.util.Set;
import javax.security.auth.Subject;

public class ServerCommandExecutor {
    public static void executeFor(Subject subject) {
        Set<String> roles = subject.getPrincipals().stream()
            .map(Principal::getName)
            .filter(r -> r.equals("ADMIN") || r.equals("OPERATOR") || r.equals("AUDITOR") || r.equals("GUEST"))
            .collect(java.util.stream.Collectors.toSet());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Available Commands:");

        if (roles.contains("ADMIN")) {
            System.out.println("1. view_status\n2. restart_service\n3. clear_logs\n4. shutdown_server");
                } else if (roles.contains("OPERATOR")) {
                    System.out.println("1. view_status\n2. restart_service");
                } else if (roles.contains("AUDITOR")) {
                    System.out.println("1. view_status");
                } else if (roles.contains("GUEST")) {
                    System.out.println("1. home_info");
                }

        System.out.print("Enter command: ");
        String command = scanner.nextLine();

        switch (command) {
            case "view_status":
                System.out.println("Server is up and running.");
                break;
            case "restart_service":
                if (roles.contains("ADMIN") || roles.contains("OPERATOR"))
                    System.out.println("Restarting service...");
                else
                    System.out.println("Unauthorized command.");
                break;
            case "clear_logs":
            case "shutdown_server":
                if (roles.contains("ADMIN"))
                    System.out.println(command + " executed.");
                else
                    System.out.println("Unauthorized command.");
                break;
            case "home_info":
                if (roles.contains("GUEST"))
                    System.out.println("Welcome to Server Monitor CLI.");
                else
                    System.out.println("Unauthorized command.");
                break;
            default:
                System.out.println("Unknown command.");
        }
    }
}
