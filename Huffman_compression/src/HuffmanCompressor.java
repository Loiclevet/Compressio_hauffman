import java.util.PriorityQueue;

public class HuffmanCompressor {

    private static final int ALPHABET_SIZE = 256;


    public HuffmanEncoderResult compress(final String data) {
        final int[] freq = buildFrequencyTable(data);
        final Node root = buildHuffmanTree(freq);

        return null;
    }

    

    private static Node buildHuffmanTree(int[] freq){

        final PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        for (char i = 0; i < ALPHABET_SIZE; i++){
            if(freq[i] > 0 ){
                priorityQueue.add(new Node(i, freq[i],null,null));
            }
        }

        if(priorityQueue.size()==1){
            priorityQueue.add(new Node('\0',1,null,null));


        }

        while(priorityQueue.size() > 1){
            final Node left = priorityQueue.poll();
            final Node right = priorityQueue.poll();
            final Node parent = new Node('\0', left.frequency + right.frequency, left,right);
            priorityQueue.add(parent);
        }
    return priorityQueue.poll();
    }


    private static int[] buildFrequencyTable(final String data){
        final int[] freq = new int[ALPHABET_SIZE];
        for(final char character : data.toCharArray()){
            freq[character]++;
        }
        return freq;
    }

    //public String decompress(final_HuffmanEncoderResult result){
        //   return null;
    //}

    static class Node implements Comparable<Node>{
        private final char character;
        private final int frequency;
        private final Node leftChild;
        private final Node rightChild;

        private Node(final char character,
                     final int frequency,
                     final Node leftChild,
                     final Node rightChild){
            this.character = character;
            this.frequency = frequency;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        boolean isLeaf(){
            return this.leftChild == null && this.rightChild == null;
        }

        @Override
        public int compareTo(Node that) {
            final int frequencyComparison = Integer.compare(this.frequency, that.frequency);
            if(frequencyComparison!=0){
                return frequencyComparison;
            }
            return Integer.compare(this.character, that.character);
        }
    }
    static class HuffmanEncoderResult {

    }

    public static void main(String[] args){
        final String test = "abcdefg";
        final int[] ft = buildFrequencyTable(test);
        final Node n = buildHuffmanTree(ft);
        System.out.println(n);
    }

}
