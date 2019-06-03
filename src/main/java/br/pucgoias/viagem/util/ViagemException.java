package br.pucgoias.viagem.util;

/**
 * Classe implementa Excessões e avisos do sistema direcianada ao Usuário
 */
public class ViagemException extends Exception {
	
	private static final long serialVersionUID = 1189188521388183949L;
	private Exception ex;
	private String msg;

	public ViagemException(String msg){
		this.msg = msg;
	}

	public ViagemException(Exception e, String mensagem){
		e.printStackTrace();
		ex = e;
		msg = mensagem;
	}

	public Exception getEx() {
		return ex;
	}

	public String getMsg() {
		return msg;
	}
	
}
