document.addEventListener("DOMContentLoaded", function () {
    let selectAll = document.getElementById("selectAll");
    let checkboxes = document.querySelectorAll(".customerCheckbox");
    let deleteBtn = document.getElementById("deleteBtn");

    // Chọn tất cả
    selectAll.addEventListener("change", function () {
        checkboxes.forEach(checkbox => {
            checkbox.checked = this.checked;
        });
    });

});

// document.addEventListener("DOMContentLoaded", function () {
//     document.querySelectorAll(".editCustomerBtn").forEach(button => {
//         button.addEventListener("click", function () {
//             let categoryId = this.getAttribute("data-id");
//             let categoryName = this.getAttribute("data-name");
//             let categoryDescription = this.getAttribute("data-description");
//
//             document.getElementById("editCategoryId").value = categoryId;
//             document.getElementById("editCategoryName").value = categoryName;
//             document.getElementById("editCategoryDescription").value = categoryDescription;
//
//             new bootstrap.Modal(document.getElementById("editCategoryModal")).show();
//         });
//     });
// });

function deleteSelectedCustomer() {
    let selectedIds = [];
    document.querySelectorAll(".customerCheckbox:checked").forEach(checkbox => {
        selectedIds.push(checkbox.value);
    });

    if (selectedIds.length === 0) {
        Swal.fire({
            icon: "warning",
            title: "Chưa chọn khách hàng!",
            text: "Vui lòng chọn ít nhất một khách hàng trước khi xóa.",
            confirmButtonText: "OK"
        });
        return;
    }

    Swal.fire({
        title: "Bạn có chắc chắn?",
        text: "Những khách hàng đã chọn sẽ bị xóa!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#d33",
        cancelButtonColor: "#3085d6",
        confirmButtonText: "Xóa",
        cancelButtonText: "Hủy"
    }).then((result) => {
        if (result.isConfirmed) {
            fetch("/Admin/customer-management/delete", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(selectedIds)
            })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        Swal.fire({
                            icon: "success",
                            title: "Thành công!",
                            text: "Khách hàng đã được xóa.",
                            confirmButtonText: "OK"
                        }).then(() => {
                            location.reload();
                        });
                    } else {
                        Swal.fire({
                            icon: "error",
                            title: "Lỗi!",
                            text: "Có lỗi xảy ra, vui lòng thử lại!",
                            confirmButtonText: "OK"
                        });
                    }
                })
                .catch(error => {
                    console.error("Lỗi:", error);
                    Swal.fire({
                        icon: "error",
                        title: "Lỗi hệ thống!",
                        text: "Không thể kết nối đến server.",
                        confirmButtonText: "OK"
                    });
                });
        }
    });
}