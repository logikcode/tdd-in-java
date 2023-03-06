package logikcode.springframework.vetspring;

import logikcode.springframework.petty.fauxspring.Model;
import logikcode.springframework.petty.model.Speciality;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ModelMapImpl implements Model {
    Map<String, Set<Speciality>> map = new HashMap<>();
    @Override
    public void addAttribute(String key, Object o) {
        map.put(key, (Set<Speciality>) o);
    }

    @Override
    public void addAttribute(Object o) {

    }

    public Map<String, Set<Speciality>> getMap(){
        return map;
    }
}
