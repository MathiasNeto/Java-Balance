package mixmatias.financeiro.models;

import mixmatias.financeiro.exceptions.NullPointerExceptionPersonalized;
import mixmatias.financeiro.interfaces.IBalance;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UranoIntegra implements IBalance<Product> {
    @Override
    public void export(List<Product> products, String path) {
        if (products == null) {
            throw new NullPointerExceptionPersonalized("The list of products is null.");
        }
        try {
        String result = products.stream().peek(product -> {
            if (product == null) {
                throw new NullPointerExceptionPersonalized("One of the products is null.");
            }
        }).map(product ->
                String.format("%06d", product.getCode()) +
                        "*" +
                        "0" +
                        String.format("%-20s", product.getDescription()).substring(0, 20) +
                        String.format("%09.2f", product.getValue()).replace('.', ',') +
                        "00000" +
                        "D"
        ).collect(Collectors.joining("\n"));


            if(products.size() == 0){
                throw new NullPointerExceptionPersonalized("product is null");
            }
            File directory = new File(path);
            File file = new File(directory+ "/PRODUTOS.TXT");
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
