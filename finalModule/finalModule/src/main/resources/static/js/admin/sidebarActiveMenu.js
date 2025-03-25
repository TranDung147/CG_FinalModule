$(document).ready(function() {
    // Lấy đường dẫn URL hiện tại
    const path = window.location.pathname;
    console.log("Current path:", path);

    // Ban đầu ẩn tất cả submenu
    $('.treeview-menu').hide();

    // Hàm để kích hoạt menu và menu cha của nó
    function activateMenu($menuItem) {
        if ($menuItem && $menuItem.length > 0) {
            $menuItem.addClass('active');

            // Nếu menuItem là con của một treeview-menu, kích hoạt menu cha
            if ($menuItem.parent().hasClass('treeview-menu')) {
                const $parentTreeview = $menuItem.parent().parent();
                $parentTreeview.addClass('active open');
                $parentTreeview.find('> .treeview-menu').show();
            }
            return true;
        }
        return false;
    }

    // Kích hoạt menu dựa trên đường dẫn
    function highlightActivePath() {
        // Reset trạng thái active trước
        $('.sidebar-menu li').removeClass('active open');
        $('.treeview-menu').hide();

        // Sử dụng regex để kiểm tra chính xác các đường dẫn
        console.log("Testing path:", path);

        // Kiểm tra đường dẫn product-manager (bao gồm add và edit)
        if (path.includes('/Admin/product-manager')) {
            console.log("Product path detected");

            // Kích hoạt menu sản phẩm chính
            activateMenu($('#product-menu'));
            activateMenu($('#product-management-treeview'));

            // Log để debug
            console.log("Product menu activated:", $('#product-menu').length > 0);
            console.log("Product management treeview activated:", $('#product-management-treeview').length > 0);
        }
        // Kiểm tra warehouse paths (bao gồm import và export)
        else if (path.includes('/Admin/ware-houses')) {
            console.log("Warehouse path detected");
            activateMenu($('#warehouse-menu'));
        }
        // Kiểm tra transaction history paths
        else if (path.includes('/Admin/transactions')) {
            console.log("Transaction path detected");
            activateMenu($('#transactions-menu'));
        }
        // Các đường dẫn khác
        else if (path === '/Admin' || path === '/Admin/') {
            activateMenu($('#dashboard-menu'));
        }
        else if (path.includes('/Admin/customers')) {
            activateMenu($('#customers-menu'));
            activateMenu($('#sales-management-treeview'));
        }
        else if (path.includes('/Admin/order')) {
            activateMenu($('#orders-menu'));
            activateMenu($('#sales-management-treeview'));
        }
        else if (path.includes('/Admin/report')) {
            activateMenu($('#customer-report-menu'));
            activateMenu($('#reports-treeview'));
        }
        else if (path.includes('/sales/report')) {
            activateMenu($('#sales-report-menu'));
            activateMenu($('#reports-treeview'));
        }
        else if (path.includes('/Admin/suppliers-manager')) {
            activateMenu($('#suppliers-menu'));
        }
        else if (path.includes('/Admin/employee-manager')) {
            activateMenu($('#employee-menu'));
        }
        else if (path.includes('/Admin/category-manager')) {
            activateMenu($('#category-menu'));
            activateMenu($('#product-management-treeview'));
        }
        else if (path.includes('/Admin/brand-manager')) {
            activateMenu($('#brand-menu'));
            activateMenu($('#product-management-treeview'));
        }
    }
    // Áp dụng highlight ban đầu
    highlightActivePath();

    // Handler cho treeview menu clicks (kiểu Shopee)
    $('.treeview > a').on('click', function(e) {
        e.preventDefault();

        const $parentLi = $(this).parent();
        const $submenu = $(this).next('.treeview-menu');
        const isOpen = $submenu.is(':visible');

        // Toggle chỉ menu này
        if (isOpen) {
            // Không đóng menu đang active
            if (!$parentLi.hasClass('active')) {
                $submenu.slideUp(200);
                $parentLi.removeClass('open');
            }
        } else {
            $submenu.slideDown(200);
            $parentLi.addClass('open');
        }
    });

    // Handler for direct menu items
    $('.sidebar-menu > li:not(.treeview) > a').on('click', function() {
        const $menuItem = $(this).parent();
        $('.sidebar-menu > li').removeClass('active');
        $menuItem.addClass('active');
    });

    // Hiệu ứng hover
    $('.sidebar-menu li').hover(
        function() {
            if (!$(this).hasClass('active')) {
                $(this).addClass('hover');
            }
        },
        function() {
            $(this).removeClass('hover');
        }
    );
});