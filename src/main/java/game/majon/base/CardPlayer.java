package game.majon.base;

import com.sun.istack.internal.NotNull;
import game.majon.base.type.PlayerStatus;

/**
 * Created by Administrator on 2017/2/17.
 */
public abstract class CardPlayer {
    
     protected CardBase[] handPool = new CardBase[14];
    
     protected CardBase[] usedPool = new CardBase[14];

     protected PlayerStatus currStatus;

     protected CardTable currTable;

     protected int currSome = 0;
     
     protected CardPlayer(@NotNull CardTable cardTable,@NotNull PlayerStatus status){
        this.currTable = cardTable;
        this.currStatus = status;
     }

     protected final int getCurrSome(){
         return this.currSome;
     }

     protected final int procCashSelf(@NotNull CardRule rule){
         return rule.procCashSelf(this);
     }

     protected final CardBase[] getHandPool(){
         return this.handPool;
     }

     protected final CardBase[] getUsedPool(){
         return this.usedPool;
     }

     /*操作类*/
    /**
     * 排序
     */
    protected abstract void sort();

    /**
     * 重置
     */
    protected abstract void reset();

    /*交互类*/
    /**
     * 起牌
     * @param card
     */
     protected abstract void get(CardBase card);

    /**
     * 出牌
     * @param card
     */
     protected abstract void post(CardBase card);

    /**
     * 吃
     */
     protected abstract void eat();

    /**
     * 碰
     */
     protected abstract void touch();

    /**
     * 明杠
     */
    protected abstract void brightPush();

    /**
     * 胡
     */
    protected abstract void win();

    /*自有类*/
    /**
     * 杠（皮、赖）
     * @param card
     */
     protected abstract void push(CardBase card);
    /**
     * 暗杠
     * @param card
     */
     protected abstract void darkPush(CardBase card);

    /**
     * 自摸
     */
     protected abstract void winSelf();

     /*计算类方法*/

    /**
     * 吃检查
     * @return
     */
     protected abstract boolean eatCheck();

    /**
     * 碰检查
     * @return
     */
     protected abstract boolean touchCheck();

    /**
     * 明杠检查
     * @return
     */
     protected abstract boolean brightCheck();

    /**
     * 暗杠检查
     * @return
     */
     protected abstract boolean winCheck();

    /**
     * 单张杠检查
     * @return
     */
     protected abstract boolean canPush();

    /**
     * 暗杠检查
     * @return
     */
     protected abstract boolean canDark();

    /**
     * 胡牌检查
     * @return
     */
     protected abstract boolean canWinSelf();
}
