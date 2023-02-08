import java.io.Console;
import java.util.*;
public class Main
{
    ArrayList<Person> personList = new ArrayList<Person>(); //Список персон, сюда сохраняются все люди
    Scanner scanner = new Scanner(System.in); // Инициализируем сканер для получения текста

    public static void main(String[] args)
    {

        System.out.println("Привет, друг! Ты в своей электронной книжке!");
        boolean loop = true; //Переменная, чтобы закольцевать цикл работы программы

        Main main = new Main(); // Для обращения к сканеру
        Library library = new Library(main.scanner);
        NoteBook noteBook = new NoteBook(main.scanner, main.personList, library);

        //Несколько пробных персон
        main.personList.add(new Person("Bob", "Manager", "Soldier Korzuna home 1", new String[]{"+79218811688"}));
        main.personList.add(new Person("Oleg", "Gamer", "Home", new String[]{"+79288836688", "+79221855677"}));

       while(loop) // Запускаем бесконечный цикл работы программы
        {
            System.out.println("Вот список твоих контактов: ");
            for(int i = 0; i < main.personList.size(); i++) //Выводим информацию о существующих контактах
            {
                System.out.println(main.personList.get(i).get_name() + "\t" + main.personList.get(i).get_post() +
                        "\t" + main.personList.get(i).get_address() + "\t" + main.personList.get(i).get_phoneNumbers()[0]);
            }

            //Список действий
            System.out.println("\n\rКакие действия ты хочешь принять? (Напиши номер действия)");
            System.out.println("\t1 - Создать контакт");
            System.out.println("\t2 - Редактировать контакт");
            System.out.println("\t3 - Найти контакт");
            System.out.println("\t4 - Узнать всё про контакт");
            System.out.println("\t5 - Выйти из программы");

            //Ввод выбранного действия
            System.out.print("Действие -> ");
            String answer = main.scanner.nextLine();
            boolean result = library.performingActions(answer, noteBook);
            if(!result)
                loop = false;
        }
    }
}