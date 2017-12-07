
$(function() {
    var form = $('#inferForm');
    
    $("#BtnInferir").click(function(ev){       
        ev.preventDefault();
        $("#Btn").val("Inferir");
        var formData = form.serialize();
        $.ajax({
            url: $(form).attr('action'),
            type: 'POST',
            dataType: 'json',
            data: formData,
            success: function(data) {
                if(data.errorParser2 !== null){
                    alert(data.Parser2);
                }
                else if(data.errorParser3 !== null){
                    alert(data.Parser3);
                }
                else{
                    
                    $('#formula').html(data.historial);
                    MathJax.Hub.Typeset();
                    
                    var nSol = $('#nSolucion').val();
                    if(nSol==="new"){
                        $('#nSolucion').val(data.nSol);
                        nSol = $('#nSolucion').val();
                        var url = $(form).attr('action');
                        url = url.replace("new",nSol);
                        $(form).attr('action',url);
                    }
                    if(data.resuelto === "1"){
                        alert("Felicidades ha resuelto al demostracion!!");
                        window.location = $("#linkDemostrar").attr("href");
                    }
                    $('#nStatement_id').val("");
                    $('#instanciacion_id').val("");
                    $('#leibniz_id').val("");
                    $("#nuevoMetodo").val("0");
                }
                
            }
        });
    });
    
    $("#BtnRetroceder").click(function(ev){       
        ev.preventDefault();
        $("#Btn").val("Retroceder");
        var formData = form.serialize();
        
        $.ajax({
            type: 'POST',
            url: $(form).attr('action'),
            dataType: 'json',
            data: formData,
            success: function(data) {

                $('#formula').html(data.historial);
                MathJax.Hub.Typeset();
                if(data.cambiarMetodo === "1"){
                    $("#metodosDemostracion").val("0");
                    $("#metodosDiv").show();
                    $('#inferForm').hide();
                    $('#nStatement_id').val("");
                    $('#instanciacion_id').val("");
                    $('#leibniz_id').val("");
                    $("#selectTeoInicial").val("1");
                    $("#nuevoMetodo").val("1");
                }
                
            }
        });
    });
    
    $('#BtnLimpiar').click(function(){
        $('#nStatement_id').val("");
        $('#instanciacion_id').val("");
        $('#leibniz_id').val("");
    });
    
});

function leibnizMouse(p1,p2){

    //alert(p1);
    //alert(p2);
    var resp;
    var nivel;
    var padres = [];
    
    if(p1[0] == p2[0]){
    	resp = p1[0];
        alert(resp);
        return;
    }  
	if(p1[1] <= p2[1]){
    	nivel = p1[1];
        padres[0] = p1[0];
    	padres[1] = $("#" + p2[0]).parents("." + nivel).attr("id");
    }
    else{
    	nivel = p2[1];
        padres[0] = $("#" + p1[0]).parents("." + nivel).attr("id");
    	padres[1] = p2[0];
    }
    //alert(padres);
    if(padres[0] == padres[1]){
    	resp = padres[0];
    	alert(resp);
        return;
    }
    else{
    	resp = $("#" + padres[0]).parent().attr("id");
        alert(resp);
        return;
    }    
}

