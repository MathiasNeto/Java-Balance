package mixmatias.financeiro.interfaces;

import java.io.FileNotFoundException;
import java.util.List;

public interface IBalance<T> {
    void export(List<T> products, String path);
}
