// Function to delete all questions from LocalStorage
function deleteAllQuestions() {
    // Confirm deletion
    if (confirm("Are you sure you want to delete all questions? This action cannot be undone.")) {
        // Clear the questions from LocalStorage
        localStorage.removeItem('questions');

        // Optionally clear any student answers tied to the questions
        // localStorage.removeItem('studentAnswers'); // Uncomment if implemented

        // Reload the page to reflect the changes
        displayQuestions();

        // Show confirmation notification
        showNotification("All questions have been deleted successfully!", "success");
    }
}

// Function to display all saved questions
function displayQuestions() {
    const questionsList = document.getElementById('questionsList'); // Use the correct ID from your HTML

    // Retrieve questions from LocalStorage
    let questions = JSON.parse(localStorage.getItem('questions')) || [];

    // Clear the current list
    questionsList.innerHTML = '';

    // If no questions are available
    if (questions.length === 0) {
        questionsList.innerHTML = "<p>No questions available.</p>";
    } else {
        // Add each question to the list
        questions.forEach((question, index) => {
            const questionDiv = document.createElement('div');
            questionDiv.classList.add('question-item');
            questionDiv.innerHTML = `
                <p><strong>Q${index + 1}: </strong>${question}</p>
            `;
            questionsList.appendChild(questionDiv);
        });
    }
}

// Function to show a notification (needs implementation)
function showNotification(message, type) {
    // Placeholder for now - you can implement a notification system here
    console.log(`Notification: ${message} (Type: ${type})`); // For now, just log the message
}

// Attach event listener to the "Delete All Questions" button
document.getElementById('delete-all-btn').addEventListener('click', deleteAllQuestions);

// Call the function to display questions when the page loads
window.onload = displayQuestions;
