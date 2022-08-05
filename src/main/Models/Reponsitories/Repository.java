package main.Models.Reponsitories;

import main.Models.Interfaces.IRepository;

import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Repository<TEntity> implements IRepository<TEntity> {
    private List<TEntity> entity = new ArrayList<TEntity>();

    @Override
    public List<TEntity> findAll() {
        return this.entity;
    }

    @Override
    public List<TEntity> get(Predicate<TEntity> pre, Function<TEntity,Boolean> filter) {
        return entity.stream().filter(pre).sorted(Comparator.comparing(filter)).toList();
    }

    @Override
    public TEntity find(Predicate<TEntity> pre) {
        return entity.stream().filter(pre).findFirst().get();
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
