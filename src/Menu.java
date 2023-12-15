import java.util.Scanner;

public class Menu {
    private PhoneBook phoneBook;
    private Person person;
    private Scanner scanner;

    public void menuProg() throws Exception {
        phoneBook = new PhoneBook();
        person = new Person();
        scanner = new Scanner(System.in);
        String action;
        System.out.println("Добро пожаловать!\nВыберите пункт меню:");
        do {
            System.out.println("1. Записать новый контакт");
            System.out.println("2. Посмотреть существующие контакты");
            System.out.println("3. Удалить контакт");
            System.out.println("4. Сохранить в файл");
            System.out.println("5. Отсортировать контакты по убыванию фамилии");
            System.out.println("6. Отфильтровать контакты по первой букве фамилии");
            System.out.println("7. Отредактировать контакт");
            System.out.println("8. Найти контакт по букве");
            System.out.println("9. Найти контакт по с пропущенными буквами");
            System.out.println("10. Выход");
            action = scanner.nextLine();
            if (action.equals("1")) {
                person = new Person();
                phoneBook.addPerson(person);
            } else if (action.equals("2")) {
                phoneBook.printAllPerson();
            } else if (action.equals("3")) {
                phoneBook.deletePerson();
            } else if (action.equals("4")) {
                phoneBook.saveDataFile(person);
            } else if (action.equals("5")) {
                phoneBook.sortirovka();
            } else if (action.equals("6")) {
                phoneBook.filtacia();
            } else if (action.equals("7")) {
                phoneBook.redactirovanie();
            } else if (action.equals("8")) {
                phoneBook.poiskBukva();
            } else if (action.equals("9")) {
                phoneBook.poiskPropusk();
            } else if (action.equals("10")) {
                System.out.println("До свидания!");
                System.exit(0);
                break;
            }
        }
        while (true);


    }
}
