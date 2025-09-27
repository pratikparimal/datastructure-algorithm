package youtube.graph.striver;

import java.util.*;

public class AccountsMerge {

    static class DisjointSet {
        List<Integer> parent = new ArrayList<>();
        List<Integer> size = new ArrayList<>();

        public DisjointSet(int n) {
            for (int i = 0; i <= n; i++) {
                parent.add(i);
                size.add(1);
            }
        }

        public int findParent(int node) {
            int iParent = parent.get(node);
            if (iParent == node) return node;
            int tParent = findParent(iParent);
            parent.set(node, tParent);
            return parent.get(node);
        }

        public void unionBySize(int u, int v) {
            int parentU = findParent(u);
            int parentV = findParent(v);
            if (parentU == parentV) return;
            if (size.get(parentU) > size.get(parentV)) {
                parent.set(parentV, parentU);
                size.set(parentU, size.get(parentU) + size.get(parentV));
            } else {
                parent.set(parentU, parentV);
                size.set(parentV, size.get(parentU) + size.get(parentV));
            }
        }
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String, Integer> mailNodeMap = new HashMap<>();
        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);
        //traverse through each accounts
        // to map mail to the node if the mail is not present in the map from before
        // but if it is present from before, that means this mail needs to be merged to the node where similar mail was seen before
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {//accounts[i][0] is name and rest are emails
                String mail = accounts.get(i).get(j);
                if (!mailNodeMap.containsKey(mail)) {
                    mailNodeMap.put(mail, i);
                } else {
                    ds.unionBySize(i, mailNodeMap.get(mail));
                }
            }
        }
        // now from the prepared map, merge the mail according to the parent of the node
        List<List<String>> mergeMail = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            mergeMail.add(new ArrayList<>());
        }
        for (Map.Entry<String, Integer> entry: mailNodeMap.entrySet()) {
            String mail = entry.getKey();
            int index = entry.getValue();
            mergeMail.get(ds.findParent(index)).add(mail);
        }

        List<List<String>> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> mails = mergeMail.get(i);
            if (mails.isEmpty()) continue;
            String name = accounts.get(i).getFirst();
            List<String> temp = new ArrayList<>();
            temp.add(name);
            Collections.sort(mails);
            temp.addAll(mails);
            answer.add(temp);
        }
        return answer;
    }

    public static void main(String[] args) {
        String[][] accounts = {
                {"John","johnsmith@mail.com","john_newyork@mail.com"},
                {"John","johnsmith@mail.com","john00@mail.com"},
                {"Mary","mary@mail.com"},
                {"John","johnnybravo@mail.com"}};
        List<List<String>> accountList = Arrays.stream(accounts).map(Arrays::asList).toList();
        System.out.println(accountsMerge(accountList));
    }
}
