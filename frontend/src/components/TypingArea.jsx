import { useEffect, useRef, useState } from "react";
import {
    getSnippet,
    saveAttempt,
} from "../api";
import Timer from "./Timer";
import Results from "../pages/Results";

function TypingArea({ language, onBack }) {
    const [snippet, setSnippet] = useState(null);
    const [typed, setTyped] = useState("");
    const [running, setRunning] = useState(false);
    const [finished, setFinished] = useState(false);
    const [metrics, setMetrics] = useState(null);

    const DURATION = 30;
    const startTime = useRef(null);

    useEffect(() => {
        getSnippet(language, 1).then(setSnippet);
    }, [language]);

    const finishTest = async (
        currentTyped = typed,
        elapsedTime = null
    ) => {
        if (!snippet || finished) return;

        const elapsed = elapsedTime ||
    Math.min(
        DURATION,
        Math.max(
            1,
            Math.ceil(
                (Date.now() - startTime.current) / 1000
            )
        )
    );

        let correct = 0;

        currentTyped.split("").forEach((char, index) => {
            if (char === snippet.code[index]) {
                correct++;
            }
        });

        const charactersTyped = currentTyped.length;
        const incorrect = charactersTyped - correct;
        const minutes = elapsed / 60;

        const cpm = Math.round(correct / minutes);
        const wpm = Math.round(cpm / 5);

        const rawWpm = Math.round(
            (charactersTyped / minutes) / 5
        );

        const accuracy = charactersTyped
            ? Math.round(
                  (correct / charactersTyped) * 100
              )
            : 0;

        const consistency = Math.max(
            0,
            Math.round(accuracy - incorrect * 0.5)
        );

        const calculatedMetrics = {
            wpm,
            cpm,
            rawWpm,
            accuracy,
            correctCharacters: correct,
            incorrectCharacters: incorrect,
            errors: incorrect,
            consistency
        };

        setMetrics(calculatedMetrics);
        setRunning(false);
        setFinished(true);

        // Save completed attempt
        try {
            const user = JSON.parse(
                localStorage.getItem("snaketypeUser")
            );

            await saveAttempt({
                userId: user.id,
                snippetId: snippet.id,
                languageId: snippet.language.id,
                mode: "TIMED",
                duration: elapsed,
                charactersTyped,
                correctCharacters: correct,
                incorrectCharacters: incorrect,
                cpm,
                wpm,
                rawSpeed: rawWpm,
                accuracy,
                consistency,
                errorCount: incorrect,
                complexity: snippet.complexity
            });

            console.log("Attempt saved successfully");
        } catch (error) {
            console.error("Failed to save attempt:", error);
        }
    };

    const handleChange = (event) => {
        const value = event.target.value;

        if (!running && value.length > 0) {
            startTime.current = Date.now();
            setRunning(true);
        }

        setTyped(value);

        if (
            snippet &&
            value.length >= snippet.code.length
        ) {
            const elapsed = Math.min(
                DURATION,
                Math.max(
                    1,
                    Math.ceil(
                        (Date.now() - startTime.current) / 1000
                    )
                )
            );

            finishTest(value, elapsed);
        }
    };

    const handleTimeUp = () => {
        finishTest();
    };

    const restart = () => {
        setTyped("");
        setRunning(false);
        setFinished(false);
        setMetrics(null);
        startTime.current = null;

        getSnippet(language, 1).then(setSnippet);
    };

    if (!snippet) {
        return (
            <div className="app">
                Loading snippet...
            </div>
        );
    }

    if (finished && metrics) {
        return (
            <Results
                metrics={metrics}
                onRestart={restart}
                onBack={onBack}
            />
        );
    }

    return (
        <div className="typing-page">
            <button
                className="back-button"
                onClick={onBack}
            >
                ← Languages
            </button>

            <div className="typing-header">
                <div>
                    <h2>{language}</h2>
                    <p>{snippet.title}</p>
                </div>

                <Timer
                    running={running}
                    duration={DURATION}
                    onTimeUp={handleTimeUp}
                />
            </div>

            <div className="code-box">
                {snippet.code.split("").map(
                    (char, index) => {
                        let className = "";

                        if (index < typed.length) {
                            className =
                                typed[index] === char
                                    ? "correct"
                                    : "incorrect";
                        } else if (
                            index === typed.length
                        ) {
                            className = "current";
                        }

                        return (
                            <span
                                className={className}
                                key={index}
                            >
                                {char}
                            </span>
                        );
                    }
                )}
            </div>

            <textarea
                className="typing-input"
                value={typed}
                onChange={handleChange}
                disabled={finished}
                autoFocus
                placeholder="Start typing the code here..."
                spellCheck="false"
            />
        </div>
    );
}

export default TypingArea;