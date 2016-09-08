package ru.dmm.steamservice.data.rs;

/**
 * Created by Dmitry
 */
public class AppDataDescriptions {
    private long def_index;
    private int is_itemset_name;
    private int quality;

    public long getDef_index() {
        return def_index;
    }

    public void setDef_index(long def_index) {
        this.def_index = def_index;
    }

    public int getIs_itemset_name() {
        return is_itemset_name;
    }

    public void setIs_itemset_name(int is_itemset_name) {
        this.is_itemset_name = is_itemset_name;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }
}
