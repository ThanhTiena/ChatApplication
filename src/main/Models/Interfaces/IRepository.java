package main.Models.Interfaces;

import javax.swing.text.html.parser.Entity;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface IRepository<TEntity> {
    List<TEntity> findAll();
    List<TEntity> get(Predicate<TEntity> predicate, Function<TEntity,Boolean> filter);
    TEntity find(Predicate<TEntity> predicate);

    void insert(TEntity entity);

    void update(TEntity oldEntity,TEntity entity);

    void delete(TEntity entity);
}
