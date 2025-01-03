// Timer variables
let timer;
let hours = 0;
let minutes = 0;
let seconds = 0;
let isRunning = false;

// Function to start and update the timer
function startTimer() {
    if (!isRunning) {
        timer = setInterval(function () {
            seconds++;
            if (seconds >= 60) {
                seconds = 0;
                minutes++;
            }
            if (minutes >= 60) {
                minutes = 0;
                hours++;
            }

            // Format the time as HH:MM:SS
            const formattedTime = `${String(hours).padStart(2, '0')}:${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;
            document.getElementById('timer').textContent = formattedTime;
        }, 1000);
        isRunning = true;
    }
}

// Function to stop the timer
function stopTimer() {
    clearInterval(timer);
    isRunning = false;
}

// Function to dynamically display the questions
function displayQuestions() {
    // Retrieve questions from LocalStorage
    const questions = JSON.parse(localStorage.getItem('questions')) || [];
    const questionsContainer = document.getElementById('questions-container');
    questionsContainer.innerHTML = ''; // Clear previous content

    if (questions.length === 0) {
        questionsContainer.innerHTML = '<p>No questions available. Please contact the administrator.</p>';
        return;
    }

    // Populate questions dynamically
    questions.forEach((q, index) => {
        const questionDiv = document.createElement('div');
        questionDiv.classList.add('question');
        questionDiv.innerHTML = `
            <p><strong>Q${index + 1}: </strong>${q.question}</p>
            <textarea id="answer${index}" placeholder="Type your answer here..."></textarea>
        `;
        questionsContainer.appendChild(questionDiv);
    });
}

// Function to submit the exam
function submitExam() {
    stopTimer(); // Stop the timer on submission

    // Retrieve the questions from LocalStorage
    const questions = JSON.parse(localStorage.getItem('questions')) || [];

    // Collect student answers
    const studentAnswers = questions.map((q, index) => ({
        question: q.question,
        answer: document.getElementById(`answer${index}`).value,
    }));

    // Structure the submission data with a timestamp
    const submissionData = {
        answers: studentAnswers,
        timestamp: new Date().toISOString(),
    };

    // Save the submission to LocalStorage for later retrieval
    localStorage.setItem('studentAnswers', JSON.stringify(submissionData));

    // Display confirmation and redirect
    alert('Your answers have been submitted! You will now be redirected to the homepage.');
    window.location.href = 'home.html'; // Redirect to homepage
}

// Attach event listener to the submit button
document.getElementById('submit-exam-btn').addEventListener('click', submitExam);

// Start the timer and display questions when the page loads
window.onload = function () {
    startTimer(); // Start the exam timer
    displayQuestions(); // Display the exam questions
};