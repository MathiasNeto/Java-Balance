package mixmatias.financeiro.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mixmatias.financeiro.interfaces.IBalance;

import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FizolaSmart implements IBalance<Product> {
    @Override
    public void export(List<Product> products) {
        String result = products.stream().map(product ->
                String.format("%06d",product.getCode()) +
                        product.getType()+
                        String.format("%-22s",product.getDescription()).substring(0,22)+
                        String.format(("%07d"), (int) (product.getValue()*100)) +
                        "000")
                .collect(Collectors.joining("\n"));

        System.out.println(result);

        try {
            File file = new File("CADTXT.txt");

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(result);
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}