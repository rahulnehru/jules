package com.rnehru.dules.rule.logical;

import com.rnehru.dules.context.Context;
import com.rnehru.dules.rule.Rule;

import java.util.List;

public class Or extends LogicalRule {

    public Or(List<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public boolean evaluate(Context context) {
        int trueRules = 0;
        for (Rule rule : rules) {
            if(rule.evaluate(context)) {
                trueRules++;
            }
        }
        return trueRules > 0;
    }
}