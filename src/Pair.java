
public class Pair <T1, T2> {

    private T1 first;
    private T2 second;

    public Pair (T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond(){
        return second;
    }

    public void setFirst(T1 first) {
        this.first = first;
    }

    public void setSecond(T2 second) {
        this.second = second;
    }

    public static void main(String[] args) {
        Pair<Integer, Integer> intPair = new Pair<>(4, 8);
        Pair<Double, String> mixPair = new Pair<>(0.5, "science");
        Pair<Pair, Pair> pairPair = new Pair<>(intPair, mixPair); 
        System.out.println(intPair.getFirst());
        System.out.println(mixPair.getSecond());
        System.out.println(intPair);
        System.out.println(mixPair);
        System.out.println(pairPair.getFirst());
        System.out.println(pairPair.getSecond());
    }

}