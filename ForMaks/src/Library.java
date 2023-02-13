import java.util.Scanner;

public class Library {
    private Scanner scanner;

    public Library(Scanner scan) {
        this.scanner = scan;
    }

    public boolean performingActions(String action, NoteBook noteBook) {
        switch (action) // Сравниваем ответ
        {
            case "1": //Создание новой персоны
                boolean resultOfCreate = noteBook.createPerson();
                if (resultOfCreate)
                    System.out.println("Контакт успешно создан!\n");
                else
                    System.out.println("Контакт не был создан!\n");
                return true;
            case "2": //Редактирование имеющийся персоны
                System.out.println("Введите имя контакта, который вы хотите редактировать! Или его ID (Точь в точь)");
                boolean resultOfRedact = noteBook.redactPerson(scanner.nextLine());
                if (resultOfRedact)
                    System.out.println("Контакт успешно отредактирован!\n");
                else
                    System.out.println("Контакт не был отредактирован!\n");
                return true;
            case "3": //Поиск персоны
                System.out.print("Введите имя контакта, или первые буквы его имени\n->");
                noteBook.searchPerson(scanner.nextLine());
                return true;
            case "4": //Узнать подробнее о персоне
                System.out.println("Введите имя контакта, о котором вы хотите узнать информацию! Или его ID (Точь в точь)");
                boolean resultOfMoreInf = noteBook.moreInformation(scanner.nextLine());
                if (resultOfMoreInf)
                    System.out.println("Выведена вся информация!\n");
                else
                    System.out.println("Ошибочка! Такого человечка не существует!\n");
                return true;
            case "5": //ВЫход из программы
                System.out.println("Удачи, уважаемый пользователь!");
                return false;
            default: //Выводится, если введено что-то другое
                System.out.print("Такое действие мною не предусмотрено(((\n\n");
                return true;
        }
    }

    public String isNothing(String incoming, String lastvalue) {
        if (incoming.equals("") || incoming.equals("\n")) {
            //Если не захочет менять пост
            incoming = lastvalue;
            return incoming;
        }
        return incoming;
    }

    public int isNull(String incoming, int lastvalue) {
        int count = 0;

        if (incoming.equals("") || incoming.equals("\n"))
            count = lastvalue;
        else if (Integer.parseInt(incoming) <= 0) //Если он напишет 0
        {
            System.out.print("У человека обязан быть телефон!");
        } else {
            count = Integer.parseInt(incoming);
        }

        return count;
    }
}
