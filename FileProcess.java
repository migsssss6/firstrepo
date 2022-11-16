import java.io.*;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

/*GBP -> USD  = 1.654
        CHF -> USD  = 1.10
        EUR -> USD  = 1.35

        1usd = 1/1.35eur = 0.74eur
        1.10usd = 1.10*1/1.35 = 0.81eur
        1.654usd 1.654*1/1.35 = 1.22eur*/
class Bank{
    int companyCode;
    int account;

    @Override
    public String toString() {
        return "Bank{" +
                "companyCode=" + companyCode +
                ", account=" + account +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", creditRating='" + creditRating + '\'' +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                '}';
    }

    String city;
    String country;

    public Bank(int companyCode, int account, String city, String country, String creditRating, String currency, double amount) {
        this.companyCode = companyCode;
        this.account = account;
        this.city = city;
        this.country = country;
        this.creditRating = creditRating;
        this.currency = currency;
        this.amount = amount;
    }

    public int getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(int companyCode) {
        this.companyCode = companyCode;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    String creditRating;
    String currency;
    double amount;
}
public class FileProcess {

    public static void main(String[] args) {
        List<Bank> bankList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        String[] val = {};
        try {
            InputStream input = new FileInputStream("FILE.DAT");
            BufferedReader r = new BufferedReader(
                    new InputStreamReader(input));

            while(r.ready()){
                String line = r.readLine();
                String removeSpaceString = line.trim().replaceAll("\\s+"," ");
                 stringList.add(removeSpaceString);

            }


        }catch(Exception e){

        }
        stringList.remove(0);
        System.out.println(stringList);

        for(int i=0; i<stringList.size();i++){
            String temp = stringList.get(i);

            String sp[] = temp.split(" ");

            if(sp.length<7) {
                bankList.add(new Bank(Integer.parseInt(sp[0]),Integer.parseInt(sp[1]),"",sp[2],sp[3],sp[4],Double.parseDouble(sp[5])));

            }else if(sp.length<8){
                bankList.add(new Bank(Integer.parseInt(sp[0]),Integer.parseInt(sp[1]),sp[2],sp[3],sp[4],sp[5],Double.parseDouble(sp[6])));

            }else{
                bankList.add(new Bank(Integer.parseInt(sp[0]),Integer.parseInt(sp[1]),sp[2]+sp[3],sp[4],sp[5],sp[6],Double.parseDouble(sp[7])));
            }

        }
        System.out.println(bankList);
        List<Bank> res = new ArrayList<>();

        for(int i=0;i<bankList.size();i++){
            Bank b = bankList.get(i);
            System.out.println(b.getCurrency());
            if(b.getCurrency().equals("CHF")){

                res.add(new Bank(b.getCompanyCode(),b.getAccount(),b.getCity(),b.getCountry(),b.getCreditRating(),b.getCurrency(),b.getAmount()*.81));
            }
            else if(b.getCurrency().equals("GBP")){
                res.add(new Bank(b.getCompanyCode(),b.getAccount(),b.getCity(),b.getCountry(),b.getCreditRating(),b.getCurrency(),b.getAmount()*1.22));
            }else if(b.getCurrency().equals("USD")){
                res.add(new Bank(b.getCompanyCode(),b.getAccount(),b.getCity(),b.getCountry(),b.getCreditRating(),b.getCurrency(),b.getAmount()*0.74));
            }
            else{
                res.add(new Bank(b.getCompanyCode(),b.getAccount(),b.getCity(),b.getCountry(),b.getCreditRating(),b.getCurrency(),b.getAmount()));
            }
        }
        System.out.println(res);
        //For London two values will be considered wherein UK is not present i.e 789463.4CHF,456.85014GBP convert to EUR to get 639465.354EUR,557.357EUR take average get 320011.35
        Map<String,Double> mp = res.stream().collect(Collectors.groupingBy(Bank::getCountry,Collectors.averagingDouble(Bank::getAmount)));
        System.out.println(mp);

        }

    }











