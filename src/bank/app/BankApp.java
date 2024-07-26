package bank.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankApp {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            System.out.println("---------------------------------------------");
            System.out.println("1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.종료");
            System.out.println("---------------------------------------------");
            System.out.print("선택> ");

            String selectNo = scanner.nextLine();
            
            if (selectNo.equals("1")) {
                createAccount();
            } else if (selectNo.equals("2")) {
                accountList();
            } else if (selectNo.equals("3")) {
                deposit();
            } else if (selectNo.equals("4")) {
                withdraw();
            } else if (selectNo.equals("5")) {
                run = false;
            }
        }
        System.out.println("프로그램 종료");
    }

    private static void createAccount() {
        System.out.println("----------");
        System.out.println("계좌생성");
        System.out.println("----------");
        System.out.print("계좌번호: ");
        String ano = scanner.nextLine();
        System.out.print("계좌주: ");
        String owner = scanner.nextLine();
        System.out.print("초기입금액: ");
        int balance = 0;
        try {
            balance = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다. 초기입금액은 숫자여야 합니다.");
            return;
        }

        Account newAccount = new Account(ano, owner, balance);
        accounts.add(newAccount);
        System.out.println("결과: 계좌가 생성되었습니다.");
    }

    private static void accountList() {
        System.out.println("----------");
        System.out.println("계좌목록");
        System.out.println("----------");
        for (Account account : accounts) {
            System.out.println("계좌번호: " + account.getAno() + ", 계좌주: " + account.getOwner() + ", 잔고: " + account.getBalance());
        }
    }

    private static void deposit() {
        System.out.println("----------");
        System.out.println("예금");
        System.out.println("----------");
        System.out.print("계좌번호: ");
        String ano = scanner.nextLine();
        System.out.print("예금액: ");
        int amount = 0;
        try {
            amount = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다. 예금액은 숫자여야 합니다.");
            return;
        }

        Account account = findAccount(ano);
        if (account == null) {
            System.out.println("결과: 계좌가 없습니다.");
            return;
        }
        account.setBalance(account.getBalance() + amount);
        System.out.println("결과: 예금이 성공되었습니다.");
    }

    private static void withdraw() {
        System.out.println("----------");
        System.out.println("출금");
        System.out.println("----------");
        System.out.print("계좌번호: ");
        String ano = scanner.nextLine();
        System.out.print("출금액: ");
        int amount = 0;
        try {
            amount = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다. 출금액은 숫자여야 합니다.");
            return;
        }

        Account account = findAccount(ano);
        if (account == null) {
            System.out.println("결과: 계좌가 없습니다.");
            return;
        }
        if (account.getBalance() < amount) {
            System.out.println("결과: 잔고가 부족합니다.");
            return;
        }
        account.setBalance(account.getBalance() - amount);
        System.out.println("결과: 출금이 성공되었습니다.");
    }

    private static Account findAccount(String ano) {
        for (Account account : accounts) {
            if (account.getAno().equals(ano)) {
                return account;
            }
        }
        return null;
    }
}
