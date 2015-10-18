package ru.yaal.jobexam;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * @author Aleksey Yablokov
 */
public class HtmlSubjectDataSourceTest {

    @Test
    public void testTakeSubjectByPath() throws Exception {
        String path = "programmer/spring.html";
        File baseDir = new File(HtmlSubjectDataSourceTest.class.getResource(path).toURI()).getParentFile().getParentFile();
        SubjectsDateSource sds = new HtmlSubjectDataSource(baseDir);
        Subject subject = sds.takeSubjectByPath(path);

        assertThat(subject.getName(), equalTo("Опрос по Spring"));
        assertThat(subject.getPath(), equalTo(path));

        List<Question> questions = subject.getQuestions();
        assertThat(questions, hasSize(2));

        Question q1 = questions.get(0);
        assertThat(q1.getText(), containsString("Какой аннотации не хватает на классе?\n<br>"));
        assertThat(q1.getAnswers(), contains(new Answer[]{
                new AnswerImpl("<pre><code>@Settings</code></pre>", false),
                new AnswerImpl("<code>@Component</code>", false),
                new AnswerImpl("<code>@Configuration</code>", true),
                new AnswerImpl("<code>@Beans</code>", false),
                new AnswerImpl("<code>@Server</code>", false),
                new AnswerImpl("<code>@ServerBean</code>", false),
                new AnswerImpl("Нет правильного ответа", false)}));
    }
}