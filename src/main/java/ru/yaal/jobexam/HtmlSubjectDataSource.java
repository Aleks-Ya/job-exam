package ru.yaal.jobexam;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksey Yablokov
 */
public class HtmlSubjectDataSource implements SubjectsDateSource {
    private File baseDir;

    public HtmlSubjectDataSource(File baseDir) {
        assert baseDir.exists();
        this.baseDir = baseDir;
    }

    public Subject takeSubjectByPath(String path) throws IOException {
        File file = new File(baseDir, path);
        if (file.exists()) {
            Document doc = Jsoup.parse(file, "UTF-8");

            try {
                List<Question> questions = parseQuestions(doc);
                String name = doc.title();
                return new SubjectImpl(questions, name, path);
            } catch (IllegalStateException e) {
                throw new IllegalStateException("Can't parse " + file.getAbsolutePath());
            }
        } else {
            throw new FileNotFoundException(file.getAbsolutePath());
        }
    }

    private List<Question> parseQuestions(Document doc) {
        List<Question> questions = new ArrayList<Question>();
        Elements questionDivs = doc.body().children();
        for (Element question : questionDivs) {
            if (question.tagName().equals("div")) {
                Elements divs = question.getElementsByTag("div");
                //element #0 is parent <div>
                String questionText = divs.get(1).html();
                List<Answer> answers = new ArrayList<Answer>();
                for (int i = 2; i < divs.size(); i++) {
                    Element answerDiv = divs.get(i);
                    boolean isRight = answerDiv.hasAttr("data-right-answer");
                    answers.add(new AnswerImpl(answerDiv.html(), isRight));
                }
                if (answers.isEmpty()) {
                    throw new IllegalStateException("Question must have >=3 divs (question and 2 answers");
                }
                questions.add(new QuestionImpl(questionText, answers));
            }
        }
        if (questions.isEmpty()) {
            throw new IllegalStateException("No questions in file");
        }
        return questions;
    }
}
