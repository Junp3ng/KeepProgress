import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Solution1600 {



}

class ThroneInheritance {

    HashMap<String, List<String>> edges = new HashMap<>();
    HashMap<String, Boolean> deaths = new HashMap<>();
    String kingName;
    public ThroneInheritance(String kingName) {
        this.kingName = kingName;
        edges.put(kingName, new ArrayList<String>());
        deaths.put(kingName, false);
    }

    public void birth(String parentName, String childName) {
        var list = edges.get(parentName);
        list.add(childName);
        edges.put(parentName, list);
        edges.put(childName, new ArrayList<>());
        deaths.put(childName, false);
    }

    public void death(String name) {
        deaths.put(name, true);
    }

    public List<String> getInheritanceOrder() {
        List<String> inheritanceList = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        stack.add(kingName);
        while (!stack.isEmpty()) {
            String name = stack.pop();
            if (!deaths.get(name)) {
                inheritanceList.add(name);
            }
            List<String> list = edges.get(name);
            if (list != null) {
                for (int i = list.size() - 1; i >= 0; i--) {
                    stack.push(list.get(i));
                }
            }
        }
        return inheritanceList;
    }
}
