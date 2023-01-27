import java.util.*;
public class Main
{
    ArrayList<Person> personList= new ArrayList<Person>(); //Список персон, сюда сохраняются все люди
    Scanner scanner = new Scanner(System.in); // Инициализируем сканер для получения текста

    public static void main(String[] args)
    {
        System.out.println("Привет, друг! Ты в своей электронной книжке!");
        boolean loop = true; //Переменная, чтобы закольцевать цикл работы программы
        Main main = new Main(); // Для обращения к сканеру

        //Несколько пробных персон
        main.personList.add(new Person("Bob", "Manager", "Soldier Korzuna home 1", new String[]{"+79218811688"}));
        main.personList.add(new Person("Oleg", "Gamer", "Home", new String[]{"+79288836688", "+79221855677"}));

       while(loop) // Запускаем бесконечный цикл работы программы
        {
            System.out.println("Вот список твоих контактов: ");
            for(int i = 0; i < main.personList.size(); i++) //Выводим информацию о существующих контактах
            {
                System.out.println(main.personList.get(i)._name + "\t" + main.personList.get(i)._post +
                        "\t" + main.personList.get(i)._address + "\t" + main.personList.get(i)._phoneNumbers[0]);
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
            switch (answer) // Сравниваем ответ
            {
                case "1": //Создание новой персоны
                    boolean resultOfCreate = main.createPerson();
                    if(resultOfCreate)
                        System.out.println("Контакт успешно создан!\n");
                    else
                        System.out.println("Контакт не был создан!\n");
                    break;
                case "2": //Редактирование имеющийся персоны
                    System.out.println("Введите имя контакта, который вы хотите редактировать! (Точь в точь)");
                    boolean resultOfRedact = main.redactPerson(main.scanner.nextLine());
                    if (resultOfRedact)
                        System.out.println("Контакт успешно отредактирован!\n");
                    else
                        System.out.println("Контакт не был отредактирован!\n");
                    break;
                case "3": //Поиск персоны
                    System.out.print("Введите имя контакта, или первые буквы его имени\n->");
                    main.searchPerson(main.scanner.nextLine());
                    break;
                case "4": //Узнать подробнее о персоне
                    System.out.println("Введите имя контакта, о котором вы хотите узнать информацию! (Точь в точь)");
                    boolean resultOfMoreInf = main.moreInformation(main.scanner.nextLine());
                    if(resultOfMoreInf)
                        System.out.println("Выведена вся информация!\n");
                    else
                        System.out.println("Ошибочка! Такого человечка не существует!\n");
                    break;
                case "5": //ВЫход из программы
                    System.out.println("Удачи, уважаемый пользователь!");
                    return;
                default: //Выводится, если введено что-то другое
                    System.out.print("Такое действие мною не предусмотрено(((\n\n");
            }
        }
    }

    public boolean createPerson() //Создание человека
    {
        System.out.print("Какое имя у человека?\n->");
        String name = scanner.nextLine();
        System.out.print("Какой пост у человека?\n->");
        String post = scanner.nextLine();
        System.out.print("Какой адрес у человека?\n->");
        String address = scanner.nextLine();

        try
        {
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
            for(int i = 0; i < phone.length; i++) //Цикл по количеству телефонов в массиве
            {
                phone[i] = scanner.nextLine();
            }
            personList.add(new Person(name, post, address, phone));
            System.out.println("\n");
            return true;
        }
        catch (Exception exception) //Если человек в количестве телефонов введет букву
        {
            System.out.println("Введите число!");
            return false;
        }
    }
    public boolean redactPerson(String startName) //Редактирование человека
    {
        for(int i = 0; i < personList.size(); i++) //Цикл по количеству существующих персон
        {
            var redactingPerson = personList.get(i); //Получаем персону
            if(startName.equals(redactingPerson._name)) //Сравниваем имя персоны
            {
                System.out.printf("Отредактируйте имя или запишите его заново! Текущее имя: '%s'." +
                        " Чтобы оставить текущее имя нажмите 'Enter'\n->", personList.get(i)._name);
                String name = scanner.nextLine();
                if (name.equals("") || name.equals("\n")) //Если не захочет менять имя
                    name = personList.get(i)._name;
                System.out.printf("Отредактируйте пост человека или запишите текущий! Текущий пост: '%s'. " +
                        "Чтобы оставить текущее значение нажмите 'Enter'\n->", personList.get(i)._post);
                String post = scanner.nextLine();
                if (post.equals("") || post.equals("\n")) //Если не захочет менять пост
                    post = personList.get(i)._post;
                System.out.printf("Укажите адрес проживания человека! Текущий адрес: '%s'. Чтобы оставить текущее значение нажмите 'Enter'\n->", personList.get(i)._address);
                String address = scanner.nextLine();
                if (address.equals("") || address.equals("\n")) //Если не захочет менять адресс
                    address = personList.get(i)._address;


                try
                {
                    String[] phone; //Объявляем массив телефонов
                    String countOfPhones = "";
                    System.out.printf("Сколько телефонов у человека? (Введите цифру). Текущее значение : '%s'. Чтобы оставить текущее значение нажмите 'Enter'\n-> ", personList.get(i)._phoneNumbers.length);
                    countOfPhones = scanner.nextLine();
                    if(countOfPhones.equals("") || countOfPhones.equals("\n")) //Если человек не хочет менять количество телефонов
                    {
                        phone = new String[personList.get(i)._phoneNumbers.length];
                    }
                    else if (Integer.parseInt(countOfPhones) == 0) //Если он напишет 0
                    {
                        System.out.print("У человека обязан быть телефон!");
                        return false;
                    }
                    else
                    {
                        phone = new String[Integer.parseInt(countOfPhones)];
                    }

                    System.out.println("Вводите через Enter номера телефонов (Ввели номер, а после нажали Enter и так далее). Чтобы оставить номер телефона нажмите 'Enter'. Текущие номера телефонов: ");
                    for(int j = 0; j < personList.get(i)._phoneNumbers.length; j++) //Выводим текущее номера телефонов
                        System.out.print("\t" + personList.get(i)._phoneNumbers[j]);
                    System.out.println();
                    for(int j = 0; j < phone.length; j++)
                    {
                        phone[j] = scanner.nextLine();
                        if(phone[j].equals("") || phone[j].equals("\n") && j < personList.get(i)._phoneNumbers.length) //Если хочет оставить текущий номер телефонв
                            phone[j] = personList.get(i)._phoneNumbers[j];
                    }
                    personList.remove(i); //Удаляем прошлую
                    personList.add(new Person(name, post, address, phone)); //Добавляем новую персону
                    System.out.println("\n");
                    return true;
                }
                catch (Exception exception)
                {
                    System.out.println("Введите число!" + exception.getMessage());
                    return false;
                }
            }
        }
        System.out.println("\n");
        return false;
    }
    public boolean moreInformation(String name) //Больше информации о персоне
    {

        for (int i = 0; i<personList.size(); i++) //Цикл по количеству перосон
        {
            if(personList.get(i)._name.equals(name)) //Проверка на совпадение имени
            {
                //Вывод всеё информации о персоне
                System.out.println("Имя: " + personList.get(i)._name);
                System.out.println("Пост: " + personList.get(i)._post);
                System.out.println("Адрес: " + personList.get(i)._address);
                System.out.print("Телефон\\Телефоны: ");
                for (var number:personList.get(i)._phoneNumbers)
                {
                    System.out.print("\t" + number);
                }
                System.out.println();
                return true;
            }
        }
        return false; //Если персоны нет
    }
    public void searchPerson(String name)
    {
        System.out.printf("Вот список всех контактов, начинающихся на '%s' :\n", name);
        for (int i = 0; i<personList.size(); i++) //Цикл по всем персонам
        {
            if(personList.get(i)._name.startsWith(name)) //Проверка на совпадение с именем
            {
                System.out.println(personList.get(i)._name + "\t" + personList.get(i)._post +
                        "\t" + personList.get(i)._address + "\t" + personList.get(i)._phoneNumbers[0]); //Вывод информации о персонах по критерию имени
            }
        }
        System.out.println("");
    }
}

class Person //Персона
{
    String _name, _post, _address;
    String[] _phoneNumbers;

    public Person(String name, String post, String address, String[] phoneNumbers) //Конструктор персоны
    {
        this._name = name;
        this._address = address;
        this._phoneNumbers = phoneNumbers;
        this._post = post;
    }
}