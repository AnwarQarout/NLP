public class Prediction implements Comparable<Prediction> {
    private int frequency;
    private String word;

    public Prediction(){

    }

    public Prediction(String word){
        this.word = word;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void increaseFrequency(){
        this.frequency ++;
    }



    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }


    public int compareTo(Prediction p){
        if(this.frequency > p.frequency){
            return -1;
        }
        else if(this.frequency < p.frequency){
            return 1;
        }
        return 0;
    }
}
