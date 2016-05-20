package com.gluketic.example.fragments.masterdetail.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper class for providing sample content for user interface
 *
 * Created by Goran Luketic
 */
public class DummyContentManager {

    private static DummyContentManager sInstance;

    private static final int COUNT = 25;
    public ArrayList<DummyItem> mItems;
    public Map<String, DummyItem> mItemMap;

    public static synchronized DummyContentManager getInstance() {
        if (DummyContentManager.sInstance == null) {
            DummyContentManager.sInstance = new DummyContentManager();
        }

        return DummyContentManager.sInstance;
    }

    private DummyContentManager() {
        mItems = new ArrayList<>();
        mItemMap = new HashMap<>();
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private void addItem(DummyItem item) {
        mItems.add(item);
        mItemMap.put(item.getId(), item);
    }

    public ArrayList<DummyItem> getItems() {
        return mItems;
    }

    public Map<String, DummyItem> getItemsMap() {
        return mItemMap;
    }

    private DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
}
