import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class NoteBook {
    private Scanner scanner;
    private ArrayList<Person> personList;
    private Library library;

    public NoteBook(Scanner scan, ArrayList<Person> personListik, Library lib) {
        this.scanner = scan;
        this.personList = personListik;
        this.library = lib;
    }

    public boolean moreInformation(String name) //Больше информации о персоне
    {

        for (int i = 0; i < personList.size(); i++) //Цикл по количеству перосон
        {
            if (personList.get(i).getName().equals(name) || name.equals(Integer.toString(personList.get(i).getId()))) //Проверка на совпадение имени
            {
                //Вывод всеё информации о персоне
                System.out.println("Имя: " + personList.get(i).getName());
                System.out.println("Пост: " + personList.get(i).getPost());
                System.out.println("Адрес: " + personList.get(i).getAddress());
                System.out.print("Телефон\\Телефоны: ");
                for (var number : personList.get(i).getPhoneNumbers()) {
                    System.out.print("\t" + number);
                }
                System.out.println();
                return true;
            }
        }
        return false; //Если персоны нет
    }

    public void searchPerson(String name) {
        System.out.printf("Вот список всех контактов, начинающихся на '%s' :\n", name);
        for (int i = 0; i < personList.size(); i++) //Цикл по всем персонам
        {
            if (personList.get(i).getName().startsWith(name)) //Проверка на совпадение с именем
            {
                System.out.println(personList.get(i).getId() + "\t" + personList.get(i).getName() + "\t" + personList.get(i).getPost() +
                        "\t" + personList.get(i).getAddress() + "\t" + personList.get(i).getPhoneNumbers()[0]); //Вывод информации о персонах по критерию имени
            }
        }
        System.out.println("");
    }

    public boolean redactPerson(String startName) //Редактирование человека
    {
        for (int i = 0; i < personList.size(); i++) //Цикл по количеству существующих персон
        {
            var redactingPerson = personList.get(i); //Получаем персону
            if (startName.equals(redactingPerson.getName()) || startName.equals(Integer.toString(redactingPerson.getId()))) //Сравниваем имя персоны
            {
                System.out.printf("Отредактируйте имя или запишите его заново! Текущее имя: '%s'." + " Чтобы оставить текущее имя нажмите 'Enter'\n->", personList.get(i).getName());
                String name = library.isNothing(scanner.nextLine(), personList.get(i).getName());
                System.out.printf("Отредактируйте пост человека или запишите текущий! Текущий пост: '%s'. " + "Чтобы оставить текущее значение нажмите 'Enter'\n->", personList.get(i).getPost());
                String post = library.isNothing(scanner.nextLine(), personList.get(i).getPost());
                System.out.printf("Укажите адрес проживания человека! Текущий адрес: '%s'. Чтобы оставить текущее значение нажмите 'Enter'\n->", personList.get(i).getAddress());
                String address = library.isNothing(scanner.nextLine(), personList.get(i).getAddress());
                try {
                    String[] phone; //Объявляем массив телефонов
                    System.out.printf("Сколько телефонов у человека? (Введите цифру). Текущее значение : '%s'. Чтобы оставить текущее значение нажмите 'Enter'\n-> ", personList.get(i).getPhoneNumbers().length);
                    int countOfPhones = library.isNull(scanner.nextLine(), personList.get(i).getPhoneNumbers().length);
                    //Если человек не хочет менять количество телефонов
                    phone = new String[countOfPhones];

                    System.out.println("Вводите через Enter номера телефонов (Ввели номер, а после нажали Enter и так далее). Чтобы оставить номер телефона нажмите 'Enter'. Текущие номера телефонов: ");
                    for (int j = 0; j < personList.get(i).getPhoneNumbers().length; j++) //Выводим текущее номера телефонов
                        System.out.print("\t" + personList.get(i).getPhoneNumbers()[j]);
                    System.out.println();
                    for (int j = 0; j < phone.length; j++) {
                        phone[j] = scanner.nextLine();
                        if (phone[j].equals("") || phone[j].equals("\n") && j < personList.get(i).getPhoneNumbers().length) //Если хочет оставить текущий номер телефонв
                            phone[j] = personList.get(i).getPhoneNumbers()[j];
                    }
                    personList.remove(i); //Удаляем прошлую

                    personList.add(new Person(i+1,name, post, address, phone)); //Добавляем новую персону
                    System.out.println("\n");
                    return true;
                } catch (Exception exception) {
                    System.out.println("Введите число!" + exception.getMessage());
                    return false;
                }
            }
        }
        System.out.println("\n");
        return false;
    }

    public boolean createPerson() //Создание человека
    {
        System.out.print("Какое имя у человека?\n->");
        String name = scanner.nextLine();
        System.out.print("Какой пост у человека?\n->");
        String post = scanner.nextLine();
        System.out.print("Какой адрес у человека?\n->");
        String address = scanner.nextLine();

        try {
            int countOfPhones = 0; //Количество его телефонов
            System.out.print("Сколько телефонов у человека? (Введите цифру). \n-> ");
            countOfPhones = scanner.nextInt();
            if (countOfPhones == 0) //Проверка на то, не ввел ли человек 0, так как 0 телефонов не может быть
            {
                System.out.print("У человека обязан быть телефон!");
                return false;
            }

            String[] phone = new String[countOfPhones]; //Создаем массив строк (телефонов)
            System.out.println("Вводите через Enter номера телефонов (Ввели номер, а после нажали Enter и так далее)");
            scanner.nextLine();
            for (int i = 0; i < phone.length; i++) //Цикл по количеству телефонов в массиве
            {
                phone[i] = scanner.nextLine();
            }
            int id = personList.size()+1;
            personList.add(new Person(id, name, post, address, phone));
            System.out.println("\n");
            return true;
        } catch (Exception exception) //Если человек в количестве телефонов введет букву
        {
            System.out.println("Введите число!");
            return false;
        }
    }
}
