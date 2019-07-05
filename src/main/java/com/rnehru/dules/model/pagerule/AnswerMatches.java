package com.rnehru.dules.model.pagerule;

import com.rnehru.dules.context.Context;
import com.rnehru.dules.context.Page;

import java.util.List;
import java.util.Objects;

public class AnswerMatches extends PageRule {

    private String answer;
    private String question;

    public AnswerMatches(String parentPage, String answer, String question) {
        this.parentPage = parentPage;
        this.answer = answer;
        this.question = question;
    }

    @Override
    public boolean evaluate(Context context) {
        boolean isTrue = false;
        for (Page page : context.getPages()) {
            if(null != page.getQuestionsAndAnswers() &&
                    Objects.equals(page.getName(), parentPage) &&
                    Objects.equals(page.getQuestionsAndAnswers().get(question), answer)){
                isTrue = true;
            }
        }
        return isTrue;
    }
}
