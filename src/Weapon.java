import java.util.Random;

public class Weapon extends Equipment {
    public enum WeaponType{
        SWORD,
        AXE,
        FLAIL,
        BOW
    }
    private WeaponType type;
    private int damage;

    public Weapon(String name, WeaponType type){
        super(name);
        this.type = type;
        this.weight = calculateWeight();

        Random rand = new Random();
        this.damage = rand.nextInt(24) + 1;
    }

    public int getStat(){
        return this.damage;
    }

    @Override
    public String getType(){
        switch(this.type){
            case SWORD:
                return "Sword";
            case AXE:
                return "Axe";
            case FLAIL: 
                return "Flail";
            case BOW: 
                return "Bow";
            default:
                return "";
        }
    }

    @Override
    protected int calculateWeight(){
        switch(this.type){
            case SWORD:
                return 10;
            case AXE:
                return 15;
            case FLAIL: 
                return 8;
            case BOW: 
                return 5;
            default:
                return 0;
        }
    }
}
