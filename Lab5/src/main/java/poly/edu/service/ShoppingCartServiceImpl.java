package poly.edu.service;

import poly.edu.data.DB;
import poly.edu.model.Item;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final Map<Integer, Item> map = new HashMap<>();

    @Override
    public Item add(Integer id) {
        Item it = map.get(id);
        if (it == null) {
            Item src = DB.items.get(id);
            if (src == null) return null;
            it = new Item(src.getId(), src.getName(), src.getPrice(), 1);
            map.put(id, it);
        } else {
            it.setQty(it.getQty() + 1);
        }
        return it;
    }

    @Override
    public void remove(Integer id) { map.remove(id); }

    @Override
    public Item update(Integer id, int qty) {
        Item it = map.get(id);
        if (it == null) return null;
        if (qty <= 0) { map.remove(id); return null; }
        it.setQty(qty);
        return it;
    }

    @Override
    public void clear() { map.clear(); }

    @Override
    public Collection<Item> getItems() { return map.values(); }

    @Override
    public int getCount() { return map.values().stream().mapToInt(Item::getQty).sum(); }

    @Override
    public double getAmount() { return map.values().stream()
            .mapToDouble(i -> i.getPrice() * i.getQty()).sum(); }
}
