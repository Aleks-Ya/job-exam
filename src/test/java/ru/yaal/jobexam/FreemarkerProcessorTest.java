package ru.yaal.jobexam;

import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * @author Aleksey Yablokov
 */
public class FreemarkerProcessorTest {

    @Test
    public void testProcess() throws IOException, TemplateException, URISyntaxException {
        List<? extends Answer> answers = Arrays.asList(
                new AnswerImpl("Сорок", true),
                new AnswerImpl("У нее лапки", false)
        );
        List<? extends Question> questions = Collections.singletonList(
                new QuestionImpl("Сколько ног у сороконожки?", answers));
        Subject subject = new SubjectImpl(questions, "Зоология", "zoo/legs.html");

        FreemarkerProcessor fp = new FreemarkerProcessor();
        String actual = fp.process(subject);

        Path path = Paths.get(FreemarkerProcessorTest.class.getResource("expected_freemarker_processor_output.txt").toURI());
        String expected = Files.readAllLines(path).stream().collect(Collectors.joining("\n"));
        assertEquals(expected, actual);
    }
}