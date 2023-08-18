package org.kie.kogito.codegen.data;

public class QuestionWithAnnotatedEnum {

    private String question;
    private AnswerWithAnnotations answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public AnswerWithAnnotations getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerWithAnnotations answer) {
        this.answer = answer;
    }

}
