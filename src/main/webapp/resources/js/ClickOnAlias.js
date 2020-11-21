/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function clickAlias(Math1,alias,valorAlias)
{
    var render=document.getElementById(Math1);
//    render.innerHTML="<span id=\"Math1\">$$"+terminos[0]+"$$</span>";
   
    
    render.onclick = function (event) {
      if (!event) {event = window.event};
      var target = event.toElement || event.target;
      while (target && (!target.id || target.id.substring(0,4) != 'agru' )) {target = target.parentNode};
      if(target)
      {


        var tipo = target.id.split("@")[1];
        var index = parseInt(target.id.split("@")[2]);

        var math = MathJax.Hub.getAllJax(Math1)[0];
        var originalText = math.originalText;
        var newText;

        if(tipo == 'alias')
        {
            newText = originalText.replace("\\cssId{"+target.id+"}{"+ alias[index] +"}","\\cssId{"+target.id.replace("alias","valor")+"}{\\style{cursor:pointer;}{\\underline{~"+ valorAlias[index] +"}}}");
        }
        else
        {
            newText = originalText.replace("\\cssId{"+target.id+"}{\\style{cursor:pointer;}{\\underline{~"+ valorAlias[index] +"}}}","\\cssId{"+target.id.replace("valor","alias")+"}{"+ alias[index] +"}");
        }

        MathJax.Hub.Queue(["Text",math,newText]);
      }
    };   
}

function teoremaClickeable(/*teoId*/){
    
    var data = {};
    //data["teoid"] = teoId;
    var form = $('#inferForm');
    $.ajax({
        type: 'POST',
        url: $(form).attr('action')+"/teoremaClickeablePL",
        dataType: 'json',
        data: data,
        success: function(data) {
            if(data.lado === "0"){
                alert("El teorema seleccionado no aplica para el metodo Partir de un lado.");
                $("#metodosDiv").show();
            }
            else{
                $('#formula').html(data.historial);
                MathJax.Hub.Typeset();
            }
        }
    });
    
    
}

function showInstantiation(){
    var data = {};
    data["nStatement"] = $('#nStatement_id').val();
    data["instanciacion"] = setSubstitutionOnInput('substitutionButtonsId');
    //var teoSol = $("#nSolucion").val();
    //data["teoSol"] = teoSol;
    var action = $('#inferForm').attr('action');
    var arr = action.split('/');
    var attr1 = arr.pop();
    var attr2 = arr.pop();
    $("#modalLoading").css('display','inline-block');
    $.ajax({
        type: 'POST',
        url: action.substring(0,action.length-(attr1.length+attr2.length+1) )+"inst",
        dataType: 'json',
        data: data,
        success: function(data) {
                $("#modalLoading").css('display','none');
                if(data.error !== null){
                    alert(data.error);
                }
                else{
                   $('#showInstantiation').html('$'+data.instantiation+'$');
                   MathJax.Hub.Typeset();
                }
        },
            error: function(XMLHttpRequest, textStatus, errorThrown) { 
              $("#modalLoading").css('display','none');
              alert("Status: " + textStatus); alert("Error: " + errorThrown/*XMLHttpRequest.responseText*/); 
            }
    });
}

function teoremaInicialMD(teoid){
    var data = {};
    data["teoid"] = teoid;
    data["nuevoMetodo"] = $('#nuevoMetodo_id').val();
    //var teoSol = $("#nSolucion").val();
    //data["teoSol"] = teoSol;
    var form = $('#inferForm');
    $.ajax({
        type: 'POST',
        url: $(form).attr('action')+"/teoremaInicialMD",
        dataType: 'json',
        data: data,
        success: function(data) {

            $('#formula').html(data.historial);
            MathJax.Hub.Typeset();
            $('#inferForm').show();
            //$("#nuevoMetodo").val("1");
            //$('#teoremaInicial').val(teoid);
            $("#selectTeoInicial").val("0");
            $(".teoIdName").css({"cursor":"","color":""});
            $(".operator").css({"cursor":"pointer","color":"#08c"});
            $("#currentTeo").hide();
            var nSol = $(form).attr('action').split('/').pop(); //$('#nSolucion').val();
            if(nSol==="new"){
               //$('#nSolucion').val(data.nSol);
               //nSol = $('#nSolucion').val();
               var url = $(form).attr('action');
               url = url.substring(0,url.length-3)+data.nSol;
               $(form).attr('action',url);
            }
        },
            error: function(XMLHttpRequest, textStatus, errorThrown) { 
              alert("Status: " + textStatus); alert("Error: " + errorThrown/*XMLHttpRequest.responseText*/); 
            }
    });
}

function metodoD(/*teoid*/){
    var data = {};
    data["nuevoMetodo"] = $('#nuevoMetodo_id').val();
    //var teoSol = $("#nSolucion").val();
    //data["teoSol"] = teoSol;
    var form = $('#inferForm');
    
    $.ajax({
        type: 'POST',
        url: $(form).attr('action')+"/teoremaInicialD",
        dataType: 'json',
        data: data,
        success: function(data) {
            if(data.lado === "0"){
                alert("El teorema seleccionado no aplica para el metodo Debilitambien.");
                $("#metodosDiv").show();
            }
            else{
                $('#formula').html(data.historial);
                MathJax.Hub.Typeset();
                //$('#teoremaInicial').val(teoid + "@" + data.lado);
                $("#inferForm").show();
                //$("#nuevoMetodo").val("1");
                var nSol = $(form).attr('action').split('/').pop(); //$('#nSolucion').val();
                if(nSol==="new"){
                    //$('#nSolucion').val(data.nSol);
                    //nSol = $('#nSolucion').val();
                    var url = $(form).attr('action');
                    url = url.substring(0,url.length-3)+data.nSol;
                    $(form).attr('action',url);
                }
            }
        },
            error: function(XMLHttpRequest, textStatus, errorThrown) { 
              alert("Status: " + textStatus); alert("Error: " + errorThrown/*XMLHttpRequest.responseText*/); 
            }
    });
    
}

function metodoF(/*teoid*/){
    var data = {};
    data["nuevoMetodo"] = $('#nuevoMetodo_id').val();
    //var teoSol = $("#nSolucion").val();
    //data["teoSol"] = teoSol;
    var form = $('#inferForm');
    
    $.ajax({
        type: 'POST',
        url: $(form).attr('action')+"/teoremaInicialF",
        dataType: 'json',
        data: data,
        success: function(data) {
            if(data.lado === "0"){
                alert("El teorema seleccionado no aplica para el metodo Fortalecimiento.");
                $("#metodosDiv").show();
            }
            else{
                $('#formula').html(data.historial);
                MathJax.Hub.Typeset();
                //$('#teoremaInicial').val(teoid + "@" + data.lado);
                $("#inferForm").show();
                //$("#nuevoMetodo").val("1");
                var nSol = $(form).attr('action').split('/').pop(); //$('#nSolucion').val();
                if(nSol==="new"){
                    //$('#nSolucion').val(data.nSol);
                    //nSol = $('#nSolucion').val();
                    var url = $(form).attr('action');
                    url = url.substring(0,url.length-3)+data.nSol;
                    $(form).attr('action',url);
                }
            }
        },
            error: function(XMLHttpRequest, textStatus, errorThrown) { 
              alert("Status: " + textStatus); alert("Error: " + errorThrown/*XMLHttpRequest.responseText*/); 
            }
    });
    
}

function transMethod(/*teoid*/){
    var data = {};
    data["nuevoMetodo"] = $('#nuevoMetodo_id').val();
    //var teoSol = $("#nSolucion").val();
    //data["teoSol"] = teoSol;
    var form = $('#inferForm');
    
    $.ajax({
        type: 'POST',
        url: $(form).attr('action')+"/iniStatementT",
        dataType: 'json',
        data: data,
        success: function(data) {
            if(data.lado === "0"){
                alert("El teorema seleccionado no aplica para el metodo Transitivity.");
                $("#metodosDiv").show();
            }
            else{
                $('#formula').html(data.historial);
                MathJax.Hub.Typeset();
                //$('#teoremaInicial').val(teoid + "@" + data.lado);
                $("#inferForm").show();
                //$("#nuevoMetodo").val("1");
                var nSol = $(form).attr('action').split('/').pop(); //$('#nSolucion').val();
                if(nSol==="new"){
                    //$('#nSolucion').val(data.nSol);
                    //nSol = $('#nSolucion').val();
                    var url = $(form).attr('action');
                    url = url.substring(0,url.length-3)+data.nSol;
                    $(form).attr('action',url);
                }
            }
        },
            error: function(XMLHttpRequest, textStatus, errorThrown) { 
              alert("Status: " + textStatus); alert("Error: " + errorThrown/*XMLHttpRequest.responseText*/); 
            }
    });
    
}

function clickTeoremaInicial(teoid)
{
    var id = "";
    if (teoid.substring(0,3)==="ST-")
    {
        id = 'teoIdName'+teoid.substring(3);
    }
    else if (teoid.substring(0,3)==="MT-")
    {
        id = 'metateoIdName'+teoid.substring(3);
    }
    document.getElementById(id).onclick = function(event){
       var selectTeoInicial = $("#selectTeoInicial").val();
       if (selectTeoInicial==="1")
       {
           teoremaInicialMD(teoid);
       }
    };
}

function clickOperator(Math1,myField,teoid,vars)
{
    var render=document.getElementById(Math1);
    render.onclick = function (event) {
    var targetString = "";
    if (teoid.substring(0,3)==="ST-")
    {
        targetString = 'click@'+teoid.substring(3);
    }
    else if (teoid.substring(0,3)==="MT-")
    {
        targetString = 'clickmeta@'+teoid.substring(3);
    }
    if (!event) {event = window.event};
      var target = event.toElement || event.target;
      while (target && (!target.id || 
                         target.id !=targetString))
      {target = target.parentNode};
      if(target)
      {
        var metodo = document.getElementById('metodosDemostracion').value;
        if(metodo === "1"){
            var selectTeoInicial = $("#selectTeoInicial").val(); 
            if(selectTeoInicial==="1"){
                ;//teoremaInicialMD(teoid);
            }
            else{
                var inputStatement = document.getElementById(myField);
                inputStatement.value = teoid;
                $('#stbox').text(teoid);
                setJaxSubstitutionVariables(vars,'substitutionButtonsId');
            }
        }
        else{
            var inputStatement = document.getElementById(myField);
            inputStatement.value = teoid;
            $('#stbox').text(teoid);
            $('#instantiationModal').children().children().children().children()[0].innerText = "Instantiation of "+teoid.substring(3);
            document.getElementById('substitutionButtonsId.SubstitutionDiv').children[0].innerHTML = "<center><table><tr><td>Substitution:</td> <td><div class=\"dropdown\"><button style=\"padding:.05rem .1rem;\" class=\"btn btn-secondary btn-sm dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\"></button><div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\"><a class=\"dropdown-item\" href=\"#\" onclick=\"showInstantiation();\" data-target=\"#instantiationModal\" data-toggle=\"modal\">show instantiation</a></div></div></td></tr></table></center>";
            setJaxSubstitutionVariables(vars,'substitutionButtonsId');
        }
      }
    };    
}

