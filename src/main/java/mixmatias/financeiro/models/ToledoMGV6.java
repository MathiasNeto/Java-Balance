package mixmatias.financeiro.models;

import mixmatias.financeiro.exceptions.NullPointerExceptionPersonalized;
import mixmatias.financeiro.interfaces.IBalance;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

public class ToledoMGV6 implements IBalance<Product> {
    @Override
    public void export(List<Product> products, String path) {
        if(products == null){
            throw new NullPointerExceptionPersonalized("The list of products is null.");
        }
        try {
            String result = products.stream().peek(product -> {
                if (product == null) {
                    throw new NullPointerExceptionPersonalized("One of the products is null.");
                }
            }).map(product ->
                String.format("01" +
                        "1" +
                        "%06d", product.getCode()) +
                        String.format("%06d", (int) (product.getValue() * 100)) +
                        "000" +
                        String.format("%-50s", product.getDescription()).substring(0, 50) +
                        "000000" +
                        "0000" +
                        "000000" +
                        "0" +
                        "0" +
                        "0000" +
                        "000000000000" +
                        "00000000000" +
                        "0" +
                        "0000" +
                        "0000" +
                        "0000" +
                        "0000" +
                        "0000" +
                        "0000" +
                        "000000000000" +
                        "000000" +
                        "|01|" +
                        String.format("%-35s", "") +
                        String.format("%-35s", "") +
                        "000000" +
                        "000000" +
                        "000000" +
                        "0000000|0000|0||"
        ).collect(Collectors.joining("\n"));

            File directory = new File(path);
            File file = new File(directory+ "/ITENSMGV.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (NullPointerExceptionPersonalized e) {
            System.err.println(e.getMessage());
        } catch (StringIndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
    }
}
