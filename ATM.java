import java.util.Random;
import java.util.Scanner;
public class ATM{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        
        System.out.println("What is your name?");
        String uName = scan.nextLine();

        int id = rand.nextInt(100000);

        System.out.println("What is your credit card balance?");
        int uCreditCardBal = Integer.parseInt(scan.nextLine());

        System.out.println("What is your saving account balance?");
        double uSaveAccountBalance = Double.parseDouble(scan.nextLine());

        System.out.println("What is your checking account balance?");
        double uCheckAccountBalance = Double.parseDouble(scan.nextLine());

        System.out.println("What is your credit card APR?");
        double uAPR = Double.parseDouble(scan.nextLine());

        System.out.println("What is your overdraft fee?");
        double uOverFee = Double.parseDouble(scan.nextLine());

        System.out.println("What is your saving intrest rate?");
        double uSavingIntrestRate = Double.parseDouble(scan.nextLine());

        Credit creditCard = new Credit(uAPR, id, uCreditCardBal);

        Checking checkingAccount = new Checking(uName, uCheckAccountBalance, uOverFee, id);
   
        Savings savingAccount = new Savings(uName, uSaveAccountBalance, uSavingIntrestRate, id);

        boolean run = true;
        while(run){
            System.out.println("1. Credit Card" + '\n' + "2. Checking" + '\n' + "3. Savings" + '\n' +"4. Exit");
            int uInput = Integer.parseInt(scan.nextLine());
            if(uInput == 1){
                while(uInput != 5){
                    System.out.println("1. Check Balance" + '\n' + "2. Make Purchase" + '\n' + "3. Display Monthly Statement" + '\n' + "4. Create Monthly Payments" + '\n' + "5. Back to Main Menu");
                    uInput = Integer.parseInt(scan.nextLine());
                    if(uInput == 1){
                        System.out.println("Account value: " + creditCard.getAccValue());
                    }else if(uInput == 2){
                        System.out.println("How much is the purchase?");
                        double uPurchase = Double.parseDouble(scan.nextLine());
                        creditCard.makePurchase(uPurchase);
                        System.out.println("Account value: " + creditCard.getAccValue());

                    }else if(uInput == 3){
                        System.out.println(creditCard.monthlyStatment());
                    }else if(uInput == 4){
                        System.out.println("How many months do you want to pay off in?");
                        int uMonth = Integer.parseInt(scan.nextLine());
                        System.out.println("It will take $" + (Math.ceil(creditCard.monthsToPayOff(uMonth) * 100)/100)+ " a month to pay off.");
                    }
                }
                
            }else if(uInput == 2){
                while(uInput != 4){
                    System.out.println("1. Check Balance" + '\n' + "2. Withdraw" + '\n' + "3. Deposit" + '\n' + "4. Back to Main Menu");
                    uInput = Integer.parseInt(scan.nextLine());
                    if(uInput == 1){
                        System.out.println("Account value: " + checkingAccount.getAccValue());
                    }else if(uInput == 2){
                        System.out.println("How much do you want to withdraw?");
                        int uWithdraw = Integer.parseInt(scan.nextLine());
                        checkingAccount.withdraw(uWithdraw);
                        System.out.println("Account value: " + checkingAccount.getAccValue());

                    }else if(uInput == 3){
                        System.out.println("How much do you want to deposit?");
                        int uDeposit = Integer.parseInt(scan.nextLine());
                        checkingAccount.deposit(uDeposit);
                        System.out.println("Account value: " + checkingAccount.getAccValue());

                    }
                }
            }else if(uInput == 3){
                while(uInput != 5){
                    System.out.println("1. Check Balance" + '\n' + "2. Withdraw" + '\n' + "3. Deposit" + '\n' + "4. Display Months until Goal" + '\n' + "5. Back to Main Menu");
                    uInput = Integer.parseInt(scan.nextLine());

                    if(uInput == 1){
                        System.out.println("Account value: " + savingAccount.getAccValue());

                    }else if(uInput == 2){
                        System.out.println("How much do you want to withdraw?");
                        int uWithdraw = Integer.parseInt(scan.nextLine());
                        System.out.println(savingAccount.withdrawSaving(uWithdraw));
                        System.out.println("Account value: " + savingAccount.getAccValue());
                    }else if(uInput == 3){
                        System.out.println("How much do you want to deposit?");
                        int uDeposit = Integer.parseInt(scan.nextLine());
                        savingAccount.deposit(uDeposit);
                        System.out.println("Account value: " + savingAccount.getAccValue());
                    }else if(uInput == 4){
                        System.out.println("What is your goal?");
                        double uGoal = Double.parseDouble(scan.nextLine());
                        System.out.println( (Math.ceil(savingAccount.amountInAccount(uGoal) * 100) / 100) + " months");
                    }
                }

            }
        }

    }

}
class Account {
    private int id;
    private double accValue;
    
    public Account(int idValue){ 
        id = idValue;
        accValue = 0;
    }
    public Account(double value, int idValue){
        id = idValue;
        accValue = value;
    }

    public int getId(){
        return id;
    }

    public double getAccValue(){
        return accValue;
    }

    public void deposit(double value){
        accValue += value;
    }

    public void withdraw(double value){
        accValue -= value;
    }

    @Override
    public String toString(){ 
        return "Account Id: " + id + ", Account Value: " + accValue;
    }
}

class Bank extends Account{
    private String accHolderName;

    public Bank(int idValue) {
        super(idValue);
        accHolderName = "John Doe";
    }

    public Bank(String name, double value, int idValue){
        super(value, idValue);
        accHolderName = name;
    }
    public String getHolderName(){
        return accHolderName;
    }
    public String toString(){
        return "Account Holder Name: " + accHolderName + super.toString();
    }

}

class Credit extends Account{
    private double apr;
    public Credit(int idValue){
        super(idValue);
        apr = 15.3;
    }
    public Credit(double AnnualPR, int idValue, double creditValue){
        super(creditValue, idValue);
        apr = AnnualPR;
    }

    public double getAPR(){
        return apr;
    }
    public void setAPR(double AnnualPR){
        apr = AnnualPR;
    }
    public void makePurchase(double purchase){
        deposit(purchase);
    }
    public String monthlyStatment(){
        return "Balance: " + getAccValue() + " Non Payment Balance: " + (getAccValue() + (getAccValue() * (apr/12)));
    }
    public double monthsToPayOff(int months){
        double monthlyRate = apr/12;
        return getAccValue() * ((monthlyRate * Math.pow(1 + monthlyRate, months))/(Math.pow(1 + monthlyRate, months) -1));
    }
    public String toString(){
        return super.toString() + ", Apr: " + apr;
    }

}

class Checking extends Bank{
    private double overDraftFee;

    public Checking(int idValue){
        super(idValue);
        overDraftFee = 35;
    }
    public Checking(String name, double value, double fee, int idValue){
        super(name, value, idValue);
        overDraftFee = fee;
    }
    public void withdraw(double withdraw){
        if(getAccValue() >= withdraw){
            super.withdraw(withdraw);
        }else{
            super.withdraw(withdraw + overDraftFee);
        }
    }
    public double getOverdraftFee(){
        return overDraftFee;
    }
    public void setOverdraftFee(double fee){
        overDraftFee = fee;
    }
    public String toString(){
        return super.toString() + ", OverdraftFee: " + overDraftFee;
    }
}

class Savings extends Bank{
    private double interestRate;
    
    public Savings(int idValue){
        super(idValue);
        interestRate = 1;
    }
    public Savings(String name, double value, double rate, int idValue){
        super(name, value, idValue);
        interestRate = rate;
    }
    public double getInterestRate(){
        return interestRate;
    }
    public void setInterestRate(double rate){
        interestRate = rate;
    }

    public double amountInAccount(double value){
        return (
        (Math.log(value/getAccValue()))
        /
        (12 * Math.log(1 + (interestRate/12)))
        ); // formula acting weird
    }
    public String withdrawSaving(double value){
        if(getAccValue() >= value){
            super.withdraw(value);
            return "Completed withdraw";
        }
        else{
            return "Account balance too low.";
        }
    }
}

