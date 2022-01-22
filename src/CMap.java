import java.util.Scanner;

public class CMap {


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";


    Scanner scanInt = new Scanner(System.in);

    private int size =5;
    public CAnimal[][] map;

    CFarmer myFarmer = new CFarmer();


    public CMap(int size) {
        this.size=size;
        map = new CAnimal[size][size];
    }

    public void initializeEmptyMap(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                CAnimal myAnimal = new CAnimal();
                map[i][j] = myAnimal;
            }
        }
    }


    public void printMap(){
        for(int i=0;i<size;i++){
            //System.out.println("");
            for(int j=0;j<size;j++){
                if(map[i][j].getJenis().equalsIgnoreCase("EMPTY")==false){
                    System.out.print("| "+ANSI_BLUE + map[i][j] + ANSI_RESET + " ");
                }
                else{
                    System.out.print("| "+ map[i][j] + " ");
                }

            }
            System.out.println("|");
        }
    }

    public void printColorMap(int index){

        int x=index/10;
        int y=index%10;
        for(int i=0;i<size;i++){
            //System.out.println("");
            for(int j=0;j<size;j++){
                if (i==x && j==y){
                    System.out.print("| "+ANSI_RED + map[i][j] +ANSI_RESET+ " ");
                }
                else{
                    System.out.print("| "+ map[i][j] + " ");
                }

            }
            System.out.println("|");
        }


    }

    public void printAnimal(){
        int sapi=0;
        int ayam=0;
        if(map.length==0){
            System.out.println("there is no animal added yet!");
        }
        else{
            try{
                for (int i = 0; i < size; i++) {
                    for(int j=0;j<size;j++){
                        if(map[i][j] instanceof CCow){
                            System.out.println((i+1) + ".Sapi umur " + map[i][j].getUmur());
                            sapi++;
                        }
                        else if(map[i][j] instanceof CChicken){
                            System.out.println((i+1) + ".Ayam umur " + map[i][j].getUmur() + "berat: "+ ((CChicken) map[i][j]).getWeight()+"\n");
                            ayam++;
                        }
                    }//inner for

                }//end outer for
                if(ayam ==0 && sapi==0){
                    System.out.println("peternakan kosong.....");
                }
            }//end try
            catch (Exception e){
                System.out.println("something went wrong in print animal");
            }

        }

    }



    public void addAnimalMenu(){
        int resp=-1;

        while(resp!=3){
            System.out.println(
                    "1.Sapi\n" +
                    "2.Ayam\n" +
                    "3.Cancel");

            resp = scanInt.nextInt();
            if (resp ==1){
                addCow();
                break;
            }
            else if(resp==2){
                addChicken();
                break;
            }
        }
    }

    public void addCow(){
        int successAdd=0;
        int x;
        int y;
        int twoDigit;
        while(successAdd!=1){
            System.out.println("this is addCow function");
            System.out.println("enter x  and y value for map: \n" +
                    "enter it as 2 digit for example 13 mean x=1 and y=3\n" +
                    "below are the map for available space\n");
            printMap();
            twoDigit = scanInt.nextInt();
            System.out.println(twoDigit);
            x=twoDigit/10;
            y=twoDigit%10;


            if (map[x][y].getJenis().equalsIgnoreCase("EMPTY")){
                System.out.println("enter age for your cow: ");
                int age = scanInt.nextInt();
                CCow mySapi = new CCow(x,y,"sapi",age);
                map[x][y] = mySapi;

                printColorMap(twoDigit);
                System.out.println("cow successfully added");
                successAdd=1;

                break;

            }
            else{
                System.out.println("This place is already occupied by another animal");
            }
        }
    }


    public void addChicken(){
        int successAdd=0;
        int x;
        int y;
        int twoDigit;
        while(successAdd!=1){
            System.out.println("this is addChicken function");
            System.out.println("enter x  and y value for map: \n" +
                    "enter it as 2 digit for example 13 mean x=1 and y=3\n" +
                    "below are the map for available space\n");
            printMap();
            twoDigit = scanInt.nextInt();
            x=twoDigit/10;
            y=twoDigit-(x*10);
            if (map[x][y].getJenis().equalsIgnoreCase("EMPTY")){
                System.out.println("enter age for your chicken: ");
                int weight = scanInt.nextInt();
                CChicken myCow = new CChicken(x,y,"ayam",weight);
                map[x][y] = myCow;
                printColorMap(twoDigit);
                System.out.println("chicken successfully added");
                successAdd=1;

            }
            else{
                System.out.println("This place is already occupied by another animal");
            }
        }
    }


    public void changeDays(){
        int jmlSapi=0;

        System.out.println("adding 3 days to all of our animals");

        for (int i = 0; i < size ; i++) {

            for(int j=0;j<size;j++){

                map[i][j].setUmur(map[i][j].getUmur()+3);

                if (map[i][j] instanceof CCow){
                    jmlSapi++;
                }//if

            }//inner

        }//outer

        addWeight();
        addMilk(jmlSapi);

    }

    public void addMilk(int jmlSapi){
        if(jmlSapi>=1){
            CAnimal.setJmlTotalSusuSapi(CAnimal.getJmlTotalSusuSapi()+jmlSapi * 2);
        }

    }

    public void addWeight(){
        for (int i = 0; i < size; i++) {
            for(int j=0;j < size; j++){
                if(map[i][j] instanceof CChicken){
                    float weight = ((CChicken) map[i][j]).getWeight();
                    ((CChicken) map[i][j]).setWeight((float) (weight+5));
                }
            }
        }
    }

    public int checkMilk(){
        int jmlMilk=-1;
        jmlMilk = CAnimal.getJmlTotalSusuSapi();
        return jmlMilk;
    }

    public int checkReadyChicken(){
        int jmlChicken=0;
        for (int i = 0; i < size; i++) {
            for(int j=0;j<size;j++){
                if (map[i][j] instanceof CChicken){
                    if (((CChicken) map[i][j]).getWeight() >= 5) {
                        jmlChicken++;
                    }
                }// outer if
            }


        }//end for
        return jmlChicken;
    }

    public int collectMilk(){
        int jmlMilk=0;
        jmlMilk = CAnimal.getJmlTotalSusuSapi();
        CAnimal.setJmlTotalSusuSapi(0);
        return jmlMilk;
    }

    public int collectChicken(){

        int jmlAyam=0;
        for (int i = 0; i < size; i++) {
            for (int j=0;j<size;j++){
                if (map[i][j] instanceof CChicken) {
                    if (((CChicken) map[i][j]).getWeight() >= 5) {

                        CAnimal myAnimal = new CAnimal(i,j,"EMPTY",0);

                        map[i][j]=myAnimal;

                        jmlAyam += 1;
                    }
                }// outer if
            }


        }//end for

        return jmlAyam;
    }//end collect chicken

    public int collectCow(){

        int jmlSapi=0;
        for (int i = 0; i < size; i++) {
            for (int j=0;j<size;j++){
                if (map[i][j] instanceof CCow) {
                    CAnimal myAnimal = new CAnimal(i,j,"EMPTY",0);

                    map[i][j]=myAnimal;

                    jmlSapi += 1;

                }// outer if
            }


        }//end for

        return jmlSapi;
    }//end collect cow

    public String collectByPosition(int xy){
        int x=xy/10;
        int y=xy%10;
        String jenis ="EMPTY";
        for (int i = 0; i < size; i++) {
            for (int j=0;j<size;j++){
                if (i==x && j==y) {
                    if(map[i][j] instanceof CCow){
                        jenis = "sapi";
                    }
                    else if(map[i][j] instanceof CChicken){
                        jenis = "ayam";
                    }
                    CAnimal myAnimal = new CAnimal(i,j,"EMPTY",0);

                    map[i][j]=myAnimal;

                }// outer if
            }
        }//end for
        return jenis;
    }



    public void sellAnimal(){
        int resp=-1;
        int jmlAyam=0;
        int jmlSapi=0;
        int money=0;
        System.out.println( "1.Sell all chicken\n" +
                            "2.Sell all cow\n" +
                            "3.Sell animal on coordinates\n" +
                            "4.Cancel");
        resp = scanInt.nextInt();
        while(resp!=4){
            if(resp==1){
                System.out.println("Only chickens with weight more than 5kg can be sold");
                jmlAyam=collectChicken();
                if (jmlAyam<=0){
                    System.out.println("We have no chicken to be sold yet!");
                }
                else{
                    money = jmlAyam*20;
                    addMoney(money);
                    System.out.println(jmlAyam+" chicken sold for: $"+money);

                }

                resp=4;
                break;
            }//end resp 1
            else if(resp==2){
                System.out.println("Selling all cows");
                jmlSapi=collectCow();
                if(jmlSapi<=0){
                    System.out.println("We have no cows to be sold!");
                }
                else{
                    money = jmlSapi*400;
                    addMoney(money);
                    System.out.println(jmlSapi+" cow sold for: $"+money);

                }



                resp=4;
                break;
            }//end resp 2

            else if(resp ==3){
                String jenis="";
                int posisi=-1;
                printMap();
                System.out.println("masukan posisi kandang");
                posisi = scanInt.nextInt();


                jenis=collectByPosition(posisi);
                if (jenis.equalsIgnoreCase("sapi")){
                    money += 400;
                }
                else if(jenis.equalsIgnoreCase("ayam")){
                    money += 20;
                }
                addMoney(money);
                System.out.println(jenis + " sold for: $ "+money );
                printMap();

                resp=4;
                break;
            }
        }
    }

    public void addMoney(int money){
        if (money>0){
            myFarmer.addMoney(money);
        }

    }



//    public int searchByARR(int xy){
//        int x=xy/10;
//        int y=xy%10;
//        int ind = -1;
//
//        for (int i=0; i< size;i++){
//            for(int j=0;j<size;j++){
//                if (map[x][y].getX() == x && map[x][y].getY()==y){
//                    ind = i;
//                    break;
//                }
//            }
//
//        }
//        return ind;
//
//    }
    public void collectMenu(){
        int ayam=0;
        int susu=0;
        ayam = checkReadyChicken();
        susu = checkMilk();
        System.out.println("Mengecek hewan ternak...");

        System.out.println();
        System.out.println("susu: "+susu);
        System.out.println("ayam: "+ayam);
        if(ayam >=1 && susu <=0){
            System.out.println("Ayam bisa dipanen: " + ayam + " ekor");
            confirmPanen(1,ayam);
        }

        else if(ayam <=0 && susu >= 3 ){

            System.out.println("Susu bisa dipanen: " + susu + "liter");
            confirmPanen(2,susu);
        }

        else if(ayam >0 && susu >=3){
            System.out.println("ayam dan susu siap dipanen");
            System.out.println("Ayam: " + ayam + " ekor");
            System.out.println("Susu: " + susu + "liter");
            confirmPanen(3, ayam, susu);
        }

        else if (ayam <= 0 && susu <= 0){
            System.out.println("Tidak ada yang bisa dipanen");
        }
        else{
            System.out.println("theres something wrong here ");
        }

    }

    public void confirmPanen(int tipePanen, int jumlahApapun){
        int resp=0;
        System.out.println("konfirmasi panen? 1 utk iya 0 utk cancel");
        resp = scanInt.nextInt();

        while (resp==1){

            if (tipePanen==1){
                collectChicken();
                System.out.println("berhasil panen ayam sebanyak: "+jumlahApapun+"ekor");
                resp=0;
                break;
            }

            else if(tipePanen==2){
                collectMilk();
                System.out.println("berhasil panen susu sebanyak: "+jumlahApapun+"L");
                resp=0;
                break;
            }
        }
    }

    public void confirmPanen(int tipePanen, int jumlahAyam, int jumlahSusu){
        int resp=0;
        System.out.println("konfirmasi panen? 1 utk iya 0 utk cancel");
        resp = scanInt.nextInt();

        while (resp==1){
            if (tipePanen==1){
                collectMilk();
                System.out.println("berhasil panen ayam: " +jumlahAyam +" susu: "+ jumlahSusu);
            }
        }
    }

    public int checkKandangKosong(){
        int lokasi=0;

        for (int y=0;y<size;y++){
            for (int x=0;x<size;x++){
                if (map[y][x]==null){
                    lokasi = (x*10)+y;
                }
            }
        }

        return lokasi;
    }

}
