package com.rnehru.dules.rule.contextual;

import com.rnehru.dules.context.Context;
import com.rnehru.dules.context.Page;
import org.junit.Test;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class AnswerDateAfterTest {

    @Test(expected = DateTimeParseException.class)
    public void constructor_throwsException_whenComparisonDateNotInDateFormat() {
        new AnswerDateAfter("parentPage", "question",  "bla");
    }

    @Test
    public void constructor_doesNotThrowException_whenComparisonDateInDateFormat() {
        new AnswerDateAfter("parentPage", "question", "2010-01-01");
    }

    @Test
    public void evaluate_returnsFalse_whenContextIsNull() {
        assertFalse(new AnswerDateAfter("page", "question", "2010-01-01").evaluate(null));
    }

    @Test
    public void evaluate_returnsFalse_whenContextHasNullPages() {
        Context ctx = new Context(null);
        assertFalse(new AnswerDateAfter("page", "question", "2010-01-01").evaluate(ctx));
    }

    @Test
    public void evaluate_returnsFalse_whenContextHasEmptyPages() {
        Context ctx = new Context(new ArrayList<>());
        assertFalse(new AnswerDateAfter("page", "question", "2010-01-01").evaluate(ctx));
    }

    @Test
    public void evaluate_returnsFalse_whenContextHasPageButHasNullMap() {
        Page page = new Page("page", null);
        List<Page> pages = new ArrayList<>();
        pages.add(page);
        Context ctx = new Context(pages);
        assertFalse(new AnswerDateAfter("page", "question", "2010-01-01").evaluate(ctx));
    }

    @Test
    public void evaluate_returnsFalse_whenContextHasPageButHasEmptyMap() {
        Map<String, String> answers = new HashMap<>();
        Page page = new Page("page", answers);
        List<Page> pages = new ArrayList<>();
        pages.add(page);
        Context ctx = new Context(pages);
        assertFalse(new AnswerDateAfter("page", "question", "2010-01-01").evaluate(ctx));
    }

    @Test
    public void evaluate_returnsFalse_whenContextHasPageButHasMapWithNullAnswers() {
        Map<String, String> answers = new HashMap<>();
        answers.put("question", null);
        Page page = new Page("page", answers);
        List<Page> pages = new ArrayList<>();
        pages.add(page);
        Context ctx = new Context(pages);
        assertFalse(new AnswerDateAfter("page", "question", "2010-01-01").evaluate(ctx));
    }

    @Test(expected = DateTimeParseException.class)
    public void evaluate_throwsException_whenContextHasAnswerForQuestionWhichIsIncorrectFormat() {
        Map<String, String> answers = new HashMap<>();
        answers.put("question", "bla");
        Page page = new Page("page", answers);
        List<Page> pages = new ArrayList<>();
        pages.add(page);
        Context ctx = new Context(pages);
        new AnswerDateAfter("page", "question", "2010-01-01").evaluate(ctx);
    }

    @Test
    public void evaluate_returnsFalse_whenContextHasAnswerForQuestionWhichIsBeforeComparisonDate() {
        Map<String, String> answers = new HashMap<>();
        answers.put("question", "1970-01-01");
        Page page = new Page("page", answers);
        List<Page> pages = new ArrayList<>();
        pages.add(page);
        Context ctx = new Context(pages);
        assertFalse(new AnswerDateAfter("page", "question", "2010-01-01").evaluate(ctx));
    }

    @Test
    public void evaluate_returnsTrue_whenContextHasAnswerForQuestionWhichIsAfterComparisonDate() {
        Map<String, String> answers = new HashMap<>();
        answers.put("question", "2020-01-01");
        Page page = new Page("page", answers);
        List<Page> pages = new ArrayList<>();
        pages.add(page);
        Context ctx = new Context(pages);
        assertTrue(new AnswerDateAfter("page", "question", "2010-01-01").evaluate(ctx));
    }

}