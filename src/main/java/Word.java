public class Word {

    private String word;
    private String definition;

    public Word() {
    }

    public void makeWord(String word, String def) {
        this.word = word;
        this.definition = def;
    }

    public void setWord(String word){
        this.word = word;
    }

    public void setDefinition(String def){
        this.definition = def;
    }
    public String getWord() {
        return this.word;
    }
    public String getDefinition() {
        return this.definition;
    }

    public String toString() {
        System.out.println(this.word);
        System.out.println(this.definition);
        return null;
    }
}
