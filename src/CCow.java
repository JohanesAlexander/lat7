public class CCow extends CAnimal{
    private int collectMilkDay;
    public CCow(int ix, int iy, String iJns, int iUmr) {
        super(ix, iy, iJns, iUmr);
        collectMilkDay=3;
    }

    public int getCollectMilkDay() {
        return collectMilkDay;
    }

    public void setCollectMilkDay(int collectMilkDay) {
        this.collectMilkDay = collectMilkDay;
    }
}


