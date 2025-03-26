$(document).ready(function() {
    // Get current URL path
    const path = window.location.pathname;
    console.log("Current path:", path);

    // Initially hide all submenus
    $('.treeview-menu').hide();

    // Function to activate menu and its parent
    function activateMenu($menuItem) {
        if ($menuItem && $menuItem.length > 0) {
            $menuItem.addClass('active');

            // If menuItem is inside a treeview-menu, activate and open its parent
            if ($menuItem.parent().hasClass('treeview-menu')) {
                const $parentTreeview = $menuItem.parent().parent();
                $parentTreeview.addClass('active open');
                $parentTreeview.find('> .treeview-menu').show();
            }
            return true;
        }
        return false;
    }

    // Highlight active path
    function highlightActivePath() {
        // Reset active states
        $('.sidebar-menu li').removeClass('active open');
        $('.treeview-menu').hide();

        // Detailed path matching
        if (path === '/Admin' || path === '/Admin/') {
            activateMenu($('#dashboard-menu'));
        }
        else if (path.includes('/Admin/report') || path.includes('/Admin/statistical')) {
            activateMenu($('#report-menu'));

            if (path.includes('/Admin/report')) {
                $('a[href*="/Admin/report"]').parent().addClass('active');
            } else if (path.includes('/Admin/statistical')) {
                $('a[href="/Admin/statistical?type=day"]').parent().addClass('active');
            }
        }
        else if (path.includes('/Admin/order/add') || path.includes('/Admin/customers')) {
            activateMenu($('#sales-menu'));

            if (path.includes('/Admin/order/add')) {
                $('a[href*="/Admin/order/add"]').parent().addClass('active');
            } else if (path.includes('/Admin/customers')) {
                $('a[href*="/Admin/customers"]').parent().addClass('active');
            }
        }
        else if (path.includes('/Admin/suppliers-manager')) {
            activateMenu($('#supplier-menu'));
        }
        else if (path.includes('/Admin/ware-houses')) {
            activateMenu($('#warehouse-menu'));
        }
        else if (path.includes('/Admin/employee-manager')) {
            activateMenu($('#employee-menu'));
        }
        else if (path.includes('/Admin/category-manager') ||
            path.includes('/Admin/brand-manager') ||
            path.includes('/Admin/product-manager')) {
            // Đảm bảo menu Quản lý Hàng hoá luôn mở ra và được highlight
            const $goodsMenu = $('#goods-menu');
            $goodsMenu.addClass('active open');
            $goodsMenu.find('.treeview-menu').show();

            if (path.includes('/Admin/category-manager')) {
                $('#category-menu').addClass('active');
            } else if (path.includes('/Admin/brand-manager')) {
                $('#brand-menu').addClass('active');
            } else if (path.includes('/Admin/product-manager')) {
                $('#product-menu').addClass('active');
            }
        }
    }
    // Apply initial highlight
    highlightActivePath();

    // Treeview menu click handler
    $('.treeview > a').on('click', function(e) {
        e.preventDefault();
        const $parentLi = $(this).parent();
        const $submenu = $(this).next('.treeview-menu');
        const isOpen = $submenu.is(':visible');

        // Close all other submenus
        $('.treeview-menu').not($submenu).slideUp(200);
        $('.treeview').not($parentLi).removeClass('open');

        // Toggle current menu
        if (isOpen) {
            // Don't close active menu
            if (!$parentLi.hasClass('active')) {
                $submenu.slideUp(200);
                $parentLi.removeClass('open');
            }
        } else {
            $submenu.slideDown(200);
            $parentLi.addClass('open');
        }
    });

    // Direct menu items handler
    $('.sidebar-menu > li:not(.treeview) > a').on('click', function() {
        const $menuItem = $(this).parent();
        $('.sidebar-menu > li').removeClass('active');
        $menuItem.addClass('active');
    });

    // Hover effects
    $('.sidebar-menu li').hover(
        function() {
            if (!$(this).hasClass('active')) {
                $(this).addClass('hover');

                // Open submenu on hover for treeview
                if ($(this).hasClass('treeview')) {
                    $(this).find('> .treeview-menu').stop(true, true).slideDown(200);
                }
            }
        },
        function() {
            $(this).removeClass('hover');

            // Close submenu if not active
            if ($(this).hasClass('treeview') && !$(this).hasClass('active')) {
                $(this).find('> .treeview-menu').stop(true, true).slideUp(200);
            }
        }
    );
});