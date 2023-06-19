package diary.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Store the check list data.
 * 
 * @author iceman
 * @version 1.0
 */
public class CheckListVO implements Serializable {
    private Map<String, Boolean> checkList;
    private static final long serialVersionUID = 1L;

    /**
     * CheckListVO constructor.
     */
    public CheckListVO() {
        checkList = new HashMap<>();
    }

    /**
     * 
     * @param itemName
     */
    public void addItem(String itemName) {
        checkList.put(itemName, false);
    }

    /**
     * 
     * @param itemName
     */
    public void deleteItem(String itemName) {
        checkList.remove(itemName);
    }

    /**
     * 
     * @param itemName
     * @param itemStatus
     */
    public void ChangeItemStatus(String itemName, boolean itemStatus) {
        checkList.put(itemName, itemStatus);
    }

}
