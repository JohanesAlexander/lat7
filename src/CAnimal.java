public class CAnimal {
    public static int jmlTotalSusuSapi = 0;
    protected int x,y;
    protected String jenis="EMPTY";
    protected int umur;

    public CAnimal(){
        x=0;
        y=0;
        umur=0;
        jenis="EMPTY";
    }

    public CAnimal(int ix, int iy, String iJns, int iUmur){
        x=ix;
        y=iy;
        jenis=iJns;
        umur=iUmur;
    }

    public static int getJmlTotalSusuSapi() {
        return jmlTotalSusuSapi;
    }

    public static void setJmlTotalSusuSapi(int jmlTotalSusuSapi) {
        CAnimal.jmlTotalSusuSapi = jmlTotalSusuSapi;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    @Override
    public String toString() {
        return  jenis.substring(0,1);
    }
}
