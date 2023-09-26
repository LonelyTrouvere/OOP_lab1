import java.util.Random;

public class Armor extends Equipment {
    public enum ArmorType{
        HELMET,
        BODY,
        GAUNTLETS,
        LEGGINGS
    }
    private ArmorType type;
    private int protection;

    public Armor(String name, ArmorType type){
        super(name);
        this.type = type;
        this.weight = calculateWeight();

        Random rand = new Random();
        this.protection = rand.nextInt(9) + 1;
    }

    public int getStat(){
        return this.protection;
    }

    @Override
    public String getType(){
        switch(this.type){
            case HELMET:
                return "Helmet";
            case BODY:
                return "Body";
            case GAUNTLETS: 
                return "Gauntlets";
            case LEGGINGS: 
                return "Leggings";
            default:
                return "";
        }
    }

    @Override
    protected int calculateWeight(){
        switch(this.type){
            case HELMET:
                return 6;
            case BODY:
                return 18;
            case GAUNTLETS: 
                return 3;
            case LEGGINGS: 
                return 5;
            default:
                return 0;
        }
    }
}
