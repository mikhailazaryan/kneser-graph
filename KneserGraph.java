import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KneserGraph {
    //TODO for arbitrary integers and other data types
    //protected ArrayList set;
    protected int n;
    protected int k;
    protected List<List<Integer>> vertices;


    public KneserGraph (int n, int k) {
        //this.set = set;
        this.n = n;
        this.k = k;
        this.initialize();
    }

    private void initialize () {
        if (n<k) {
           int t;
           t = k;
           k = n;
           n = t;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        System.out.println("New graph has "+binomi(n, k)+" vertices");
        List<List<Integer>> vertices = getDisjointSubsets(list, k);
        for (List<Integer> vertice : vertices) {
            System.out.println(vertice);
        }

    }

    private static int binomi(int n, int k) {
        if ((n == k) || (k == 0))
            return 1;
        else
            return binomi(n - 1, k) + binomi(n - 1, k - 1);
    }
    public static List<List<Integer>> getDisjointSubsets(List<Integer> list, int n) {
        List<List<Integer>> disjointSubsets = new ArrayList<>();
        if (n == 0) {
            disjointSubsets.add(new ArrayList<>());
            return disjointSubsets;
        }
        if (list.isEmpty() || list.size() < n) {
            return disjointSubsets;
        }

        int firstElement = list.get(0);
        List<Integer> restOfList = list.subList(1, list.size());

        List<List<Integer>> subsetsWithoutFirst = getDisjointSubsets(restOfList, n);
        List<List<Integer>> subsetsWithFirst = getDisjointSubsets(restOfList, n - 1);

        for (List<Integer> subset : subsetsWithFirst) {
            List<Integer> subsetWithFirst = new ArrayList<>(subset);
            subsetWithFirst.add(firstElement);
            disjointSubsets.add(subsetWithFirst);
        }

        disjointSubsets.addAll(subsetsWithoutFirst);

        return disjointSubsets;
    }
    public static void main(String[] args) {
        KneserGraph k52_petersen = new KneserGraph(5,2);
        KneserGraph k73 = new KneserGraph(7,3);

    }
}
