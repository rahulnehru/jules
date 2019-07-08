package com.rnehru.dules.rule.contextual;

import com.rnehru.dules.context.Context;
import com.rnehru.dules.context.Page;
import com.rnehru.dules.rule.Rule;

import java.util.Objects;

import static com.rnehru.dules.rule.Rule.*;

public class AnswerMatches extends DependsOnPageItem {

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
        if(!contextInvalid(context)) {
            for (Page page : context.getPages()) {
                if (null != page.getQuestionsAndAnswers() &&
                        Objects.equals(page.getName(), parentPage) &&
                        Objects.equals(page.getQuestionsAndAnswers().get(question), answer)) {
                    isTrue = true;
                }
            }
        }
        return isTrue;
    }
}