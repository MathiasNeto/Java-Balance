package mixmatias.financeiro.models;

import mixmatias.financeiro.exceptions.NullPointerExceptionPersonalized;
import mixmatias.financeiro.interfaces.IBalance;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FizolaSmart implements IBalance<Product> {

    @Override
    public void export(List<Product> products, String path){
        if (products == null) {
            throw new NullPointerExceptionPersonalized("The list of products is null.");
        }
        try {
            String result = products.stream().peek(product -> {
                if (product == null) {
                    throw new NullPointerExceptionPersonalized("One of the products is null.");
                }
                if(!(Objects.equals(product.getType(), "P") || Objects.deepEquals(product.getType(), "U"))){
                            throw new InvalidParameterException("Invalid getType parameter");
                        }
            }).map(product ->
                            String.format("%06d", product.getCode()) +
                                    product.getType() +
                                    String.format("%-22s", product.getDescription()).substring(0, 22) +
                                    String.format(("%07d"), (int) (product.getValue() * 100)) +
                                    "000")
                    .collect(Collectors.joining("\n"));
            File directory = new File(path);
//            if (!directory.exists() && !directory.mkdirs()) {
//                throw new IOException("Não foi possível criar o diretório: " + path);
//            }
            File file = new File(directory, "/CADTXT.txt");

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
        }catch (InvalidParameterException e){
            System.err.println(e.getMessage());
        }
    }
}