import java.util.Comparator;
import java.util.LinkedList;

public class Knight implements IBarter{
    private int maxEquipLoad;
    private int equipLoad;
    private String name;
    private int level;
    private int gold;
    private int goldBonus;
    private LinkedList<Equipment> equipment;

    public Knight(String name){
        this.name = name;
        this.maxEquipLoad = 30;
        this.equipLoad = 0;
        this.gold = 0;
        this.level = 1;
        this.goldBonus = 0;
        this.equipment = new LinkedList<Equipment>();
    }

    public int getEquipLoad(){
        return this.equipLoad;
    }

    public int getMaxEquipLoad(){
        return this.maxEquipLoad;
    }

    public int getGold(){
        return this.gold;
    }

    public int getLevel(){
        return this.level;
    }

    public String getName(){
        return this.name;
    }

    public LinkedList<Equipment> getEquipment(){
        return this.equipment;
    }

    public void doQuest(){
        if (this.level % 5 == 0)
            this.goldBonus += 10;
        if (this.level % 3 == 0)
            this.maxEquipLoad += 1;
        this.level += 1;
        this.gold += 20 + this.goldBonus;
    }

    public void sellItem(Equipment item){
        this.gold += item.getCost();
        this.equipLoad -= item.getWeight();
        this.equipment.remove(item);
    }

    public void buyItem(Equipment item){
        this.gold -= item.getCost();
        this.equipLoad += item.getWeight();
        this.equipment.add(item);
    }

    public void sortByWeight(){
        this.equipment.sort(new Comparator<Equipment>() {
            @Override
            public int compare(Equipment e1, Equipment e2){
                return e1.getWeight() - e2.getWeight();
            }
        });
    }

    public void sortByCost(){
        this.equipment.sort(new Comparator<Equipment>() {
            @Override
            public int compare(Equipment e1, Equipment e2){
                return e1.getCost() - e2.getCost();
            }
        });
    }

}
