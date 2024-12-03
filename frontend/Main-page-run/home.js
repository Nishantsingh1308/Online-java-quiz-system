// Function to close the page
document.getElementById('close-btn').addEventListener('click', function() {
    // Attempt to close the window/tab
    window.close();

    // For safety: Check if window.close() was successful
    // This fallback message ensures it doesn't break in browsers that block the action
    if (!window.closed) {
        alert("This window cannot be closed using this button.");
    }
});
