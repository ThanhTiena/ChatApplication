package main.Models.Interfaces;

import org.junit.jupiter.api.Order;

import javax.management.Query;
import javax.swing.text.html.parser.Entity;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

public interface RepositoryInterface<TEntity> {

    Iterator<Entity> get(Predicate<TEntity> predicate, Function<TEntity,Boolean> filter);
    TEntity find(Predicate<TEntity> predicate);

    void insert(TEntity entity);

    void update(TEntity entity);

    void delete(TEntity entity);
}
