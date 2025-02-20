// Toggle sidebar
document.getElementById('sidebar-toggle').addEventListener('click', () => {
    document.body.classList.toggle('sidebar-collapsed');
});


// Toggle submenu
document.querySelectorAll('.treeview > a').forEach(item => {
    item.addEventListener('click', event => {
        event.preventDefault();
        const parent = item.parentElement;
        parent.classList.toggle('open');
    });
});
