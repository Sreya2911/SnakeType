package com.snaketype.data;

import com.snaketype.entity.Language;
import com.snaketype.entity.Snippet;
import com.snaketype.repository.LanguageRepository;
import com.snaketype.repository.SnippetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final LanguageRepository languageRepository;
    private final SnippetRepository snippetRepository;

    public DataInitializer(LanguageRepository languageRepository,
                           SnippetRepository snippetRepository) {
        this.languageRepository = languageRepository;
        this.snippetRepository = snippetRepository;
    }

    @Override
    public void run(String... args) {

        if (languageRepository.count() > 0) {
            return;
        }

        addLanguage("HTML", "Structure", "Basic HTML",
                "<div class=\"card\">\n  <h1>Hello World</h1>\n</div>", 1);

        addLanguage("CSS", "Styling", "Basic CSS",
                ".card {\n  padding: 20px;\n  border-radius: 8px;\n}", 1);

        addLanguage("JavaScript", "Basics", "Function",
                "function greet(name) {\n  return `Hello ${name}`;\n}", 1);

        addLanguage("Python", "Basics", "Function",
                "def greet(name):\n    return f\"Hello {name}\"", 1);

        addLanguage("C", "Basics", "Hello World",
                "#include <stdio.h>\n\nint main() {\n    printf(\"Hello\");\n    return 0;\n}", 1);

        addLanguage("C++", "Basics", "Hello World",
                "#include <iostream>\nusing namespace std;\n\nint main() {\n    cout << \"Hello\";\n    return 0;\n}", 1);

        addLanguage("Java", "Basics", "Hello World",
                "public class Main {\n    public static void main(String[] args) {\n        System.out.println(\"Hello\");\n    }\n}", 1);

        addLanguage("MySQL", "Queries", "Select Query",
                "SELECT name, email\nFROM users\nWHERE id = 1;", 1);
    }

    private void addLanguage(String languageName,
                             String topic,
                             String title,
                             String code,
                             int complexity) {

        Language language = languageRepository.save(
                new Language(languageName)
        );

        snippetRepository.save(
                new Snippet(language, topic, title, code, complexity)
        );
    }
}