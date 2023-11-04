package mixmatias.financeiro;

import mixmatias.financeiro.enums.TypeBalance;
import mixmatias.financeiro.interfaces.IBalance;
import mixmatias.financeiro.models.BalanceFactory;
import mixmatias.financeiro.models.Product;

import javax.naming.directory.BasicAttribute;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Product product = new Product(3, "QUEIJO GRUYERE KG", "p".toUpperCase(), 21.90);

        List<Product> products = Arrays.asList(product);
        System.out.println("-----------FINXOLA_SMART------------");
        BalanceFactory.getBalance(TypeBalance.FINZOLA_SMART).export(products, ".");
        System.out.println("-----------TOLEDO_MGV6------------");
        BalanceFactory.getBalance(TypeBalance.TOLETO_MGV6).export(products, ".");
        System.out.println("-----------URANO_INTEGRA------------");
        BalanceFactory.getBalance(TypeBalance.URANO_INTEGRA).export(products, ".");
    }
}
