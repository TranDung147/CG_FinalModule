document.getElementById("selectAll").addEventListener("click", function() {
    let checkboxes = document.querySelectorAll(".employeeCheckbox");
    checkboxes.forEach(checkbox => checkbox.checked = this.checked);
});

function disableSelectedEmployees() {
    let selectedIds = [];
    document.querySelectorAll(".employeeCheckbox:checked").forEach(checkbox => {
        selectedIds.push(checkbox.value);
    });

    if (selectedIds.length === 0) {
        alert("Vui lòng chọn ít nhất một nhân viên!");
        return;
    }

    if (!confirm("Bạn có chắc chắn muốn xoá những nhân viên đã chọn không?")) {
        return;
    }

    fetch("/Admin/employee-manager/disable", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ employeeIds: selectedIds })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                location.reload();
            } else {
                alert("Có lỗi xảy ra, vui lòng thử lại!");
            }
        })
        .catch(error => console.error("Lỗi:", error));
}