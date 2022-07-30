package main.Models.Reponsitories;

import main.Models.Interfaces.IRepository;

import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Repository<TEntity> implements IRepository<TEntity> {
    private List<TEntity> entity = new ArrayList<TEntity>();
    private Predicate<TEntity> predicate;
    @Override
    public List<TEntity> get(Predicate<TEntity> pre, Function<TEntity,Boolean> filter) {
        this.predicate = pre;
        return entity.stream().filter(this.predicate).sorted(Comparator.comparing(filter)).toList();
    }

    @Override
    public TEntity find(Predicate pre) {
        this.predicate = pre;
        return entity.stream().filter(x -> x.equals(this.predicate)).findFirst().get();
    }

    @Override
    public void insert(Object o) {
        this.entity.add((TEntity) o);
    }

    @Override
    public void update(Object o) {
        
    }

    @Override
    public void delete(Object o) {
        this.entity.remove((TEntity) o);
    }


}
