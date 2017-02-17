package game.majon.base;

import com.sun.istack.internal.NotNull;
import game.majon.base.expr.CardValueErrorException;
import game.majon.base.type.CardStatus;
import game.majon.base.type.CardType;

/**
 * Created by Administrator on 2017/2/17.
 * 牌超类
 */
public abstract class CardBase {

    protected CardType type;

    protected int val;

    protected CardStatus status;

    protected CardBase(@NotNull CardType type,@NotNull int val)throws CardValueErrorException{
        if (val <= 0 ||val >9 ||type == CardType.FENG && val > 7)throw new CardValueErrorException();
        this.type = type;
        this.val = val;
        this.status = CardStatus.UNUSED;
    }

    
}
