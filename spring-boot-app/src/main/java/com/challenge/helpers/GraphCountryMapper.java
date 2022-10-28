package com.challenge.helpers;

import java.util.List;
import java.util.stream.Collectors;

import com.challenge.extensions.IndexedHashMap;
import com.challenge.models.app.Node;
import com.challenge.models.dto.BorderCrossing;
import com.challenge.models.raw.githubusercontent.com.Country;

public class GraphCountryMapper {
    private IndexedHashMap<String, List<String>> _nameBordersHashMap;

    public GraphCountryMapper(Country[] countries) {
        _nameBordersHashMap = new IndexedHashMap<String, List<String>>();
        for (Country c : countries) {
            _nameBordersHashMap.put(c.getCca3(), c.getBorders());
        }
    }

    public List<List<Node>> getGraph() {
        return _nameBordersHashMap.entrySet().stream().map(e -> e.getValue().stream()
                .map(b -> new Node(_nameBordersHashMap.getKeyIndex(b), 1))
                .collect(Collectors.toList())).collect(Collectors.toList());
    }

    public BorderCrossing getBorderCrossing(List<Node> nodes) {
        BorderCrossing result = new BorderCrossing();
        result.setRoute(nodes.stream().map(c -> _nameBordersHashMap.getKeyAt(c.id)).collect(Collectors.toList()));
        return result;
    }

    public int getMapSize() {
        return _nameBordersHashMap.getKeys().size();
    }

    public int getIndex(String value) {
        return _nameBordersHashMap.getKeyIndex(value.toUpperCase());
    }

}
