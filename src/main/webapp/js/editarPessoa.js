//valida o formulario
function validarFormulario(){
	var campo = document.getElementById('formulario:nome');
	if(campo.value.length==0){
		alert('O campo nome deve ser preenchido.');
		campo.focus();
		return false;
	}
	return true;
}

//valida o formulario
function validarTelefone(){
	var campo = document.getElementById('formulario:numero');
	if(campo.value.length==0){
		alert('O campo n�mero deve ser preenchido.');
		campo.focus();
		return false;
	}
	else{
		for(var i=0; i<campo.value.length; i++){
			if(campo.value[i]<escape('0') || campo.value[i]>escape('9')){
				alert('O campo n�mero deve ser num�rico.');
				campo.focus();
				return false;
			}
		}
	}
	return true;
}
