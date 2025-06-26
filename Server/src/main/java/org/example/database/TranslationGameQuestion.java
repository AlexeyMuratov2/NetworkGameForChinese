package org.example.database;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TranslationGameQuestion {
    private String chineseWord;
    private String correctTranslation;
    private List<String> options;

    public TranslationGameQuestion(String chinese, String correct, ArrayList<String> options) {
        this.chineseWord = chinese;
        this.correctTranslation = correct;
        this.options = options;
    }

    public String getArgs(){
        StringBuilder args = new StringBuilder();
        args.append(chineseWord);
        args.append(",");
        args.append(correctTranslation);
        args.append(",");
        args.append(this.getOptionsAsString());
        return args.toString();
    }

    public String getOptionsAsString(){
        return String.join(",", options);
    }
}
