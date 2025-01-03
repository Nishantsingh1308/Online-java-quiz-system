// Function to delete all questions from LocalStorage
function deleteAllQuestions() {
    // Confirm deletion
    if (confirm("Are you sure you want to delete all questions? This action cannot be undone.")) {
        // Clear the questions from LocalStorage
        localStorage.removeItem('questions');

        // Reload the page to reflect the changes
        loadQuestions();

        // Optionally, show a confirmation message (this can be a notification as well)
        document.getElementById('status-message').textContent = "All questions have been deleted!";
    }
}

// Function to add a new question
function addQuestion() {
    const questionInput = document.getElementById('question-input');
    const questionText = questionInput.value.trim();

    if (!questionText) {
        // Show a status message if the input is empty
        document.getElementById('status-message').textContent = 'Please enter a question before saving.';
        return;
    }

    // Retrieve existing questions from LocalStorage or initialize an empty array
    let questions = JSON.parse(localStorage.getItem('questions')) || [];

    // Create a new question object with a unique ID
    const newQuestion = {
        id: questions.length + 1, // Incremental ID based on array length
        question: questionText
    };

    // Add the new question to the array
    questions.push(newQuestion);

    // Save updated questions array to LocalStorage
    localStorage.setItem('questions', JSON.stringify(questions));

    // Display updated status message
    document.getElementById('status-message').textContent = 'Question saved successfully!';

    // Clear the input field
    questionInput.value = '';

    // Reload the question list immediately after saving
    loadQuestions(); // This will refresh the question list on the current page

    // Optionally, call loadQuestions from view-question.js on the 'view' page to refresh there as well
    window.location.reload(); // This reloads the current page to reflect added questions
}

// Function to load and display existing questions
function loadQuestions() {
    const questions = JSON.parse(localStorage.getItem('questions')) || [];
    const questionsContainer = document.getElementById('questions-list');
    questionsContainer.innerHTML = ''; // Clear previous content

    if (questions.length === 0) {
        questionsContainer.textContent = 'No questions available. Add some questions to start.';
        return;
    }

    // Dynamically display each question
    questions.forEach((q) => {
        const questionDiv = document.createElement('div');
        questionDiv.classList.add('question-item');
        questionDiv.textContent = `Q${q.id}: ${q.question}`;
        questionsContainer.appendChild(questionDiv);
    });
}

// Function to discard input changes
function discardQuestion() {
    const questionInput = document.getElementById('question-input');
    questionInput.value = ''; // Clear the input field
    document.getElementById('status-message').textContent = 'Changes discarded.';
}

// Attach event listeners for adding and discarding questions
document.getElementById('add-question-btn').addEventListener('click', addQuestion);
document.getElementById('discard-question-btn').addEventListener('click', discardQuestion);

// Load existing questions when the page loads
window.onload = loadQuestions;