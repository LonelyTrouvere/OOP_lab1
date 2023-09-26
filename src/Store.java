import java.util.LinkedList;

public class Store implements IBarter {
    private LinkedList<Equipment> goods;

    public Store(){
        this.goods = new LinkedList<Equipment>(); 
    }

    public LinkedList<Equipment> getGoods(){
        return this.goods;
    }

    public void buyItem(Equipment item){
        goods.add(item);
    }

    public void sellItem(Equipment item){
        goods.remove(item);
    }

    public void addStock(LinkedList<Equipment> list){
        for (Equipment item : list){
            this.goods.add(item);
        }
    }

    public LinkedList<Equipment> findInPriceRange(int min, int max){
        LinkedList<Equipment> ans = new LinkedList<Equipment>();

        for(Equipment item : this.goods){
            if (item.getCost() >= min && item.getCost() <= max){
                ans.add(item);
            }
        }

        return ans;
    }
}
