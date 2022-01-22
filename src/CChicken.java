public class CChicken extends CAnimal{
    private float weight;

    public CChicken(int ix, int iy, String iJns, float iWeight){
        super(ix,iy,iJns,0);
        weight=iWeight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

}
