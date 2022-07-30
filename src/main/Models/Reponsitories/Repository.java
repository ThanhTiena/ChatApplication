package main.Models.Reponsitories;

import main.Models.Interfaces.RepositoryInterface;

import java.util.*;

public class Repository<Entity> implements RepositoryInterface {
    private List<Entity> data = new ArrayList<Entity>();
    @Override
    public Entity find(String predicate) {
        return data.stream().filter(x -> x.equals(predicate)).findFirst().get();
    }

    @Override
    public void insert(Object o) {

    }

    @Override
    public void update(Object o) {

    }

    @Override
    public void delete(Object o) {

    }
}
