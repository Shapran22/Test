import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) throws Exceptions {
        //Сканируем данные с консони.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input:");
        String line = scanner.nextLine();
        scanner.close();

        //В качестве маркера для разбиения входных данных на отдельные части выступает "пробел".
        String[] dataString = line.split(" ");

        //Сразу проверку делаем на то, правильное ли количество данных мы ввели. Если нет,
        //то выскакивает исключение.
        if (dataString.length < 4){
            throw new Exceptions("\nOutput:\nВы ввели недостаточно данных.");
        } else if (dataString.length > 4) {
            throw new Exceptions("\nOutput:\nВы ввели избыточное число данных.");
        }

        //Поскольку у нас всё равно пока ещё строковые данные, хоть и разбитые на отдельные элементы, нам надо
        //теперь проверить являются ли первые три элемента одномерного массива числами, а последний элемент
        //– строкой. В противном случае выкидывается исключение о том, что либо вместо чисел ввели буквы,
        // либо наоборот.
        double[] numbers = new double[dataString.length - 1];
        String typePerson = "";
        int counter = 0;

        for (String number : dataString) {
            if (counter < dataString.length - 1) {
                IsNumber checkNumber = new IsNumber();
                boolean CheckNumber = checkNumber.isNumber(number);
                if (CheckNumber){
                    numbers[counter++] = Double.parseDouble(number);
                }else {
                    throw new Exceptions("\nOutput:\nЧисловые данные некорректны.");
                }
            } else {
                IsString checkString = new IsString();
                boolean CheckString = checkString.isString(dataString[counter]);
                if (CheckString){
                    typePerson = dataString[counter];
                    typePerson = typePerson.toUpperCase();
                } else {
                    throw new Exceptions("\nOutput:\nВы ввели вместо букв числа.");
                }
            }
        }

        //Теперь делаем проверку на случай, если тип клиента не соответствует клиентам из ТЗ, а также на случай,
        //если человек не сможет погасить кредит с такими процентами и ежемесячным платежом.
        if (!typePerson.equals(Person.HUMAN.toString()) && !typePerson.equals(Person.BUSINESS.toString())){
            throw new Exceptions("\nOutput:\nВы не являетесь типом клиента, которого мы обслуживаем.");
        } else if (numbers[1]*12 < numbers[0]*numbers[2]*0.01) {
            throw new Exceptions("\nOutput:\nВы не сможете погасить кредит с такими выплатами.");
        }

        // Эта штука нужна, потому что при некоторых параметрах задачи выскакивает результат с большим числом знаков
        //после запятой. Этой штукой я округляю до первого знака после запятой, как в примерах из ТЗ.
        DecimalFormat dF = new DecimalFormat( "#.#\n" );

        //Если все вышеперечисленные условия выполнены,
        //то производятся вычисления для одного из типов клиентов банка.
        if (typePerson.equals(Person.HUMAN.toString())) {

            Human human1 = new Human();

            human1.setBankAccount(numbers[0]);
            double ba = human1.getBankAccount();

            human1.setPaymentPerYear(numbers[1]);
            double ppy = human1.getPaymentPerYear();

            human1.setPaymentPercentage(numbers[2]);
            double pp = human1.getPaymentPercentage();

            double overpayment = human1.Overpayment(ba, ppy, pp);
            double count = ba + overpayment;

            System.out.print("Output:\n"+dF.format(overpayment));
            System.out.println("//общая сумма к оплате: " + dF.format(count));

        } else if (typePerson.equals(Person.BUSINESS.toString())) {

            Business busunes1 = new Business();

            busunes1.setBankAccount(numbers[0]);
            double ba = busunes1.getBankAccount();

            busunes1.setPaymentPerYear(numbers[1]);
            double ppy = busunes1.getPaymentPerYear();

            busunes1.setPaymentPercentage(numbers[2]);
            double pp = busunes1.getPaymentPercentage();

            double overpayment = Business.Overpayment(ba, ppy, pp);
            double count = ba + overpayment;

            System.out.print("Output:\n"+dF.format(overpayment));
            System.out.println("//общая сумма к оплате: " + dF.format(count));
        }
    }
}