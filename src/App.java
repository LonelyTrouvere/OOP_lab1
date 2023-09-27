import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    public static void printInv(LinkedList<Equipment> list){
        int i = 1;
        for(Equipment item : list){
            if (item instanceof Weapon)
                System.out.println(i+". "+item.getName()+" --- Type: "+item.getType()+" --- Damage: "+item.getStat()+" --- Cost: "+item.getCost()+" --- Weight: "+item.getWeight());
            else
                System.out.println(i+". "+item.getName()+" --- Type: "+item.getType()+" --- Protection: "+item.getStat()+" --- Cost: "+item.getCost()+" --- Weight: "+item.getWeight());
            i += 1;
        }
    }

    public static void storeManager(LinkedList<Equipment> stock){

    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
        System.out.println("Welcome traveler! What is your name?");
 
        String name = reader.readLine();
        Knight knight = new Knight(name);
        Store store = new Store();

        LinkedList<Equipment> defaultStock = new LinkedList<Equipment>();
        defaultStock.add(new Weapon("Silver sword", Weapon.WeaponType.SWORD));
        defaultStock.add(new Weapon("Rusty Axe", Weapon.WeaponType.AXE));
        defaultStock.add(new Weapon("Iron Flail", Weapon.WeaponType.FLAIL));
        defaultStock.add(new Weapon("Wooden Bow", Weapon.WeaponType.BOW));
        defaultStock.add(new Armor("Simple helmet", Armor.ArmorType.HELMET));
        defaultStock.add(new Armor("Dragon Armor", Armor.ArmorType.BODY));
        defaultStock.add(new Armor("Leather Gauntlets", Armor.ArmorType.GAUNTLETS));
        defaultStock.add(new Armor("Plate Leggings", Armor.ArmorType.LEGGINGS));

        store.addStock(defaultStock);
        
        System.out.println("Very well " + knight.getName() + ". Let's get you started");

        while (true){
            Boolean quit = false;
            System.out.println(knight.getName()+" ------- Lvl: "+knight.getLevel()+" ------- Gold: "+knight.getGold()+" ------- Equip Load: "+knight.getEquipLoad()+" ------- Max Equip Load: "+knight.getMaxEquipLoad());
            System.out.println("Options: ");
            System.out.println("1. Go to adventure");
            System.out.println("2. Buy equipment");
            System.out.println("3. Sell equipment");
            System.out.println("4. Check inventory");
            System.out.println("0. Exit");

            System.out.print("You choose? ");
            int opt = Integer.parseInt(reader.readLine());

            switch (opt){
                case 1:
                    knight.doQuest();
                    break;
                case 2:
                    LinkedList<Equipment> stock = store.getGoods();
                    printInv(stock);
                    System.out.println((stock.size()+1)+". Find equipment in price range");
                    System.out.println("0. Return");
                    System.out.print("You choose? ");
                    int optBuy = Integer.parseInt(reader.readLine());
                    optBuy -= 1;

                    if(optBuy == stock.size()){
                        System.out.println("Enter your minimum and maximum price range");
                        int min = Integer.parseInt(reader.readLine());
                        int max = Integer.parseInt(reader.readLine());

                        stock = store.findInPriceRange(min, max);
                        printInv(stock);
                        System.out.println("0. Return");
                        System.out.print("You choose? ");
                        optBuy = Integer.parseInt(reader.readLine());
                        optBuy -= 1;
                    }

                    if (optBuy < 0 || optBuy >= stock.size()+1)
                        break;

                    Equipment item = stock.get(optBuy);
                    if (item.getCost() > knight.getGold()){
                        System.out.println("Not enough gold!");
                        break;
                    }
                    if(item.getWeight() + knight.getEquipLoad() > knight.getMaxEquipLoad()){
                        System.out.println("The item doesn't fit into inventory!");
                        break;
                    }
                    knight.buyItem(item);
                    store.sellItem(item);
                    System.out.println("Item is in your inventory now!");
                    break;
                case 3:
                    LinkedList<Equipment> inventory = knight.getEquipment();
                    printInv(inventory);
                    System.out.println("0. Return");
                    System.out.print("You choose? ");
                    int optSell = Integer.parseInt(reader.readLine());
                    optSell -= 1;

                    if (optSell < 0 || optSell >= inventory.size()+1)
                        break;

                    Equipment item1 = inventory.get(optSell);
                    knight.sellItem(item1);
                    store.buyItem(item1);
                    System.out.println("Item has been sold!");
                    break;
                case 4:
                    System.out.println("1. Sort by item weight");
                    System.out.println("2. Sort by item cost");
                    System.out.println("3. Do not sort");
                    System.out.println("0. Return");
                    System.out.print("You choose? ");
                    int optInv = Integer.parseInt(reader.readLine());

                    if (optInv > 3 || optInv < 1)
                        break;

                    LinkedList<Equipment> inv;
                    switch(optInv){
                        case 1:
                            knight.sortByWeight();
                            break;
                        case 2:
                            knight.sortByCost();
                    }

                    inv = knight.getEquipment();
                    printInv(inv);
                    break;
                case 0:
                    System.out.print("Are you sure you want to exit? (y/n) "); 
                    String quitOpt = reader.readLine();
                    if (quitOpt.equals("y")){
                        System.out.print("May your journey be safe!");
                        quit = true;
                    } else {
                        System.out.println("We are glad you are with us!");
                    }
                    break;
                default:
                    System.out.println("Unknown action");
            }

            if (quit){
                break;
            }
        }
    }
}
