package com.zidnyscience.model;

import java.util.List;

public class BeTest {
    private int id;
    private String the_question;
    private List<Options> Options;
    private Answer answer_char;


    public BeTest(int id, String the_question, List<Options> options, Answer answer_char) {
        this.id = id;
        this.the_question = the_question;
        this.Options = options;
        this.answer_char = answer_char;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThe_question() {
        return the_question;
    }

    public void setThe_question(String the_question) {
        this.the_question = the_question;
    }

    public List<Options> getOptions() {
        return Options;
    }

    public void setOptions(List<Options> options) {
        Options = options;
    }

    public Answer getAnswer_char() {
        return answer_char;
    }

    public void setAnswer_char(Answer answer_char) {
        this.answer_char = answer_char;
    }

    public enum Answer{
        A,B,C,D
    }

    public enum TestStatus{
        UNSelected,TURE,FALSE
    }


    public static class Options {
        private BeTest.Answer option_char;
        private String option;
        private BeTest.TestStatus testStatus;

        public Options(BeTest.Answer option_char, String option, BeTest.TestStatus testStatus) {
            this.option_char = option_char;
            this.option = option;
            this.testStatus = testStatus;
        }

        public BeTest.TestStatus getTestStatus() {
            return testStatus;
        }

        public void setTestStatus(BeTest.TestStatus testStatus) {
            this.testStatus = testStatus;
        }

        public BeTest.Answer getOption_char() {
            return option_char;
        }

        public void setOption_char(BeTest.Answer option_char) {
            this.option_char = option_char;
        }

        public String getOption() {
            return option;
        }

        public void setOption(String option) {
            this.option = option;
        }
    }


}
