// Chọn tất cả checkbox
document.getElementById("selectAll").addEventListener("click", function() {
    let checkboxes = document.querySelectorAll(".employeeCheckbox");
    checkboxes.forEach(checkbox => checkbox.checked = this.checked);
});

// Gửi danh sách nhân viên đã chọn để vô hiệu hóa
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

    fetch("/Admin/disable", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ employeeIds: selectedIds })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                location.reload(); // Reload lại trang để cập nhật trạng thái
            } else {
                alert("Có lỗi xảy ra, vui lòng thử lại!");
            }
        })
        .catch(error => console.error("Lỗi:", error));
}