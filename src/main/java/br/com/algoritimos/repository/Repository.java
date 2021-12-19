package br.com.algoritimos.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.algoritimos.pojo.Pojo;

public class Repository {
	private final static HashMap<Integer, Pojo> Pojos = new HashMap<Integer, Pojo>();

    public List<Pojo> GetAll(){
        return new ArrayList<Pojo>(Pojos.values());
    }

    public Pojo Get(final int id) {
        return Pojos.get(id);
    }

    public void Add(final Pojo Pojo) {
        if(Pojo.getId() == 0 )
            Pojo.setId(generateId(Pojos.size() + 1));
        Pojos.put(Pojo.getId(), Pojo);
    }

    public void Edit(final Pojo Pojo) {
        Pojos.remove(Pojo.getId());
        Pojos.put(Pojo.getId(), Pojo);
    }

    public void Delete(final int id) {
        Pojos.remove(id);
    }

    private int generateId(final int possible)
    {
        if(Pojos.containsKey(possible))
            return generateId(possible + 1);
        return possible;
    }
}
