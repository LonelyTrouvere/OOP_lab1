public abstract class Equipment {
    protected String name;
    protected int weight;
    protected int cost;

    public Equipment (String name){
        this.name = name;
        this.cost = name.length()*10;
    }

    public String getName() {
        return this.name;
    }

    abstract public String getType();
    abstract public int getStat();

    public int getWeight(){
        return this.weight;
    }

    public int getCost(){
        return this.cost;
    }

    abstract protected int calculateWeight();
}
