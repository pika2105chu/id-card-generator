function generateID() {
    const name = document.getElementById("name").value;
    const year = document.getElementById("year").value;
    const section = document.getElementById("section").value;
    const branch = document.getElementById("branch").value;
    const bloodGroup = document.getElementById("bloodGroup").value;
    const enrollment = document.getElementById("enrollment").value;
    const mobile = document.getElementById("mobile").value;
    const photoInput = document.getElementById("photo");

    // Set ID card values
    document.getElementById("cardName").innerText = name;
    document.getElementById("cardYear").innerText = year;
    document.getElementById("cardSection").innerText = section;
    document.getElementById("cardBranch").innerText = branch;
    document.getElementById("cardBloodGroup").innerText = bloodGroup;
    document.getElementById("cardEnrollment").innerText = enrollment;
    document.getElementById("cardMobile").innerText = mobile;

    // Handle profile photo
    if (photoInput.files && photoInput.files[0]) {
        const reader = new FileReader();
        reader.onload = function (e) {
            document.getElementById("profileImg").src = e.target.result;
        };
        reader.readAsDataURL(photoInput.files[0]);
    }

    // Show the ID card
    document.getElementById("idCardContainer").style.display = "block";
}
