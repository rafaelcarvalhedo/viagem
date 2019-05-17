package br.pucgoias.viagem.persistencia;

import br.pucgoias.viagem.util.ViagemException;

import java.io.Serializable;
import java.util.List;

/**
 * Interface que define as operacoes da camada de persistencia generica
 * @author Gilcimar
 *
 */
public interface GenericoDAO<T, ID extends Serializable> {
	
	/**
	 * Retorna a classe a ser persistida
	 * @return
	 */
	public Class<T> getObjectClass();
	
	/**
	 * Inclui um objeto T na base de dados
	 * @param object
	 * @return
	 * @throws ViagemException
	 */
	public T incluir(T object) throws ViagemException;
	
	/**
	 * Altera um objeto T na base de dados
	 * @param object
	 * @return
	 * @throws ViagemException
	 */
	public T alterar(T object) throws ViagemException;
	
	/**
	 * Consulta um objeto T da base de dados
	 * @param id
	 * @return
	 * @throws ViagemException
	 */
	public T consultar(Integer id) throws ViagemException;
	
	/**
	 * Exclui um objeto T  da base de dados
	 * @param id
	 * @throws ViagemException
	 */
	public void excluir(Integer id) throws ViagemException;
	
	/**
	 * Lista os objetos T da base de dados
	 * @return
	 * @throws ViagemException
	 */
	public List<T> listar() throws ViagemException;
}
