document.addEventListener('DOMContentLoaded', function() {
    // Check if modal should be shown (after validation errors)
    const showAddEmployeeModal = /*[[${showAddEmployeeModal}]]*/ false;
    if (showAddEmployeeModal) {
        const modal = new bootstrap.Modal(document.getElementById('addEmployeeModal'));
        modal.show();
    }
});
document.addEventListener('DOMContentLoaded', function() {
    // Xử lý khi click nút sửa nhân viên
    document.querySelectorAll('.btn-edit-employee').forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            const employeeId = this.getAttribute('data-employee-id');
            fetchEmployeeData(employeeId);
        });
    });

    // Check if modal should be shown (after validation errors)
    const showEditEmployeeModal = /*[[${showEditEmployeeModal}]]*/ false;
    if (showEditEmployeeModal) {
        const modal = new bootstrap.Modal(document.getElementById('editEmployeeModal'));
        modal.show();
    }
});

// Thêm vào phần JavaScript hiện có
document.addEventListener('DOMContentLoaded', function() {
    // Code hiện tại của bạn...

    // Thêm xử lý nút hiển thị/ẩn mật khẩu
    const togglePassword = document.getElementById('togglePassword');
    const passwordField = document.getElementById('editPassword');
    const toggleIcon = document.getElementById('togglePasswordIcon');

    if (togglePassword) {
        togglePassword.addEventListener('click', function() {
            // Chuyển đổi loại trường nhập liệu
            const type = passwordField.getAttribute('type') === 'password' ? 'text' : 'password';
            passwordField.setAttribute('type', type);

            // Đổi icon
            if (type === 'text') {
                toggleIcon.classList.remove('fa-eye');
                toggleIcon.classList.add('fa-eye-slash');
            } else {
                toggleIcon.classList.remove('fa-eye-slash');
                toggleIcon.classList.add('fa-eye');
            }
        });
    }
});

function fetchEmployeeData(employeeId) {
    // Gọi API để lấy thông tin nhân viên
    fetch(`/Admin/employee-manager/get/${employeeId}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('editEmployeeId').value = data.employeeId;
            document.getElementById('editUserId').value = data.userId;
            document.getElementById('editEmployeeName').value = data.employeeName;
            document.getElementById('editEmployeeBirthday').value = data.employeeBirthday;
            document.getElementById('editEmail').value = data.email;
            document.getElementById('editEmployeePhone').value = data.employeePhone;
            document.getElementById('editEmployeeAddress').value = data.employeeAddress;
            document.getElementById('editEmployeePosition').value = data.employeePosition;
            document.getElementById('editUsername').value = data.username;
            document.getElementById('editPassword').value = data.password;

            // Hiển thị modal
            const modal = new bootstrap.Modal(document.getElementById('editEmployeeModal'));
            modal.show();
        })
        .catch(error => {
            console.error('Error fetching employee data:', error);
            Swal.fire({
                icon: 'error',
                title: 'Lỗi',
                text: 'Không thể tải thông tin nhân viên. Vui lòng thử lại sau.'
            });
        });
}