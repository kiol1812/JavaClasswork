/* 2024/03/28 楊育哲 41143264
 * 實作帳號創建 - 密碼 & 用戶名檢測(使用例外處理)
 */

import java.util.Scanner;

public class h10328 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Acount user = new Acount();
        String userName="", userPassword="";
        do{
            System.out.println("input user name and password");
            userName = sc.nextLine();
            userPassword = sc.nextLine();
        }while(!user.create(userName, userPassword));
    }
}

class NameException extends Exception {
    private int number;
    public NameException(int n){
        this.number = n;
    }
    public String getMessage(){
        if(number==1) return "name's length > 10";
        return "first character isn't use english";
    }
}
class PasswordException extends Exception {
    private int number;
    public PasswordException(int n){
        this.number = n;
    }
    public String getMessage(){
        if(number==1) return "password's length < 8";
        else if(number==2) return "haven't used specially character";
        else if(number==3) return "haven't used upper character";
        else if(number==4) return "haven't used lower character";
        return "other error";
    }
}

class Acount extends Exception{
    private String name;
    private String password;
    public Acount(){}
    public Acount(String n, String p){
        this.name = n;
        this.password = p;
    }
    public boolean create(String n, String p){
        this.name = n;
        this.password = p;
        if(!checkName() || !checkPassword()){
            System.out.println("please choose new user name or password.");
            return false;
        }
        return true;
    }
    public boolean checkName(){
        // 英文字母開頭
        // 不得超過十個字
        try{
            if(name.length()>10) throw new NameException(1);
            else if(!(name.charAt(0)>=65&&name.charAt(0)<=90||name.charAt(0)>=97&&name.charAt(0)<=122))
                throw new NameException(2); // first character isn't use english
        }catch(NameException ex){
            System.out.println(ex.getMessage());
            return false;
        }finally{
            System.out.println("feasible name for use");
        }
        return true;
    }
    public boolean checkPassword(){
        // 至少包含一特殊字元 #@!_$%^&
        // 長度大於八
        // 至少包含一大寫字母及一小寫字母
        char scArray[] = {'#', '@', '!', '_', '$', '%', '^', '&'};
        try{
            if(password.length()<8) throw new PasswordException(1);
            boolean specialCharacter=false, upper=false, lower=false;
            for(int i=0; i<password.length(); i++){
                if(!lower && password.charAt(i)>60&&password.charAt(i)<123) lower=true;
                if(!upper && password.charAt(i)>64&&password.charAt(i)<91) upper=true;
                if(!specialCharacter){
                    for(int j=0; j<scArray.length; j++){
                        if(scArray[j]==password.charAt(i)) specialCharacter=true;
                    }
                }
            }
            if(!specialCharacter) throw new PasswordException(2);
            if(!upper) throw new PasswordException(3);
            if(!lower) throw new PasswordException(4);
        }catch(PasswordException ex){
            System.out.println(ex.getMessage());
            return false;
        }finally{
            System.out.println("feasible password for use");
        }
        return true;
    }
}