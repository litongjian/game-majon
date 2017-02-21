package game.marjon.core.base;

/**
 * Created by Administrator on 2017/2/21.
 */
public class MCard {

    private String name;

    private int type;

    private int value;

    private int times=0;

    private int owner = -1;

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getTimes() {
        return times;
    }

    public void addTimes() {
        this.times++;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public MCard(String name, int type, int value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public MCard copyNewInstance(){
        MCard rs = new MCard(this.name,this.type,this.value);
        rs.times = this.times;
        return rs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MCard mCard = (MCard) o;

        if (type != mCard.type) return false;
        return value == mCard.value;
    }

    @Override
    public int hashCode() {
        int result = type;
        result = 31 * result + value;
        return result;
    }

    @Override
    public String toString() {
        return "MCard{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", value=" + value +
                '}';
    }
}
