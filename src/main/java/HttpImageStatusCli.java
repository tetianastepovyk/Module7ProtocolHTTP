import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
public class HttpImageStatusCli {
    private static boolean isExit = false;
    private HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();

    void askStatus() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter HTTP status code, or exit to end the program: ");
            String userInput = scanner.nextLine();
            if ("exit".equalsIgnoreCase(userInput)) {
                break;
            } else {
                try {
                    int code = Integer.parseInt(userInput.strip());
                    CompletableFuture.runAsync(() -> {
                        String url = new HttpStatusChecker().getStatusImage(code);
                        if (url != null) {
                            downloader.downloadStatusImage(code);
                        }
                    });
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number or 'exit'.");
                }
            }
        }
    }
}
