$(document).ready(function() {
    // Get current URL path
    const path = window.location.pathname;

    // Initially hide all submenus
    $('.treeview-menu').hide();

    // Helper function to activate a menu and its parent
    function activateMenu($menuItem) {
        $menuItem.addClass('active');
        if ($menuItem.parent().hasClass('treeview-menu')) {
            const $parentTreeview = $menuItem.parent().parent();
            $parentTreeview.addClass('active open');
            $parentTreeview.find('> .treeview-menu').show();
        }
    }

    // Highlight active menu based on current path
    function highlightActivePath() {
        // Reset active state first
        $('.sidebar-menu li').removeClass('active open');
        $('.treeview-menu').hide();

        // Check each path condition and activate the appropriate menu
        if (path === '/Admin' || path === '/Admin/') {
            // Dashboard is active
            activateMenu($('a[href="/Admin"]').parent());
        }
        else if (path.includes('/Admin/customers')) {
            // Customers page
            activateMenu($('a[href="/Admin/customers"]').parent());
            activateMenu($('.sidebar-menu a:contains("Quản lý Bán hàng")').parent());
        }
        else if (path.includes('/Admin/order')) {
            // Orders page
            activateMenu($('a[href="/Admin/order/add"]').parent());
            activateMenu($('.sidebar-menu a:contains("Quản lý Bán hàng")').parent());
        }
        else if (path.includes('/Admin/report')) {
            // Customer reports
            activateMenu($('a[href="/Admin/report"]').parent());
            activateMenu($('.sidebar-menu a:contains("Báo cáo")').parent());
        }
        else if (path.includes('/sales/report')) {
            // Sales reports
            activateMenu($('a[href="/sales/report"]').parent());
            activateMenu($('.sidebar-menu a:contains("Báo cáo")').parent());
        }
        else if (path.includes('/Admin/suppliers-manager')) {
            // Suppliers management
            const $supplierMenuItem = $('a[href="/Admin/suppliers-manager"]').parent();
            activateMenu($supplierMenuItem);
            $supplierMenuItem.addClass('active');
            // If it's inside a submenu, uncomment and modify the line below:
            // activateMenu($('.sidebar-menu a:contains("Quản lý hệ thống")').parent());
        }
        else if (path.includes('/Admin/ware-houses')) {
            // Warehouse management
            activateMenu($('a[href="/Admin/ware-houses"]').parent());
        }
        else if (path.includes('/Admin/employee-manager')) {
            // Employee management
            activateMenu($('a[href="/Admin/employee-manager"]').parent());
        }
        else if (path.includes('/Admin/category-manager')) {
            // Category management
            activateMenu($('a[href="/Admin/category-manager"]').parent());
            activateMenu($('.sidebar-menu a:contains("Quản lý Hàng hoá")').parent());
        }
        else if (path.includes('/Admin/brand-manager')) {
            // Brand management
            activateMenu($('a[href="/Admin/brand-manager"]').parent());
            activateMenu($('.sidebar-menu a:contains("Quản lý Hàng hoá")').parent());
        }
        else if (path.includes('/Admin/product-manager')) {
            // Product management
            activateMenu($('a[href="/Admin/product-manager"]').parent());
            activateMenu($('.sidebar-menu a:contains("Quản lý Hàng hoá")').parent());
        }

        // Adding debug console log to verify path detection
        console.log("Current path:", path);
        console.log("Supplier menu item exists:", $('a[href="/Admin/suppliers-manager"]').length > 0);
    }

    // Apply initial highlighting
    highlightActivePath();

    // Handler for treeview menu clicks (Shopee style)
    $('.treeview > a').on('click', function(e) {
        e.preventDefault();

        const $parentLi = $(this).parent();
        const $submenu = $(this).next('.treeview-menu');
        const isOpen = $submenu.is(':visible');

        // Toggle only this menu's visibility
        if (isOpen) {
            // Don't close active menus in Shopee style
            if (!$parentLi.hasClass('active')) {
                $submenu.slideUp(200);
                $parentLi.removeClass('open');
            }
        } else {
            $submenu.slideDown(200);
            $parentLi.addClass('open');
        }
    });

    // Handler for direct menu items (non-treeview items like "Quản lý Nhà cung cấp")
    $('.sidebar-menu > li:not(.treeview) > a').on('click', function() {
        // Add active class when clicking these items
        const $menuItem = $(this).parent();
        $('.sidebar-menu > li').removeClass('active');
        $menuItem.addClass('active');
    });

    // Apply a subtle highlight effect to menu items on hover
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
    $('<style>')
        .prop('type', 'text/css')
        .html(`
            .sidebar-menu .active > a {
                font-weight: bold;
                background-color: rgba(255, 255, 255, 0.1);
            }
            .sidebar-menu .hover > a {
                background-color: rgba(255, 255, 255, 0.05);
            }
            .treeview.active {
                background-color: rgba(255, 255, 255, 0.05);
            }
        `)
        .appendTo('head');
});