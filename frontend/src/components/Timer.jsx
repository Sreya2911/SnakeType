import { useEffect, useRef, useState } from "react";

function Timer({ running, duration = 30, onTimeUp }) {
    const [time, setTime] = useState(duration);
    const onTimeUpRef = useRef(onTimeUp);

    useEffect(() => {
        onTimeUpRef.current = onTimeUp;
    }, [onTimeUp]);

    useEffect(() => {
        if (!running) return;

        const startTime = Date.now();

        const interval = setInterval(() => {
            const elapsed = Math.floor((Date.now() - startTime) / 1000);
            const remaining = Math.max(duration - elapsed, 0);

            setTime(remaining);

            if (remaining === 0) {
                clearInterval(interval);
                onTimeUpRef.current();
            }
        }, 100);

        return () => clearInterval(interval);
    }, [running, duration]);

    useEffect(() => {
        if (!running) {
            setTime(duration);
        }
    }, [running, duration]);

    return <div className="timer">{time}s</div>;
}

export default Timer;