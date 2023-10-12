package repositories;

import java.util.ArrayList;

public class IRepository<T> {
    private T t;
    private ArrayList<T> objects = new ArrayList<>();
    public T getByID(int id){
        if(id < 0){
            //jakiś wyjątek rzucić :)
        }
        return objects.get(id);
    }
    public String getAll(){
        String result = "";
        for(T o : objects){
            result += o.toString();
        }
        return result;

    }
    public int getLastID(){
        return objects.size()-1;
    }
    public void add(T object){
        if(object == null){
            //jakiś wyjątek rzucić :)
        }
        objects.add(object);
    }

    public void update(T newObject, int oldObjectID) {
        if(newObject == null || oldObjectID < 0){
            //jakiś wyjątek rzucić :)
        }
        objects.set(oldObjectID, newObject);
    }

    public void remove(T object) {
        if (object == null) {
            //jakiś wyjątek rzucić :)
        }
        objects.remove(object);
    }

}
