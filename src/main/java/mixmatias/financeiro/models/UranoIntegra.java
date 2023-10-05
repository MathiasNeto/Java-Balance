package mixmatias.financeiro.models;

import mixmatias.financeiro.interfaces.IBalance;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class UranoIntegra implements IBalance<Product> {
    @Override
    public void export(List<Product> products) {
        String result = products.stream().map(product ->
                String.format("%06d", product.getCode()) +
                        "*" +
                        "0" +
                        String.format("%-20s", product.getDescription()).substring(0, 20) +
                        String.format("%09.2f", product.getValue()).replace('.', ',') +
                        "00000" +
                        "D"
        ).collect(Collectors.joining("\n"));

        System.out.println(result);

        try {
            File file = new File("PRODUTOS.TXT");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
