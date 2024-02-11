import java.util.Scanner;

public class LuhnAlgorithm {
    
    public static boolean isValid(String cardNumber) {
        // Удаляем все пробелы из номера карты и переворачиваем строку
        String reversed = new StringBuilder(cardNumber.replaceAll("\\s", "")).reverse().toString();
        
        int sum = 0;
        boolean alternate = false; // удвоение каждой второй цифры
        for (int i = 0; i < reversed.length(); i++) {
            int digit = Character.digit(reversed.charAt(i), 10); // Получаем цифру из символа
            if (alternate) {
                digit *= 2; // Удваиваем каждую вторую цифру
                if (digit > 9) {
                    digit -= 9; // Если удвоенная цифра больше 9, вычитаем 9
                }
            }
            sum += digit; // Добавляем цифру к сумме
            alternate = !alternate; // Переключаем значение переключателя
        }
        
        return sum % 10 == 0; // Возвращаем true, если сумма делится на 10 без остатка
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите номер карты (для выхода введите 'exit'):");
            String cardNumber = scanner.nextLine();
            if (cardNumber.equalsIgnoreCase("exit")) {
                break;
            }
            if (isValid(cardNumber)) {
                System.out.println("Номер карты действителен. ВПЕРЕЕЕЕЕЕД\r\n" + //
            "═════════════════════════════════\r\n" + //
            "░░░╔╗░╔╗╔══╗╔╗░╔╗╔══╗░░╔╗░╔══╗░░░\r\n" + //
            "░░░║╚╦╝║╚╗╔╝║╚╦╝║║╔╗║░░║║░║╔╗║░░░\r\n" + //
            "░░░╚╗║╔╝╔╝╚╗╚╗║╔╝║╠╣║░░║╚╗║╠╣║░░░\r\n" + //
            "░░░░╚═╝░╚══╝░╚═╝░╚╝╚╝░░╚═╝╚╝╚╝░░░\r\n" + //
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\r\n" + //
            "╔══╗╔╗░╔╗╔═╗╔╗░╔╗╔╗╔═╗╔══╗╔═╗╔═╦╗\r\n" + //
            "║░═╣║╚╦╝║║║║║║░║║║║║╔╝╚╗╔╝║║║║║║║\r\n" + //
            "║░═╣╚╗║╔╝║║║║╚╗║╚╝║║╚╗╔╝╚╗║║║║║║║\r\n" + //
            "╚══╝░╚═╝░╚═╝╚═╝╚══╝╚═╝╚══╝╚═╝╚╩═╝\r\n" + //
            "═════════════════════════════════");
            } else {
                System.out.println("Номер карты недействителен =((((\r\n" + //
                                        "▒▒▒▒▒█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█\r\n" + //
                                        "▒▒▒▒▒█░▒▒▒▒▒▒▒▓▒▒▓▒▒▒▒▒▒▒░█\r\n" + //
                                        "▒▒▒▒▒█░▒▒▓▒▒▒▒▒▒▒▒▒▄▄▒▓▒▒░█░▄▄\r\n" + //
                                        "▄▀▀▄▄█░▒▒▒▒▒▒▓▒▒▒▒█░░▀▄▄▄▄▄▀░░█\r\n" + //
                                        "█░░░░█░▒▒▒▒▒▒▒▒▒▒▒█░░░░░░░░░░░█\r\n" + //
                                        "▒▀▀▄▄█░▒▒▒▒▓▒▒▒▓▒█░░░█▒░░░░█▒░░█\r\n" + //
                                        "▒▒▒▒▒█░▒▓▒▒▒▒▓▒▒▒█░░░░░░░▀░░░░░█\r\n" + //
                                        "▒▒▒▄▄█░▒▒▒▓▒▒▒▒▒▒▒█░░█▄▄█▄▄█░░█\r\n" + //
                                        "▒▒▒█░░░█▄▄▄▄▄▄▄▄▄▄█░█▄▄▄▄▄▄▄▄▄█\r\n" + //
                                        "▒▒▒█▄▄█░░█▄▄█░░░░░░█▄▄█░░█▄▄█");
                
            }
        }
        scanner.close();
    }
}