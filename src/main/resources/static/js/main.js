$(document).ready(function() {
    let table  = $("#tableAlunos").DataTable( {
        "language": {
            "search": "Pesquisar",
            "info": "Exibindo página _PAGE_ de _PAGES_",
            "lengthMenu":"Exibir _MENU_ entradas",
            "emptyTable": "Sem dados disponíveis",
            "paginate": {
                "previous": "Anterior",
                "next": "Próxima"
            },
            "infoFiltered": "(Filtrado de _MAX_ entradas totais)"
        }
    } );

    table
        .columns( '.status' )
        .search( 'Important' )
        .draw();
});

$("#gerar").click((e) => {
    const data = new Date();
    const anoAtual = data.getFullYear();
    let txt = "ATA";
    let aleatorio = Math.floor(Math.random() * 1500);
    document.getElementById("matricula").value = (txt +  anoAtual + aleatorio);
});