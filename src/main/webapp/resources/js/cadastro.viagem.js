// https://br-cidade-estado-nodejs.glitch.me/cidades?q=Br
$(function () {

    $.widget("ui.autocomplete", $.ui.autocomplete, {

        _renderMenu: function (ul, items) {
            var that = this;
            ul.attr("class", "nav-stacked  bs-autocomplete-menu");
            $.each(items, function (index, item) {
                that._renderItemData(ul, item);
            });
        },

        _resizeMenu: function () {
            var ul = this.menu.element;
            ul.outerWidth(Math.min(
                // Firefox wraps long text (possibly a rounding bug)
                // so we add 1px to avoid the wrapping (#7513)
                ul.width("").outerWidth() + 1,
                this.element.outerWidth()
            ));
        },
        _renderItem: function (ul, item) {
            return $('<li>')
                .data("item.autocomplete", item)
                .append('<a>' + item.cidade + '</a></li>')
                .appendTo(ul);
        }
    });
    var cache = [];
    $(".origemInput,.destinoInput").autocomplete({
        minLength: 2,
        source: function (request, response) {
            var term = request.term;
            if (term in cache) {
                response(cache[term]);
                return;
            }
            var params = {q: term};
            $.getJSON("https://br-cidade-estado-nodejs.glitch.me/cidades?_limit=10", params, function (data, status, xhr) {
                cache[term] = data;
                response(data);
            });
        }, select: function (event, ui) {
            $(event.target).val(ui.item.cidade);
            return false;
        }
    });

    $('.date-time-input').datepicker({
        language: 'pt-BR'
    });

});

var diaPartida = IMask(
    document.getElementById('formulario:diaPartida'),
    {
        mask: Date,
        min: new Date(1990, 0, 1),
        max: new Date(2030, 0, 1),
        lazy: false
    });
var diaChegada = IMask(
    document.getElementById('formulario:diaChegada'),
    {
        mask: Date,
        min: new Date(1990, 0, 1),
        max: new Date(2030, 0, 1),
        lazy: false
    });