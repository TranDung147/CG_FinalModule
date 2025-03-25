$(document).ready(function() {
    const path = window.location.pathname;

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

        // Loop over all treeview items to check and highlight them
        $('.sidebar-menu li.treeview > a').each(function() {
            const $this = $(this);
            const menuPath = $this.attr('href');

            if (path.includes(menuPath)) {
                activateMenu($this.parent());
            }
        });

        // Check specific cases where path matches particular menu
        if (path === '/Admin' || path === '/Admin/') {
            activateMenu($('a[href="/Admin"]').parent());
        }
        else if (path.includes('/Admin/customers')) {
            activateMenu($('a[href="/Admin/customers"]').parent());
        }
        else if (path.includes('/Admin/order')) {
            activateMenu($('a[href="/Admin/order/add"]').parent());
        }
        else if (path.includes('/Admin/report')) {
            activateMenu($('a[href="/Admin/report"]').parent());
        }
        else if (path.includes('/Admin/suppliers-manager')) {
            activateMenu($('a[href="/Admin/suppliers-manager"]').parent());
        }
        else if (path.includes('/Admin/ware-houses')) {
            activateMenu($('a[href="/Admin/ware-houses"]').parent());
        }
        else if (path.includes('/Admin/employee-manager')) {
            activateMenu($('a[href="/Admin/employee-manager"]').parent());
        }
        else if (path.includes('/Admin/category-manager')) {
            activateMenu($('a[href="/Admin/category-manager"]').parent());
        }
        else if (path.includes('/Admin/brand-manager')) {
            activateMenu($('a[href="/Admin/brand-manager"]').parent());
        }
        else if (path.includes('/Admin/product-manager')) {
            activateMenu($('a[href="/Admin/product-manager"]').parent());
        }
    }

    // Apply initial highlighting
    highlightActivePath();

    // Treeview click behavior
    $('.treeview > a').on('click', function(e) {
        e.preventDefault();
        const $parentLi = $(this).parent();
        const $submenu = $(this).next('.treeview-menu');
        const isOpen = $submenu.is(':visible');

        // Toggle only this menu's visibility
        if (isOpen) {
            $submenu.slideUp(200);
            $parentLi.removeClass('open');
        } else {
            $submenu.slideDown(200);
            $parentLi.addClass('open');
        }
    });

    // Handler for non-treeview items (direct menu items)
    $('.sidebar-menu > li:not(.treeview) > a').on('click', function() {
        const $menuItem = $(this).parent();
        $('.sidebar-menu > li').removeClass('active');
        $menuItem.addClass('active');
    });
});
