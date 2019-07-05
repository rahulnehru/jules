package com.rnehru.dules.model.contextual;

import com.rnehru.dules.context.Context;
import com.rnehru.dules.context.Page;

import java.util.Objects;

public class PageExists extends DependsOnPageItem {


    public PageExists(String parentPage) {
        this.parentPage = parentPage;
    }

    @Override
    public boolean evaluate(Context context) {
        boolean isTrue = false;
        if(null != context && null != context.getPages() && !context.getPages().isEmpty()){
            for (Page page : context.getPages()) {
                if(Objects.equals(parentPage, page.getName()) &&
                        null != page.getQuestionsAndAnswers() &&
                        !page.getQuestionsAndAnswers().isEmpty()){
                    isTrue = true;
                }
            }
        }
        return isTrue;
    }
}
