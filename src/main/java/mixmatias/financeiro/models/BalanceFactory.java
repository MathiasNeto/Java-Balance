package mixmatias.financeiro.models;

import mixmatias.financeiro.enums.TypeBalance;
import mixmatias.financeiro.interfaces.IBalance;


public class BalanceFactory {

    public static IBalance<Product> getBalance(TypeBalance typeBalance) {
        if (TypeBalance.FINZOLA_SMART.equals(typeBalance)) {
            return new FizolaSmart();
        } else if (TypeBalance.TOLETO_MGV6.equals(typeBalance)) {
            return new ToledoMGV6();
        } else if (TypeBalance.URANO_INTEGRA.equals(typeBalance)) {
            return new UranoIntegra();
        }
        return null;
    }
}
