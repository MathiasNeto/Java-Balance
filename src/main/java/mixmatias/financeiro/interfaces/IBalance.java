package mixmatias.financeiro.interfaces;

import java.util.List;

public interface IBalance<T> {
    void export(List<T> products);
}
