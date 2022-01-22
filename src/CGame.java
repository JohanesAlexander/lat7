import java.util.Scanner;
import java.util.Arrays;

public class CGame {

    final private int lvlSize=3;
    private int lvl=1;
    private int mapSize;

    private CMap [] myArr = {null,null,null};

    public CMap [] getMyArr(){
        return myArr;
    }

    public int getLvl(){
        return lvl;
    }

    public void setLvl(int lvl){
        this.lvl=lvl;
    }

    public int getMapSize() {
        return mapSize;
    }
    public void setMapSize(int mapSize){
        this.mapSize=mapSize;
    }

    Scanner scanInt = new Scanner(System.in);
    Scanner scanStr = new Scanner(System.in);








    public int mainMenu(){
        int resp=-1;

        System.out.println("0.exit\n" +
                "1.Input hewan\n" +
                "2.Cetak hewan\n" +
                "3.Cetak map\n" +
                "4.Ganti hari di peternakan (seluruh hewan)\n" +
                "5.Cek/panen hewan ternak\n" +
                "6. Jual hewan ternak\n" +
                "");
        resp = scanInt.nextInt();
        return resp;

    }



    public void createObj(){

        if(lvl==1){
            setMapSize(3);
            CMap myMap = new CMap(getMapSize());
            myArr[lvl-1]=myMap;

        }
        else if(lvl==2){
            setMapSize(5);
            CMap myMap = new CMap(getMapSize());
            myArr[lvl-1]=myMap;
        }
        else if(lvl==3){
            setMapSize(7);
            CMap myMap = new CMap(getMapSize());
            myArr[lvl-1]=myMap;
        }
    }

    public CGame() {

        CFarmer orang = new CFarmer();


        int menu = -1;


        while (menu!=0){

            if(myArr[lvl-1]==null){
                createObj();
            }

            if (myArr[lvl - 1].map == null) {
                myArr[lvl-1].initializeEmptyMap();
            }


            menu = -1;
            menu = mainMenu();
            if(menu==0){
                System.exit(0);
            }

            else if (menu==1){
                //printMap();
                myArr[lvl-1].addAnimalMenu();
            }
            else if(menu==2){
                myArr[lvl-1].printAnimal();
            }
            else if(menu ==3){
                myArr[lvl-1].printMap();
            }
            else if(menu==4){
                myArr[lvl-1].changeDays();
            }
            else if(menu==5){
                myArr[lvl-1].collectMenu();
            }
            else if(menu==6){
                myArr[lvl-1].sellAnimal();
            }

        }
    }

}
