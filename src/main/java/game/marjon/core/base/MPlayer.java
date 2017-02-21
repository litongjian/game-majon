package game.marjon.core.base;

/**
 * Created by Administrator on 2017/2/21.
 */
public class MPlayer {

    public void ready(){
        if (currRoom>0&&currStatus!=PLAYING){
            currStatus = currStatus == STANDBY?NORMAL:STANDBY;
        }
    }

    public static final int PLAYING = 2;

    public static final int STANDBY = 1;

    public static final int NORMAL = 0;

    private String acct;

    private int cash = 0;

    private int currIdx;

    private int currRoom=-1;

    private int currStatus = NORMAL;

    public int getCurrRoom() {
        return currRoom;
    }

    public void setCurrRoom(int currRoom) {
        this.currRoom = currRoom;
    }

    public int getCurrStatus() {
        return currStatus;
    }

    public void setCurrStatus(int currStatus) {
        this.currStatus = currStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MPlayer mPlayer = (MPlayer) o;

        return acct != null ? acct.equals(mPlayer.acct) : mPlayer.acct == null;
    }

    @Override
    public int hashCode() {
        return acct != null ? acct.hashCode() : 0;
    }

    public MPlayer(String acct) {
        this.acct = acct;
    }

    public int getCash() {
        return cash;
    }

    public void addCash(int cash) {
        this.cash += cash;
    }

    public void clearCash(){this.cash = 0;}

    public int getCurrIdx() {
        return currIdx;
    }

    public void setCurrIdx(int currIdx) {
        this.currIdx = currIdx;
    }

    public String getAcct() {
        return acct;
    }

    @Override
    public String toString() {
        return "MPlayer{" +
                "acct='" + acct + '\'' +
                ", cash=" + cash +
                ", currIdx=" + currIdx +
                '}';
    }
}
